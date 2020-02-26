package me.avery246813579.minersfortune.gamemode.conditions;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;

public class NoDropItems extends GameCondition implements Listener{
	public NoDropItems(){
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}
	
	@Override
	public void resetCondition(String conditition) {
		
	}

	@Override
	public void stopCondition() {
		
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event){
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event){
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent event){
		event.getDrops().clear();
	}
}
