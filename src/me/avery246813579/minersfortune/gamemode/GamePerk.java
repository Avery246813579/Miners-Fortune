package me.avery246813579.minersfortune.gamemode;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class GamePerk implements Listener {
	protected abstract void giveOnSpawn(Player player);
	protected abstract void onEnable();
	protected abstract void remove();
}
