package me.avery246813579.minersfortune.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.MinersFortune;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {
	public static int timeState;

	public static void sendPM(Player sender, String[] messages) {
		if (sender == null) {
			return;
		}
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			for (String message : messages)
				out.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sender.sendPluginMessage(MinersFortune.getPlugin(), "BungeeCord", b.toByteArray());
	}

	public static Integer getBooleanByteType(boolean b) {
		if (b) {
			return 1;
		} else {
			return 0;
		}
	}

	public static String createString(String[] args, int start) {
		StringBuilder string = new StringBuilder();

		for (int x = start; x < args.length; x++) {
			string.append(args[x]);

			if (x != args.length - 1) {
				string.append(" ");
			}
		}

		return string.toString();
	}

	public static List<ChatColor> getChatColors() {
		List<ChatColor> colors = new ArrayList<ChatColor>();

		colors.add(ChatColor.DARK_RED);
		colors.add(ChatColor.RED);
		colors.add(ChatColor.GOLD);
		colors.add(ChatColor.YELLOW);
		colors.add(ChatColor.DARK_GREEN);
		colors.add(ChatColor.GREEN);
		colors.add(ChatColor.AQUA);
		colors.add(ChatColor.DARK_AQUA);
		colors.add(ChatColor.DARK_BLUE);
		colors.add(ChatColor.BLUE);
		colors.add(ChatColor.LIGHT_PURPLE);
		colors.add(ChatColor.DARK_PURPLE);

		return colors;
	}

	public static int getNextTimeState() {
		timeState++;
		return timeState;
	}
}
