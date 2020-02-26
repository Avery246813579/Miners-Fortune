package me.avery246813579.minersfortune.gamemode.conditions;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;

public class NoInventoryInteract extends GameCondition implements Listener {
	public NoInventoryInteract() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	@Override
	public void resetCondition(String conditition) {
	}

	@Override
	public void stopCondition() {
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryInteractEvent event){
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			event.setCancelled(true);
		}
	}
}
