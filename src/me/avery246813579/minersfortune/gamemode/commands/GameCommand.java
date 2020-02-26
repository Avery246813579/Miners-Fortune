package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class GameCommand implements CommandExecutor{
	/** Private **/
	private ArrayList<GameState> whenAble;
	private String command;
	private Ranks canUse;
	
	/** Protected **/
	protected boolean consoleCanUse = true;
	
	public GameCommand(String command, Ranks canUse, ArrayList<GameState> whenAble){
		this.command = command;
		this.canUse = canUse;
		this.setWhenAble(whenAble);
	}

	protected abstract void runCommand(CommandSender sender, String[] args);
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if(CommandLabel.equalsIgnoreCase(command)){
			if(sender instanceof ConsoleCommandSender && !consoleCanUse){
				sender.sendMessage(ChatColor.GREEN + "Console >> " + ChatColor.RED + "The console can not use this command!");
				return true;
			}
			
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(RankManager.findPlayerRank(player) != null){
					if(RankManager.findPlayerRank(player).getRankNumber() < canUse.getRankNumber()){
						sendCommandMessage(sender, ChatColor.RED + "This requires Staff Rank [" + canUse.getChatColor() + canUse.getName() + ChatColor.RED + "]");
						return true;
					}
				}
			}
			
			if(!whenAble.contains(GameManager.getGameState())){
				sendCommandMessage(sender, ChatColor.RED + "Command not able to be run at this time");
				return true;
			}
			
			runCommand(sender, args);
		}
		
		return false;
	}
	
	public static void sendCommandMessage(CommandSender sender, String message){
		sender.sendMessage(ChatColor.GREEN + "Command >> " + ChatColor.GRAY + message);
	}
	


	public Ranks getCanUse() {
		return canUse;
	}


	public void setCanUse(Ranks canUse) {
		this.canUse = canUse;
	}

	public ArrayList<GameState> getWhenAble() {
		return whenAble;
	}

	public void setWhenAble(ArrayList<GameState> whenAble) {
		this.whenAble = whenAble;
	}

}
