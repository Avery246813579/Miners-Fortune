package me.avery246813579.minersfortune.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enchants {
	private static List<Integer> customEnchants = new ArrayList<Integer>();

	private static final String[] RCODE = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

	private static final int[] BVAL = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	public static ItemStack updateEnchants(ItemStack item, List<String> lore, String displayName) {
		ArrayList<String> enchants = new ArrayList<String>();
		for (Enchantment ench : item.getEnchantments().keySet()) {
			if (!isNatural(ench)) {
				enchants.add(ChatColor.GRAY + ench.getName() + " " + toRoman(((Integer) item.getEnchantments().get(ench)).intValue()));
			}
		}
		
		for(String l : lore){
			enchants.add(l);
		}
		
		
		
		ItemMeta meta = item.getItemMeta();
		meta.setLore(enchants);
		meta.setDisplayName(displayName);
		item.setItemMeta(meta);
		return item;
	}

	@SuppressWarnings("deprecation")
	private static boolean isNatural(Enchantment ench) {
		if (customEnchants.contains(Integer.valueOf(ench.getId())))
			return false;
		return true;
	}

	private static String toRoman(int binary) {
		if ((binary <= 0) || (binary >= 4000)) {
			throw new NumberFormatException("Value outside roman numeral range.");
		}
		String roman = "";
		for (int i = 0; i < RCODE.length; i++) {
			while (binary >= BVAL[i]) {
				binary -= BVAL[i];
				roman = roman + RCODE[i];
			}
		}
		return roman;
	}
}
