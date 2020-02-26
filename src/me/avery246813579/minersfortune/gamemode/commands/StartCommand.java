package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class StartCommand extends GameCommand{

	public StartCommand() {
		super("start", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.Recruit)));
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(sender instanceof Player){
			GameManager.broadcastGameMessage(((Player)sender).getDisplayName() + ChatColor.YELLOW + " has started the game!");
		}else{
			GameManager.broadcastGameMessage(ChatColor.YELLOW + "Game has been started!");
		}
		GameManager.setForceStart(true);
	}
}
