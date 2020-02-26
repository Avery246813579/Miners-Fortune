package me.avery246813579.minersfortune.menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Menu implements Listener {
	protected boolean giveDefaultItems = true;
	private Map<Player, Integer> inventoryPlace = new HashMap<Player, Integer>();
	private Inventory[] inventories;
	private String menuName;
	private ItemStack item;

	public Menu(ItemStack item, String menuName) {
		this.item = item;
		this.menuName = menuName;
		this.inventories = createInventories();

		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	protected abstract Inventory[] createInventories();

	protected abstract Inventory[] createInventories(Player player);

	protected abstract void checkItem(Player player, ItemStack item);

	public void openMenu(Player player, int inventory) {
		if (createInventories(player) == null) {
			inventories = createInventories();
		} else {
			inventories = createInventories(player);
		}
		
		Inventory[] playerInventory = inventories.clone();
		if (giveDefaultItems) {
			playerInventory[inventory].setItem(inventories[inventory].getSize() - 14, home());
		}
		if ((inventory + 1) < inventories.length && giveDefaultItems) {
			playerInventory[inventory].setItem(inventories[inventory].getSize() - 12, nextPage());
		}
		if (inventories.length <= (inventory + 1) && inventories.length > 1 && giveDefaultItems) {
			playerInventory[inventory].setItem(inventories[inventory].getSize() - 16, lastPage());
		}

		player.openInventory(playerInventory[inventory]);
		inventoryPlace.put(player, inventory);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() == null)
			return;
		if (!event.getItem().isSimilar(item))
			return;
		if (event.getAction() == Action.PHYSICAL)
			return;
		if (!MenuHandler.isUseVanity())
			sendMessae(event.getPlayer(), ChatColor.RED + "Vanity menu is disabled at this time!");

		openMenu(event.getPlayer(), 0);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getCurrentItem() == null)
			return;

		Player player = (Player) event.getWhoClicked();
		if (event.getCurrentItem().isSimilar(item))
			openMenu(player, 0);
		if (!MenuHandler.isUseVanity())
			sendMessae(player, ChatColor.RED + "Vanity menu is disabled at this time!");

		if (!event.getInventory().getTitle().equalsIgnoreCase(menuName))
			return;
		if (event.getCurrentItem().isSimilar(nextPage())) {
			event.setCancelled(true);
			openMenu(player, 1);
		}
		if (event.getCurrentItem().isSimilar(lastPage())) {
			event.setCancelled(true);
			openMenu(player, 0);
		}
		if (event.getCurrentItem().isSimilar(home())) {
			event.setCancelled(true);
			MenuHandler.vanityMenu.openMenu(player, 0);
		}

		event.setCancelled(true);
		checkItem(player, event.getCurrentItem());
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (inventoryPlace.containsKey(event.getPlayer())) {
			inventoryPlace.remove(event.getPlayer());
		}
	}

	public boolean hasRank(Player player, Ranks ranks) {
		Ranks playerRank = RankManager.findPlayerRank(player);

		if (ranks.getDonationNumber() <= playerRank.getDonationNumber() || playerRank.isStaffed()) {
			return true;
		} else {
			sendMessae(player, ChatColor.RED + "This requires Rank [" + ranks.getChatColor() + ranks.getName() + ChatColor.RED + "]");
			return false;
		}
	}

	public void sendMessae(Player player, String message) {
		player.sendMessage(ChatColor.GREEN + "Vanity >> " + ChatColor.GRAY + message);
	}

	public ItemStack nextPage() {
		return createItem(Material.ARROW, ChatColor.GREEN + "Next Page", new String[] { ChatColor.YELLOW + "Click to advance" });
	}

	public ItemStack lastPage() {
		return createItem(Material.ARROW, ChatColor.GREEN + "Last Page", new String[] { ChatColor.YELLOW + "Click to advance" });
	}

	public ItemStack home() {
		return createItem(Material.BED, ChatColor.GREEN + "Go Back ➜", new String[] { ChatColor.YELLOW + "Click to advance" });
	}

	public static ItemStack createItem(ItemStack itemStack, String name, String[] lore) {
		List<String> stringLore = new ArrayList<String>();
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		for (String loreString : lore) {
			stringLore.add(loreString);
		}
		itemMeta.setLore(stringLore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static ItemStack createItem(Material material, String name, String[] lore) {
		List<String> stringLore = new ArrayList<String>();
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		for (String loreString : lore) {
			stringLore.add(loreString);
		}
		itemMeta.setLore(stringLore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack getItem() {
		return item;
	}

	public String menuName() {
		return menuName;
	}

	public Inventory[] getInventories() {
		return inventories;
	}
}
