package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class FeedCommand extends MinerCommand{
	public FeedCommand() {
		super("feed", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			if(sender instanceof Player){
				Player player = (Player) sender;
				player.setFoodLevel(20);
				sendStatusMessage(sender, ChatColor.YELLOW + "You have been fed!");
			}else{
				sendCommandMessage(sender, ChatColor.RED + "Console Usage: " + ChatColor.YELLOW + "/Feed (Player)");
			}
		}else{
			if(Bukkit.getPlayer(args[0]) != null){
				Player player = Bukkit.getPlayer(args[0]);
				player.setFoodLevel(20);
				
				sendStatusMessage(sender, ChatColor.YELLOW + "You have fed " + player.getName() + "!");
				sendStatusMessage(player, ChatColor.YELLOW + "You have been fed by " + sender.getName() + "!");
			}else{
				sendCommandMessage(sender, ChatColor.RED + "Player not found!");
			}
		}
	}
}
