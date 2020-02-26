package me.avery246813579.minersfortune.core.scoreboard;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameType;

public abstract class IScoreboard{
	protected String title;
	protected List<IScore> variables = new ArrayList<IScore>();
	protected GameType gameType;
	protected GameState gameState;
	
	public IScoreboard(GameType gameType){
		this.gameType = gameType;
		
		IScoreboardHandler.IScoreboards.add(this);
	}
	
	public IScoreboard(GameState gameState){
		this.gameState = gameState;
		
		IScoreboardHandler.IScoreboards.add(this);
	}
	
	protected void loadVariables(GamePlayer gamePlayer){
		variables.clear();
	}

	public IScore findVariable(String string){
		for(IScore iScore : variables){
			if(iScore.getScore().equalsIgnoreCase(string)){
				return iScore;
			}
		}
		
		return null;
	}
}
