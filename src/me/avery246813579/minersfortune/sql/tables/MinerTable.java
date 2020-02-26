package me.avery246813579.minersfortune.sql.tables;

public class MinerTable {
	/** Classes **/
	private int player_id, exp, emeralds;
	private String lastLocation, inventory, classType, masteries, relics, quests, vaultStorage, spells, skills;
	
	public MinerTable(int player_id, int exp, int emeralds, String lastLocation, String inventory, String classType, String masteries, String relics, String quests, String spells, String skills, String vaultStorage){
		this.player_id = player_id;
		this.exp = exp;
		this.emeralds = emeralds;
		this.lastLocation = lastLocation;
		this.inventory = inventory;
		this.classType = classType;
		this.masteries = masteries;
		this.relics = relics;
		this.quests = quests;
		this.spells = spells;
		this.skills = skills;
		this.vaultStorage = vaultStorage;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getEmeralds() {
		return emeralds;
	}

	public void setEmeralds(int emeralds) {
		this.emeralds = emeralds;
	}

	public String getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(String lastLocation) {
		this.lastLocation = lastLocation;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getMasteries() {
		return masteries;
	}

	public void setMasteries(String masteries) {
		this.masteries = masteries;
	}

	public String getRelics() {
		return relics;
	}

	public void setRelics(String relics) {
		this.relics = relics;
	}

	public String getQuests() {
		return quests;
	}

	public void setQuests(String quests) {
		this.quests = quests;
	}

	public String getVaultStorage() {
		return vaultStorage;
	}

	public void setVaultStorage(String vaultStorage) {
		this.vaultStorage = vaultStorage;
	}

	public String getSpells() {
		return spells;
	}

	public void setSpells(String spells) {
		this.spells = spells;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
}
