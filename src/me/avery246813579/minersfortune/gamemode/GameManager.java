package me.avery246813579.minersfortune.gamemode;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboardHandler;
import me.avery246813579.minersfortune.gamemode.commands.BuyKitCommand;
import me.avery246813579.minersfortune.gamemode.commands.ChangeCommand;
import me.avery246813579.minersfortune.gamemode.commands.CreateCommand;
import me.avery246813579.minersfortune.gamemode.commands.GotoCommand;
import me.avery246813579.minersfortune.gamemode.commands.KitCommand;
import me.avery246813579.minersfortune.gamemode.commands.KitInfoCommand;
import me.avery246813579.minersfortune.gamemode.commands.KitsCommand;
import me.avery246813579.minersfortune.gamemode.commands.LimbowCommand;
import me.avery246813579.minersfortune.gamemode.commands.SaveWorldCommand;
import me.avery246813579.minersfortune.gamemode.commands.StartCommand;
import me.avery246813579.minersfortune.gamemode.commands.StopCommand;
import me.avery246813579.minersfortune.gamemode.commands.SuicideCommand;
import me.avery246813579.minersfortune.gamemode.commands.TeamCommand;
import me.avery246813579.minersfortune.gamemode.commands.UpdateCommand;
import me.avery246813579.minersfortune.gamemode.conditions.ConditionHandler;
import me.avery246813579.minersfortune.gamemode.kits.KitHandler;
import me.avery246813579.minersfortune.gamemode.kits.perks.PerkHandler;
import me.avery246813579.minersfortune.gamemode.listeners.PlayerListener;
import me.avery246813579.minersfortune.gamemode.listeners.SignListener;
import me.avery246813579.minersfortune.gamemode.timers.EndTimer;
import me.avery246813579.minersfortune.gamemode.timers.LiveTimer;
import me.avery246813579.minersfortune.gamemode.timers.LobbyBarTimer;
import me.avery246813579.minersfortune.gamemode.timers.LobbyTimer;
import me.avery246813579.minersfortune.gamemode.timers.PrepareTimer;
import me.avery246813579.minersfortune.menus.MenuHandler;
import me.avery246813579.minersfortune.sql.tables.GlobalStatsTable;
import me.avery246813579.minersfortune.sql.tables.MapTable;
import me.avery246813579.minersfortune.sql.tables.ServerTable;
import me.avery246813579.minersfortune.util.StatusBarApi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GameManager {
	/** Classes **/
	private static GameManager instance;
	private static MinersFortune plugin;

	/** Variables **/
	private static List<GameCondition> gameConditions = new ArrayList<GameCondition>();
	private static List<GameWinType> winTypes = new ArrayList<GameWinType>();
	private static List<GamePlayer> gamePlayers = new ArrayList<GamePlayer>();
	private static List<GameTeam> teams = new ArrayList<GameTeam>();
	private static List<Player> winners = new ArrayList<Player>();
	private static List<GameKit> kits = new ArrayList<GameKit>();

	/** Private Variables **/
	private static GameState gameState = GameState.Limbow;
	private static String[] description;
	private static int currentMap = 0;
	private static GameType gameType;
	private static MapTable mapTable;
	private static GameTeam winner;

	private static boolean forceStart = false;
	private static boolean forceStop = false;
	private static boolean starting = false;

	public GameManager(MinersFortune plugin) {
		GameManager.plugin = plugin;
		GameManager.instance = this;
		
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());

		for (GameType gameType : GameType.values()) {
			if (gameType.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(serverTable.getGameType())) {
				GameManager.gameType = gameType;
				serverTable.setCurrentGame(gameType);
			}
		}

		/** Loads Listener **/
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), plugin);

		if (gameType != null) {
			init();
		}
	}

	public static void forceStopGame() {
		forceStop = true;
		mapTable = null;
		gameState = GameState.Limbow;

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (StatusBarApi.hasDragonBar(player)) {
				StatusBarApi.removeDragonBar(player);
			}
			if (StatusBarApi.hasWitherBar(player)) {
				StatusBarApi.removeWitherBar(player);
			}
		}

		for (GameCondition condition : gameConditions) {
			condition.stopCondition();
		}
		
		IScoreboardHandler.updateTeamScoreboards();
		IScoreboardHandler.updatePlayers();
	}

	public void init() {
		/** Loads Commands **/
		plugin.getCommand("team").setExecutor(new TeamCommand());
		plugin.getCommand("start").setExecutor(new StartCommand());
		plugin.getCommand("create").setExecutor(new CreateCommand());
		plugin.getCommand("goto").setExecutor(new GotoCommand());
		plugin.getCommand("change").setExecutor(new ChangeCommand());
		plugin.getCommand("limbow").setExecutor(new LimbowCommand());
		plugin.getCommand("fstop").setExecutor(new StopCommand());
		plugin.getCommand("update").setExecutor(new UpdateCommand());
		plugin.getCommand("saveworld").setExecutor(new SaveWorldCommand());
		plugin.getCommand("kit").setExecutor(new KitCommand());
		plugin.getCommand("kitinfo").setExecutor(new KitInfoCommand());
		plugin.getCommand("buykit").setExecutor(new BuyKitCommand());
		plugin.getCommand("kits").setExecutor(new KitsCommand());
		plugin.getCommand("suicide").setExecutor(new SuicideCommand());

		/** Enables Listeners **/
		Bukkit.getServer().getPluginManager().registerEvents(new SignListener(), MinersFortune.getPlugin());

		/** Creates new condition Handler **/
		new ConditionHandler();
		new PerkHandler();
		new KitHandler();

		/** Sets the info for game **/
		for (GameTeamTypes teamTypes : gameType.getTeams()) {
			teams.add(new GameTeam(teamTypes.name(), teamTypes.getChatColor(), teamTypes, teamTypes.getColor()));
		}

		kits = gameType.getKits();
		description = gameType.getDescription();
		gameConditions = gameType.getConditions();
		winTypes = gameType.getWinTypes();
		mapTable = null;

		/** Sends all players to lobby spawn **/
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.teleport(GameLocations.LOBBY.getLocation());
			gamePlayers.add(new GamePlayer(player));

			for (Player players : Bukkit.getOnlinePlayers()) {
				player.showPlayer(players);
			}
		}
		
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setCurrentGame(gameType);
		serverTable.setServerStatus(gameState.name());
		plugin.getSqlHandler().saveServer(serverTable);

		/** Loads Game **/
		loadLobby();
	}

	public static void resetGame() {
		forceStart = false;
		forceStop = false;
		setStarting(false);

		me.avery246813579.minersfortune.listeners.PlayerListener.setCanSpeak(true);
		PlayerListener.setFrozen(false);

		for (GameKit gameKit : kits) {
			if (gameKit.getPerks() != null) {
				for (GamePerk gamePerk : gameKit.getPerks()) {
					gamePerk.remove();
				}
			}
		}

		for (GameCondition gameCondition : gameConditions) {
			gameCondition.stopCondition();
		}

		gamePlayers.clear();
		kits = gameType.getKits();
		description = gameType.getDescription();
		gameConditions = gameType.getConditions();
		gameState = GameState.Recruit;
		winTypes = gameType.getWinTypes();
		winners.clear();
		teams.clear();
		winner = null;
		mapTable = null;
		LiveTimer.setGameIsOver(false);

		for (GameTeamTypes teamTypes : gameType.getTeams()) {
			teams.add(new GameTeam(teamTypes.name(), teamTypes.getChatColor(), teamTypes, teamTypes.getColor()));
		}

		for (GamePlayer gamePlayer : gamePlayers) {
			gamePlayer.setAlive(false);
			gamePlayer.setKit(null);
			gamePlayer.setKiller(null);
			gamePlayer.setKills(0);
			gamePlayer.setPlayedGame(false);
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (findPlayer(player) == null) {
				gamePlayers.add(new GamePlayer(player));
			} else {
				findPlayer(player).resetManualPlayer();
				findPlayer(player).giveRespectedItems();
			}

			player.teleport(GameLocations.LOBBY.getLocation());

			for (Player players : Bukkit.getOnlinePlayers()) {
				player.showPlayer(players);
			}

			IScoreboardHandler.updatePlayerTeamScoreboard(player);
		}

		/** Updates sign location **/
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setCurrentGame(gameType);
		serverTable.setServerStatus(gameState.name());
		plugin.getSqlHandler().saveServer(serverTable);
		
		IScoreboardHandler.updatePlayers();
		loadLobby();
	}

	public static void loadLobby() {
		if (chooseMap()) {
			startRecruiting();
		}
	}

	public static boolean chooseMap() {
		List<MapTable> maps = MinersFortune.getPlugin().getSqlHandler().getAllActiveMaps(gameType.getDisplayName().replaceAll(" ", ""));
		if (maps == null) {
			broadcastGameMessage(ChatColor.RED + "Game has been moved to Limbow! Reason: No maps found!");
			forceStopGame();
			return false;
		}

		mapTable = maps.get(currentMap);

		GameMapHelper.loadMap(Bukkit.getConsoleSender(), GameManager.getMapTable().getMapLocation());
		for (GameTeam gameTeam : teams) {
			if (MinersFortune.getPlugin().getSqlHandler().getTeam(mapTable.getMapId(), gameTeam.getName()) == null) {
				broadcastGameMessage(ChatColor.RED + "Game has been moved to Limbow! Reason: No teams found!");
				forceStopGame();
				return false;
			}

			gameTeam.setSpawns(MinersFortune.getPlugin().getSqlHandler().getTeam(mapTable.getMapId(), gameTeam.getName()).getLocationSpawns());
		}

		currentMap++;
		if (currentMap >= maps.size()) {
			currentMap = 0;
		}

		/** Updates sign location **/
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setGameMap(mapTable.getMapName());
		plugin.getSqlHandler().saveServer(serverTable);

		return true;
	}

	public static void startRecruiting() {
		/** Sets Game State **/
		gameState = GameState.Recruit;

		/** Updates sign location **/
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setServerStatus(gameState.name());
		plugin.getSqlHandler().saveServer(serverTable);

		IScoreboardHandler.updatePlayers();
		
		/** Creates a new lobby bar **/
		new LobbyBarTimer(instance);
	}

	public boolean checkRecruiting() {
		if (Bukkit.getOnlinePlayers().length >= (int) (GameManager.getGameType().getMaxPlayers() * .50)) {
			return true;
		}

		return false;
	}

	public void stopRecruiting(int timeState) {
		/** Sets Starting Gamestate **/
		gameState = GameState.Prepare;

		new LobbyTimer();
	}

	public static void prepareGame() {
		if (Bukkit.getOnlinePlayers().length < gameType.getMinPlayers() && !forceStart) {
			broadcastGameMessage(ChatColor.RED + "Not enough players to start game!");
			resetGame();
			return;
		}

		chooseTeams();

		/** Sends players messages **/
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
			player.sendMessage(ChatColor.GREEN + "Gamemode ➜ " + ChatColor.YELLOW + getGameType().getDisplayName());
			player.sendMessage("");
			player.sendMessage(ChatColor.GREEN + "How to play ➜ " + ChatColor.YELLOW + getDescription()[0]);
			player.sendMessage(ChatColor.YELLOW + getDescription()[1]);
			player.sendMessage(ChatColor.YELLOW + getDescription()[2]);
			player.sendMessage(ChatColor.YELLOW + getDescription()[3]);
			player.sendMessage("");
			player.sendMessage(ChatColor.GREEN + "Map ➜ " + ChatColor.YELLOW + mapTable.getMapName() + ChatColor.GREEN + " Created by ➜ " + ChatColor.YELLOW + mapTable.getMapCreator());
			player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");

			/** Plays ready sound **/
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 2.0F, 1.0F);

			/** Resets Game Players **/
			for (GamePlayer gamePlayer : gamePlayers) {
				gamePlayer.resetManualPlayer();
			}

			player.closeInventory();
			MenuHandler.resetPlayer(player);

			if (StatusBarApi.hasDragonBar(player)) {
				StatusBarApi.removeDragonBar(player);
			}
			if (StatusBarApi.hasWitherBar(player)) {
				StatusBarApi.removeWitherBar(player);
			}

			for (Entity entity : Bukkit.getWorld("GameWorld").getEntities()) {
				if (!(entity instanceof Player)) {
					entity.remove();
				}
			}

			/** Teleports Player To Spawn **/
			for (GameTeam gameTeam : teams) {
				if (gameTeam.getPlayers().contains(player)) {
					Location location = gameTeam.getSpawn().clone().add(0.5D, 0.5D, 0.5D);
					location.setPitch(0);

					try {
						player.teleport(location);
					} catch (Exception ex) {
						ex.printStackTrace();
						player.teleport(location);
					}
				}
			}
		}

		IScoreboardHandler.updateTeamScoreboards();
		IScoreboardHandler.updatePlayers();
		PlayerListener.setFrozen(true);
		me.avery246813579.minersfortune.listeners.PlayerListener.setCanSpeak(false);

		setStarting(true);
		new PrepareTimer();
	}

	public static void chooseTeams() {
		/** Desides Teams **/
		int leastAmount = Bukkit.getOnlinePlayers().length / teams.size();
		List<Player> players = new ArrayList<Player>();

		for (Player player : Bukkit.getOnlinePlayers()) {
			players.add(player);
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			for (GameTeam team : teams) {
				if (team.getQueue().contains(player)) {
					if (team.getPlayers().size() < leastAmount) {
						team.getPlayers().add(player);
						players.remove(player);
					}
				}
			}
		}

		for (Player player : players) {
			selectTeam(player);
		}
	}

	public static void selectTeam(Player player) {
		GameTeam gameTeam = null;

		for (GameTeam gameTeams : teams) {
			if (gameTeam == null || gameTeams.getPlayers().size() < gameTeam.getPlayers().size()) {
				gameTeam = gameTeams;
			}
		}

		gameTeam.addPlayer(player);
	}

	public static void startGame() {
		if (Bukkit.getOnlinePlayers().length < gameType.getMinPlayers() && !forceStart) {
			broadcastGameMessage(ChatColor.RED + "Not enough players to start game!");
			resetGame();
			return;
		}

		gameState = GameState.Live;
		setStarting(false);

		/** Updates sign location **/
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setServerStatus(gameState.name());
		plugin.getSqlHandler().saveServer(serverTable);

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (StatusBarApi.hasDragonBar(player)) {
				StatusBarApi.removeDragonBar(player);
			}
			if (StatusBarApi.hasWitherBar(player)) {
				StatusBarApi.removeWitherBar(player);
			}

			player.playSound(player.getLocation(), Sound.CAT_MEOW, 2.0F, 1.0F);
		}

		for (GameKit gameKit : kits) {
			if (gameKit.getPerks() != null) {
				for (GamePerk gamePerk : gameKit.getPerks()) {
					gamePerk.onEnable();
				}
			}
		}

		for (GamePlayer gp : gamePlayers) {
			GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(MinersFortune.getPlugin().getSqlHandler().getPlayerId(gp.getPlayer()));
			globalStatsTable.setGames_played(globalStatsTable.getGames_played() + 1);
			MinersFortune.getPlugin().getSqlHandler().saveGlobalStats(globalStatsTable);

			gp.setAlive(true);
			gp.setPlayedGame(true);
			gp.getGameStatsTable().setLosses(gp.getGameStatsTable().getLosses() + 1);
			gp.giveRespectedItems();
			gp.saveStatsTable();
		}

		for (GameCondition gameCondition : gameConditions) {
			gameCondition.resetCondition(MinersFortune.getPlugin().getSqlHandler().getCondition(mapTable.getMapId()));
		}

		Bukkit.getWorld("GameWorld").setTime(0);
		Bukkit.getWorld("GameWorld").setStorm(false);
		Bukkit.getWorld("GameWorld").setThundering(false);
		
		IScoreboardHandler.updatePlayers();
		
		PlayerListener.setFrozen(false);
		me.avery246813579.minersfortune.listeners.PlayerListener.setCanSpeak(true);

		new LiveTimer();
	}

	public static void startEnd() {
		me.avery246813579.minersfortune.listeners.PlayerListener.setCanSpeak(false);
		gameState = GameState.End;

		/** Updates sign location **/
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setServerStatus(gameState.name());
		plugin.getSqlHandler().saveServer(serverTable);

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (StatusBarApi.hasDragonBar(player)) {
				StatusBarApi.removeDragonBar(player);
			}
			if (StatusBarApi.hasWitherBar(player)) {
				StatusBarApi.removeWitherBar(player);
			}
		}

		for (GameKit gameKit : kits) {
			if (gameKit.getPerks() != null) {
				for (GamePerk gamePerk : gameKit.getPerks()) {
					gamePerk.remove();
				}
			}
		}

		/** Resets Game Players **/
		for (GamePlayer gamePlayer : gamePlayers) {
			gamePlayer.resetManualPlayer();
		}
		
		IScoreboardHandler.updatePlayers();

		new EndTimer();
	}

	public static void endGame() {
		me.avery246813579.minersfortune.listeners.PlayerListener.setCanSpeak(true);
		resetGame();
	}

	public static void sendGameMessage(Player player, String message) {
		player.sendMessage(ChatColor.GREEN + "Game >> " + ChatColor.GRAY + message);
	}

	public static void broadcastGameMessage(String message) {
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.sendMessage(ChatColor.GREEN + "Game >> " + ChatColor.GRAY + message);
		}
	}

	public static GameTeam findQueue(Player player) {
		for (GameTeam gameTeam : teams) {
			if (gameTeam.getQueue().contains(player)) {
				return gameTeam;
			}
		}

		return null;
	}

	public static GamePlayer findPlayer(Player player) {
		for (GamePlayer gp : gamePlayers) {
			if (gp.getPlayer() == player) {
				return gp;
			}
		}

		return null;
	}

	public static GameTeam findTeam(Player player) {
		for (GameTeam gameTeam : teams) {
			if (gameTeam.getPlayers().contains(player)) {
				return gameTeam;
			}
		}

		return null;
	}

	public static GameKit findKit(String name) {
		for (GameKit gameKit : kits) {
			if (gameKit.getDisplayName().equalsIgnoreCase(name)) {
				return gameKit;
			}
		}

		return null;
	}

	public static void checkIfWinner(Player player) {
		int alive = 0;

		if (winTypes != null) {
			if (winTypes.contains(GameWinType.LastManStanding)) {
				Player lastAlive = null;
				for (GamePlayer gp : GameManager.getGamePlayers()) {
					if (gp.isAlive()) {
						alive++;
						lastAlive = gp.getPlayer();
					}
				}

				if (alive == 2) {
					winners.add(player);
				} else if (alive == 1) {
					if (!LiveTimer.isGameIsOver()) {
						Player playerAlive = null;

						if (winners.size() >= 1) {
							playerAlive = winners.get(0);
						}
						winners.clear();
						winners.add(lastAlive);
						winners.add(player);
						if (playerAlive != null) {
							winners.add(playerAlive);
						}
						LiveTimer.setGameIsOver(true);
					}
				}
			}
		}
	}
	
	public static int getAlivePlayers(){
		int alive = 0;
		for (GamePlayer gp : GameManager.getGamePlayers()) {
			if (gp.isAlive()) {
				alive++;
			}
		}
		
		return alive;
	}
	
	public static int getAliveTeamPlayers(GameTeam gameTeam){
		int alive = 0;
		
		for (GamePlayer gp : GameManager.getGamePlayers()) {
			if (gp.isAlive()) {
				if (gameTeam.getGameTeamTypes() == findTeam(gp.getPlayer()).getGameTeamTypes()) {
					alive++;
				}
			}
		}
		
		return alive;
	}

	public static MinersFortune getPlugin() {
		return plugin;
	}

	public static boolean isForceStart() {
		return forceStart;
	}

	public static void setForceStart(boolean forceStart) {
		GameManager.forceStart = forceStart;
	}

	public static GameManager getInstance() {
		return instance;
	}

	public static void setInstance(GameManager instance) {
		GameManager.instance = instance;
	}

	public static boolean isForceStop() {
		return forceStop;
	}

	public static void setForceStop(boolean forceStop) {
		GameManager.forceStop = forceStop;
	}

	public static List<GameTeam> getTeams() {
		return teams;
	}

	public static void setTeams(List<GameTeam> teams) {
		GameManager.teams = teams;
	}

	public static List<GameKit> getKits() {
		return kits;
	}

	public static void setKits(List<GameKit> kits) {
		GameManager.kits = kits;
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		GameManager.gameState = gameState;
	}

	public static String[] getDescription() {
		return description;
	}

	public static void setDescription(String[] description) {
		GameManager.description = description;
	}

	public static GameType getGameType() {
		return gameType;
	}

	public static void setGameType(GameType gameType) {
		GameManager.gameType = gameType;
	}

	public static void setPlugin(MinersFortune plugin) {
		GameManager.plugin = plugin;
	}

	public static List<GameCondition> getGameConditions() {
		return gameConditions;
	}

	public static void setGameConditions(List<GameCondition> gameConditions) {
		GameManager.gameConditions = gameConditions;
	}

	public static List<GameWinType> getWinTypes() {
		return winTypes;
	}

	public static void setWinTypes(List<GameWinType> winTypes) {
		GameManager.winTypes = winTypes;
	}

	public static MapTable getMapTable() {
		return mapTable;
	}

	public static void setMapTable(MapTable mapTable) {
		GameManager.mapTable = mapTable;
	}

	public static int getCurrentMap() {
		return currentMap;
	}

	public static void setCurrentMap(int currentMap) {
		GameManager.currentMap = currentMap;
	}

	public static List<GamePlayer> getGamePlayers() {
		return gamePlayers;
	}

	public static void setGamePlayers(List<GamePlayer> gamePlayers) {
		GameManager.gamePlayers = gamePlayers;
	}

	public static List<Player> getWinners() {
		return winners;
	}

	public static void setWinners(List<Player> winners) {
		GameManager.winners = winners;
	}

	public static GameTeam getWinner() {
		return winner;
	}

	public static void setWinner(GameTeam winner) {
		GameManager.winner = winner;
	}

	public static boolean isStarting() {
		return starting;
	}

	public static void setStarting(boolean starting) {
		GameManager.starting = starting;
	}

	public static enum GameLocations {
		LOBBY(new Location(Bukkit.getWorld("world"), 232.5, 37.5, 258.5, 180, 0));

		private Location location;

		GameLocations(Location location) {
			this.location = location;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}
	}
}
