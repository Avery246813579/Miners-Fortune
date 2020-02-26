package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BadDayEffect extends IParticle{
	RainCloudEffect cloudEffect;
	
	public BadDayEffect(Location location) {
		super(location, IParticleActions.WALK, IParticleType.CLOUD);
		cloudEffect = new RainCloudEffect(location);
	}
	
	public BadDayEffect(Player player) {
		super(player, IParticleActions.WALK, IParticleType.CLOUD);
		cloudEffect = new RainCloudEffect(player);
	}
	
	@Override
	public void startEffect() {
		super.startEffect();

		cloudEffect.startEffect();
	}
	
	@Override
	public void stopEffect() {
		super.stopEffect();

		cloudEffect.stopEffect();
	}
}