package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboardHandler;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameType;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.MapTable;
import me.avery246813579.minersfortune.sql.tables.ServerTable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChangeCommand extends GameCommand{

	public ChangeCommand() {
		super("change", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.Limbow, GameState.Recruit)));
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length <= 1){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Change (Game|Map|PermGame) (Argument)");
			return;
		}
		
		String argument = args[1];
		
		if(args[0].equalsIgnoreCase("Game")){
			
			GameType game = null;
			for(GameType gameType : GameType.values()){
				if(gameType.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(argument)){
					game = gameType;
				}
			}
			
			if(game == null){
				sendCommandMessage(sender, ChatColor.RED + "Game mode not found! Please try again!");
				return;
			}
			
			GameManager.setGameType(game);
			GameManager.broadcastGameMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "The game has been changed to " + game.getDisplayName() + "!");
			IScoreboardHandler.updatePlayers();
		} 
		
		else if(args[0].equalsIgnoreCase("Map")){
			MapTable map = null;
			for(MapTable mapTable : MinersFortune.getPlugin().getSqlHandler().getAllMaps(GameManager.getGameType().getDisplayName().replaceAll(" ", ""))){
				if(mapTable.getMapName().replaceAll(" ", "").equalsIgnoreCase(argument)){
					map = mapTable;
				}
			}
			
			if(map == null){
				sendCommandMessage(sender, ChatColor.RED + "Map not found! Please try again!");
				return;
			}
			
			GameManager.setMapTable(map);
			GameManager.broadcastGameMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "The map has been changed to " + map.getMapName() + "!");
			IScoreboardHandler.updatePlayers();
		}
		
		else if(args[0].equalsIgnoreCase("PermGame")){
			if(GameManager.getGameState() != GameState.Limbow){
				sendCommandMessage(sender, ChatColor.RED + "You can only set the perm game of this server while the game is in limbow!");
				return;
			}
			
			ServerTable serverTable = MinersFortune.getPlugin().getSqlHandler().getServer(Bukkit.getServerName());
			serverTable.setGameType(argument);
			MinersFortune.getPlugin().getSqlHandler().saveServer(serverTable);
			sendCommandMessage(sender, ChatColor.YELLOW + "You have saved the servers perm game as " + argument);
			IScoreboardHandler.updatePlayers();
		}
		
		else{
			sendCommandMessage(sender, ChatColor.RED + "Could not find argument!");
			return;
		}
	}
	
}
