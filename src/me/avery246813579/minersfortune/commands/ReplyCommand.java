package me.avery246813579.minersfortune.commands;

import java.util.ArrayList;
import java.util.Arrays;

import me.avery246813579.minersfortune.ranks.Ranks;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand extends MinerCommand {
	public ReplyCommand() {
		super(new ArrayList<String>(Arrays.asList("reply", "r")), Ranks.DEFAULT);
		this.consoleCanUse = false;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Reply (Message)");
			return;
		}

		if (MessageCommand.getLastMessage().containsKey((Player) sender)) {
			Player targetPlayer = MessageCommand.getLastMessage().get((Player) sender);
			if(MessageCommand.getLastMessage().containsKey(MessageCommand.getLastMessage().get((Player) sender))){
				MessageCommand.getLastMessage().remove((Player) sender);
			}
			
			StringBuilder parser = new StringBuilder();
			for (int x = 0; x < args.length; x++) {

				parser.append(args[x]);

				if (x != args.length - 1) {
					parser.append(" ");
				}
			}
			
			String message = parser.toString();
			sender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Message to " + targetPlayer.getName() + " >> " + ChatColor.YELLOW + ChatColor.BOLD + message);
			targetPlayer.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Message from " + ((Player) sender).getName() + " >> " + ChatColor.YELLOW + ChatColor.BOLD + message);
			MessageCommand.getLastMessage().put(targetPlayer, (Player) sender);
		} else {
			sendCommandMessage(sender, ChatColor.RED + "No one has sent you a message.");
		}
	}
}
