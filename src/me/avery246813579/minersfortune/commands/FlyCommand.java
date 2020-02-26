package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class FlyCommand extends MinerCommand{

	public FlyCommand() {
		super("fly", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			if(sender instanceof Player){
				Player player = (Player) sender;
				toggleFlight(player);
			}else{
				sendCommandMessage(sender, ChatColor.RED + "Console Usage: " + ChatColor.YELLOW + "/Fly (Player)");
			}
		}else{
			if(Bukkit.getPlayer(args[0]) != null){
				Player player = Bukkit.getPlayer(args[0]);
				
				toggleFlight(player);
				sendCommandMessage(sender, ChatColor.YELLOW + "You have toggled " + player.getName() + "'s flight!");
			}else{
				sendCommandMessage(sender, ChatColor.RED + "Player not found!");
			}
		}
	}
	
	public void toggleFlight(Player player){
		if(player.isFlying()){
			player.setAllowFlight(false);
			player.setFlying(false);
			sendStatusMessage(player, ChatColor.YELLOW + "You can no longer fly!");
		}else{
			player.setAllowFlight(true);
			player.setFlying(true);
			player.setFlySpeed(0.1F);
			sendStatusMessage(player, ChatColor.YELLOW + "You can now fly!");
		}
	}
}
