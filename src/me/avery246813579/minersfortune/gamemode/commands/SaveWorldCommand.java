package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import me.avery246813579.minersfortune.gamemode.GameMapHelper;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class SaveWorldCommand extends GameCommand{
	public SaveWorldCommand() {
		super("saveworld", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.Limbow)));
		this.consoleCanUse = true;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/SaveWorld (Map Name)");
			return;
		}
		
		if(GameMapHelper.loadMap(sender, args[0])){
			sendCommandMessage(sender, ChatColor.YELLOW + "The map has been saved!");
		}else{
			sendCommandMessage(sender, ChatColor.RED + "Map file could not be found! Please check if it exists!");
		}
	}
}
