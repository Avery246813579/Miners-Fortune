package me.avery246813579.minersfortune.commands;

import java.util.ArrayList;
import java.util.Arrays;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class GamemodeCommand extends MinerCommand {
	public GamemodeCommand() {
		super(new ArrayList<String>(Arrays.asList("gamemode", "gm")), Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player player = (Player) sender;

				if (player.getGameMode() == GameMode.ADVENTURE) {
					player.setGameMode(GameMode.SURVIVAL);
				} else if (player.getGameMode() == GameMode.SURVIVAL) {
					player.setGameMode(GameMode.CREATIVE);
				} else if (player.getGameMode() == GameMode.CREATIVE) {
					player.setGameMode(GameMode.SURVIVAL);
				}

				sendStatusMessage(sender, ChatColor.YELLOW + "Gamemode has been changed to " + player.getGameMode().toString().toLowerCase() + "!");
				return;
			} else {
				sendCommandMessage(sender, ChatColor.RED + "Console Usage: " + ChatColor.YELLOW + "/Gamemode (Type) (Player)");
				return;
			}
		} else if (args.length == 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
					player.setGameMode(GameMode.SURVIVAL);
				} else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
					player.setGameMode(GameMode.CREATIVE);
				} else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
					player.setGameMode(GameMode.ADVENTURE);
				} else {
					sendCommandMessage(sender, ChatColor.RED + "Could not find gamemode type!");
					return;
				}

				sendStatusMessage(sender, ChatColor.YELLOW + "Gamemode has been changed to " + player.getGameMode().toString().toLowerCase() + "!");
			} else {
				sendCommandMessage(sender, ChatColor.RED + "Console Usage: " + ChatColor.YELLOW + "/Gamemode (Type) (Player)");
				return;
			}
		} else {
			if (Bukkit.getPlayer(args[1]) != null) {
				Player player = Bukkit.getPlayer(args[1]);

				if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
					player.setGameMode(GameMode.SURVIVAL);
				} else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
					player.setGameMode(GameMode.CREATIVE);
				} else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
					player.setGameMode(GameMode.ADVENTURE);
				} else {
					sendCommandMessage(sender, ChatColor.RED + "Could not find gamemode type!");
					return;
				}

				sendStatusMessage(player, ChatColor.YELLOW + "Gamemode has been changed to " + player.getGameMode().toString().toLowerCase() + "!");
				sendStatusMessage(sender, ChatColor.YELLOW + player.getName() + "'s gamemode has been changed to " + player.getGameMode().toString().toLowerCase() + "!");
			} else {
				sendCommandMessage(sender, ChatColor.RED + "Player not found!");
			}
		}
	}
}
