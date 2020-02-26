package me.avery246813579.minersfortune.gamemode.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameTeam;
import me.avery246813579.minersfortune.gamemode.timers.LiveTimer;
import me.avery246813579.minersfortune.util.StatusBarApi;

public class ControlPoints extends GameCondition implements Runnable {
	public Map<GameTeam, Integer> teamPoints = new HashMap<GameTeam, Integer>();
	List<ControlPoint> points = new ArrayList<ControlPoint>();
	int timeState;

	@Override
	public void resetCondition(String condition) {
		timeState = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), this, 20L, 20L);

		teamPoints.clear();
		points.clear();

		for (GameTeam gameTeam : GameManager.getTeams()) {
			teamPoints.put(gameTeam, 0);
		}

		String[] pLoc = condition.split(" ");
		for (String pointL : pLoc) {
			String[] locations = pointL.split(",");
			points.add(new ControlPoint(new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(locations[1]), Integer.parseInt(locations[2]), Integer.parseInt(locations[3])), new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(locations[4]), Integer.parseInt(locations[5]), Integer
					.parseInt(locations[6]))));
		}
	}

	@Override
	public void stopCondition() {
		Bukkit.getScheduler().cancelTask(timeState);
	}

	public void run() {
		if (GameManager.getGameState() == GameState.Live) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (GameManager.findPlayer(player).isAlive()) {
					if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STAINED_GLASS) {
						for (ControlPoint controlPoint : points) {
							if (controlPoint.getBlocks().contains(player.getLocation().getBlock().getRelative(BlockFace.DOWN))) {
								controlPoint.updateControlPoint(player, GameManager.findTeam(player));
							}
						}
					}
				}
			}

			for (ControlPoint controlPoint : points) {
				if (controlPoint.getInControl() != null) {
					int point = teamPoints.get(controlPoint.getInControl());
					teamPoints.remove(controlPoint.getInControl());
					teamPoints.put(controlPoint.getInControl(), (point + 1));
				}
			}

			String barMessage = "";

			for (GameTeam gameTeam : GameManager.getTeams()) {
				barMessage = barMessage + "| " + gameTeam.getColor() + gameTeam.getName() + ": " + teamPoints.get(gameTeam) + " ";

				if (teamPoints.get(gameTeam) >= 500) {
					Bukkit.getScheduler().cancelTask(timeState);
					GameManager.setWinner(gameTeam);
					LiveTimer.setGameIsOver(true);
				}
			}

			for (Player player : Bukkit.getOnlinePlayers()) {
				int version = ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();

				if (version >= 47) {
					StatusBarApi.removeWitherBar(player);
					StatusBarApi.setWitherBar(player, barMessage.substring(1, barMessage.length()), 100);
				}else{
					StatusBarApi.setDragonBar(player, barMessage.substring(1, barMessage.length()), 100);
				}
			}
		}
	}
}
