package me.avery246813579.minersfortune.core.particles.types;

import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.particles.IParticleActions;
import me.avery246813579.minersfortune.core.particles.IParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GodEffect extends IParticle{
	MagicBoxEffect magicEffect;
	HeartSwirlEffect heartSwirlEffect;
	HappySwirlEffect happySwirlEffect;
	
	public GodEffect(Location location) {
		super(location, IParticleActions.WALK, IParticleType.INSTANT_SPELL);
		magicEffect = new MagicBoxEffect(location);
		heartSwirlEffect = new HeartSwirlEffect(location);
		happySwirlEffect = new HappySwirlEffect(location);
	}
	
	public GodEffect(Player player) {
		super(player, IParticleActions.WALK, IParticleType.INSTANT_SPELL);
		magicEffect = new MagicBoxEffect(player);
		heartSwirlEffect = new HeartSwirlEffect(player);
		happySwirlEffect = new HappySwirlEffect(player);
	}
	
	@Override
	public void startEffect() {
		super.startEffect();

		magicEffect.startEffect();
		heartSwirlEffect.startEffect();
		happySwirlEffect.startEffect();
	}
	
	@Override
	public void stopEffect() {
		super.stopEffect();

		magicEffect.stopEffect();
		heartSwirlEffect.stopEffect();
		happySwirlEffect.stopEffect();
	}
}