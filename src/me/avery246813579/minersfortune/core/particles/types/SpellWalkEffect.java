package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpellWalkEffect extends IParticle{
	public SpellWalkEffect(Location location) {
		super(location, IParticleActions.WALK, IParticleType.INSTANT_SPELL);
	}
	
	public SpellWalkEffect(Player player) {
		super(player, IParticleActions.WALK, IParticleType.INSTANT_SPELL);
	}
}