package me.avery246813579.minersfortune.core.entities.vanity.mounts;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Variant;

import me.avery246813579.minersfortune.core.entities.vanity.IVanityEntity;
import me.avery246813579.minersfortune.core.entities.vanity.types.IHorse;

public class HorseMount extends IVanityEntity {
	public HorseMount(Player player) {
		super(new IHorse(ChatColor.YELLOW + player.getName() + "'s Mount", Variant.HORSE), player);
	}

	public HorseMount(Player player, String name) {
		super(new IHorse(ChatColor.translateAlternateColorCodes('&', name), Variant.HORSE), player);
	}
}
