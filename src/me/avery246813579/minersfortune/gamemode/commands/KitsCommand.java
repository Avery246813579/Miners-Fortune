package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;

import me.avery246813579.minersfortune.gamemode.GameKit;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class KitsCommand extends GameCommand{

	public KitsCommand() {
		super("kits", Ranks.DEFAULT, new ArrayList<GameState>(Arrays.asList(GameState.Prepare, GameState.Recruit, GameState.End)));
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		String kits = "";
		
		for(GameKit gameKit : GameManager.getKits()){
			kits = kits + ", " + gameKit.getDisplayName();
		}
		
		sender.sendMessage(ChatColor.GREEN + "Kits >> " + ChatColor.YELLOW + kits.substring(1, kits.length()));
	}
}
