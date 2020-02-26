package me.avery246813579.minersfortune.sql.tables;

public abstract class GameStatsTable {
	int player_id, wins, losses, kills, deaths, credits_earned;
	
	public GameStatsTable(int player_id, int wins, int losses, int kills, int deaths, int credits_earned){
		this.player_id = player_id;
		this.wins = wins;
		this.losses = losses;
		this.kills = kills;
		this.deaths = deaths;
		this.credits_earned = credits_earned;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getCredits_earned() {
		return credits_earned;
	}

	public void setCredits_earned(int credits_earned) {
		this.credits_earned = credits_earned;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
}
