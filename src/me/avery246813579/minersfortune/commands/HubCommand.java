package me.avery246813579.minersfortune.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.util.Util;

public class HubCommand extends MinerCommand{
	public HubCommand() {
		super("hub", Ranks.DEFAULT);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		Util.sendPM((Player) sender, new String[] {"Connect", "hub"});
	}
}
