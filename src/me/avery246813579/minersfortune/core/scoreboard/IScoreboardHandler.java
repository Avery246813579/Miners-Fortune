package me.avery246813579.minersfortune.core.scoreboard;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameTeam;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.util.IChatColor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class IScoreboardHandler implements Listener {
	public static List<IScoreboard> IScoreboards = new ArrayList<IScoreboard>();
	public static IScoreboard currentScoreboard;
	
	public static void updateLeave(){
		/** Sets the current scoreboard **/
		currentScoreboard = getScoreboard();
		
		/** Gets Players Variable **/
		IScore iScore = currentScoreboard.findVariable("Players");
		
		/** Removes the score **/
		currentScoreboard.variables.remove(iScore);
		
		/** Checks and updates IScore **/
		if(iScore != null){
			iScore.setResult(ChatColor.GREEN + Integer.toString(Bukkit.getOnlinePlayers().length - 1));
		}
		
		/** Adds score back **/
		currentScoreboard.variables.add(iScore);
		
		/** Updates all the players scoreboard **/
		for(Player players : Bukkit.getOnlinePlayers()){
			update(GameManager.findPlayer(players));
		}
		
		/** Resets current scoreboard **/
		currentScoreboard = null;
	}

	public static void updatePlayers() {
		/** Sets the current Scoreboard **/
		currentScoreboard = getScoreboard();

		/** Updates all the player scoreboards **/
		for (Player players : Bukkit.getOnlinePlayers()) {
			update(GameManager.findPlayer(players));
		}

		/** Resets current scoreboard **/
		currentScoreboard = null;
	}

	public static void update(GamePlayer gamePlayer) {
		/** If Player is leaving; return **/
		if(gamePlayer == null){
			return;
		}
		
		/** Gets the player **/
		Player player = gamePlayer.getPlayer();

		/** Gets current scoreboard **/
		IScoreboard IScoreboard = currentScoreboard;
		if (IScoreboard == null) {
			IScoreboard = getScoreboard();
		}

		/** Gets the player's scoreboard **/
		Scoreboard scoreboard = player.getScoreboard();
		if (scoreboard == null) {
			createScoreboard(player);
			scoreboard = player.getScoreboard();
		}

		/** Updates the IScoreboard's variables **/
		IScoreboard.loadVariables(gamePlayer);

		/** Gets the objective from the scoreboard **/
		Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);

		if(objective == null){
			objective = scoreboard.registerNewObjective("Sidebar", "dummy");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		
		/** Resets scores **/
		for (OfflinePlayer offlinePlayers : scoreboard.getPlayers()) {
			scoreboard.resetScores(offlinePlayers);
		}
		
		/** Sets Title **/
		objective.setDisplayName(IScoreboard.title);

		/** Loops threw variables and updates player scoreboard **/
		int score = 1;
		for (IScore iScore : IScoreboard.variables) {
			if (iScore.getResult() != null) {
				objective.getScore(Bukkit.getOfflinePlayer("" + ChatColor.GREEN + iScore.getResult())).setScore(score);
			} else {
				objective.getScore(Bukkit.getOfflinePlayer("" + ChatColor.GREEN + iScore.getError())).setScore(score);
			}
			score++;
			objective.getScore(Bukkit.getOfflinePlayer("" + ChatColor.WHITE + ChatColor.BOLD + iScore.getScore())).setScore(score);
			score++;
			objective.getScore(Bukkit.getOfflinePlayer("" + IChatColor.getRandomColor() + "")).setScore(score);
			score++;
		}

		/** Sets the players scoreboard **/
		player.setScoreboard(scoreboard);
	}
	
	public static void updateTeamScoreboards(){
		for(Player player : Bukkit.getOnlinePlayers()){
			updatePlayerTeamScoreboard(player);
		}
	}

	public static void createScoreboard(Player player) {
		/** Gets Scoreboard Manager **/
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

		/** Creates new scoreboard **/
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

		/** Creates objective **/
		Objective objective = scoreboard.registerNewObjective("SIDEBAR", "dummy");

		/** Places scoreboard on sidebar **/
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		/** Sets player a new scoreboard **/
		player.setScoreboard(scoreboard);
	}
	
	public static void updatePlayerTeamScoreboard(Player player) {
		Scoreboard scoreboard = player.getScoreboard();
		if(scoreboard == null){
			createScoreboard(player);
			scoreboard = player.getScoreboard();
		}
		
		if (GameManager.getGameState() == GameState.Live || GameManager.getGameState() == GameState.Prepare) {
			for (GameTeam gameTeam : GameManager.getTeams()) {
				if (gameTeam.getPlayers().contains(player)) {
					if(scoreboard.getTeam(gameTeam.getName()) == null){
						Team team = scoreboard.registerNewTeam(gameTeam.getName());
						team.setAllowFriendlyFire(gameTeam.isCanTeamHurt());
						team.setPrefix(gameTeam.getColor() + "");
						team.setDisplayName(gameTeam.getName());
						team.setCanSeeFriendlyInvisibles(gameTeam.isCanSeeInvisiablePlayers());
					}
					
					scoreboard.getTeam(gameTeam.getName()).addPlayer(player);
					if (player.getName().length() < 14)
						player.setPlayerListName(gameTeam.getColor() + player.getName());
					else
						player.setPlayerListName(gameTeam.getColor() + player.getName().substring(0, 14));
					player.setDisplayName(RankManager.findPlayerRank(player).getPrefix() + gameTeam.getColor() + player.getName() + ChatColor.WHITE);
				}
			}
		} else {
			for (Ranks ranks : Ranks.values()) {
				if (RankManager.findPlayerRank(player) == ranks) {
					if(scoreboard.getTeam(ranks.getName()) == null){
						Team team = scoreboard.registerNewTeam(ranks.getName());
						team.setAllowFriendlyFire(false);
						team.setPrefix(ranks.getPrefix());
						team.setDisplayName(ranks.getName());
						team.setCanSeeFriendlyInvisibles(true);
					}
					
					if (!scoreboard.getTeam(ranks.getName()).hasPlayer(player)) {
						scoreboard.getTeam(ranks.getName()).addPlayer(player);
						player.setDisplayName(ranks.getPrefix() + player.getName() + ChatColor.WHITE);
						if (player.getName().length() < 14) {
							player.setPlayerListName(ranks.getChatColor() + player.getName());
						} else {
							player.setPlayerListName(ranks.getChatColor() + player.getName().substring(0, 14));
						}
					}
				}
			}
		}
	}


	public static IScoreboard getScoreboard() {
		if (GameManager.getGameState() == GameState.Live) {
			for (IScoreboard iScoreboards : IScoreboards) {
				if (iScoreboards.gameType == GameManager.getGameType()) {
					return iScoreboards;
				}
			}
		} else {
			for (IScoreboard iScoreboards : IScoreboards) {
				if (iScoreboards.gameState == GameManager.getGameState()) {
					return iScoreboards;
				}
			}
		}

		return null;
	}
}
