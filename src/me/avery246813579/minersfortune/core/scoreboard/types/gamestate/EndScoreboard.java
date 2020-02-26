package me.avery246813579.minersfortune.core.scoreboard.types.gamestate;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;

import me.avery246813579.minersfortune.core.scoreboard.IScore;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboard;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameState;

public class EndScoreboard extends IScoreboard {
	public EndScoreboard() {
		super(GameState.End);
	}

	@Override
	protected void loadVariables(GamePlayer gamePlayer) {
		super.loadVariables(gamePlayer);

		variables.add(new IScore("Server", Bukkit.getServerName(), "Unknown"));
		variables.add(new IScore("Map", GameManager.getMapTable().getMapName(), "Deciding"));
		if (GameManager.getWinner() != null) {
			variables.add(new IScore("Winner", GameManager.getWinner().getName(), ""));
		} else if (GameManager.getWinners().get(0) != null) {
			variables.add(new IScore("Winner", GameManager.getWinners().get(0).getName(), ""));
		} else{
			variables.add(new IScore("Winner", "No one", ""));
		}

		title = "" + ChatColor.GOLD + ChatColor.BOLD + GameManager.getGameType().getDisplayName();
	}
}
