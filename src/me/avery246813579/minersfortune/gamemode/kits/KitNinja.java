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

public class KitNinja extends GameKit{
	public KitNinja() {
		super("Ninja", 500, Ranks.DEFAULT, "A silent assassin! Iron and Chain armor, 6 pearls, and 1 sword.", Material.POTION);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK("Ninja's Dagger", Material.IRON_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY +"A dagger for a silent assassin!"}, 1));
		items.add(createItemStacK("Ninja's Pearls", Material.ENDER_PEARL, ItemType.Tool, new String[]{ChatColor.GRAY + "Used for a quick getaway!"}, 6));
		items.add(createItemStacK("Fuse", Material.BLAZE_POWDER, ItemType.Tool, new String[]{ChatColor.GRAY + "Used to plant and defuse bombs"}, 1));
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
		armor[0] = new ItemStack(Material.CHAINMAIL_BOOTS);
		armor[1] = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armor[3] = new ItemStack(Material.IRON_HELMET);
		return armor;
	}
}
