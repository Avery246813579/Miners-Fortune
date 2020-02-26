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

public class KitLongBow extends GameKit{
	public KitLongBow() {
		super("Longbow", 0, Ranks.DEFAULT, "A long ranged assassin.", Material.BOW);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK("Longbow's Bow", Material.BOW, ItemType.Weapon, new String[]{ChatColor.GRAY +"A bow made by the greattest"}, 1, new Enchantment[]{Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_INFINITE}, new int[]{3, 1}));
		items.add(createItemStacK("Longbow's Dagger", Material.STONE_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY + "A self defence item", ChatColor.GRAY + "for close range"}, 1, new Enchantment[]{Enchantment.KNOCKBACK}, new int[]{1}));
		items.add(createItemStacK("Longbow's Arrow", Material.ARROW, ItemType.Weapon, new String[]{ChatColor.GRAY + "A tool for the longbow"}, 1));
		items.add(createItemStacK("Fuse", Material.BLAZE_POWDER, ItemType.Tool, new String[]{ChatColor.GRAY + "Used to plant and defuse bombs"}, 1));
		return items;
	}

	@Override
	public List<GamePerk> returnPerks() {
		return null;
	}

	@Override
	public ItemStack[] returnArmor() {
		ItemStack[] armor = new ItemStack[4];
		armor[3] = new ItemStack(Material.CHAINMAIL_HELMET);
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armor[1] = new ItemStack(Material.IRON_LEGGINGS);
		armor[0] = new ItemStack(Material.CHAINMAIL_BOOTS);
		return armor;
	}
}
