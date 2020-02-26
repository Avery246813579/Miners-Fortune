package me.avery246813579.minersfortune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import me.avery246813579.minersfortune.commands.BalCommand;
import me.avery246813579.minersfortune.commands.BanCommand;
import me.avery246813579.minersfortune.commands.BroadcastCommand;
import me.avery246813579.minersfortune.commands.BuildCommand;
import me.avery246813579.minersfortune.commands.ClearChatCommand;
import me.avery246813579.minersfortune.commands.DayCommand;
import me.avery246813579.minersfortune.commands.EffectCommand;
import me.avery246813579.minersfortune.commands.FeedCommand;
import me.avery246813579.minersfortune.commands.FlyCommand;
import me.avery246813579.minersfortune.commands.GamemodeCommand;
import me.avery246813579.minersfortune.commands.HealCommand;
import me.avery246813579.minersfortune.commands.HelpCommand;
import me.avery246813579.minersfortune.commands.HubCommand;
import me.avery246813579.minersfortune.commands.MenuCommand;
import me.avery246813579.minersfortune.commands.MessageCommand;
import me.avery246813579.minersfortune.commands.MoneyCommand;
import me.avery246813579.minersfortune.commands.MountCommand;
import me.avery246813579.minersfortune.commands.MuteChatCommand;
import me.avery246813579.minersfortune.commands.MuteCommand;
import me.avery246813579.minersfortune.commands.NightCommand;
import me.avery246813579.minersfortune.commands.OpenCommand;
import me.avery246813579.minersfortune.commands.RankManagerCommand;
import me.avery246813579.minersfortune.commands.ReplyCommand;
import me.avery246813579.minersfortune.commands.RulesCommand;
import me.avery246813579.minersfortune.commands.StaffWhitelistCommand;
import me.avery246813579.minersfortune.commands.StatsCommand;
import me.avery246813579.minersfortune.commands.TimeCommand;
import me.avery246813579.minersfortune.commands.TpCommand;
import me.avery246813579.minersfortune.commands.TphereCommand;
import me.avery246813579.minersfortune.commands.TpposCommand;
import me.avery246813579.minersfortune.commands.UnbanCommand;
import me.avery246813579.minersfortune.commands.UnmuteCommand;
import me.avery246813579.minersfortune.commands.VanishCommand;
import me.avery246813579.minersfortune.core.CoreLoader;
import me.avery246813579.minersfortune.core.entities.vanity.IVanityEntityHandler;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.handlers.CustomEntityHandler;
import me.avery246813579.minersfortune.handlers.SignHandler;
import me.avery246813579.minersfortune.listeners.ChatListener;
import me.avery246813579.minersfortune.listeners.PlayerListener;
import me.avery246813579.minersfortune.menus.MenuHandler;
import me.avery246813579.minersfortune.menus.MountMenu;
import me.avery246813579.minersfortune.menus.PetMenu;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.sql.SqlHandler;
import me.avery246813579.minersfortune.sql.tables.ServerTable;
import me.avery246813579.minersfortune.util.ItemDatabase;
import me.avery246813579.minersfortune.util.UUIDUtil;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MinersFortune extends JavaPlugin {
	/** Classes **/
	private SqlHandler sqlHandler = new SqlHandler(this);
	private RankManager rankManager = new RankManager(this);
	private static ItemDatabase itemDatabase;
	private static MinersFortune plugin;

	/** Variables **/
	private Map<Player, UUID> uuids = new HashMap<Player, UUID>();
	private static List<Player> playersCanBuild = new ArrayList<Player>();
	private static List<Player> vanished = new ArrayList<Player>();
	private static boolean canBuild = false;
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * NAME PERKS
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */

	@Override
	public void onEnable() {
		/** Creates Static Variable **/
		MinersFortune.plugin = this;
		
		/** Starts core **/
		new CoreLoader();

		/** Loads server in sql **/
		if (sqlHandler.getServer(Bukkit.getServerName()) == null) {
			sqlHandler.createServer(Bukkit.getServer());
		}

		/** Loads people online **/
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setOnlinePlayers(Bukkit.getOnlinePlayers().length);
		plugin.getSqlHandler().saveServer(serverTable);

		/** Enables Listeners **/
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new ChatListener(), this);

		/** Registers Coommands **/
		getCommand("ban").setExecutor(new BanCommand());
		getCommand("mute").setExecutor(new MuteCommand());
		getCommand("unban").setExecutor(new UnbanCommand());
		getCommand("unmute").setExecutor(new UnmuteCommand());
		getCommand("rankmanager").setExecutor(new RankManagerCommand());
		getCommand("hub").setExecutor(new HubCommand());
		getCommand("bal").setExecutor(new BalCommand());
		getCommand("money").setExecutor(new MoneyCommand());
		getCommand("staffwhitelist").setExecutor(new StaffWhitelistCommand());
		getCommand("build").setExecutor(new BuildCommand());
		getCommand("clearchat").setExecutor(new ClearChatCommand());
		getCommand("mutechat").setExecutor(new MuteChatCommand());
		getCommand("stats").setExecutor(new StatsCommand());
		getCommand("day").setExecutor(new DayCommand());
		getCommand("night").setExecutor(new NightCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("open").setExecutor(new OpenCommand());
		getCommand("time").setExecutor(new TimeCommand());
		getCommand("tp").setExecutor(new TpCommand());
		getCommand("tphere").setExecutor(new TphereCommand());
		getCommand("tppos").setExecutor(new TpposCommand());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("rules").setExecutor(new RulesCommand());
		getCommand("vanish").setExecutor(new VanishCommand());
		getCommand("effect").setExecutor(new EffectCommand());
		getCommand("menu").setExecutor(new MenuCommand());
		getCommand("mount").setExecutor(new MountCommand());

		BroadcastCommand broadcastCommand = new BroadcastCommand();
		getCommand("broadcast").setExecutor(broadcastCommand);
		
		GamemodeCommand gamemodeCommand = new GamemodeCommand();
		getCommand("gamemode").setExecutor(gamemodeCommand);
		getCommand("gm").setExecutor(gamemodeCommand);

		ReplyCommand replyCommand = new ReplyCommand();
		getCommand("reply").setExecutor(replyCommand);
		getCommand("r").setExecutor(replyCommand);
		
		MessageCommand messageCommand = new MessageCommand();
		getCommand("msg").setExecutor(messageCommand);
		getCommand("message").setExecutor(messageCommand);
		getCommand("tell").setExecutor(messageCommand);
		getCommand("w").setExecutor(messageCommand);

		/** Starts Statics **/
		new MenuHandler(this);

		/** Loads all players online **/
		if (Bukkit.getOnlinePlayers().length != 0) {
			String[] names = new String[Bukkit.getOnlinePlayers().length];

			int i = 0;
			for (Player player : Bukkit.getOnlinePlayers()) {
				names[i] = player.getName();
				i++;
			}

			UUIDUtil fetcher = new UUIDUtil(Arrays.asList(names));

			Map<String, UUID> response = null;
			try {
				response = fetcher.call();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Player player : Bukkit.getOnlinePlayers()) {
				uuids.put(player, response.get(player.getName()));
			}
		}

		/** Applies rank to online players **/
		for (Player player : Bukkit.getOnlinePlayers()) {
			RankManager.applyPrefix(player);
		}

		/** Saves Config **/
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", broadcastCommand);
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		try{
		itemDatabase = new ItemDatabase();
		}catch(Exception e){
			
		}
		/** Creates new game **/
		new CustomEntityHandler();
		new GameManager(this);
		new SignHandler();
	}

	@Override
	public void onDisable() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (PetMenu.getPets().containsValue(entity)) {
					entity.remove();
				}
				
				if (MountMenu.getMounts().containsValue(entity)) {
					entity.remove();
				}
				
				if(IVanityEntityHandler.contains(entity)){
					entity.remove();
				}
			}
		}
		
		ServerTable serverTable = getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setOnlinePlayers(0);
		getSqlHandler().saveServer(serverTable);
	}

	public Map<Player, UUID> getUuids() {
		return uuids;
	}

	public void setUuids(Map<Player, UUID> uuids) {
		this.uuids = uuids;
	}

	public SqlHandler getSqlHandler() {
		return sqlHandler;
	}

	public void setSqlHandler(SqlHandler sqlHandler) {
		this.sqlHandler = sqlHandler;
	}

	public RankManager getRankManager() {
		return rankManager;
	}

	public void setRankManager(RankManager rankManager) {
		this.rankManager = rankManager;
	}

	public static MinersFortune getPlugin() {
		return plugin;
	}

	public static void setPlugin(MinersFortune plugin) {
		MinersFortune.plugin = plugin;
	}

	public static ItemDatabase getItemDatabase() {
		return itemDatabase;
	}

	public static void setItemDatabase(ItemDatabase itemDatabase) {
		MinersFortune.itemDatabase = itemDatabase;
	}

	public static boolean isCanBuild() {
		return canBuild;
	}

	public static void setCanBuild(boolean canBuild) {
		MinersFortune.canBuild = canBuild;
	}

	public static List<Player> getPlayersCanBuild() {
		return playersCanBuild;
	}

	public static void setPlayersCanBuild(List<Player> playersCanBuild) {
		MinersFortune.playersCanBuild = playersCanBuild;
	}

	public static List<Player> getVanished() {
		return vanished;
	}

	public static void setVanished(List<Player> vanished) {
		MinersFortune.vanished = vanished;
	}

}
