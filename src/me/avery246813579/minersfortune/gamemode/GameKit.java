package me.avery246813579.minersfortune.gamemode;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.util.Enchants;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class GameKit {
	private String displayName;
	private String description;
	private List<ItemStack> items = new ArrayList<ItemStack>();
	private List<GamePerk> perks = new ArrayList<GamePerk>();
	private ItemStack[] armor = new ItemStack[4];
	private Material displayItem;
	private Ranks canUse;
	private int cost;

	public GameKit(String displayName, int cost, Ranks canUse, String description, Material displayItem) {
		this.displayName = displayName;
		this.items = returnItems();
		this.perks = returnPerks();
		this.armor = returnArmor();
		this.cost = cost;
		this.canUse = canUse;
		this.description = description;
		this.displayItem = displayItem;
	}

	public abstract List<ItemStack> returnItems();

	public abstract List<GamePerk> returnPerks();

	public abstract ItemStack[] returnArmor();

	public void giveKit(Player player) {
		for (ItemStack itemStack : items) {
			player.getInventory().addItem(itemStack);
		}

		player.getInventory().setArmorContents(armor);

		if (perks != null) {
			for (GamePerk gamePerk : perks) {
				gamePerk.giveOnSpawn(player);
			}
		}
	}

	public ItemStack createItemStacK(String name, Material material, ItemType itemType, String[] stringLore, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());

		for (String s : stringLore) {
			lore.add(s);
		}

		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}

	public ItemStack createItemStacK(String name, ItemStack itemStack, ItemType itemType, String[] stringLore, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());

		for (String s : stringLore) {
			lore.add(s);
		}

		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}

	public ItemStack createItemStacK(String name, Material material, ItemType itemType, String[] stringLore, int amount, Enchantment enchantment, int level) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());

		for (String s : stringLore) {
			lore.add(s);
		}

		itemStack.addUnsafeEnchantment(enchantment, level);

		itemStack = Enchants.updateEnchants(itemStack, lore, name);
		return itemStack;
	}

	public ItemStack createItemStacK(String name, Material material, ItemType itemType, String[] stringLore, int amount, Enchantment[] enchantments, int[] levels) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());

		for (String s : stringLore) {
			lore.add(s);
		}

		int i = 0;
		for (Enchantment enchantment : enchantments) {
			int level = levels[i];
			itemStack.addUnsafeEnchantment(enchantment, level);
			i++;
		}
		
		itemStack = Enchants.updateEnchants(itemStack, lore, name);

		return itemStack;
	}


	public enum ItemType {
		Consumable, Material, Craftable, Placeable, Wearable, Weapon, Potion, Tool;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<ItemStack> getItems() {
		return items;
	}

	public void setItems(List<ItemStack> items) {
		this.items = items;
	}

	public ItemStack[] getArmor() {
		return armor;
	}

	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}

	public List<GamePerk> getPerks() {
		return perks;
	}

	public void setPerks(List<GamePerk> perks) {
		this.perks = perks;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Ranks getCanUse() {
		return canUse;
	}

	public void setCanUse(Ranks canUse) {
		this.canUse = canUse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Material getDisplayItem() {
		return displayItem;
	}

	public void setDisplayItem(Material displayItem) {
		this.displayItem = displayItem;
	}
}
