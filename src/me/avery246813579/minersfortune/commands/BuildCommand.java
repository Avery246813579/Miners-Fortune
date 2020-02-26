package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;

public class BuildCommand extends MinerCommand {
	public BuildCommand() {
		super("build", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0 || args.length == 1) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Build (Add|Remove|Set) (Player|Boolean)");
			return;
		}

		if (args[0].equalsIgnoreCase("add")) {
			Player targetPlayer = Bukkit.getPlayer(args[1]);

			if (targetPlayer != null) {
				MinersFortune.getPlayersCanBuild().add(targetPlayer);
				sendCommandMessage(sender, ChatColor.YELLOW + "You have added " + args[1] + " to be able to build!");
			} else {
				sendCommandMessage(sender, ChatColor.RED + "Target player not found!");
			}
		} else if (args[0].equalsIgnoreCase("set")) {
			try {
				MinersFortune.setCanBuild(Boolean.parseBoolean(args[1]));
			} catch (Exception ex) {
				sendCommandMessage(sender, ChatColor.RED + "Could not parse boolean!");
			}
		} else if (args[0].equalsIgnoreCase("remove")) {
			Player targetPlayer = Bukkit.getPlayer(args[0]);

			if (targetPlayer != null) {
				if (MinersFortune.getPlayersCanBuild().contains(targetPlayer)) {
					MinersFortune.getPlayersCanBuild().add(targetPlayer);
					sendCommandMessage(sender, ChatColor.YELLOW + "You have removed " + args[1] + " to be able to build!");
				} else {
					sendCommandMessage(sender, ChatColor.RED + "This player is not in can build!");
				}
			} else {
				sendCommandMessage(sender, ChatColor.RED + "Target player not found!");
			}
		} else {
			sendCommandMessage(sender, ChatColor.RED + "Argument not found!");
		}
	}
}
