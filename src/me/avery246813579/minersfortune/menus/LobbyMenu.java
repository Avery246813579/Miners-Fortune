package me.avery246813579.minersfortune.menus;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbyMenu extends Menu{
	public LobbyMenu() {
		super(createItem(Material.FEATHER, ChatColor.YELLOW + "Lobby Menu", new String[]{ChatColor.GRAY + "Click to access"}), "Lobby Menu");
		this.giveDefaultItems = false;
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory inventory = Bukkit.createInventory(null, 36, "Lobby Menu");
		inventory.setItem(10, MenuHandler.kitMenu.getItem());
		inventory.setItem(12, MenuHandler.buyKitMenu.getItem());
		inventory.setItem(14, MenuHandler.teamMenu.getItem());
		inventory.setItem(16, createItem(Material.BOOK, ChatColor.YELLOW + "Help Menu", new String[]{ChatColor.GRAY + "Coming soon"}));
		return new Inventory[]{inventory};
	}

	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
	}
}
