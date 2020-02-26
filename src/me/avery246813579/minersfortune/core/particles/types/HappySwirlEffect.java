package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HappySwirlEffect extends IParticle{
	public HappySwirlEffect(Location location) {
		super(location, IParticleActions.HALO_SWIRL, IParticleType.HAPPY_VILLAGER);
	}
	
	public HappySwirlEffect(Player player) {
		super(player, IParticleActions.HALO_SWIRL, IParticleType.HAPPY_VILLAGER);
	}
}