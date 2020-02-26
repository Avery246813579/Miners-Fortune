package me.avery246813579.minersfortune.commands;

import java.util.Date;
import java.util.TimeZone;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;

import me.avery246813579.minersfortune.ranks.Ranks;

public class TimeCommand extends MinerCommand{
	public TimeCommand() {
		super("time", Ranks.DEFAULT);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void runCommand(CommandSender sender, String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        Date date = new Date();
        date.setHours(date.getHours() + 1);
        sendCommandMessage(sender, ChatColor.YELLOW + "Current Server Time: " + date);
	}
}
