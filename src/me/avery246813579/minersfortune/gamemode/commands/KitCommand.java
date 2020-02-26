package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class KitCommand extends GameCommand{
	public KitCommand() {
		super("kit", Ranks.DEFAULT, new ArrayList<GameState>(Arrays.asList(GameState.Prepare, GameState.Recruit)));
		this.consoleCanUse = false;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Kit (Kit Name)");
			return;
		}
		
		GamePlayer gamePlayer = GameManager.findPlayer((Player) sender);
		
		if(GameManager.findKit(args[0]) == null){
			gamePlayer.sendKitMessage(ChatColor.RED + "Kit not found!");
			return;
		}

		gamePlayer.selectKit(GameManager.findKit(args[0]));
	}
}
