package me.avery246813579.minersfortune.gamemode;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GameTeam {
	/** Variables **/
	private List<Player> players = new ArrayList<Player>();
	private List<Player> queue = new ArrayList<Player>();
	private GameTeamTypes gameTeamTypes;
	private boolean visable = true, canSeeInvisiablePlayers = true, canTeamHurt = false;
	private List<Location> spawns;
	private int currentSpawn;
	private String name;
	private ChatColor color;
	private byte teamColor;
	
	public GameTeam(String name, ChatColor color, GameTeamTypes gameTeamTypes, byte teamColor){
		this.color = color;
		this.gameTeamTypes = gameTeamTypes;
		this.name = name;
		this.teamColor = teamColor;
		
		if(GameManager.getGameType() == GameType.SurvivalGames || GameManager.getGameType() == GameType.ArcherGames){
			canSeeInvisiablePlayers = false;
			canTeamHurt = true;
		}
	}
	
	public void sendTeamMessage(Player player, String message){
		player.sendMessage(ChatColor.GREEN + "Team >> " + ChatColor.GRAY + message);
	}
	
	public void addQueue(Player player){
		if(queue.contains(player)){
			sendTeamMessage(player, ChatColor.RED + "You are already queued for " + name + " team.");
			return;
		}
		
		sendTeamMessage(player, ChatColor.YELLOW + "You have been queued up for " + name + " team.");
		queue.add(player);
	}
	
	public void addPlayer(Player player){		
		players.add(player);
		sendTeamMessage(player, ChatColor.YELLOW + "You joined " + name + " team.");
	}
	
	public void removePlayer(Player player){
		players.remove(player);
	}
	
	public Location getSpawn(){
		if(currentSpawn == (spawns.size() - 1)){
			currentSpawn = 0;
		}
		
		Location spawn = spawns.get(currentSpawn);
		currentSpawn++;
		
		return spawn;
	}

	public List<Location> getSpawns() {
		return spawns;
	}

	public void setSpawns(List<Location> spawns) {
		this.spawns = spawns;
	}

	public int getCurrentSpawn() {
		return currentSpawn;
	}

	public void setCurrentSpawn(int currentSpawn) {
		this.currentSpawn = currentSpawn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ChatColor getColor() {
		return color;
	}

	public void setColor(ChatColor color) {
		this.color = color;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isVisable() {
		return visable;
	}

	public void setVisable(boolean visable) {
		this.visable = visable;
	}

	public List<Player> getQueue() {
		return queue;
	}

	public void setQueue(List<Player> queue) {
		this.queue = queue;
	}

	public boolean isCanSeeInvisiablePlayers() {
		return canSeeInvisiablePlayers;
	}

	public void setCanSeeInvisiablePlayers(boolean canSeeInvisiablePlayers) {
		this.canSeeInvisiablePlayers = canSeeInvisiablePlayers;
	}

	public boolean isCanTeamHurt() {
		return canTeamHurt;
	}

	public void setCanTeamHurt(boolean canTeamHurt) {
		this.canTeamHurt = canTeamHurt;
	}

	public GameTeamTypes getGameTeamTypes() {
		return gameTeamTypes;
	}

	public void setGameTeamTypes(GameTeamTypes gameTeamTypes) {
		this.gameTeamTypes = gameTeamTypes;
	}

	public byte getTeamColor() {
		return teamColor;
	}

	public void setTeamColor(byte teamColor) {
		this.teamColor = teamColor;
	}
}
