package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class TphereCommand extends MinerCommand{
	public TphereCommand() {
		super("tphere", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Tphere (Player)");
		}else{
			if(Bukkit.getPlayer(args[0]) != null){
				Player player = (Player) sender;
				
				player.teleport(Bukkit.getPlayer(args[0]).getLocation());
				sendTeleportMessage(sender, ChatColor.YELLOW + "Teleported " + player.getName() + " to yourself!");
				sendTeleportMessage(Bukkit.getPlayer(args[0]), "Teleported to " + player.getName() + "!");
			}else{
				sendPlayerNotFound(sender);
			}
		}
	}
}
