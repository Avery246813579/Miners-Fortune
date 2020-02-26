package me.avery246813579.minersfortune.core.particles.actions;

import org.bukkit.Location;

import me.avery246813579.minersfortune.core.particles.IParticleAction;

public class VerticalSwirlAction extends IParticleAction {
	private int particle = 0, height = 0, particles = 10;
	float radius = 0.7f;
	boolean rising;

	public VerticalSwirlAction() {
		super(5L);
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

		/** Sets the height of the particle **/
		if (rising) {
			height++;
		} else {
			height--;
		}

		/** Makes sure the height of the particle is correct **/
		if (height > 3) {
			rising = false;
			height = 3;
		}

		if (height < -5) {
			rising = true;
			height = -5;
		}

		/** Calculating circle location **/
		angle = 2 * Math.PI * particle / particles;
		x = Math.cos(angle) * radius;
		z = Math.sin(angle) * radius;
		
		/** Added the circle locations and height **/
		location.add(x, 1 + (0.2 * height), z);
		
		/** Displays the particles **/
		particleType.display(location, 0, 0, 0, 1, 1);
	}

	@Override
	public IParticleAction copy() {
		return new VerticalSwirlAction();
	}
}
