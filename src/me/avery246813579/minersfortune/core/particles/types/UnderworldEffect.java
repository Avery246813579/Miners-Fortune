package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class UnderworldEffect extends IParticle{
	LavaCloudEffect cloudEffect;
	AngrySwirlEffect angrySwirlEffect;
	
	public UnderworldEffect(Location location) {
		super(location, IParticleActions.WALK, IParticleType.LARGE_SMOKE);
		cloudEffect = new LavaCloudEffect(location);
		angrySwirlEffect = new AngrySwirlEffect(location);
	}
	
	public UnderworldEffect(Player player) {
		super(player, IParticleActions.WALK, IParticleType.LARGE_SMOKE);
		cloudEffect = new LavaCloudEffect(player);
		angrySwirlEffect = new AngrySwirlEffect(player);
	}
	
	@Override
	public void startEffect() {
		super.startEffect();

		cloudEffect.startEffect();
		angrySwirlEffect.startEffect();
	}
	
	@Override
	public void stopEffect() {
		super.stopEffect();

		cloudEffect.stopEffect();
		angrySwirlEffect.stopEffect();
	}
}