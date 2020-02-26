package me.avery246813579.minersfortune.gamemode.kits;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.avery246813579.minersfortune.gamemode.GameKit;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.gamemode.kits.perks.PerkHandler;
import me.avery246813579.minersfortune.ranks.Ranks;

public class KitGod extends GameKit{
	public KitGod() {
		super("God", 0, Ranks.DEFAULT, "A power like now other!ALL his arrows cause explosions.", Material.GOLDEN_APPLE);
	}

	@Override
	public List<ItemStack> returnItems() {
		List<ItemStack> items = new ArrayList<ItemStack>();
		items.add(createItemStacK(ChatColor.YELLOW + "God's Sword", Material.DIAMOND_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY + "Sword for the gods."}, 1, new Enchantment[]{Enchantment.DAMAGE_UNDEAD, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ARTHROPODS, Enchantment.FIRE_ASPECT}, new int[]{7, 7, 7,2}));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Bow", Material.BOW, ItemType.Weapon, new String[]{ChatColor.GRAY + "Bow for the gods."}, 1, new Enchantment[]{Enchantment.ARROW_INFINITE, Enchantment.ARROW_FIRE, Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_DAMAGE}, new int[]{1,1,2,7}));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Pick", Material.DIAMOND_PICKAXE, ItemType.Tool, new String[]{ChatColor.GRAY + "Pick for the gods."}, 1, new Enchantment[]{Enchantment.DIG_SPEED}, new int[]{5}));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Pearls", Material.ENDER_PEARL, ItemType.Tool, new String[]{ChatColor.GRAY + "Pearls for the gods."}, 2));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Speed Potion", new ItemStack(Material.POTION, 2, (byte) 8226), ItemType.Consumable, new String[]{ChatColor.GRAY + "A drink for the gods."}, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Strength Potion", new ItemStack(Material.POTION, 2, (byte) 8233), ItemType.Consumable, new String[]{ChatColor.GRAY + "A drink for the gods."}, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Apple", new ItemStack(Material.GOLDEN_APPLE, 2, (byte) 1), ItemType.Consumable, new String[]{ChatColor.GRAY + "A meal for the gods."}, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Steak", Material.COOKED_BEEF, ItemType.Consumable, new String[]{ChatColor.GRAY + "A meal for the gods."}, 10));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Compass", Material.COMPASS, ItemType.Tool, new String[]{ChatColor.GRAY + "A navigation device for the gods."}, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Arrow", Material.ARROW, ItemType.Tool, new String[]{ChatColor.GRAY + "A item for the god's bow."}, 1));
		return items;
	}

	@Override
	public List<GamePerk> returnPerks() {
		List<GamePerk> perks = new ArrayList<GamePerk>();
		perks.add(PerkHandler.arrowExplosionPerk);
		return perks;
	}

	@Override
	public ItemStack[] returnArmor() {
		ItemStack[] armor = new ItemStack[4];
		armor[0] = createItemStacK("God's Boots", Material.DIAMOND_BOOTS, ItemType.Wearable, new String[]{"Best protection for a god"}, 1, new Enchantment[]{Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FALL, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE}, new int[]{7,7,7,7,7});
		armor[1] = createItemStacK("God's Leggings", Material.DIAMOND_LEGGINGS, ItemType.Wearable, new String[]{"Best protection for a god"}, 1, new Enchantment[]{Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE}, new int[]{7,7,7,7});
		armor[2] = createItemStacK("God's Chestplate", Material.DIAMOND_CHESTPLATE, ItemType.Wearable, new String[]{"Best protection for a god"}, 1, new Enchantment[]{Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE}, new int[]{7,7,7,7});
		armor[3] = createItemStacK("God's Helmet", Material.DIAMOND_HELMET, ItemType.Wearable, new String[]{"Best protection for a god"}, 1, new Enchantment[]{Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.OXYGEN}, new int[]{7,7,7,7,7});
		return armor;
	}
}
