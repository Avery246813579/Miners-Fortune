package me.avery246813579.minersfortune.menus;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.gamemode.GameKit;
import me.avery246813579.minersfortune.gamemode.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BuyKitMenu extends Menu {
	public BuyKitMenu() {
		super(createItem(Material.EMERALD, ChatColor.YELLOW + "Buy Kit Menu", new String[] { "Click to access" }), "Buy Kit Menu");
		this.giveDefaultItems = false;
	}

	@Override
	protected Inventory[] createInventories() {
		return null;
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		if (item.getType() == Material.WOOD_DOOR) {
			player.closeInventory();
			MenuHandler.lobbyMenu.openMenu(player, 0);
		}

		for (GameKit gameKit : GameManager.getKits()) {
			if (gameKit.getDisplayItem() == item.getType()) {
				GameManager.findPlayer(player).buyKit(gameKit);
				player.closeInventory();
			}
		}
	}

	@Override
	protected Inventory[] createInventories(Player player) {
		Inventory firstInventory = Bukkit.createInventory(null, 54, "Buy Kit Menu");
		Inventory secondInventory = Bukkit.createInventory(null, 54, "Buy Kit Menu");

		int i = 10;
		Inventory currentUsing = firstInventory;
		for (GameKit gameKit : GameManager.findPlayer(player).getNotOwnedKits()) {
			if (i == 17 || i == 26) {
				i = i + 2;
			}

			if (i == 35) {
				currentUsing = secondInventory;
				i = 10;
			}

			int wordPlace = 2;
			String currentString = "";
			List<String> lore = new ArrayList<String>();
			String[] words = gameKit.getDescription().split(" ");
			for (String word : words) {
				if (wordPlace != 5) {
					currentString = currentString + " " + word;
				} else {
					lore.add(currentString);
					currentString = word;
					wordPlace = 0;
				}

				wordPlace++;
			}

			if (lore.size() == 1) {
				currentUsing.setItem(
						i,
						createItem(gameKit.getDisplayItem(), ChatColor.YELLOW + gameKit.getDisplayName(), new String[] { ChatColor.GREEN + "Kit Name: " + ChatColor.YELLOW + gameKit.getDisplayName(), ChatColor.GREEN + "Kit Cost: " + ChatColor.YELLOW + gameKit.getCost(),
								ChatColor.GREEN + "Description: " + ChatColor.YELLOW + lore.get(0) }));
			}else if(lore.size() == 2){
				currentUsing.setItem(
						i,
						createItem(gameKit.getDisplayItem(), ChatColor.YELLOW + gameKit.getDisplayName(), new String[] { ChatColor.GREEN + "Kit Name: " + ChatColor.YELLOW + gameKit.getDisplayName(), ChatColor.GREEN + "Kit Cost: " + ChatColor.YELLOW + gameKit.getCost(),
								ChatColor.GREEN + "Description: " + ChatColor.YELLOW + lore.get(0), ChatColor.YELLOW + lore.get(1) }));
			}else if(lore.size() == 3){
				currentUsing.setItem(
						i,
						createItem(gameKit.getDisplayItem(), ChatColor.YELLOW + gameKit.getDisplayName(), new String[] { ChatColor.GREEN + "Kit Name: " + ChatColor.YELLOW + gameKit.getDisplayName(), ChatColor.GREEN + "Kit Cost: " + ChatColor.YELLOW + gameKit.getCost(),
								ChatColor.GREEN + "Description: " + ChatColor.YELLOW + lore.get(0), ChatColor.YELLOW + lore.get(1), ChatColor.YELLOW + lore.get(2) }));
			}else if(lore.size() == 4){
				currentUsing.setItem(
						i,
						createItem(gameKit.getDisplayItem(), ChatColor.YELLOW + gameKit.getDisplayName(), new String[] { ChatColor.GREEN + "Kit Name: " + ChatColor.YELLOW + gameKit.getDisplayName(), ChatColor.GREEN + "Kit Cost: " + ChatColor.YELLOW + gameKit.getCost(),
								ChatColor.GREEN + "Description: " + ChatColor.YELLOW + lore.get(0), ChatColor.YELLOW + lore.get(1), ChatColor.YELLOW + lore.get(2), ChatColor.YELLOW + lore.get(3) }));
			}else if(lore.size() == 5){
				currentUsing.setItem(
						i,
						createItem(gameKit.getDisplayItem(), ChatColor.YELLOW + gameKit.getDisplayName(), new String[] { ChatColor.GREEN + "Kit Name: " + ChatColor.YELLOW + gameKit.getDisplayName(), ChatColor.GREEN + "Kit Cost: " + ChatColor.YELLOW + gameKit.getCost(),
								ChatColor.GREEN + "Description:" + ChatColor.YELLOW + lore.get(0), ChatColor.YELLOW + lore.get(1), ChatColor.YELLOW + lore.get(2), ChatColor.YELLOW + lore.get(3), ChatColor.YELLOW + lore.get(4) }));
			}
			i++;
		}

		firstInventory.setItem(40, createItem(Material.WOOD_DOOR, ChatColor.YELLOW + "Back to Menu", new String[] { ChatColor.GRAY + "Return to lobby menu" }));
		if (secondInventory == null) {
			return new Inventory[] { firstInventory };
		}

		secondInventory.setItem(40, createItem(Material.WOOD_DOOR, ChatColor.YELLOW + "Back to Menu", new String[] { ChatColor.GRAY + "Return to lobby menu" }));
		return new Inventory[] { firstInventory, secondInventory };
	}
}
