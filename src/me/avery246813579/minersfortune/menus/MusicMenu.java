package me.avery246813579.minersfortune.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MusicMenu extends Menu {
	public MusicMenu() {
		super(createItem(Material.RECORD_10, ChatColor.YELLOW + "Music", new String[] { ChatColor.GRAY + "Click to access" }), "Music Menu");
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory inventory = Bukkit.createInventory(null, 54, "Music Menu");
		inventory.setItem(10, createItem(Material.RECORD_10, ChatColor.YELLOW + "Ward", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(11, createItem(Material.RECORD_11, ChatColor.YELLOW + "Record 11", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(12, createItem(Material.RECORD_12, ChatColor.YELLOW + "Wait", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(13, createItem(Material.RECORD_3, ChatColor.YELLOW + "Blocks", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(14, createItem(Material.RECORD_4, ChatColor.YELLOW + "Chirp", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(15, createItem(Material.RECORD_5, ChatColor.YELLOW + "Far", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(16, createItem(Material.RECORD_6, ChatColor.YELLOW + "Mall", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(19, createItem(Material.RECORD_7, ChatColor.YELLOW + "Mellohi", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(20, createItem(Material.RECORD_8, ChatColor.YELLOW + "Stal", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(21, createItem(Material.RECORD_9, ChatColor.YELLOW + "Strad", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(22, createItem(Material.GOLD_RECORD, ChatColor.YELLOW + "13", new String[] { ChatColor.GRAY + "Click to play music" }));
		inventory.setItem(23, createItem(Material.GREEN_RECORD, ChatColor.YELLOW + "Cat", new String[] { ChatColor.GRAY + "Click to play music" }));

		return new Inventory[] { inventory };
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void checkItem(Player player, ItemStack item) {
		if (item.getType() == Material.RECORD_10) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.AIR);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_10);
		} else if (item.getType() == Material.RECORD_11) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_11);
		} else if (item.getType() == Material.RECORD_12) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_12);
		} else if (item.getType() == Material.RECORD_3) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_3);
		} else if (item.getType() == Material.RECORD_4) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_4);
		} else if (item.getType() == Material.RECORD_5) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_5);
		} else if (item.getType() == Material.RECORD_6) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_6);
		} else if (item.getType() == Material.RECORD_7) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_7);
		} else if (item.getType() == Material.RECORD_8) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_8);
		} else if (item.getType() == Material.RECORD_9) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.RECORD_9);
		} else if (item.getType() == Material.GOLD_RECORD) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.GOLD_RECORD);
		} else if (item.getType() == Material.GREEN_RECORD) {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, Material.GREEN_RECORD);
		} else {
			player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 0);
			return;
		}

		sendMessae(player, ChatColor.YELLOW + "Now playing " + item.getItemMeta().getDisplayName() + "!");
		player.closeInventory();
	}
	
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
