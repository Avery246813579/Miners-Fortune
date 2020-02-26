package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class TpCommand extends MinerCommand{
	public TpCommand() {
		super("tp", Ranks.ADMIN);
		this.consoleCanUse = true;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/TP (Target) {Recipient}");
		}else if(args.length == 1){
			if(sender instanceof Player){
				if(Bukkit.getPlayer(args[0]) != null){
					 ((Player) sender).teleport(Bukkit.getPlayer(args[0]));
					 sendTeleportMessage(sender, "You have teleported to " + Bukkit.getPlayer(args[0]).getName() + "!");
				}else{
					sendPlayerNotFound(sender);
				}
			}else{
				sendConsoleUsage(sender, "/TP (Target) (Recipient)");
			}
		}else{
			if(Bukkit.getPlayer(args[0]) != null){
				Player target = Bukkit.getPlayer(args[0]);
				
				if(Bukkit.getPlayer(args[1]) != null){
					Player recipient = Bukkit.getPlayer(args[1]);
					
					recipient.teleport(target.getLocation());
					sendTeleportMessage(recipient, "Teleported to " + target.getName() + "!");
					sendTeleportMessage(sender, "Teleported " + recipient.getName() + " to " + target.getName() + "!");
				}else{
					sendRecipientNotFound(sender);
				}
			}else{
				sendPlayerNotFound(sender);
			}
		}
	}
}
