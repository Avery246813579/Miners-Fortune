package me.avery246813579.minersfortune.gamemode.kits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.avery246813579.minersfortune.gamemode.GameKit;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.gamemode.kits.perks.PerkHandler;
import me.avery246813579.minersfortune.ranks.Ranks;

public class KitRewind extends GameKit{
	public KitRewind() {
		super("Rewind", 500, Ranks.DEFAULT, "A fighter who can shift time.", Material.WATCH);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK("Rewind's Sword", Material.IRON_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY +"A sword for the great"}, 1, Enchantment.DAMAGE_ALL, 1));
		items.add(createItemStacK("Rewind's Watch", Material.WATCH, ItemType.Tool, new String[]{ChatColor.GRAY + "Used to travel back in time"}, 1));
		items.add(createItemStacK("Fuse", Material.BLAZE_POWDER, ItemType.Tool, new String[]{ChatColor.GRAY + "Used to plant and defuse bombs"}, 1));
		return items;
	}

	@Override
	public List<GamePerk> returnPerks() {
		return new ArrayList<GamePerk>(Arrays.asList(PerkHandler.rewindWatch));
	}

	@Override
	public ItemStack[] returnArmor() {
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.IRON_HELMET);
		armor[2] = createItemStacK("Rewind's Chestplate", Material.IRON_CHESTPLATE, ItemType.Wearable, new String[]{""}, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		armor[1] = new ItemStack(Material.IRON_LEGGINGS);
		armor[0] = new ItemStack(Material.IRON_BOOTS);
		return armor;
	}

}
