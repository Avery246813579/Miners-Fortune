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

public class KitShortbow extends GameKit{
	public KitShortbow() {
		super("Shortbow", 0, Ranks.DEFAULT, "A short ranged assassin.", Material.BOW);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK("Shortbow's Bow", Material.BOW, ItemType.Weapon, new String[]{ChatColor.GRAY +"A bow made by the greattest"}, 1, new Enchantment[]{Enchantment.ARROW_DAMAGE, Enchantment.ARROW_INFINITE}, new int[]{3, 1}));
		items.add(createItemStacK("Shortbow's Dagger", Material.STONE_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY + "A self defence item", ChatColor.GRAY + "for close range"}, 1, new Enchantment[]{Enchantment.DAMAGE_ALL}, new int[]{1}));
		items.add(createItemStacK("Shortbow's Arrow", Material.ARROW, ItemType.Weapon, new String[]{ChatColor.GRAY + "A tool for the Shortbow"}, 1));
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
