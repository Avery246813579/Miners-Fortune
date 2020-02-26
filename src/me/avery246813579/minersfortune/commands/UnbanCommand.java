package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.BanTable;

public class UnbanCommand extends MinerCommand{
	public UnbanCommand() {
		super("unban", Ranks.MODERATOR);
		this.consoleCanUse = true;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Unban (Player)");
			return;
		}
		
		if(MinersFortune.getPlugin().getSqlHandler().findAccount(args[0]) == null){
			sendCommandMessage(sender, ChatColor.RED + "Target player could not be found in system!");
			return;
		}
		
		BanTable banTable = MinersFortune.getPlugin().getSqlHandler().getBanned(MinersFortune.getPlugin().getSqlHandler().getPlayerId(args[0]));
		
		if(!banTable.isBanned()){
			sendCommandMessage(sender, ChatColor.RED + "Target player is not banned!");
			return;
		}
		
		banTable.setBanned("false");
		MinersFortune.getPlugin().getSqlHandler().saveBan(banTable);
		MinersFortune.getPlugin().getSqlHandler().addAction(MinersFortune.getPlugin().getSqlHandler().getPlayerId((Player) sender), "Unbanned " + args[0]);
		sender.sendMessage(ChatColor.GREEN + "Ban >> " + ChatColor.YELLOW + "You have unbanned " + args[0] + "!");
	}
}
