package me.avery246813579.minersfortune.core.particles.types;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

public class LavaCloudEffect extends IParticle{
	public LavaCloudEffect(Player player) {
		super(player, IParticleActions.CLOUD_DRIP, IParticleType.DRIP_LAVA);
	}
	
	public LavaCloudEffect(Location location) {
		super(location, IParticleActions.CLOUD_DRIP, IParticleType.DRIP_LAVA);
	}
}
