package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.menus.MenuHandler;
import me.avery246813579.minersfortune.ranks.Ranks;

public class StatsCommand extends MinerCommand {
	public StatsCommand() {
		super("stats", Ranks.DEFAULT);
		this.consoleCanUse = false;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0) {
			MenuHandler.statsMenu.openMenu((Player) sender, 0);
			return;
		}
		
		Player targetPlayer = Bukkit.getPlayer(args[0]);
		
		if(targetPlayer != null){
			MenuHandler.statsMenu.openPlayerInventory((Player) sender, targetPlayer);
		}else{
			sender.sendMessage(ChatColor.GREEN + "Stats >> " + ChatColor.RED + "Target player not found!");
		}
	}
}
