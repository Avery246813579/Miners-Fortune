package me.avery246813579.minersfortune.gamemode.conditions;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;

public class OutsideMap extends GameCondition implements Runnable {
	private static List<Block> blocks = new ArrayList<Block>();
	int timeState;

	@Override
	public void resetCondition(String conditition) {
		timeState = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), this, 20L, 20L);

		String[] splits = conditition.split(" ");

		Location location = new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(splits[2].split(",")[0]), Integer.parseInt(splits[2].split(",")[1]), Integer.parseInt(splits[2].split(",")[2]));
		Location location2 = new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(splits[3].split(",")[0]), Integer.parseInt(splits[3].split(",")[1]), Integer.parseInt(splits[3].split(",")[2]));

		loadBlocks(location, location2);
	}

	public void loadBlocks(Location firstLocation, Location secondLocation) {
		blocks.clear();
		int topBlockX = (firstLocation.getBlockX() < secondLocation.getBlockX() ? secondLocation.getBlockX() : firstLocation.getBlockX());
		int bottomBlockX = (firstLocation.getBlockX() > secondLocation.getBlockX() ? secondLocation.getBlockX() : firstLocation.getBlockX());

		int topBlockY = (firstLocation.getBlockY() < secondLocation.getBlockY() ? secondLocation.getBlockY() : firstLocation.getBlockY());
		int bottomBlockY = (firstLocation.getBlockY() > secondLocation.getBlockY() ? secondLocation.getBlockY() : firstLocation.getBlockY());

		int topBlockZ = (firstLocation.getBlockZ() < secondLocation.getBlockZ() ? secondLocation.getBlockZ() : firstLocation.getBlockZ());
		int bottomBlockZ = (firstLocation.getBlockZ() > secondLocation.getBlockZ() ? secondLocation.getBlockZ() : firstLocation.getBlockZ());

		for (int x = bottomBlockX; x <= topBlockX; x++) {
			for (int z = bottomBlockZ; z <= topBlockZ; z++) {
				for (int y = bottomBlockY; y <= topBlockY; y++) {
					Block block = firstLocation.getWorld().getBlockAt(x, y, z);

					blocks.add(block);
				}
			}
		}
	}

	@Override
	public void stopCondition() {
		Bukkit.getScheduler().cancelTask(timeState);
	}

	public void run() {
		if (GameManager.getGameState() == GameState.Live) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (!blocks.contains(player.getLocation().getBlock().getRelative(BlockFace.DOWN))) {
					if(GameManager.findPlayer(player).isAlive()){
						player.sendMessage(ChatColor.GREEN + "Border >> " + ChatColor.RED + ChatColor.BOLD + "WARNING, you have left the battlefield! Return or die!");
						player.damage(4);
					}else{
						player.teleport(GameManager.getMapTable().getMp());
					}
				}
			}
		}
	}

	public static List<Block> getBlocks() {
		return blocks;
	}

	public static void setBlocks(List<Block> blocks) {
		OutsideMap.blocks = blocks;
	}
}
