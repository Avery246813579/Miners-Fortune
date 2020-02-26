package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameMapHelper;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class GotoCommand extends GameCommand{
	public GotoCommand() {
		super("goto", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.Limbow)));
		this.consoleCanUse = false;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Goto (Map Name)");
			return;
		}
		
		if(GameMapHelper.loadMap(sender, args[0])){
			sendCommandMessage(sender, ChatColor.YELLOW + "Loading map! You will be teleported there in 10 seconds!");
		}else{
			sendCommandMessage(sender, ChatColor.RED + "Map could not be found! Please check if it exists!");
			return;
		}
		
		final String mapName = args[0];
		final Player player = (Player) sender;
		new BukkitRunnable(){
			public void run() {
				player.teleport(Bukkit.getWorld("GameWorld").getSpawnLocation());
				sendCommandMessage(player, ChatColor.YELLOW + "You have been teleported to " + mapName + "!");
				sendCommandMessage(player, ChatColor.YELLOW + "To leave " + mapName + " just use the /spawn command!");
			}
		}.runTaskLater(MinersFortune.getPlugin(), 280);
	}

}
