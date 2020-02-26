package me.avery246813579.minersfortune.sql.tables;

public class ArcherGamesTable extends GameStatsTable{
	public ArcherGamesTable(int player_id, int wins, int losses, int kills, int deaths, int credits_earned) {
		super(player_id, wins, losses, kills, deaths, credits_earned);
	}
}
