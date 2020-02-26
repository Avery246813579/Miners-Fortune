package me.avery246813579.minersfortune.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.avery246813579.minersfortune.ranks.Ranks;

public class RulesCommand extends MinerCommand{
	public RulesCommand() {
		super("rules", Ranks.DEFAULT);
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
		sender.sendMessage(ChatColor.GREEN + "Want to know the rules?! Click one of the following Links!");
		sender.sendMessage(ChatColor.GREEN + "Server: " + ChatColor.YELLOW + "http://198.245.55.118/~felcraft/rules.php?type=server");
		sender.sendMessage(ChatColor.GREEN + "Website: " + ChatColor.YELLOW + "http://198.245.55.118/~felcraft/rules.php?type=website");
		sender.sendMessage(ChatColor.GREEN + "Other: " + ChatColor.YELLOW + "http://198.245.55.118/~felcraft/rules.php?type=other");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
	}
}
