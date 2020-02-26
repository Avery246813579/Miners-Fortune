package me.avery246813579.minersfortune.sql.tables;

import me.avery246813579.minersfortune.gamemode.GameType;

public class ServerTable {
	private String serverName, serverStatus, gameType, gameMap;
	private int onlinePlayers, maxPlayers;
	private GameType currentGame;
	private boolean whitelist;
	
	public ServerTable(String serverName, String serverStatus, String gameType, String gameMap, int onlinePlayers, int maxPlayers, String currentGame, boolean whitelist){
		this.serverName = serverName;
		this.serverStatus = serverStatus;
		this.gameType = gameType;
		this.gameMap = gameMap;
		this.onlinePlayers = onlinePlayers;
		this.maxPlayers = maxPlayers;
		this.whitelist = whitelist;
		
		for(GameType game : GameType.values()){
			if(game.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(currentGame)){
				this.currentGame = game;
			}
		}
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getGameMap() {
		return gameMap;
	}

	public void setGameMap(String gameMap) {
		this.gameMap = gameMap;
	}

	public int getOnlinePlayers() {
		return onlinePlayers;
	}

	public void setOnlinePlayers(int onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public GameType getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(GameType currentGame) {
		this.currentGame = currentGame;
	}

	public boolean isWhitelist() {
		return whitelist;
	}

	public void setWhitelist(boolean whitelist) {
		this.whitelist = whitelist;
	}
}
