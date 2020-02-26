package me.avery246813579.minersfortune.core.scoreboard.types.gamestate;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.scoreboard.IScore;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboard;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.sql.tables.AccountTable;

public class LimbowScoreboard extends IScoreboard{
	public LimbowScoreboard() {
		super(GameState.Limbow);
	}
	
	@Override
	protected void loadVariables(GamePlayer gamePlayer) {
		super.loadVariables(gamePlayer);

		AccountTable accountTable = MinersFortune.getPlugin().getSqlHandler().getAccount(gamePlayer.getPlayer());
		variables.add(new IScore("Website", "MinersFortune.net", ""));
		variables.add(new IScore("Server", Bukkit.getServerName(), "HUB"));
		variables.add(new IScore("Coins", Integer.toString(accountTable.getCoins()), "0"));
		variables.add(new IScore("Credits", Integer.toString(accountTable.getCredits()), "0"));
		variables.add(new IScore("Rank", RankManager.findPlayerRank(gamePlayer.getPlayer().getName()).getName(), "Default"));
		
		title = "" + ChatColor.GOLD + ChatColor.BOLD + "Miners Fortune";
	}
}
