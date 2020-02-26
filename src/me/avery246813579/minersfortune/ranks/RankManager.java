package me.avery246813579.minersfortune.ranks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.sql.tables.AccountTable;

public class RankManager {
	/** Classes **/
	static MinersFortune plugin;
	
	/** Variables **/
	private static boolean showPrefixes = true;
	
	public RankManager(MinersFortune plugin){
		RankManager.plugin = plugin;
	}
	
	public static Ranks findPlayerRank(Player player){
		if(plugin.getSqlHandler().getAccount(player) == null){
			return null;
		}
		
		for(Ranks ranks : Ranks.values()){
			if(ranks.getName().equalsIgnoreCase(plugin.getSqlHandler().getAccount(player).getRank())){
				return ranks;
			}
		}
		
		return null;
	}
	
	public static Ranks findPlayerRank(String name){
		if(plugin.getSqlHandler().findAccount(name) == null){
			return null;
		}
		
		for(Ranks ranks : Ranks.values()){
			if(ranks.getName().equalsIgnoreCase(plugin.getSqlHandler().findAccount(name).getRank())){
				return ranks;
			}
		}
		
		return null;
	}
	
	public static void applyPrefix(Player player){
		/** Checks if player can get prefix **/
		if(!showPrefixes){
			return;
		}
		
		/** Gets player account table **/
		AccountTable accountTable = plugin.getSqlHandler().getAccount(player);
	
		/** Finds and applies rank **/
		for(Ranks ranks : Ranks.values()){
			if(ranks.getName().equalsIgnoreCase(accountTable.getRank())){
				player.setCustomNameVisible(true);
				player.setDisplayName(ranks.getPrefix() + player.getName() + ChatColor.WHITE);
				if(player.getName().length() < 14) player.setPlayerListName(ranks.getChatColor() + player.getName());
				else player.setPlayerListName(ranks.getChatColor() + player.getName().substring(0, 14));
				player.setCustomName(ranks.getChatColor() + player.getName());
				updateAccount(accountTable, ranks);
				break;
			}
		}
	}
	
	public boolean setPlayerRank(String name, Ranks rank){
		/** Gets player **/
		if(plugin.getSqlHandler().findAccount(name) == null){
			return false;
		}
		
		/** Gets account **/
		AccountTable accountTable = plugin.getSqlHandler().findAccount(name);
		accountTable.setRank(rank.getName());
		updateAccount(accountTable, rank);
		
		if(Bukkit.getPlayer(name) != null){
			applyPrefix(Bukkit.getPlayer(name));
		}
		return true;
	}
	
	public static void updateAccount(AccountTable accountTable, Ranks ranks){
		accountTable.setStaff(ranks.isStaffed());
		accountTable.setRanked(ranks.isRanked());
		plugin.getSqlHandler().saveAccount(accountTable);
	}

	public boolean isShowPrefixes() {
		return showPrefixes;
	}

	public void setShowPrefixes(boolean showPrefixes) {
		RankManager.showPrefixes = showPrefixes;
	}
}
