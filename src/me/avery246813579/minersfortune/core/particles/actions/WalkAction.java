package me.avery246813579.minersfortune.core.particles.actions;

import org.bukkit.Location;

import me.avery246813579.minersfortune.core.particles.IParticleAction;

public class WalkAction extends IParticleAction {
	public WalkAction() {
		super(5L);
	}

	@Override
	public void run() {
		Location location1 = player.getLocation().add(0, 0, 0);
		Location location6 = player.getLocation().add(0, 0, .5);
		Location location5 = player.getLocation().add(0, 0, -.5);
		Location location2 = player.getLocation().add(.5, 0, 0);
		Location location3 = player.getLocation().add(.5, 0, .5);
		Location location4 = player.getLocation().add(.5, 0, -.5);
		Location location7 = player.getLocation().add(-.5, 0, 0);
		Location location8 = player.getLocation().add(-.5, 0, .5);
		Location location9 = player.getLocation().add(-.5, 0, -.5);

		particleType.display(location1, 0, 0, 0, 1, 1);
		particleType.display(location2, 0, 0, 0, 1, 1);
		particleType.display(location3, 0, 0, 0, 1, 1);
		particleType.display(location4, 0, 0, 0, 1, 1);
		particleType.display(location5, 0, 0, 0, 1, 1);
		particleType.display(location6, 0, 0, 0, 1, 1);
		particleType.display(location7, 0, 0, 0, 1, 1);
		particleType.display(location8, 0, 0, 0, 1, 1);
		particleType.display(location9, 0, 0, 0, 1, 1);
	}

	@Override
	public IParticleAction copy() {
		return new WalkAction();
	}
}
