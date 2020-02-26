package me.avery246813579.minersfortune.gamemode.conditions;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameTeam;
import me.avery246813579.minersfortune.sql.tables.DominationTable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

public class ControlPoint {
	private List<Block> blocks = new ArrayList<Block>();
	private GameTeam inControl;

	public ControlPoint(Location firstLocation, Location secondLocation) {
		loadBlocks(firstLocation, secondLocation);
	}

	public void loadBlocks(Location firstLocation, Location secondLocation) {
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

	@SuppressWarnings("deprecation")
	public void updateControlPoint(Player player, GameTeam gameTeam) {
		byte teamDye = 0;
		byte otherDye = 0;

		if (gameTeam.getColor() == ChatColor.RED) {
			otherDye = 11;
			teamDye = 14;
		} else if (gameTeam.getColor() == ChatColor.BLUE) {
			teamDye = 11;
			otherDye = 14;
		}

		if (hasOtherTeamColor(otherDye)) {
			List<Block> otherTeamColor = getOtherTeamColor(otherDye);

			if (otherTeamColor.size() == 1) {
				for (Block block : otherTeamColor) {
					block.setType(Material.STAINED_GLASS);
					BlockState bs = block.getState();
					bs.getData().setData((byte) 0);
					bs.update();
				}
			} else {
				otherTeamColor.get(0).setType(Material.STAINED_GLASS);
				BlockState bs = otherTeamColor.get(0).getState();
				bs.getData().setData((byte) 0);
				bs.update();

				otherTeamColor.get(1).setType(Material.STAINED_GLASS);
				BlockState bs1 = otherTeamColor.get(1).getState();
				bs1.getData().setData((byte) 0);
				bs1.update();
			}

			this.inControl = null;
			return;
		} else if (hasOtherTeamColor((byte) 0)) {
			List<Block> otherTeamColor = getOtherTeamColor((byte) 0);

			if (otherTeamColor.size() == 1) {
				for (Block block : otherTeamColor) {
					block.setType(Material.STAINED_GLASS);
					BlockState bs = block.getState();
					bs.getData().setData(teamDye);
					bs.update();
				}

				if (inControl == null) {
					for (Player players : Bukkit.getOnlinePlayers()) {
						players.playSound(players.getLocation(), Sound.ANVIL_LAND, 2L, 1L);
					}

					DominationTable dominationTable = (DominationTable) GameManager.findPlayer(player).getGameStatsTable();
					dominationTable.setPoints_captured(dominationTable.getPoints_captured() + 1);
					GameManager.findPlayer(player).saveStatsTable();
				}
				
				this.inControl = gameTeam;
			} else {
				otherTeamColor.get(0).setType(Material.STAINED_GLASS);
				BlockState bs = otherTeamColor.get(0).getState();
				bs.getData().setData(teamDye);
				bs.update();

				otherTeamColor.get(1).setType(Material.STAINED_GLASS);
				BlockState bs1 = otherTeamColor.get(1).getState();
				bs1.getData().setData(teamDye);
				bs1.update();
			}

			this.inControl = null;
			return;
		} else {
			if (inControl == null) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.ANVIL_LAND, 2L, 1L);
				}

				DominationTable dominationTable = (DominationTable) GameManager.findPlayer(player).getGameStatsTable();
				dominationTable.setPoints_captured(dominationTable.getPoints_captured() + 1);
				GameManager.findPlayer(player).saveStatsTable();
			}

			this.inControl = gameTeam;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean hasOtherTeamColor(int otherTeam) {
		for (Block block : blocks) {
			BlockState blockState = block.getState();

			if (blockState.getData().getData() == otherTeam) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public List<Block> getOtherTeamColor(int otherTeam) {
		List<Block> blocks = new ArrayList<Block>();

		for (Block block : this.blocks) {
			BlockState blockState = block.getState();

			if (blockState.getData().getData() == otherTeam) {
				blocks.add(block);
			}
		}

		return blocks;

	}

	public GameTeam getInControl() {
		return inControl;
	}

	public void setInControl(GameTeam inControl) {
		this.inControl = inControl;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
}
