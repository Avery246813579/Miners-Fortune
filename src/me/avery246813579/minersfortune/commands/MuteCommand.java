package me.avery246813579.minersfortune.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.BanTable;

public class MuteCommand extends MinerCommand {

	public MuteCommand() {
		super("mute", Ranks.HELPER);
		this.consoleCanUse = true;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0 || args.length == 1 || args.length == 2) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Mute (Player) (Forever/Time) (Reason)");
			return;
		}
		
		if(MinersFortune.getPlugin().getSqlHandler().findAccount(args[0]) == null){
			sendCommandMessage(sender, ChatColor.RED + "Target player could not be found in system!");
			return;
		}
		
		if(RankManager.findPlayerRank(args[0]).getRankNumber() >= RankManager.findPlayerRank((Player) sender).getRankNumber()){
			sendCommandMessage(sender, ChatColor.RED + "You can't ban people whom have higher or same rank!");
			return;
		}

		String stringDate = null;
		try {
			Date date = new Date();
			date.setTime(parseDateDiff(args[1], true));
			SimpleDateFormat df = new SimpleDateFormat("HH/mm/MM/dd/yy");
			stringDate = df.format(date);
		} catch (Exception e) {
			if (args[1].equalsIgnoreCase("forever")) {
				stringDate = "Forever";
			} else {
				sendCommandMessage(sender, ChatColor.RED + "Could not parse date!");
				return;
			}
		}
		
		StringBuilder string = new StringBuilder();
		for (int x = 2; x < args.length; x++) {

			string.append(args[x]);

			if (x != args.length - 1) {
				string.append(" ");
			}
		}

		String s = string.toString();
		
		BanTable banTable = MinersFortune.getPlugin().getSqlHandler().getBanned(MinersFortune.getPlugin().getSqlHandler().getPlayerId(args[0]));
		banTable.setMuted("true " + stringDate + " " + s);
		MinersFortune.getPlugin().getSqlHandler().saveBan(banTable);
		MinersFortune.getPlugin().getSqlHandler().addAction(MinersFortune.getPlugin().getSqlHandler().getPlayerId((Player) sender), "MUTE GIVEN TO " + args[0] + " FOR " + args[1] + " UNTIL " + stringDate + " because " + s);
		
		if(Bukkit.getPlayer(args[0]) != null){
			Bukkit.getPlayer(args[0]).sendMessage(ChatColor.GREEN + "Mute >> " + ChatColor.RED + "You have been muted until: " + ChatColor.YELLOW + stringDate + ChatColor.RED + " Reason: " + ChatColor.YELLOW + s);
		}
		
		sender.sendMessage(ChatColor.GREEN + "Mute >> " + ChatColor.YELLOW + "You have muted " + args[0] + " until " + stringDate + " for " + s);
	}

	public static long parseDateDiff(String time, boolean future) throws Exception {
		Pattern timePattern = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?" + "(?:([0-9]+)\\s*(?:s[a-z]*)?)?",
				Pattern.CASE_INSENSITIVE);
		Matcher m = timePattern.matcher(time);
		int years = 0;
		int months = 0;
		int weeks = 0;
		int days = 0;
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		boolean found = false;
		while (m.find()) {
			if (m.group() == null || m.group().isEmpty()) {
				continue;
			}
			for (int i = 0; i < m.groupCount(); i++) {
				if (m.group(i) != null && !m.group(i).isEmpty()) {
					found = true;
					break;
				}
			}
			if (found) {
				if (m.group(1) != null && !m.group(1).isEmpty())
					years = Integer.parseInt(m.group(1));
				if (m.group(2) != null && !m.group(2).isEmpty())
					months = Integer.parseInt(m.group(2));
				if (m.group(3) != null && !m.group(3).isEmpty())
					weeks = Integer.parseInt(m.group(3));
				if (m.group(4) != null && !m.group(4).isEmpty())
					days = Integer.parseInt(m.group(4));
				if (m.group(5) != null && !m.group(5).isEmpty())
					hours = Integer.parseInt(m.group(5));
				if (m.group(6) != null && !m.group(6).isEmpty())
					minutes = Integer.parseInt(m.group(6));
				if (m.group(7) != null && !m.group(7).isEmpty())
					seconds = Integer.parseInt(m.group(7));
				break;
			}
		}
		if (!found)
			throw new Exception("Illegal Date");

		if (years > 20)
			throw new Exception("Illegal Date");

		Calendar c = new GregorianCalendar();
		if (years > 0)
			c.add(Calendar.YEAR, years * (future ? 1 : -1));
		if (months > 0)
			c.add(Calendar.MONTH, months * (future ? 1 : -1));
		if (weeks > 0)
			c.add(Calendar.WEEK_OF_YEAR, weeks * (future ? 1 : -1));
		if (days > 0)
			c.add(Calendar.DAY_OF_MONTH, days * (future ? 1 : -1));
		if (hours > 0)
			c.add(Calendar.HOUR_OF_DAY, hours * (future ? 1 : -1));
		if (minutes > 0)
			c.add(Calendar.MINUTE, minutes * (future ? 1 : -1));
		if (seconds > 0)
			c.add(Calendar.SECOND, seconds * (future ? 1 : -1));
		return c.getTimeInMillis();
	}
}
