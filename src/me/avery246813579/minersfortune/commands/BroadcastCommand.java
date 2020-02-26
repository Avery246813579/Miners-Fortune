package me.avery246813579.minersfortune.commands;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.util.Util;

public class BroadcastCommand extends MinerCommand implements PluginMessageListener{
	private Map<String, String> messageQueue = new HashMap<String, String>();

	public BroadcastCommand() {
		super("broadcast", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length <= 1) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Broadcast (Server/All) (Message)");
			return;
		}

		String server = args[0];

		if (server.equalsIgnoreCase("all")) {
			server = "ALL";
		}
		
        Util.sendPM((sender instanceof Player) ? (Player)sender : org.bukkit.Bukkit.getOnlinePlayers()[0], new String[] { "PlayerList", server });
        this.messageQueue.put(server, ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', Util.createString(args, 1)));
        sendCommandMessage(sender, ChatColor.YELLOW + "You have broadcasted a message!");
	}

	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
		try {
			String subchannel = in.readUTF();
			if (subchannel.equals("PlayerList")) {
				String server = in.readUTF();
				String playerList = in.readUTF();

				for (String name : playerList.split(", ")) {
					Util.sendPM(player, new String[] { "Message", name, (String) this.messageQueue.get(server) });
				}
				this.messageQueue.remove("server");
			}
		} catch (Exception localException) {
		}
	}
}
