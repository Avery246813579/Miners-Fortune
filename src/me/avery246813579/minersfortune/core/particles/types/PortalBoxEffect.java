package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PortalBoxEffect extends IParticle{
	public PortalBoxEffect(Location location) {
		super(location, IParticleActions.BOX, IParticleType.PORTAL);
	}
	
	public PortalBoxEffect(Player player) {
		super(player, IParticleActions.BOX, IParticleType.PORTAL);
	}
}