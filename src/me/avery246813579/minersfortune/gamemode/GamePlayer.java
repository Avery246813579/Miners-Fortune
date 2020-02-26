package me.avery246813579.minersfortune.gamemode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboardHandler;
import me.avery246813579.minersfortune.menus.MenuHandler;
import me.avery246813579.minersfortune.menus.VanityMenu;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.sql.tables.AccountTable;
import me.avery246813579.minersfortune.sql.tables.ArcherGamesTable;
import me.avery246813579.minersfortune.sql.tables.DominationTable;
import me.avery246813579.minersfortune.sql.tables.GameStatsTable;
import me.avery246813579.minersfortune.sql.tables.SearchAndDestroyTable;
import me.avery246813579.minersfortune.sql.tables.SurvivalGamesTable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.util.Vector;

public class GamePlayer {
	/** Variables **/
	private GameStatsTable gameStatsTable;
	private Objective objective;
	private GameState gameState;
	private String scoreboardTitle;
	private Player player, killer;
	private int kills, assists;
	private boolean alive, playedGame = false;
	private GameKit kit = GameManager.getGameType().getKits().get(0);

	public GamePlayer(Player player) {
		this.player = player;
		resetManualPlayer();
		giveRespectedItems();
	}

	public void checkStatsTable() {
		if (gameStatsTable == null) {
			int player_id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);

			if (GameManager.getGameType() == GameType.ArcherGames) {
				gameStatsTable = MinersFortune.getPlugin().getSqlHandler().getArcherGamesStats(player_id);
			} else if (GameManager.getGameType() == GameType.SurvivalGames) {
				gameStatsTable = MinersFortune.getPlugin().getSqlHandler().getSurvivalGamesStats(player_id);
			} else if (GameManager.getGameType() == GameType.SearchAndDestroy) {
				gameStatsTable = MinersFortune.getPlugin().getSqlHandler().getSearchAndDestroyStats(player_id);
			} else if (GameManager.getGameType() == GameType.Domination) {
				gameStatsTable = MinersFortune.getPlugin().getSqlHandler().getDominationStats(player_id);
			}
		} else {
			if (GameManager.getGameType() == GameType.ArcherGames && gameStatsTable instanceof ArcherGamesTable) {
				return;
			} else if (GameManager.getGameType() == GameType.SurvivalGames && gameStatsTable instanceof SurvivalGamesTable) {
				return;
			} else if (GameManager.getGameType() == GameType.SearchAndDestroy && gameStatsTable instanceof SearchAndDestroyTable) {
				return;
			} else if (GameManager.getGameType() == GameType.Domination && gameStatsTable instanceof DominationTable) {
				return;
			}
			
			gameStatsTable = null;
			checkStatsTable();
		}
	}
	
	public void saveStatsTable(){
		if (GameManager.getGameType() == GameType.ArcherGames) {
			MinersFortune.getPlugin().getSqlHandler().saveArcherGamesStats((ArcherGamesTable) gameStatsTable);
		} else if (GameManager.getGameType() == GameType.SurvivalGames) {
			MinersFortune.getPlugin().getSqlHandler().saveSurvivalGamesStats((SurvivalGamesTable) gameStatsTable);
		} else if (GameManager.getGameType() == GameType.SearchAndDestroy) {
			MinersFortune.getPlugin().getSqlHandler().saveSearchAndDestroyStats((SearchAndDestroyTable) gameStatsTable);
		} else if (GameManager.getGameType() == GameType.Domination) {
			MinersFortune.getPlugin().getSqlHandler().saveDominationSave((DominationTable) gameStatsTable);
		}
	}

	/***********************************
	 * 
	 * Kits
	 * 
	 **********************************/

	public List<GameKit> getOwnedKits() {
		List<GameKit> gameKits = new ArrayList<GameKit>();
		
		for (GameKit gameKit : GameManager.getKits()) {
			if (gameKit.getCost() == 0) {
				gameKits.add(gameKit);
			}
		}
		
		if(MinersFortune.getPlugin().getSqlHandler().getAllKits(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player), GameManager.getGameType().getDisplayName().replaceAll(" ", "")) == null){
			return gameKits;
		}
		
		for (String string : MinersFortune.getPlugin().getSqlHandler().getAllKits(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player), GameManager.getGameType().getDisplayName().replaceAll(" ", ""))) {
			for (GameKit gameKit : GameManager.getKits()) {
				if (gameKit.getDisplayName().equalsIgnoreCase(string)) {
					gameKits.add(gameKit);
				}
			}
		}

		return gameKits;
	}

	public List<GameKit> getNotOwnedKits() {
		List<GameKit> notOwnedKits = new ArrayList<GameKit>();
		List<GameKit> ownedKits = getOwnedKits();

		for (GameKit gameKit : GameManager.getKits()) {
			if (!ownedKits.contains(gameKit)) {
				notOwnedKits.add(gameKit);
			}
		}

		return notOwnedKits;
	}

	public boolean buyKit(GameKit gameKit) {
		if (getOwnedKits().contains(gameKit)) {
			sendKitMessage(ChatColor.RED + "You already own this kit!");
			return false;
		}

		if (gameKit.getCost() > MinersFortune.getPlugin().getSqlHandler().getAccount(player).getCredits()) {
			sendKitMessage(ChatColor.RED + "You don't have sufficient credits for this kit!");
			return false;
		}

		if (gameKit.getCanUse().getRankNumber() > RankManager.findPlayerRank(player).getRankNumber()) {
			sendKitMessage(ChatColor.RED + "This kit requires the rank " + gameKit.getCanUse().getName() + "!");
			return false;
		}

		if (gameKit.getCost() == 0) {
			sendKitMessage(ChatColor.RED + "You can not buy a free kit!");
			return false;
		}

		AccountTable accountTable = MinersFortune.getPlugin().getSqlHandler().getAccount(player);
		accountTable.setCredits(accountTable.getCredits() - gameKit.getCost());
		MinersFortune.getPlugin().getSqlHandler().saveAccount(accountTable);

		MinersFortune.getPlugin().getSqlHandler().createKit(accountTable.getPlayer_id(), GameManager.getGameType().getDisplayName().replaceAll(" ", ""), gameKit.getDisplayName());
		sendKitMessage(ChatColor.YELLOW + "You have successfully bought kit " + gameKit.getDisplayName() + " for " + GameManager.getGameType().getDisplayName() + "!");

		return true;
	}

	public boolean selectKit(GameKit gameKit) {
		if (!getOwnedKits().contains(gameKit) && gameKit.getCost() != 0) {
			sendKitMessage(ChatColor.RED + "You don't own this kit!");
			return false;
		}

		if (gameKit.getCanUse().getRankNumber() > RankManager.findPlayerRank(player).getRankNumber()) {
			sendKitMessage(ChatColor.RED + "This kit requires the rank " + gameKit.getCanUse().getName() + "!");
			return false;
		}

		kit = gameKit;
		sendKitMessage(ChatColor.YELLOW + "You have selected the kit " + gameKit.getDisplayName() + "!");
		IScoreboardHandler.update(this);
		return true;
	}

	public void displayKitInfo(GameKit gameKit) {
		player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
		player.sendMessage(ChatColor.GREEN + "Kit ➜ " + ChatColor.YELLOW + gameKit.getDisplayName());
		player.sendMessage("");
		player.sendMessage(ChatColor.GREEN + "Kit Description ➜ " + ChatColor.YELLOW + gameKit.getDescription());
		player.sendMessage("");
		player.sendMessage(ChatColor.GREEN + "Cost ➜ " + ChatColor.YELLOW + gameKit.getCost() + " Credits");
		player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
	}

	public void sendKitMessage(String message) {
		player.sendMessage(ChatColor.GREEN + "Kit >> " + ChatColor.GRAY + message);
	}

	/***********************************
	 * 
	 * Player Status Changers
	 * 
	 **********************************/

	public void makeSpectator() {
		setAlive(false);
		GameManager.checkIfWinner(player);
		resetManualPlayer();
		giveRespectedItems();
		player.teleport(GameManager.getTeams().get(0).getSpawn());
		player.setHealth(20);
		player.setVelocity(new Vector(0, 0, 0));
		player.setAllowFlight(true);
		player.setFlying(true);

		for (Player players : Bukkit.getOnlinePlayers()) {
			if (GameManager.getGameState() == GameState.Live) {
				if (GameManager.findPlayer(players).isAlive()) {
					player.showPlayer(players);
					players.hidePlayer(player);
				} else {
					player.hidePlayer(players);
					players.hidePlayer(player);
				}
			} else {
				player.showPlayer(players);
				players.showPlayer(player);
			}
		}

		player.sendMessage(ChatColor.GREEN + "Status >> " + ChatColor.YELLOW + "You are now a spectator!");
	}

	public void respawn() {
		GamePlayer gamePlayer = GameManager.findPlayer(player);
		gamePlayer.setAlive(false);
		player.setHealth(20);
		player.setVelocity(new Vector(0, 0, 0));
		player.sendMessage(ChatColor.GREEN + "Status >> " + ChatColor.YELLOW + "You will respawn in 10 seconds!");
		resetManualPlayer();

		for (Player players : Bukkit.getOnlinePlayers()) {
			if (GameManager.getGameState() == GameState.Live) {
				if (GameManager.findPlayer(players).isAlive()) {
					player.showPlayer(players);
					players.hidePlayer(player);
				} else {
					player.hidePlayer(player);
					players.hidePlayer(player);
				}
			} else {
				player.showPlayer(players);
				players.showPlayer(player);
			}
		}

		new BukkitRunnable() {
			@Override
			public void run() {
				if (GameManager.getGameState() == GameState.Live) {
					alive = true;
					resetManualPlayer();
					giveRespectedItems();
					player.teleport(GameManager.findTeam(player).getSpawn());
					player.sendMessage(ChatColor.GREEN + "Status >> " + ChatColor.YELLOW + "You have respawned!");

					for (Player players : Bukkit.getOnlinePlayers()) {
						players.showPlayer(player);
					}
				}
			}
		}.runTaskLater(MinersFortune.getPlugin(), 200);
	}

	public void giveRespectedItems() {
		if (GameManager.getGameState() == GameState.Recruit || GameManager.getGameState() == GameState.Prepare || GameManager.getGameState() == GameState.Limbow) {
			ItemStack toHub = new ItemStack(Material.WOOD_DOOR);
			ItemMeta itemMeta = toHub.getItemMeta();
			itemMeta.setDisplayName(ChatColor.YELLOW + "Back to hub");
			itemMeta.setLore(new ArrayList<String>(Arrays.asList(ChatColor.GRAY + "Click to access")));
			toHub.setItemMeta(itemMeta);

			player.getInventory().setItem(0, MenuHandler.lobbyMenu.getItem());
			player.getInventory().setItem(4, VanityMenu.vanity());
			player.getInventory().setItem(8, toHub);
		} else if (GameManager.getGameState() == GameState.Live) {
			if (isAlive()) {
				if (kit != null) {
					kit.giveKit(player);
				} else {
					kit = GameManager.getKits().get(0);
					GameManager.getKits().get(0).giveKit(player);
				}
				
				if(GameManager.getTeams().size() > 1){
					if(GameManager.findTeam(player).getGameTeamTypes() == GameTeamTypes.BLUE){
						player.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK));
					}else if(GameManager.findTeam(player).getGameTeamTypes() == GameTeamTypes.RED){
						player.getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK));
					}
				}
			} else {
				ItemStack toHub = new ItemStack(Material.WOOD_DOOR);
				ItemMeta itemMeta = toHub.getItemMeta();
				itemMeta.setDisplayName(ChatColor.YELLOW + "Back to hub");
				itemMeta.setLore(new ArrayList<String>(Arrays.asList(ChatColor.GRAY + "Click to access")));
				toHub.setItemMeta(itemMeta);

				player.getInventory().setItem(0, MenuHandler.spectatorMenu.getItem());
				player.getInventory().setItem(8, toHub);
			}
		}
	}

	public void resetManualPlayer() {
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.setGameMode(GameMode.SURVIVAL);
		player.setFlying(false);
		player.setAllowFlight(false);
		player.setMaxHealth(20);
		player.setVelocity(new Vector(0, 0, 0));
		player.setExp(0);
		player.setLevel(0);
		player.getInventory().clear();
		player.getInventory().setBoots(null);
		player.getInventory().setHelmet(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setChestplate(null);

		for (PotionEffect effect : player.getActivePotionEffects()) {
			player.removePotionEffect(effect.getType());
		}
	}

	public void payPlayer(int amount) {
		AccountTable accountTable = MinersFortune.getPlugin().getSqlHandler().getAccount(player);
		accountTable.setCredits(accountTable.getCredits() + amount);
		MinersFortune.getPlugin().getSqlHandler().saveAccount(accountTable);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public GameKit getKit() {
		return kit;
	}

	public void setKit(GameKit kit) {
		this.kit = kit;
	}

	public Player getKiller() {
		return killer;
	}

	public void setKiller(Player killer) {
		this.killer = killer;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public boolean isPlayedGame() {
		return playedGame;
	}

	public void setPlayedGame(boolean playedGame) {
		this.playedGame = playedGame;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Objective getObjective() {
		return objective;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}

	public String getScoreboardTitle() {
		return scoreboardTitle;
	}

	public void setScoreboardTitle(String scoreboardTitle) {
		this.scoreboardTitle = scoreboardTitle;
	}

	public GameStatsTable getGameStatsTable() {
		checkStatsTable();
		return gameStatsTable;
	}

	public void setGameStatsTable(GameStatsTable gameStatsTable) {
		this.gameStatsTable = gameStatsTable;
	}
}
