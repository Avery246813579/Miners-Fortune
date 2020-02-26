package me.avery246813579.minersfortune.menus;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.menus.IMenus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VanityMenu extends Menu {

	public VanityMenu() {
		super(vanity(), "Vanity Menu");
		this.giveDefaultItems = false;
	}

	/*************************
	 * 
	 * Item Stacks
	 * 
	 ************************/

	public static ItemStack vanity() {
		ItemStack is = new ItemStack(Material.REDSTONE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW + "Vanity Menu");
		is.setItemMeta(im);

		return is;
	}

	public static ItemStack vanityInfo(Player player) {
		ItemStack is = new ItemStack(Material.CHEST);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW + "Vanity");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + "Coins: " + ChatColor.GRAY + MinersFortune.getPlugin().getSqlHandler().getAccount(player).getCoins());
		im.setLore(lore);
		is.setItemMeta(im);

		return is;
	}

	public static ItemStack globalBoosters() {
		ItemStack is = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW + "Global Boosters");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED + "Coming Soon");
		is.setItemMeta(im);

		return is;
	}

	@Override
	protected Inventory[] createInventories() {
		return null;
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		
	}
	
	@Override
	protected Inventory[] createInventories(Player player) {
		Inventory i = Bukkit.createInventory(null, 54, "Vanity Menu");
		i.setItem(9, MenuHandler.MountMenu.getItem());
		i.setItem(11, MenuHandler.MorphMenu.getItem());
		i.setItem(13, MenuHandler.GadgetMenu.getItem());
		i.setItem(15, MenuHandler.FireworkMenu.getItem());
		i.setItem(17, MenuHandler.PetMenu.getItem());
		i.setItem(29, IMenus.trailMenu.getItem());
		i.setItem(31, MenuHandler.MusicMenu.getItem());
		i.setItem(33, MenuHandler.CostumeMenu.getItem());
		i.setItem(50, globalBoosters());
		i.setItem(48, vanityInfo(player));
		return new Inventory[]{i};
	}
}
