package me.avery246813579.minersfortune.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.avery246813579.minersfortune.ranks.Ranks;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand extends MinerCommand {
	private static Map<Player, Player> lastMessage = new HashMap<Player, Player>();

	public MessageCommand() {
		super(new ArrayList<String>(Arrays.asList("message", "msg", "tell", "w")), Ranks.DEFAULT);
		this.consoleCanUse = false;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Msg (Player) (Message)");
			return;
		}

		Player targetPlayer = Bukkit.getPlayer(args[0]);

		if (targetPlayer != null) {
			if(MessageCommand.getLastMessage().containsKey(MessageCommand.getLastMessage().get(targetPlayer))){
				MessageCommand.getLastMessage().remove(targetPlayer);
			}
			
			StringBuilder parser = new StringBuilder();
			for (int x = 1; x < args.length; x++) {

				parser.append(args[x]);

				if (x != args.length - 1) {
					parser.append(" ");
				}
			}
			
			String message = parser.toString();
			sender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Message to " + targetPlayer.getName() + " >> " + ChatColor.YELLOW + ChatColor.BOLD + message);
			targetPlayer.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Message from " + ((Player) sender).getName() + " >> " + ChatColor.YELLOW + ChatColor.BOLD + message);
			lastMessage.put(targetPlayer, (Player) sender);
		} else {
			sendCommandMessage(sender, ChatColor.RED + "Target player not found.");
		}
	}

	public static Map<Player, Player> getLastMessage() {
		return lastMessage;
	}

	public static void setLastMessage(Map<Player, Player> lastMessage) {
		MessageCommand.lastMessage = lastMessage;
	}
}
