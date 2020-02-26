package me.avery246813579.minersfortune.gamemode.kits;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.gamemode.GameKit;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.ranks.Ranks;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class KitSurvival extends GameKit{
	public KitSurvival() {
		super("Survival", 0, Ranks.DEFAULT, "A great kit to survive the night.", Material.BREAD);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK("Survivalist's Bread", Material.BREAD, ItemType.Consumable, new String[]{ChatColor.GRAY +"Bread made from the finest wheat"}, 5));
		return items;
	}

	@Override
	public List<GamePerk> returnPerks() {
		return null;
	}

	@Override
	public ItemStack[] returnArmor() {
		ItemStack[] armor = new ItemStack[4];
		return armor;
	}
}
