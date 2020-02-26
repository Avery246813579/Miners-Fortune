package me.avery246813579.minersfortune.util;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;

public class MessageUtil {
	public static void sendMessage(String prefix, Player player, String message){
		player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + prefix + " >> " + ChatColor.GRAY + message);
	}
}
