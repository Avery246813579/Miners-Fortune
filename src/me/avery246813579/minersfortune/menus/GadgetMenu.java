package me.avery246813579.minersfortune.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GadgetMenu extends Menu {
	public GadgetMenu() {
		super(createItem(Material.BREWING_STAND_ITEM, ChatColor.YELLOW + "Gadgets", new String[] { ChatColor.GRAY + "Click to access" }), "Gadget Menu");
	}

	@Override
	protected Inventory[] createInventories() {
		return new Inventory[]{Bukkit.createInventory(null, 54, "Gadget Menu")};
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
	}
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
