package me.avery246813579.minersfortune.core.scoreboard.types.gamestate;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;

import me.avery246813579.minersfortune.core.scoreboard.IScore;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboard;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameState;

public class RecruitScoreboard extends IScoreboard {
	public RecruitScoreboard() {
		super(GameState.Recruit);
	}

	@Override
	protected void loadVariables(GamePlayer gamePlayer) {
		super.loadVariables(gamePlayer);

		variables.add(new IScore("Server", Bukkit.getServerName(), "Unknown"));
		if (GameManager.getMapTable() != null) {
			variables.add(new IScore("Map", GameManager.getMapTable().toString(), "Deciding"));
		} else {
			variables.add(new IScore("Map", "Deciding", "Deciding"));
		}
		variables.add(new IScore("Kit", gamePlayer.getKit().getDisplayName(), "Not Selected"));
		variables.add(new IScore("Players", Integer.toString(Bukkit.getOnlinePlayers().length), "Unknown"));

		title = "" + ChatColor.GOLD + ChatColor.BOLD + GameManager.getGameType().getDisplayName();
	}
}
