package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.CommandSender;

import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class DeleteCommand extends GameCommand{

	public DeleteCommand() {
		super("delete", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.Limbow)));
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		
	}
}
