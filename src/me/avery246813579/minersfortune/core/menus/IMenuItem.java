package me.avery246813579.minersfortune.core.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.avery246813579.minersfortune.core.itemstack.IItemStack;
import me.avery246813579.minersfortune.core.perk.IPerk;
import me.avery246813579.minersfortune.core.perk.IPerkHandler;
import me.avery246813579.minersfortune.ranks.Ranks;

public abstract class IMenuItem {
	private IPerk iPerk;
	private int inventory;
	private int location;
	private IItemStack itemStack;
	private boolean confirm = true;

	public IMenuItem(int inventory, int location, IItemStack iItemStack) {
		this.inventory = inventory;
		this.location = location;
		this.itemStack = iItemStack;
	}
	
	public IMenuItem(int inventory, int location, IItemStack iItemStack, boolean confirm) {
		this.inventory = inventory;
		this.location = location;
		this.itemStack = iItemStack;
		this.confirm = confirm;
	}

	public IMenuItem(IPerk iPerk, int inventory, int location, IItemStack iItemStack) {
		this.iPerk = iPerk;
		this.inventory = inventory;
		this.location = location;
		this.itemStack = iItemStack;
	}

	public IMenuItem(IPerk iPerk, int inventory, int location, IItemStack iItemStack, boolean confirm) {
		this.iPerk = iPerk;
		this.inventory = inventory;
		this.location = location;
		this.itemStack = iItemStack;
		this.confirm = confirm;
	}

	public ItemStack getItem(Player player) {
		List<String> stringLore = new ArrayList<String>();
		ItemStack item = itemStack.getItem();

		ItemMeta itemMeta = item.getItemMeta();
		stringLore = itemMeta.getLore();
		if (iPerk != null) {
			if (IPerkHandler.hasPerk(player, iPerk.getId())) {
				itemMeta.setDisplayName(ChatColor.YELLOW + itemMeta.getDisplayName());
			} else {
				itemMeta.setDisplayName(ChatColor.RED + itemMeta.getDisplayName());
			}
		}

		if (iPerk != null) {
			if (iPerk.getFreeFor() != null) {
				if (iPerk.getFreeFor() == Ranks.DEFAULT) {
					itemMeta.setLore(stringLore);
					item.setItemMeta(itemMeta);
					return item;
				}
			}
		}

		if (iPerk != null) {
			if (IPerkHandler.hasPerk(player, iPerk.getId())) {
				itemMeta.setLore(stringLore);
				item.setItemMeta(itemMeta);
				return item;
			}
		}

		if (iPerk != null) {
			stringLore.add("");
			if (iPerk.getFreeFor() != null) {
				stringLore.add(ChatColor.GRAY + "Exclusive: " + iPerk.getPrice());
			} else {
				stringLore.add(ChatColor.GRAY + "Cost: " + iPerk.getPrice());
			}
		}

		itemMeta.setLore(stringLore);
		item.setItemMeta(itemMeta);
		return item;
	}

	protected abstract void action(Player player);

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public IPerk getiPerk() {
		return iPerk;
	}

	public void setiPerk(IPerk iPerk) {
		this.iPerk = iPerk;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

}
