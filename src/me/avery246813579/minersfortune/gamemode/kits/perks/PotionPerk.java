package me.avery246813579.minersfortune.gamemode.kits.perks;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.kits.KitHandler;

public class PotionPerk extends GamePerk {
	@Override
	protected void giveOnSpawn(Player player) {
		GamePlayer gamePlayer = GameManager.findPlayer(player);

		if (gamePlayer.getKit() == KitHandler.kitJuggernaut) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1));
		} else if(gamePlayer.getKit() == KitHandler.kitGhost) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 1));
		} else if(gamePlayer.getKit() == KitHandler.kitNinja) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2));
		}
	}
	
	@Override
	protected void remove() {
	}

	@Override
	protected void onEnable() {
		
	}
}
