package me.avery246813579.minersfortune.core.particles;

import me.avery246813579.minersfortune.core.particles.actions.BoxAction;
import me.avery246813579.minersfortune.core.particles.actions.CloudDripAction;
import me.avery246813579.minersfortune.core.particles.actions.HaloSwirlAction;
import me.avery246813579.minersfortune.core.particles.actions.HeadSwirlAction;
import me.avery246813579.minersfortune.core.particles.actions.RandomAction;
import me.avery246813579.minersfortune.core.particles.actions.VerticalSwirlAction;
import me.avery246813579.minersfortune.core.particles.actions.WalkAction;

public enum IParticleActions {
	VERTICAL_SWIRL(new VerticalSwirlAction()),
	HEAD_SWIRL(new HeadSwirlAction()),
	HALO_SWIRL(new HaloSwirlAction()),
	RANDOM(new RandomAction()),
	WALK(new WalkAction()),
	BOX(new BoxAction()),
	CLOUD_DRIP(new CloudDripAction());
	
	private IParticleAction iParticleAction;
	private IParticleActions(IParticleAction iParticleAction) {
		this.iParticleAction = iParticleAction;
	}
	
	public IParticleAction getiParticleAction() {
		return iParticleAction;
	}
}
