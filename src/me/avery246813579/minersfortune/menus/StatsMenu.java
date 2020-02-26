package me.avery246813579.minersfortune.menus;

import java.util.ArrayList;
import java.util.Arrays;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.sql.tables.ArcherGamesTable;
import me.avery246813579.minersfortune.sql.tables.DominationTable;
import me.avery246813579.minersfortune.sql.tables.GlobalStatsTable;
import me.avery246813579.minersfortune.sql.tables.SearchAndDestroyTable;
import me.avery246813579.minersfortune.sql.tables.SurvivalGamesTable;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class StatsMenu extends Menu{
	public StatsMenu() {
		super(createItem(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), ChatColor.YELLOW + "Stat Menu", new String[]{ChatColor.GRAY + "Click to access"}), "Stat Menu");
		this.giveDefaultItems = false;
	}

	@Override
	protected Inventory[] createInventories() {
		return null;
	}

	@Override
	protected Inventory[] createInventories(Player player) {
		Inventory statInventory = Bukkit.createInventory(null, 45, "Stat Menu");
		int player_id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);
		statInventory.setItem(13, getPlayerSkull(player, player_id));
		ArcherGamesTable archerGamesTable = MinersFortune.getPlugin().getSqlHandler().getArcherGamesStats(player_id);
		statInventory.setItem(28, createItem(Material.BOW, ChatColor.YELLOW + "Archer Games", new String[]{ChatColor.GREEN + "Wins: " + ChatColor.YELLOW + archerGamesTable.getWins(),
				ChatColor.GREEN + "Losses: " + ChatColor.YELLOW + archerGamesTable.getLosses(),
				ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + archerGamesTable.getKills(),
				ChatColor.GREEN + "Deaths: " + ChatColor.YELLOW + archerGamesTable.getDeaths(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + archerGamesTable.getCredits_earned()}));
		
		SurvivalGamesTable survivalGamesTable = MinersFortune.getPlugin().getSqlHandler().getSurvivalGamesStats(player_id);
		statInventory.setItem(30, createItem(Material.APPLE, ChatColor.YELLOW + "Survival Games", new String[]{ChatColor.GREEN + "Wins: " + ChatColor.YELLOW + survivalGamesTable.getWins(),
				ChatColor.GREEN + "Losses: " + ChatColor.YELLOW + survivalGamesTable.getLosses(),
				ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + survivalGamesTable.getKills(),
				ChatColor.GREEN + "Deaths: " + ChatColor.YELLOW + survivalGamesTable.getDeaths(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + survivalGamesTable.getCredits_earned()}));
		
		SearchAndDestroyTable searchAndDestroyTable = MinersFortune.getPlugin().getSqlHandler().getSearchAndDestroyStats(player_id);
		statInventory.setItem(32, createItem(Material.BLAZE_POWDER, ChatColor.YELLOW + "Search & Destroy", new String[]{ChatColor.GREEN + "Wins: " + ChatColor.YELLOW + searchAndDestroyTable.getWins(),
				ChatColor.GREEN + "Losses: " + ChatColor.YELLOW + searchAndDestroyTable.getLosses(),
				ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + searchAndDestroyTable.getKills(),
				ChatColor.GREEN + "Deaths: " + ChatColor.YELLOW + searchAndDestroyTable.getDeaths(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + searchAndDestroyTable.getCredits_earned(), 
				ChatColor.GREEN + "Bomb Arms: " + ChatColor.YELLOW + searchAndDestroyTable.getBomb_arms(),
				ChatColor.GREEN + "Bomb Defuses: " + ChatColor.YELLOW + searchAndDestroyTable.getBomb_defuses()}));
		
		DominationTable dominationTable = MinersFortune.getPlugin().getSqlHandler().getDominationStats(player_id);
		statInventory.setItem(34, createItem(Material.STAINED_GLASS, ChatColor.YELLOW + "Domination", new String[]{ChatColor.GREEN + "Wins: " + ChatColor.YELLOW + dominationTable.getWins(),
				ChatColor.GREEN + "Losses: " + ChatColor.YELLOW + dominationTable.getLosses(),
				ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + dominationTable.getKills(),
				ChatColor.GREEN + "Deaths: " + ChatColor.YELLOW + dominationTable.getDeaths(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + dominationTable.getCredits_earned(), 
				ChatColor.GREEN + "Points Captured: " + ChatColor.YELLOW + dominationTable.getPoints_captured()}));

		return new Inventory[]{statInventory};
	}
	
	public void openPlayerInventory(Player onlinePlayer, Player player) {
		Inventory statInventory = Bukkit.createInventory(null, 45, "Stat Menu");
		int player_id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);
		statInventory.setItem(13, getPlayerSkull(player, player_id));
		ArcherGamesTable archerGamesTable = MinersFortune.getPlugin().getSqlHandler().getArcherGamesStats(player_id);
		statInventory.setItem(28, createItem(Material.BOW, ChatColor.YELLOW + "Archer Games", new String[]{ChatColor.GREEN + "Wins: " + ChatColor.YELLOW + archerGamesTable.getWins(),
				ChatColor.GREEN + "Losses: " + ChatColor.YELLOW + archerGamesTable.getLosses(),
				ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + archerGamesTable.getKills(),
				ChatColor.GREEN + "Deaths: " + ChatColor.YELLOW + archerGamesTable.getDeaths(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + archerGamesTable.getCredits_earned()}));
		
		SurvivalGamesTable survivalGamesTable = MinersFortune.getPlugin().getSqlHandler().getSurvivalGamesStats(player_id);
		statInventory.setItem(30, createItem(Material.APPLE, ChatColor.YELLOW + "Survival Games", new String[]{ChatColor.GREEN + "Wins: " + ChatColor.YELLOW + survivalGamesTable.getWins(),
				ChatColor.GREEN + "Losses: " + ChatColor.YELLOW + survivalGamesTable.getLosses(),
				ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + survivalGamesTable.getKills(),
				ChatColor.GREEN + "Deaths: " + ChatColor.YELLOW + survivalGamesTable.getDeaths(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + survivalGamesTable.getCredits_earned()}));
		
		SearchAndDestroyTable searchAndDestroyTable = MinersFortune.getPlugin().getSqlHandler().getSearchAndDestroyStats(player_id);
		statInventory.setItem(32, createItem(Material.BLAZE_POWDER, ChatColor.YELLOW + "Search & Destroy", new String[]{ChatColor.GREEN + "Wins: " + ChatColor.YELLOW + searchAndDestroyTable.getWins(),
				ChatColor.GREEN + "Losses: " + ChatColor.YELLOW + searchAndDestroyTable.getLosses(),
				ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + searchAndDestroyTable.getKills(),
				ChatColor.GREEN + "Deaths: " + ChatColor.YELLOW + searchAndDestroyTable.getDeaths(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + searchAndDestroyTable.getCredits_earned(), 
				ChatColor.GREEN + "Bomb Arms: " + ChatColor.YELLOW + searchAndDestroyTable.getBomb_arms(),
				ChatColor.GREEN + "Bomb Defuses: " + ChatColor.YELLOW + searchAndDestroyTable.getBomb_defuses()}));
		
		DominationTable dominationTable = MinersFortune.getPlugin().getSqlHandler().getDominationStats(player_id);
		statInventory.setItem(34, createItem(Material.STAINED_GLASS, ChatColor.YELLOW + "Domination", new String[]{ChatColor.GREEN + "Wins: " + ChatColor.YELLOW + dominationTable.getWins(),
				ChatColor.GREEN + "Losses: " + ChatColor.YELLOW + dominationTable.getLosses(),
				ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + dominationTable.getKills(),
				ChatColor.GREEN + "Deaths: " + ChatColor.YELLOW + dominationTable.getDeaths(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + dominationTable.getCredits_earned(), 
				ChatColor.GREEN + "Points Captured: " + ChatColor.YELLOW + dominationTable.getPoints_captured()}));
		onlinePlayer.openInventory(statInventory);
		
	}


	@Override
	protected void checkItem(Player player, ItemStack item) {
	}
	
	public ItemStack getPlayerSkull(Player player, int player_id){
		GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(player_id);
		ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
		skullMeta.setDisplayName(ChatColor.YELLOW + "Global Stats");
		skullMeta.setOwner(player.getName());
		skullMeta.setLore(new ArrayList<String>(Arrays.asList(ChatColor.GREEN + "Time Online: " + ChatColor.YELLOW + globalStatsTable.getTimeOnline(),
				ChatColor.GREEN + "Games Played: " + ChatColor.YELLOW + globalStatsTable.getGames_played(),
				ChatColor.GREEN + "Credits Earned: " + ChatColor.YELLOW + globalStatsTable.getCredits_earned())));
		itemStack.setItemMeta(skullMeta);
		return itemStack;
	}
}
