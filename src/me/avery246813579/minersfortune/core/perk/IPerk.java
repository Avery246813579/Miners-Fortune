package me.avery246813579.minersfortune.core.perk;

import net.md_5.bungee.api.ChatColor;
import me.avery246813579.minersfortune.ranks.Ranks;

public class IPerk {
	private int id;
	private String name;
	private int cost = 0;
	private Ranks freeFor;
	
	public IPerk(int id, String name, int cost){
		this.id = id;
		this.name = name;
		this.cost = cost;
		
		IPerkHandler.IPerks.add(this);
	}
	
	public IPerk(int id, String name, Ranks freeFor){
		this.id = id;
		this.name = name;
		this.freeFor = freeFor;
		
		IPerkHandler.IPerks.add(this);
	}
	
	public String getPrice(){
		if(cost == 0 && freeFor != null){
			return freeFor.getChatColor() + freeFor.getName();
		}else{
			return ChatColor.RED + Integer.toString(cost);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Ranks getFreeFor() {
		return freeFor;
	}

	public void setFreeFor(Ranks freeFor) {
		this.freeFor = freeFor;
	}
}
