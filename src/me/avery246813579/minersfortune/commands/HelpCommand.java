package me.avery246813579.minersfortune.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.avery246813579.minersfortune.ranks.Ranks;

public class HelpCommand extends MinerCommand{
	public HelpCommand() {
		super("help", Ranks.DEFAULT);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.GREEN + "Need help?! Click one of the following Links!");
		sender.sendMessage(ChatColor.GREEN + "Donation: " + ChatColor.YELLOW + "http://198.245.55.118/~felcraft/help.php?type=donation");
		sender.sendMessage(ChatColor.GREEN + "Support: " + ChatColor.YELLOW + "http://198.245.55.118/~felcraft/help.php?type=support");
		sender.sendMessage(ChatColor.GREEN + "Commands: " + ChatColor.YELLOW + "http://198.245.55.118/~felcraft/help.php?type=commands");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
	}
}
