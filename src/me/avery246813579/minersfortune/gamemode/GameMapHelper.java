package me.avery246813579.minersfortune.gamemode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.world.WorldUnloadEvent;

public class GameMapHelper {
	public static void sendMapMessage(CommandSender commandSender, String message) {
		commandSender.sendMessage(ChatColor.GREEN + "Map >> " + ChatColor.GRAY + message);
	}

	public static boolean loadMap(CommandSender commandSender, String map) {
		try {
			if (new File(Bukkit.getWorldContainer() + File.separator + "GameWorld").exists()) {
				if (Bukkit.getWorld("GameWorld") != null) {
					for (Player player : Bukkit.getWorld("GameWorld").getPlayers()) {
						player.teleport(GameManager.GameLocations.LOBBY.getLocation());
					}

					Bukkit.getServer().unloadWorld("GameWorld", true);
					File world_files = new File(Bukkit.getWorldContainer() + File.separator + "GameWorld");
					world_files.delete();
				}
			}

			sendMapMessage(commandSender, ChatColor.YELLOW + "Map " + map + " has succesfully loaded!");
			copyFolder(new File("/root/Minecraft/maps/" + map), new File(Bukkit.getWorldContainer() + File.separator + "GameWorld"));
		} catch (IOException exception) {
			sendMapMessage(commandSender, ChatColor.RED + "Map " + map + " failed to load!");
			exception.printStackTrace();
			return false;
		}

		WorldCreator wc = new WorldCreator("GameWorld");
		Bukkit.createWorld(wc);

		org.bukkit.World world = Bukkit.getWorld("GameWorld");
		world.setAutoSave(false);
		WorldServer handle = ((CraftWorld) world).getHandle();
		handle.keepSpawnInMemory = false;
		world.setDifficulty(Difficulty.EASY);
		world.setGameRuleValue("doMobSpawning", "false");
		world.setTime(0);
		world.setWeatherDuration(999999999);
		if (world.hasStorm())
			world.setStorm(false);

		world.getChunkAt(0, 0).load();
		return true;
	}

	public static boolean saveWorld(CommandSender commandSender, String map) {
		try {
			if (new File(Bukkit.getWorldContainer() + File.separator + "GameWorld").exists()) {
				if (Bukkit.getWorld("GameWorld") != null) {
					for (Player player : Bukkit.getWorld("GameWorld").getPlayers()) {
						player.teleport(GameManager.GameLocations.LOBBY.getLocation());
					}

					if (new File("/root/Minecraft/maps/" + map).exists()) {
						File file = new File("/Documents/Miners Fortune/Server/root/Minecraft/maps/" + map);
						file.delete();
					}
				}
			}

			sendMapMessage(commandSender, ChatColor.YELLOW + "Map " + map + " has succesfully saved!");
			copyFolder(new File(Bukkit.getWorldContainer() + File.separator + "GameWorld"), new File("/root/Minecraft/maps/" + map));
		} catch (IOException exception) {
			sendMapMessage(commandSender, ChatColor.RED + "Map " + map + " failed to save!");
			exception.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean restoreMap(String map) {
		if (Bukkit.getWorld(map) != null)
			forceUnloadWorld(Bukkit.getWorld(map));
		return delete(new File(map));
	}

	public static boolean delete(File folder) {
		if (!folder.exists())
			return true;
		boolean retVal = true;
		if (folder.isDirectory())
			for (File f : folder.listFiles())
				if (!delete(f)) {
					retVal = false;
					System.out.println("[SG] Failed to delete file: " + f.getName());
				}
		return folder.delete() && retVal;
	}

	public static void forceUnloadWorld(org.bukkit.World world) {
		System.out.println("[SG] Beginning force unload!");
		CraftServer server = (CraftServer) Bukkit.getServer();
		CraftWorld craftWorld = (CraftWorld) world;

		WorldUnloadEvent e = new WorldUnloadEvent(world);
		Bukkit.getPluginManager().callEvent(e);

		try {
			Field f = server.getClass().getDeclaredField("worlds"); // Get
																	// worlds
																	// arraylist
			f.setAccessible(true); // Make it accessable
			@SuppressWarnings("unchecked")
			Map<String, World> worlds = (Map<String, World>) f.get(server);
			worlds.remove(world.getName().toLowerCase()); // Remove world from
															// worlds list
			f.setAccessible(false); // Make it private again
		} catch (Exception ex) { /* Impossibru */
		}

		MinecraftServer ms = getMinecraftServer();
		ms.worlds.remove(ms.worlds.indexOf(craftWorld.getHandle())); // Remove
	}

	public static void copyFolder(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			while (!dest.exists()) {
				dest.mkdir();
			}

			String files[] = src.list();

			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				copyFolder(srcFile, destFile);
			}

		} else {

			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();

		}
	}

	protected static MinecraftServer getMinecraftServer() {
		return ((CraftServer) Bukkit.getServer()).getServer();
	}
}
