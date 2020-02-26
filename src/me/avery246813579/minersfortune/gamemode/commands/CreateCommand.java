package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameType;
import me.avery246813579.minersfortune.ranks.Ranks;

public class CreateCommand extends GameCommand {

	public CreateCommand() {
		super("create", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.Limbow)));
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if (args.length < 4) {
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Create (Name) (Creator) (Gamemode) (Map Location)");
			return;
		}

		GameType game = null;
		for (GameType gameType : GameType.values()) {
			if (gameType.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(args[2])) {
				game = gameType;
			}
		}

		if (game == null) {
			sendCommandMessage(sender, ChatColor.RED + "Could not find selected gametype!");
			return;
		}

		String arenaName = args[0];
		if (MinersFortune.getPlugin().getSqlHandler().getMap(arenaName) != null) {
			sendCommandMessage(sender, ChatColor.RED + "A map with the same name is already found!");
			return;
		}

		MinersFortune.getPlugin().getSqlHandler().createMap(arenaName, args[1], args[3], game.getDisplayName().replaceAll(" ", ""));
		sendCommandMessage(sender, ChatColor.YELLOW + "You have created a new " + game.getDisplayName() + " map called " + arenaName + " created by " + args[1] + " and located at " + args[3]);
	} 

}
