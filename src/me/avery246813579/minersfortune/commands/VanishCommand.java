package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;

public class VanishCommand extends MinerCommand{
	public VanishCommand() {
		super("vanish", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		
		if(MinersFortune.getVanished().contains(player)){
			MinersFortune.getVanished().remove(player);
			
			for(Player players : Bukkit.getOnlinePlayers()){
				players.showPlayer(player);
			}
			
			player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Vanish >> " + ChatColor.YELLOW + "You take off your invisibility cloak!");
		}else{
			MinersFortune.getVanished().add(player);
			
			for(Player players : Bukkit.getOnlinePlayers()){
				players.hidePlayer(player);
			}
			
			player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Vanish >> " + ChatColor.YELLOW + "You vanished into the air!");
		}
	}
}
