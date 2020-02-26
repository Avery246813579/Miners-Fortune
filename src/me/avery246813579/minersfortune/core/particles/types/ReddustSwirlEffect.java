package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ReddustSwirlEffect extends IParticle{
	public ReddustSwirlEffect(Location location) {
		super(location, IParticleActions.VERTICAL_SWIRL, IParticleType.RED_DUST);
	}
	
	public ReddustSwirlEffect(Player player) {
		super(player, IParticleActions.VERTICAL_SWIRL, IParticleType.RED_DUST);
	}
}