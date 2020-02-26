package me.avery246813579.minersfortune.menus;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CostumeMenu extends Menu{
	HelmetMenu helmetMenu;
	
	public CostumeMenu(){
		super(createItem(Material.DIAMOND_CHESTPLATE, ChatColor.YELLOW + "Costumes", new String[]{ChatColor.GRAY + "Click to access"}), "Costume Menu");
		helmetMenu = new HelmetMenu();
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory inventory = Bukkit.createInventory(null, 36, "Costume Menu");
		inventory.setItem(10, createItem(Material.GOLD_HELMET, ChatColor.YELLOW + "Helmets", new String[]{ChatColor.GRAY + "Click to access"}));
		inventory.setItem(12, createItem(Material.GOLD_HELMET, ChatColor.YELLOW + "Chestplates", new String[]{ChatColor.GRAY + "Click to access"}));
		inventory.setItem(14, createItem(Material.GOLD_HELMET, ChatColor.YELLOW + "Leggings", new String[]{ChatColor.GRAY + "Click to access"}));
		inventory.setItem(16, createItem(Material.GOLD_BOOTS, ChatColor.YELLOW + "Boots", new String[]{ChatColor.GRAY + "Click to access"}));
		return new Inventory[]{inventory};
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		if(item.getType() == Material.GOLD_HELMET){
			helmetMenu.openMenu(player, 0);
		}
	}
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
	
	public class HelmetMenu extends Menu{
		public HelmetMenu() {
			super(createItem(Material.GOLD_HELMET, ChatColor.YELLOW + "Helmet Menu", new String[]{ChatColor.GRAY + "Click to access"}), "Helmet Menu");
			this.giveDefaultItems = false;
		}

		@Override
		protected Inventory[] createInventories() {
			Inventory inventory = Bukkit.createInventory(null, 54, "Helmet Inventory");
			inventory.setItem(10, createItem(Material.CHAINMAIL_HELMET, ChatColor.YELLOW + "Chainmail Helmet", new String[] { ChatColor.GRAY + "Click to wear", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
			inventory.setItem(11, createItem(Material.LEATHER_HELMET, ChatColor.YELLOW + "Leather Helmet", new String[] { ChatColor.GRAY + "Click to wear", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
			inventory.setItem(12, createItem(Material.IRON_HELMET, ChatColor.YELLOW + "Iron Helmet", new String[] { ChatColor.GRAY + "Click to wear", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
			inventory.setItem(13, createItem(Material.GOLD_HELMET, ChatColor.YELLOW + "Chain Helmet", new String[] { ChatColor.GRAY + "Click to wear", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
			inventory.setItem(14, createItem(Material.DIAMOND_HELMET, ChatColor.YELLOW + "Chain Helmet", new String[] { ChatColor.GRAY + "Click to wear", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
			inventory.setItem(15, createItem(Material.CHAINMAIL_HELMET, ChatColor.YELLOW + "Chain Helmet", new String[] { ChatColor.GRAY + "Click to wear", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
			inventory.setItem(16, createItem(Material.CHAINMAIL_HELMET, ChatColor.YELLOW + "Chain Helmet", new String[] { ChatColor.GRAY + "Click to wear", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
			inventory.setItem(19, createItem(Material.CHAINMAIL_HELMET, ChatColor.YELLOW + "Chain Helmet", new String[] { ChatColor.GRAY + "Click to wear", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
			return new Inventory[]{inventory};
		}

		@Override
		protected Inventory[] createInventories(Player player) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void checkItem(Player player, ItemStack item) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
