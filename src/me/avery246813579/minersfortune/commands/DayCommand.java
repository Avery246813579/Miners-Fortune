package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class DayCommand extends MinerCommand{
	public DayCommand() {
		super("day", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			if(sender instanceof Player){
				Player player = (Player) sender;
				player.getWorld().setTime(1000);
				sendCommandMessage(sender, ChatColor.YELLOW + "Time set to day in world: " + player.getWorld().getName() + "!");
				return;
			}else{
				sendCommandMessage(sender, ChatColor.RED + "Console Usage: " + ChatColor.YELLOW + "/Day (World)");
				return;
			}
		}else{
			if(Bukkit.getWorld(args[0]) != null){
				Bukkit.getWorld(args[0]).setTime(1000);
				sendCommandMessage(sender, ChatColor.YELLOW + "Time set to day in world: " + Bukkit.getWorld(args[0]).getName());
			}else{
				sendCommandMessage(sender, ChatColor.RED + "World not found!");
			}
		}
	}
}
