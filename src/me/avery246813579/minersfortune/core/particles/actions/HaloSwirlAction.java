package me.avery246813579.minersfortune.core.particles.actions;

import org.bukkit.Location;

import me.avery246813579.minersfortune.core.particles.IParticleAction;
import me.avery246813579.minersfortune.core.particles.IParticleType;

public class HaloSwirlAction extends IParticleAction {
	private int particle = 0, particles = 10;
	float radius = 0.7f;

	public HaloSwirlAction() {
		super(3L);
	}

	@Override
	public void run() {
		/** Getting location where particles will be spawned **/
		Location location = getLocation();

		/** Creating needed varibles for calculations **/
		double angle, x, z;

		/** Increasing the current particle number **/
		particle++;
		if (particle > particles) {
			particle = 0;
		}

		/** Calculating circle location **/
		angle = 2 * Math.PI * particle / particles;
		x = Math.cos(angle) * radius;
		z = Math.sin(angle) * radius;

		/** Added the circle locations and height **/
		if (particleType == IParticleType.HAPPY_VILLAGER) {
			location.add(x, 2, z);
		} else {
			location.add(x, 1.5, z);
		}
		
		/** Displays the particles **/
		particleType.display(location, 0, 0, 0, 1, 1);
	}

	@Override
	public IParticleAction copy() {
		return new HaloSwirlAction();
	}
}
