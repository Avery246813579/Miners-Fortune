package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.ServerTable;

public class StaffWhitelistCommand extends MinerCommand {
	public StaffWhitelistCommand() {
		super("staffwhitelist", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args[0].length() == 0) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/StaffWhitelist (True|False)");
			return;
		}

		try {
			boolean whitelist = Boolean.parseBoolean(args[0]);
			ServerTable serverTable = MinersFortune.getPlugin().getSqlHandler().getServer(Bukkit.getServerName());
			serverTable.setWhitelist(whitelist);
			MinersFortune.getPlugin().getSqlHandler().saveServer(serverTable);
			sendCommandMessage(sender, ChatColor.YELLOW + "You have updated status of this server!");
		} catch (Exception ex) {
			sendCommandMessage(sender, ChatColor.RED + "Could not parse boolean!");
		}
	}
}
