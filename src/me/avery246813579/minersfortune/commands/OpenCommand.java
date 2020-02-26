package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class OpenCommand extends MinerCommand {
	public OpenCommand() {
		super("open", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Open (Player)");
			return;
		} else {
			if (sender instanceof Player) {
				if (Bukkit.getPlayer(args[0]) != null) {
					Player target = Bukkit.getPlayer(args[0]);
					Player player = (Player) sender;
					
					player.openInventory(target.getInventory());
					sendStatusMessage(sender, ChatColor.YELLOW + "You opened " + target.getName() + "'s inventory!");
				} else {
					sendCommandMessage(sender, ChatColor.RED + "Player not found!");
				}
			}else{
				sendCommandMessage(sender, ChatColor.RED + "Only players can use this command!");
			}
		}
	}
}
