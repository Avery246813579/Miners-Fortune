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

public class KitTrooper extends GameKit{
	public KitTrooper() {
		super("Trooper", 0, Ranks.DEFAULT, "The all around beast at fighting", Material.GOLDEN_APPLE);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK("Trooper's Sword", Material.IRON_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY +"A sword for the great"}, 1, Enchantment.DAMAGE_ALL, 1));
		items.add(createItemStacK("Trooper's Apples", Material.GOLDEN_APPLE, ItemType.Consumable, new String[]{ChatColor.GRAY + "Food for the mighty fighter"}, 5));
		items.add(createItemStacK("Trooper's Pickaxe", Material.STONE_PICKAXE, ItemType.Tool, new String[]{ChatColor.GRAY + "Used for destroying certain blocks"}, 1));
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
		armor[3] = new ItemStack(Material.IRON_HELMET);
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armor[1] = new ItemStack(Material.IRON_LEGGINGS);
		armor[0] = new ItemStack(Material.IRON_BOOTS);
		return armor;
	}
}
