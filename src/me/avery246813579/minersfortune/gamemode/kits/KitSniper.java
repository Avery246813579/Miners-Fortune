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

public class KitSniper extends GameKit{
	public KitSniper() {
		super("Sniper", 0, Ranks.DEFAULT, "Skilled archer with adept abilities.", Material.BOW);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK(ChatColor.YELLOW + "Sniper's Bow", Material.BOW, ItemType.Weapon, new String[]{ChatColor.GRAY + "A archer's bow"}, 1, new Enchantment[]{Enchantment.ARROW_DAMAGE, Enchantment.ARROW_INFINITE}, new int[]{5, 1}));
		items.add(createItemStacK(ChatColor.YELLOW + "Sniper's Arrow", Material.ARROW, ItemType.Weapon, new String[]{ChatColor.GRAY + "A archer's arrow"}, 1));
		return items;
	}

	@Override
	public List<GamePerk> returnPerks() {
		return null;
	}

	@Override
	public ItemStack[] returnArmor() {
		ItemStack[] armor = new ItemStack[4];
		armor[0] = new ItemStack(Material.CHAINMAIL_BOOTS);
		armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		armor[2] = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		armor[3] = new ItemStack(Material.CHAINMAIL_HELMET);
		return armor;
	}
}
