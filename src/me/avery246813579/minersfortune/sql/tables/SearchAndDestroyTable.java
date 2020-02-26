package me.avery246813579.minersfortune.sql.tables;

public class SearchAndDestroyTable extends GameStatsTable{
	private int bomb_arms, bomb_defuses;
	
	public SearchAndDestroyTable(int player_id, int wins, int losses, int kills, int deaths, int credits_earned, int bomb_arms, int bomb_defuses) {
		super(player_id, wins, losses, kills, deaths, credits_earned);
		this.bomb_arms = bomb_arms;
		this.bomb_defuses = bomb_defuses;
	}

	public int getBomb_arms() {
		return bomb_arms;
	}

	public void setBomb_arms(int bomb_arms) {
		this.bomb_arms = bomb_arms;
	}

	public int getBomb_defuses() {
		return bomb_defuses;
	}

	public void setBomb_defuses(int bomb_defuses) {
		this.bomb_defuses = bomb_defuses;
	}
}
