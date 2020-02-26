package me.avery246813579.minersfortune.sql.tables;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class TeamTable {
	private String name, spawns;
	private ChatColor color;
	private int mapId;

	public TeamTable(int mapId, String name, String color, String spawns) {
		this.spawns = spawns;
		this.mapId = mapId;
		this.name = name;

		for (ChatColor chatColor : ChatColor.values()) {
			if (chatColor.name().equalsIgnoreCase(color)) {
				this.color = chatColor;
			}
		}
	}

	public List<Location> getLocationSpawns() {
		List<Location> spawns = new ArrayList<Location>();
		
		for (String stringSpawn : this.spawns.split(",")) {
			String[] split = stringSpawn.split("_");
			
			if (split.length <= 3) {
				spawns.add(new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
			}else{
				spawns.add(new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Float.parseFloat(split[3]), 0));
			}
		}
		
		return spawns;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpawns(){
		return spawns;
	}

	public void setSpawns(String spawns) {
		this.spawns = spawns;
	}

	public ChatColor getColor() {
		return color;
	}

	public void setColor(ChatColor color) {
		this.color = color;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
}
