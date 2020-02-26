package me.avery246813579.minersfortune.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.listeners.PlayerListener;
import me.avery246813579.minersfortune.ranks.Ranks;

public class MuteChatCommand extends MinerCommand {
	public MuteChatCommand() {
		super("mutechat", Ranks.HELPER);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (PlayerListener.isCanSpeak()) {
			PlayerListener.setCanSpeak(false);
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (sender instanceof Player) {
					player.sendMessage(ChatColor.GREEN + "Chat >> " + ChatColor.YELLOW + ChatColor.BOLD + player.getName() + " has muted the chat!");
				}else{
					player.sendMessage(ChatColor.GREEN + "Chat >> " + ChatColor.YELLOW + ChatColor.BOLD + "Chat has been muted!");
				}
			}
			
			sendCommandMessage(sender, ChatColor.YELLOW + "You have muted the chat.");
		} else {
			PlayerListener.setCanSpeak(true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (sender instanceof Player) {
					player.sendMessage(ChatColor.GREEN + "Chat >> " + ChatColor.YELLOW + ChatColor.BOLD + player.getName() + " has removed the muted the chat!");
				}else{
					player.sendMessage(ChatColor.GREEN + "Chat >> " + ChatColor.YELLOW + ChatColor.BOLD + "The chat mute has been removed!");
				}
			}
			
			sendCommandMessage(sender, ChatColor.YELLOW + "You have removed the chat mute.");
		}
	}
}
