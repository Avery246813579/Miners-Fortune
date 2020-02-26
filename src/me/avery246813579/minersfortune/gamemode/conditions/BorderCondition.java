package me.avery246813579.minersfortune.gamemode.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;

public class BorderCondition extends GameCondition implements Listener {
	private Map<Player, List<Location>> changedBlocks = new HashMap<Player, List<Location>>();
	private int borderSize, timeState;
	private boolean roundBorder;
	private Location center;
	private List<Location> blocks = new ArrayList<Location>();

	public void makeCircle(Location loc, Integer r) {
        int x;
        int y = loc.getBlockY();
        int z;
               
        for (double i = 0.0; i < 360.0; i += 0.1) {
        double angle = i * Math.PI / 180;
            x = (int)(loc.getX() + r * Math.cos(angle));
            z = (int)(loc.getZ() + r * Math.sin(angle));
            
            blocks.add(new Location(Bukkit.getWorld("GameWorld"), x, y, z));
        }
    }
	
	public BorderCondition() {
		/** Registers the listener **/
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	@Override
	public void resetCondition(String condition) {
		/** X,Y,Z BORDER ROUND_BORDER **/
		String[] split = condition.split(" ");

		/** Get's the location of the center of map **/
		String[] locationSplit = split[0].split(",");
		center = new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(locationSplit[0]), Integer.parseInt(locationSplit[1]), Integer.parseInt(locationSplit[2]));
		Bukkit.broadcastMessage(locationSplit[0] + ", " + locationSplit[1] + ", " + locationSplit[2]);
		
		/** Get's the radius of the border **/
		borderSize = Integer.parseInt(split[1]);

		/** Parse's if border is rounded **/
		roundBorder = Boolean.parseBoolean(split[2]);
		
		makeCircle(center, borderSize);

	}

	@Override
	public void stopCondition() {
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (GameManager.getGameConditions().contains(this)) {
			/** Get's player **/
			Player player = event.getPlayer();

			if (changedBlocks.containsKey(player)) {
				for (Location location : changedBlocks.get(player)) {
					location.getBlock().setType(Material.AIR);
				}
			}

			/** Get's game player **/
			GamePlayer gamePlayer = GameManager.findPlayer(player);

			/** Get's location of player **/
			Location playerLocation = player.getLocation();

			/** Duplicates center location **/
			Location dupLocation = center.clone();

			if (roundBorder) {
				/**
				 * Places the Y location of player to Y location of duplicate
				 * location
				 **/
				dupLocation.setY(playerLocation.getY());

				/** Get's location from spawn **/
				double distanceFromSpawn = playerLocation.distance(dupLocation);

				/** Checks if the player is 2 blocks from border **/
				if (distanceFromSpawn >= borderSize - 2D) {
					/** Checks if player is outside border **/
					if (distanceFromSpawn >= borderSize) {
						if (gamePlayer.isAlive()) {

						} else {

						}
					} else {
						if (gamePlayer.isAlive()) {
							closest(playerLocation);
						}
					}
				}
			}
		}
	}
	
	public void closest(Location location){
		double greatest = 0;
		Location gLoc = null;
		
		for(Location locations : blocks){
			Location dupLocation = locations.clone();

			dupLocation.setY(location.getY());

			/** Get's location from spawn **/
			double distanceFromSpawn = location.distance(dupLocation);

			if(distanceFromSpawn > greatest){
				greatest = distanceFromSpawn;
				gLoc = dupLocation;
			}
		}
		
		for(int y = 0; y < 150; y++){
			location.getWorld().getBlockAt((int) gLoc.getX(), y, (int)gLoc.getZ()).setType(Material.GLASS);;
		}
		
		gLoc.getBlock().setType(Material.DIAMOND_BLOCK);		
	}

	public int getTimeState() {
		return timeState;
	}

	public void setTimeState(int timeState) {
		this.timeState = timeState;
	}
}
