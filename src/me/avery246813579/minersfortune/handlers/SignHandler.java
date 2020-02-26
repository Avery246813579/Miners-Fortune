package me.avery246813579.minersfortune.handlers;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.sql.tables.ServerTable;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;

public class SignHandler {
	private static ConcurrentHashMap<Location, String> signs = new ConcurrentHashMap<Location, String>();
	private static boolean loaded = false;

	public SignHandler() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), new Runnable() {
			public void run() {
				if(!loaded){
					loadSigns();
				}
				
				if(signs.isEmpty()){
					return;
				}
				
				Iterator<Entry<Location, String>> it = signs.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<Location, String> pairs = (Map.Entry<Location, String>) it.next();

					if (pairs.getKey().getBlock().getType() == Material.SIGN || pairs.getKey().getBlock().getType() == Material.WALL_SIGN || pairs.getKey().getBlock().getType() == Material.SIGN_POST) {
						updateSign(pairs.getKey(), pairs.getValue());
					} else {
						signs.remove(pairs.getKey());
					}
				}
			}
		}, 20L, 20L);

	}

	public static void loadSigns() {
		if (!MinersFortune.getPlugin().getConfig().contains("signs")) {
			return;
		}

		for (String location : MinersFortune.getPlugin().getConfig().getConfigurationSection("signs").getKeys(false)) {
			String server = MinersFortune.getPlugin().getConfig().getString("signs." + location);
			
			if (parseLocation(location).getBlock().getState().getType() == Material.SIGN || parseLocation(location).getBlock().getState().getType() == Material.WALL_SIGN || parseLocation(location).getBlock().getState().getType() == Material.SIGN_POST) {
				updateSign(parseLocation(location), server);
				signs.put(parseLocation(location), server);
			}
		}
		
		loaded = true;
	}

	public static void createSign(Location location, String server) {
		if (!MinersFortune.getPlugin().getConfig().contains("signs")) {
			MinersFortune.getPlugin().getConfig().createSection("signs");
		}

		ConfigurationSection config = MinersFortune.getPlugin().getConfig().getConfigurationSection("signs");
		if (!config.contains(parseString(location))) {
			config.set(parseString(location), server);
			MinersFortune.getPlugin().saveConfig();
		}

		signs.put(location, server);
		updateSign(location, server);
	}

	public static void deleteSign(Location location) {
		if (!MinersFortune.getPlugin().getConfig().contains("signs")) {
			MinersFortune.getPlugin().getConfig().createSection("signs");
		}

		ConfigurationSection config = MinersFortune.getPlugin().getConfig().getConfigurationSection("signs");
		if (config.contains(parseString(location))) {
			MinersFortune.getPlugin().getConfig().getConfigurationSection("signs").getKeys(false).remove(parseString(location));
			MinersFortune.getPlugin().saveConfig();
		}

		if (signs.containsKey(location)) {
			signs.remove(location);
		}
	}

	public static void updateSign(Location location, String server) {
		ServerTable serverTable = MinersFortune.getPlugin().getSqlHandler().getServer(server);

		Sign sign = (Sign) location.getBlock().getState();
		if (serverTable != null) {
			if (!serverTable.getServerStatus().equalsIgnoreCase("Live") && !serverTable.getServerStatus().equalsIgnoreCase("End")) {
				sign.setLine(0, "" + ChatColor.GREEN + ChatColor.BOLD + "[Join-" + serverTable.getServerName() + "]");
			} else if (serverTable.getMaxPlayers() <= serverTable.getOnlinePlayers() && !serverTable.getServerStatus().equalsIgnoreCase("Live") && !serverTable.getServerStatus().equalsIgnoreCase("End")) {
				sign.setLine(0, "" + ChatColor.RED + ChatColor.BOLD + "[Full-" + serverTable.getServerName() + "]");
			} else {
				sign.setLine(0, "" + ChatColor.RED + ChatColor.BOLD + "[Spec-" + serverTable.getServerName() + "]");
			}

			sign.setLine(1, "" + ChatColor.YELLOW + serverTable.getGameMap());
			sign.setLine(2, "" + ChatColor.GRAY + serverTable.getOnlinePlayers() + "/" + serverTable.getMaxPlayers());
			sign.setLine(3, "" + ChatColor.YELLOW + serverTable.getServerStatus());
			sign.update();
		} else {
			deleteSign(location);
		}
	}

	public static Location parseLocation(String string) {
		String[] locParse = string.split(" ");
		return new Location(Bukkit.getWorld(locParse[0]), Integer.parseInt(locParse[1]), Integer.parseInt(locParse[2]), Integer.parseInt(locParse[3]));
	}

	public static String parseString(Location location) {
		return location.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
	}

	public static Map<Location, String> getSigns() {
		return signs;
	}

	public static void setSigns(ConcurrentHashMap <Location, String> signs) {
		SignHandler.signs = signs;
	}
}
