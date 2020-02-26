package me.avery246813579.minersfortune.core.scoreboard.types.gametype;

import me.avery246813579.minersfortune.core.scoreboard.IScore;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboard;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameType;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;

public class SurvivalGamesScoreboard extends IScoreboard{
	public SurvivalGamesScoreboard() {
		super(GameType.SurvivalGames);
	}
	
	@Override
	protected void loadVariables(GamePlayer gamePlayer) {
		super.loadVariables(gamePlayer);
		
		variables.add(new IScore("Server", Bukkit.getServerName(), "Unknown"));
		variables.add(new IScore("Map", GameManager.getMapTable().getMapName(), "Deciding"));
		variables.add(new IScore("Survivors", Integer.toString(GameManager.getAlivePlayers()), "Unknown"));

		title = "" + ChatColor.GOLD + ChatColor.BOLD + "Survival Games";
	}
}
