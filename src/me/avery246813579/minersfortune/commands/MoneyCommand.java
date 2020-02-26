package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;

public class MoneyCommand extends MinerCommand {
	public MoneyCommand() {
		super("money", Ranks.DEFAULT);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sendCommandMessage(sender, ChatColor.YELLOW + "Credits: " + MinersFortune.getPlugin().getSqlHandler().getAccount((Player) sender).getCredits());
			return;
		}
		if (MinersFortune.getPlugin().getSqlHandler().findAccount(args[0]) == null) {
			sendCommandMessage(sender, ChatColor.RED + "Target player not found!");
			return;
		}
		
		sendCommandMessage(sender, ChatColor.YELLOW + args[0] + "'s Credits: " + MinersFortune.getPlugin().getSqlHandler().findAccount(args[0]).getCredits());
	}
}
