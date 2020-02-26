package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NoteSwirlEffect extends IParticle{
	public NoteSwirlEffect(Location location) {
		super(location, IParticleActions.VERTICAL_SWIRL, IParticleType.NOTE);
	}
	
	public NoteSwirlEffect(Player player) {
		super(player, IParticleActions.VERTICAL_SWIRL, IParticleType.NOTE);
	}
}