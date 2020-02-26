package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AngrySwirlEffect extends IParticle{
	public AngrySwirlEffect(Location location) {
		super(location, IParticleActions.HEAD_SWIRL, IParticleType.ANGRY_VILLAGER);
	}
	
	public AngrySwirlEffect(Player player) {
		super(player, IParticleActions.HEAD_SWIRL, IParticleType.ANGRY_VILLAGER);
	}
}