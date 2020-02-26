package me.avery246813579.minersfortune.gamemode.kits;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.avery246813579.minersfortune.gamemode.GameKit;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.ranks.Ranks;

public class KitWarrior extends GameKit{
	public KitWarrior() {
		super("Warrior", 0, Ranks.DEFAULT, "A brute soldier on the font lines of a battle.", Material.IRON_SWORD);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK(ChatColor.YELLOW + "Warrior's Sword", Material.IRON_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY + "A brute's sword"}, 1, Enchantment.DAMAGE_ALL, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "Warrior's Apples", Material.GOLDEN_APPLE, ItemType.Consumable, new String[]{ChatColor.GRAY + "A healthy meal"}, 2));
		return items;
	}

	@Override
	public List<GamePerk> returnPerks() {
		return null;
	}

	@Override
	public ItemStack[] returnArmor() {
		ItemStack[] armor = new ItemStack[4];
		armor[0] = new ItemStack(Material.IRON_BOOTS);
		armor[1] = new ItemStack(Material.IRON_LEGGINGS);
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armor[3] = new ItemStack(Material.IRON_HELMET);
		return armor;
	}

}
