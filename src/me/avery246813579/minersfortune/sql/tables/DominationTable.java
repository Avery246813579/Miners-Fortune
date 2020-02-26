package me.avery246813579.minersfortune.sql.tables;

public class DominationTable extends GameStatsTable{
	private int points_captured;
	
	public DominationTable(int player_id, int wins, int losses, int kills, int deaths, int credits_earned, int points_captured) {
		super(player_id, wins, losses, kills, deaths, credits_earned);
		this.points_captured = points_captured;
	}

	public int getPoints_captured() {
		return points_captured;
	}

	public void setPoints_captured(int points_captured) {
		this.points_captured = points_captured;
	}
}
