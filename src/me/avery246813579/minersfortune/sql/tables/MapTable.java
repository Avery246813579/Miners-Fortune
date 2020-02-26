package me.avery246813579.minersfortune.sql.tables;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.avery246813579.minersfortune.gamemode.GameType;

public class MapTable {
	private int mapId;
	private String mapName = "Unknown", mapCreator, mapLocation, midPoint;
	private GameType gameType;
	private boolean active;

	public MapTable(int mapId, String mapName, String mapCreator, String mapLocation, String gameType, String midPoint, boolean active) {
		/** Basic Stuff **/
		this.mapId = mapId;
		this.mapName = mapName;
		this.mapCreator = mapCreator;
		this.mapLocation = mapLocation;
		this.midPoint = midPoint;
		this.active = active;

		/** Gets Gametype **/
		for (GameType type : GameType.values()) {
			if (type.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(gameType)) {
				this.gameType = type;
			}
		}
	}
	
	public Location getMp(){
		Location location = null;
		String[] split = midPoint.split("_");
		
		if (split.length <= 3) {
			location = new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
		}else{
			location = new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Float.parseFloat(split[3]), 0);
		}
		
		return location;
	}
	
	public String toString(){
		return mapName;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapCreator() {
		return mapCreator;
	}

	public void setMapCreator(String mapCreator) {
		this.mapCreator = mapCreator;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public String getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public String getMidPoint() {
		return midPoint;
	}

	public void setMidPoint(String midPoint) {
		this.midPoint = midPoint;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
