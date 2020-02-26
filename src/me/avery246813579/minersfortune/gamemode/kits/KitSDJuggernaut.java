package me.avery246813579.minersfortune.gamemode.kits;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.gamemode.GameKit;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.gamemode.kits.perks.PerkHandler;
import me.avery246813579.minersfortune.ranks.Ranks;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class KitSDJuggernaut extends GameKit{
	public KitSDJuggernaut() {
		super("Juggernaut", 500, Ranks.DEFAULT, "A huge, powerful and overwhelming soldier.", Material.DIAMOND_CHESTPLATE);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK(ChatColor.YELLOW + "Juggernaut's Sword", Material.STONE_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY + "Sword of the most", ChatColor.GRAY + "powerful soldiers."}, 1));
		items.add(createItemStacK("Fuse", Material.BLAZE_POWDER, ItemType.Tool, new String[]{ChatColor.GRAY + "Used to plant and defuse bombs"}, 1));
		return items;
	}

	@Override
	public List<GamePerk> returnPerks() {
		List<GamePerk> perks = new ArrayList<GamePerk>();
		perks.add(PerkHandler.juggernautDamage);
		return perks;
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
