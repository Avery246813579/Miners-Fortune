package me.avery246813579.minersfortune.sql.tables;

public class GlobalStatsTable {
	private int player_id, time_online, games_played, credits_earned;

	public GlobalStatsTable(int player_id, int time_online, int games_played, int credits_earned) {
		this.player_id = player_id;
		this.time_online = time_online;
		this.games_played = games_played;
		this.credits_earned = credits_earned;
	}

	public String getTimeOnline() {
		String timeOnline = "First Join";
		if(time_online == 0){
			return "First Join";
		}else if (time_online < 60) {
			timeOnline = time_online + " seconds";
		} else if (time_online < 3600) {
			int secondsTime = (int)((double)((double)(time_online % 60) / 60) * 10);
			timeOnline = (int) (time_online / 60) + "." + secondsTime + " minutes";
		}  else if (time_online < 86400) {
			int secondsTime = (int)((double)((double)(time_online % 3600) / 3600) * 10);
			timeOnline = (int) (time_online / 3600) + "." + secondsTime + " hours";
		} else {
			int secondsTime = (int)((double)((double)(time_online % 86400) / 86400) * 10);
			timeOnline = (int) (time_online / 86400) + "." + secondsTime + " days";
		}

		return timeOnline;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public int getTime_online() {
		return time_online;
	}

	public void setTime_online(int time_online) {
		this.time_online = time_online;
	}

	public int getGames_played() {
		return games_played;
	}

	public void setGames_played(int games_played) {
		this.games_played = games_played;
	}

	public int getCredits_earned() {
		return credits_earned;
	}

	public void setCredits_earned(int credits_earned) {
		this.credits_earned = credits_earned;
	}
}
