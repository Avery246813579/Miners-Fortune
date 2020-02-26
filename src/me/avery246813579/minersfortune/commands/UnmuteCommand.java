package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.BanTable;

public class UnmuteCommand extends MinerCommand{
	public UnmuteCommand() {
		super("unmute", Ranks.HELPER);
		this.consoleCanUse = true;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Unmute (Player)");
			return;
		}
		
		if(MinersFortune.getPlugin().getSqlHandler().findAccount(args[0]) == null){
			sendCommandMessage(sender, ChatColor.RED + "Target player could not be found in system!");
			return;
		}
		
		BanTable banTable = MinersFortune.getPlugin().getSqlHandler().getBanned(MinersFortune.getPlugin().getSqlHandler().getPlayerId(args[0]));
		
		if(!banTable.isMuted()){
			sendCommandMessage(sender, ChatColor.RED + "Target player is not muted!");
			return;
		}
		
		if(Bukkit.getPlayer(args[0]) != null){
			Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "Mute >> " + ChatColor.YELLOW + "You have been unmuted!");
		}
		
		banTable.setMuted("false");
		MinersFortune.getPlugin().getSqlHandler().saveBan(banTable);
		MinersFortune.getPlugin().getSqlHandler().addAction(MinersFortune.getPlugin().getSqlHandler().getPlayerId((Player) sender), "Unmuted " + args[0]);
		sender.sendMessage(ChatColor.GREEN + "Mute >> " + ChatColor.YELLOW + "You have unmuted " + args[0] + "!");
	}

}
