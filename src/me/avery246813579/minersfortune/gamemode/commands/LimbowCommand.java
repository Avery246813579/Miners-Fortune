package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class LimbowCommand extends GameCommand{

	public LimbowCommand() {
		super("limbow", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.Limbow)));
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args[0].length() == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Limbow (Start)");
		}
		
		if(args[0].equalsIgnoreCase("start")){
			sendCommandMessage(sender, ChatColor.YELLOW + "Started the game!");
			GameManager.resetGame();
		}
	}
}
