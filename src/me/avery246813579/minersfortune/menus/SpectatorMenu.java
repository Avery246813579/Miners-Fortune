package me.avery246813579.minersfortune.menus;

import me.avery246813579.minersfortune.gamemode.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SpectatorMenu extends Menu{
	public SpectatorMenu() {
		super(createItem(Material.COMPASS, ChatColor.YELLOW + "Spectator Menu", new String[]{ChatColor.GRAY + "Click to access"}), "Spectator Menu");
		this.giveDefaultItems = false;
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory inventory = Bukkit.createInventory(null, 27, "Spectator Menu");
		inventory.setItem(11, MenuHandler.playerMenu.getItem());
		inventory.setItem(15, createItem(Material.NETHER_STAR, "Teleport to Midpoint", new String[]{"Click to access"}));
		return new Inventory[]{inventory};
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		if(item.getType() == Material.NETHER_STAR){
			player.teleport(GameManager.getMapTable().getMp());
			PlayerMenu.sendSpecMessage(player, ChatColor.YELLOW + "You have teleported to the maps midpoint!");
		}
	}
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
