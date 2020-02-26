package me.avery246813579.minersfortune.gamemode.kits.perks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameType;
import me.avery246813579.minersfortune.gamemode.kits.KitHandler;

public class JuggernautDamage extends GamePerk implements Listener{
	public JuggernautDamage(){
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}
	
	@Override
	protected void giveOnSpawn(Player player) {
		
	}

	@Override
	protected void onEnable() {
		
	}

	@Override
	protected void remove() {
		
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		
		if (GameManager.getGameType() != GameType.SearchAndDestroy) {
			return;
		}

		if (GameManager.getGameState() != GameState.Live) {
			return;
		}

		if (GameManager.findPlayer((Player) event.getEntity()).getKit() != KitHandler.kitSDJuggernaut) {
			return;
		}

		if(event.getDamage() == 0){
			return;
		}
		
		event.setDamage((int) (event.getDamage() * .66));
	}
}
