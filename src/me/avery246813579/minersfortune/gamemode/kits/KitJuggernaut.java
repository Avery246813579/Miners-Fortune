package me.avery246813579.minersfortune.gamemode.kits;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.avery246813579.minersfortune.gamemode.GameKit;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.gamemode.kits.perks.PerkHandler;
import me.avery246813579.minersfortune.ranks.Ranks;

public class KitJuggernaut extends GameKit{
	public KitJuggernaut() {
		super("Juggernaut", 0, Ranks.DEFAULT, "A huge, powerful and overwhelming soldier.", Material.DIAMOND_CHESTPLATE);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK(ChatColor.YELLOW + "Juggernaut's Sword", Material.STONE_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY + "Sword of the most", ChatColor.GRAY + "powerful soldiers."}, 1));
		return items;
	}

	@Override
	public List<GamePerk> returnPerks() {
		List<GamePerk> perks = new ArrayList<GamePerk>();
		perks.add(PerkHandler.potionPerk);
		return perks;
	}

	@Override
	public ItemStack[] returnArmor() {
		ItemStack[] armor = new ItemStack[4];
		armor[0] = new ItemStack(Material.DIAMOND_BOOTS);
		armor[1] = new ItemStack(Material.DIAMOND_LEGGINGS);
		armor[2] = new ItemStack(Material.DIAMOND_CHESTPLATE);
		armor[3] = new ItemStack(Material.DIAMOND_HELMET);
		return armor;
	}
}
