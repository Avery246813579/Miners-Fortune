package me.avery246813579.minersfortune.gamemode;

public enum GameWinType {
	LastManStanding(1, 50, 2),
	LastTeamStanding(1, 25, 1),
	PlayForPoints(1, 50, 2);
	
	private int creditsPerElimination, creditsPerWin, creditsForPlaying;
	
	private GameWinType(int perElimination, int perWin, int creditsForPlaying) {
		this.creditsPerElimination = perElimination;
		this.creditsPerWin = perWin;
		this.creditsForPlaying = creditsForPlaying;
	}

	public int getCreditsPerElimination() {
		return creditsPerElimination;
	}

	public void setCreditsPerElimination(int creditsPerElimination) {
		this.creditsPerElimination = creditsPerElimination;
	}

	public int getCreditsPerWin() {
		return creditsPerWin;
	}

	public void setCreditsPerWin(int creditsPerWin) {
		this.creditsPerWin = creditsPerWin;
	}

	public int getCreditsForPlaying() {
		return creditsForPlaying;
	}

	public void setCreditsForPlaying(int creditsForPlaying) {
		this.creditsForPlaying = creditsForPlaying;
	}
}
