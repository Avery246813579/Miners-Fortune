package me.avery246813579.minersfortune.gamemode.conditions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;

public class SteakHeal extends GameCondition implements Listener{
	public SteakHeal(){
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	@Override
	public void resetCondition(String conditition) {
		
	}

	@Override
	public void stopCondition() {
		
	}
	
	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent event){
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			if(event.getItem().getType() == Material.COOKED_BEEF){
				event.getPlayer().setHealth(event.getPlayer().getHealth() + 4.0);
			}
		}
	}
}
