package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class ClearChatCommand extends MinerCommand {
	public ClearChatCommand() {
		super("clearchat", Ranks.HELPER);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		for (int i = 0; i <= 99; i++) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.sendMessage("");
			}
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (sender instanceof Player) {
				player.sendMessage(ChatColor.GREEN + "Chat >> " + ChatColor.YELLOW + ChatColor.BOLD + sender.getName() + " has cleared the chat!");
			} else {
				player.sendMessage(ChatColor.GREEN + "Chat >> " + ChatColor.YELLOW + ChatColor.BOLD + "Chat has been cleared!");
			}
		}

		sendCommandMessage(sender, ChatColor.YELLOW + "You have cleared chat!");
	}
}
