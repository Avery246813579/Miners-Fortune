package me.avery246813579.minersfortune.sql.tables;

public class AccountTable {
	/** Variables **/
	private int player_id, credits, coins, rank_id;
	private String name, uuid, ip, rank;
	private boolean isStaff, isRanked;
	
	public AccountTable(int player_id, String name, String uuid, String ip, String rank, int credits, int coins, int rankId, boolean isStaff, boolean isRanked){
		this.player_id = player_id;
		this.name = name;
		this.uuid = uuid;
		this.ip = ip;
		this.rank = rank;
		this.credits = credits;
		this.coins = coins; 
		this.setRank_id(rankId);
		this.isStaff = isStaff;
		this.isRanked = isRanked;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public boolean isStaff() {
		return isStaff;
	}

	public void setStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}

	public boolean isRanked() {
		return isRanked;
	}

	public void setRanked(boolean isRanked) {
		this.isRanked = isRanked;
	}

	public int getRank_id() {
		return rank_id;
	}

	public void setRank_id(int rank_id) {
		this.rank_id = rank_id;
	}
}
