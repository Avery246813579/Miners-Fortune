package me.avery246813579.minersfortune.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FireworkMenu extends Menu{

	public FireworkMenu() {
		super(createItem(Material.FIREWORK, ChatColor.YELLOW + "Fireworks", new String[]{ChatColor.GRAY + "Click to access"}), "Firework Menu");
	}

	@Override
	protected Inventory[] createInventories() {
		return new Inventory[]{Bukkit.createInventory(null, 54, "Firework Menu")};
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
