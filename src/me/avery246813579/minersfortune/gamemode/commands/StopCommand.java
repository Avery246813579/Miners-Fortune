package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class StopCommand extends GameCommand{
	public StopCommand() {
		super("fstop", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.values())));
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(GameManager.getGameState() == GameState.Limbow){
			sendCommandMessage(sender, ChatColor.RED + "The game is already in limbow!");
			return;
		}
		
		if(sender instanceof Player){
			GameManager.broadcastGameMessage(((Player)sender).getDisplayName() + ChatColor.RED + " has stopped the game!");
		}else{
			GameManager.broadcastGameMessage(ChatColor.RED + "Game has been stopped!");
		}
		
		GameManager.forceStopGame();
	}
}
