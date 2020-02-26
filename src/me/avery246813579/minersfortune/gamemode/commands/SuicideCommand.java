package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.ranks.Ranks;

public class SuicideCommand extends GameCommand{
	public SuicideCommand() {
		super("suicide", Ranks.DEFAULT, new ArrayList<GameState>(Arrays.asList(GameState.Live)));
		this.consoleCanUse = false;
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(GameManager.findPlayer((Player) sender).isAlive()){
			GameManager.findPlayer((Player) sender).getPlayer().damage(10000000);
		}
	}
}
