package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameTeam;
import me.avery246813579.minersfortune.ranks.Ranks;

public class TeamCommand extends GameCommand{
	public TeamCommand() {
		super("team", Ranks.DEFAULT, new ArrayList<GameState>(Arrays.asList(GameState.Recruit, GameState.Prepare)));
		this.consoleCanUse = false;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			if(GameManager.isStarting()){
				sendCommandMessage(sender, ChatColor.RED + "Command not able to be run at this time");
				return;
			}
			
			String teams = "";
			
			for(GameTeam team : GameManager.getTeams()){
				teams = teams + " | " + team.getName();
			}
			
			teams.substring(1, teams.length());
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Team " + teams);
			return;
		}
		
		for(GameTeam team : GameManager.getTeams()){
			if(team.getName().equalsIgnoreCase(args[0])){
				team.addQueue((Player) sender);
			}
		}
	}
}
