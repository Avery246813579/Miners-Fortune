package me.avery246813579.minersfortune.core.scoreboard.types.gametype;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;

import me.avery246813579.minersfortune.core.scoreboard.IScore;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboard;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameType;

public class SearchAndDestroyScoreboard extends IScoreboard{
	public SearchAndDestroyScoreboard() {
		super(GameType.SearchAndDestroy);
	}

	@Override
	protected void loadVariables(GamePlayer gamePlayer) {
		super.loadVariables(gamePlayer);

		variables.add(new IScore("Server", Bukkit.getServerName(), "Unknown"));
		variables.add(new IScore("Map", GameManager.getMapTable().toString(), "Deciding"));

		title = "" + ChatColor.GOLD + ChatColor.BOLD + GameManager.getGameType().getDisplayName();
	}
}
