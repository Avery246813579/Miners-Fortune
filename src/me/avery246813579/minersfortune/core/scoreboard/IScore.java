package me.avery246813579.minersfortune.core.scoreboard;

public class IScore {
	private String score, result = null, error;

	public IScore(String score, String result, String error) {
		this.score = score;
		this.result = result;
		this.error = error;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
