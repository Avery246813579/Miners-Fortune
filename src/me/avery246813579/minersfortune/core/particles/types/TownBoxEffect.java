package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TownBoxEffect extends IParticle{
	public TownBoxEffect(Location location) {
		super(location, IParticleActions.BOX, IParticleType.TOWN_AURA);
	}
	
	public TownBoxEffect(Player player) {
		super(player, IParticleActions.BOX, IParticleType.TOWN_AURA);
	}
}