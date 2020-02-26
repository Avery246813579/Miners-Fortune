package me.avery246813579.minersfortune.gamemode.conditions;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;

public class NoHunger extends GameCondition implements Listener{
	public NoHunger(){
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}
	
	@Override
	public void resetCondition(String conditition) {
		
	}

	@Override
	public void stopCondition() {
		
	}
	
	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent event){
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			event.setCancelled(true);
		}	
	}
}
