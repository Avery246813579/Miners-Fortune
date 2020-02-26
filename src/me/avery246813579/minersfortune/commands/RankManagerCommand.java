package me.avery246813579.minersfortune.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.AccountTable;

public class RankManagerCommand extends MinerCommand {

	public RankManagerCommand() {
		super("rankmanager", Ranks.ADMIN);
		this.consoleCanUse = true;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length == 0 || args.length == 1) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/RankManager (Player) (Rank)");
			return;
		}

		if (MinersFortune.getPlugin().getSqlHandler().findAccount(args[0]) == null) {
			sendCommandMessage(sender, ChatColor.RED + "Player not found!");
			return;
		}

		Ranks rank = null;
		for (Ranks ranks : Ranks.values()) {
			if (ranks.getName().equalsIgnoreCase(args[1])) {
				rank = ranks;
			}
		}

		if (rank == null) {
			sendCommandMessage(sender, ChatColor.RED + "Rank not found!");
			return;
		}

		AccountTable accountTable = MinersFortune.getPlugin().getSqlHandler().findAccount(args[0]);
		accountTable.setRank(args[1]);
		MinersFortune.getPlugin().getSqlHandler().saveAccount(accountTable);

		if (sender instanceof Player) {
			MinersFortune.getPlugin().getSqlHandler().addAction(MinersFortune.getPlugin().getSqlHandler().getPlayerId((Player) sender), "RANK " + args[1] + " GIVEN TO " + args[0]);
		} else {
			MinersFortune.getPlugin().getSqlHandler().addAction(0, "RANK " + args[1] + " GIVEN TO " + args[0]);
		}

		sendCommandMessage(sender, ChatColor.YELLOW + "You have given " + args[1] + " to " + args[0] + "!");

		if (Bukkit.getPlayer(args[0]) != null) {
			RankManager.applyPrefix(Bukkit.getPlayer(args[0]));
		}
	}
}
