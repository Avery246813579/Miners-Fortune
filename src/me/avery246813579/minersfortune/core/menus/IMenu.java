package me.avery246813579.minersfortune.core.menus;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.perk.IPerkHandler;
import me.avery246813579.minersfortune.menus.MenuHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class IMenu implements Listener {
	protected List<IMenuItem> iMenuItems = new ArrayList<IMenuItem>();
	protected boolean giveDefaults = true;
	private ItemStack itemStack;
	private int size;
	private String name, itemUse;

	public IMenu(ItemStack itemStack, String name, int size, String itemUse) {
		this.itemStack = itemStack;
		this.name = name;
		this.size = size;
		this.itemUse = itemUse;

		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	public List<Inventory> createInventories(Player player) {
		List<Inventory> inventories = new ArrayList<Inventory>();

		for (IMenuItem iMenuItem : iMenuItems) {
			if (inventories.size() >= iMenuItem.getInventory()) {
				inventories.add(Bukkit.createInventory(null, size, name));
			}

			inventories.get(iMenuItem.getInventory()).setItem(iMenuItem.getLocation(), iMenuItem.getItem(player));
		}

		if (giveDefaults) {
			int index = 0;

			for (Inventory inventory : inventories) {
				inventory.setItem(inventory.getSize() - 14, home());

				if ((index + 1) > inventories.size()) {
					inventory.setItem(inventory.getSize() - 13, nextPage());
				}

				if (inventories.size() <= (index + 1) && inventories.size() > 1) {
					inventory.setItem(inventory.getSize() - 15, lastPage());
				}

				index++;
			}
		}

		return inventories;
	}

	public void openMenu(Player player, int inventory) {
		List<Inventory> inventories = createInventories(player);

		player.openInventory(inventories.get(inventory));
	}

	public void checkItem(Player player, ItemStack item) {
		for (IMenuItem iMenuItem : iMenuItems) {
			if (iMenuItem.getItem(player).isSimilar(item)) {
				if (iMenuItem.getiPerk() != null) {
					if (IPerkHandler.hasPerk(player, iMenuItem.getiPerk().getId())) {
						iMenuItem.action(player);

						if (iMenuItem.isConfirm()) {
							String message = itemUse.replace("{ITEM}", item.getItemMeta().getDisplayName());
							sendMessae(player, message);
						}
					} else {
						if (iMenuItem.getiPerk().getFreeFor() != null) {
							sendMessae(player, ChatColor.RED + "This requires Rank [" + iMenuItem.getiPerk().getFreeFor().getChatColor() + iMenuItem.getiPerk().getFreeFor().getName() + ChatColor.RED + "]");
						} else {
							sendMessae(player, ChatColor.RED + "Perk not owned! Perk costs " + net.md_5.bungee.api.ChatColor.GREEN + iMenuItem.getiPerk().getCost() + " coins" + ChatColor.RED + "!");
						}
					}
				}else{
					iMenuItem.action(player);
					
					if (iMenuItem.isConfirm()) {
						String message = itemUse.replace("{ITEM}", item.getItemMeta().getDisplayName());
						sendMessae(player, message);
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() == null)
			return;
		if (!event.getItem().isSimilar(itemStack))
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
		if (event.getCurrentItem().isSimilar(itemStack))
			openMenu(player, 0);
		if (!MenuHandler.isUseVanity())
			sendMessae(player, ChatColor.RED + "Vanity menu is disabled at this time!");
		if (!event.getInventory().getTitle().equalsIgnoreCase(name))
			return;
		if (event.getCurrentItem().isSimilar(nextPage())) {
			event.setCancelled(true);
			openMenu(player, 1);
		}
		if (event.getCurrentItem().isSimilar(lastPage())) {
			event.setCancelled(true);
			openMenu(player, 0);
		}
		if (event.getCurrentItem().isSimilar(home()) && giveDefaults == true) {
			event.setCancelled(true);
			MenuHandler.vanityMenu.openMenu(player, 0);
		}

		event.setCancelled(true);
		checkItem(player, event.getCurrentItem());
	}

	public void sendMessae(Player player, String message) {
		player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Vanity >> " + ChatColor.GRAY + message);
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
			stringLore.add(ChatColor.GRAY + loreString);
		}
		itemMeta.setLore(stringLore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack getItem() {
		return itemStack;
	}
}
