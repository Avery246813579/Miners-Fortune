package me.avery246813579.minersfortune.commands;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class MinerCommand implements CommandExecutor {
	/** Private **/
	private List<String> commands;
	private String command;
	private Ranks canUse;

	/** Protected **/
	protected boolean consoleCanUse = false;

	public MinerCommand(String command, Ranks canUse) {
		this.command = command;
		this.canUse = canUse;
		
		//MinersFortune.getPlugin().getCommand(command).setExecutor(this);
	}

	public MinerCommand(ArrayList<String> commands, Ranks canUse) {
		this.commands = commands;
		this.canUse = canUse;
		
		//	MinersFortune.getPlugin().getCommand(cmd).setExecutor(this);
	}

	protected abstract void runCommand(CommandSender sender, String[] args);

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if (commands == null) {
			if (CommandLabel.equalsIgnoreCase(command)) {
				if (sender instanceof ConsoleCommandSender && !consoleCanUse) {
					sender.sendMessage(ChatColor.GREEN + "Console >> " + ChatColor.RED + "The console can not use this command!");
					return true;
				}

				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (RankManager.findPlayerRank(player) != null) {
						if (RankManager.findPlayerRank(player).getRankNumber() < canUse.getRankNumber()) {
							player.sendMessage(ChatColor.RED + "This requires Staff Rank [" + canUse.getChatColor() + canUse.getName() + ChatColor.RED + "]");
							return true;
						}
					}
				}

				runCommand(sender, args);
			}
		} else {
			if (commands.contains(CommandLabel)) {
				if (sender instanceof ConsoleCommandSender && !consoleCanUse) {
					sender.sendMessage(ChatColor.GREEN + "Console >> " + ChatColor.RED + "The console can not use this command!");
					return true;
				}

				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (RankManager.findPlayerRank(player) != null) {
						if (RankManager.findPlayerRank(player).getRankNumber() < canUse.getRankNumber()) {
							player.sendMessage(ChatColor.RED + "This requires Staff Rank [" + canUse.getChatColor() + canUse.getName() + ChatColor.RED + "]");
							return true;
						}
					}
				}

				runCommand(sender, args);
			}
		}

		return false;
	}

	public static void sendCommandMessage(CommandSender sender, String message) {
		sender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Command >> " + ChatColor.GRAY + message);
	}
	
	public static void sendStatusMessage(CommandSender sender, String message){
		sender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Status >> " + ChatColor.GRAY + message);
	}
	
	public static void sendTeleportMessage(CommandSender sender, String message){
		sender.sendMessage("" + ChatColor.GREEN  + ChatColor.BOLD + "Teleportation >> " + ChatColor.YELLOW + message);
	}
	
	public static void sendPlayerNotFound(CommandSender sender){
		sender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Player >> " + ChatColor.RED + "Target player not found!");
	}
	
	public static void sendRecipientNotFound(CommandSender sender){
		sender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Player >> " + ChatColor.RED + "Recipient not found!");
	}
	
	public static void sendConsoleUsage(CommandSender sender, String usage){
		sender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Command >> " + ChatColor.RED + "Console Usage: " + ChatColor.YELLOW + usage);
	}

	public Ranks getCanUse() {
		return canUse;
	}

	public void setCanUse(Ranks canUse) {
		this.canUse = canUse;
	}

	public List<String> getCommands() {
		return commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

}
