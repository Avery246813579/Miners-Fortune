package me.avery246813579.minersfortune.core.particles.actions;

import me.avery246813579.minersfortune.core.particles.IParticleAction;

import org.bukkit.Location;

public class BoxAction extends IParticleAction {
	public BoxAction() {
		super(5L);
	}

	@Override
	public void run() {
		Location location1 = player.getLocation().add(0, 2.5, 0);
		Location location2 = player.getLocation().add(0, 2.5, .5);
		Location location3 = player.getLocation().add(0, 2.5, -.5);
		Location location4 = player.getLocation().add(.5, 2.5, 0);
		Location location5 = player.getLocation().add(.5, 2.5, .5);
		Location location6 = player.getLocation().add(.5, 2.5, -.5);
		Location location7 = player.getLocation().add(-.5, 2.5, 0);
		Location location8 = player.getLocation().add(-.5, 2.5, .5);
		Location location9 = player.getLocation().add(-.5, 2.5, -.5);
		Location location10 = player.getLocation().add(0, 1.5, 0);
		Location location11 = player.getLocation().add(0, 1.5, .5);
		Location location12 = player.getLocation().add(0, 1.5, -.5);
		Location location13 = player.getLocation().add(.5, 1.5, 0);
		Location location14 = player.getLocation().add(.5, 1.5, .5);
		Location location15 = player.getLocation().add(.5, 1.5, -.5);
		Location location16 = player.getLocation().add(-.5, 1.5, 0);
		Location location17 = player.getLocation().add(-.5, 1.5, .5);
		Location location18 = player.getLocation().add(-.5, 1.5, -.5);
		Location location19 = player.getLocation().add(0, 0.5, 0);
		Location location20 = player.getLocation().add(0, 0.5, .5);
		Location location21 = player.getLocation().add(0, 0.5, -.5);
		Location location22 = player.getLocation().add(.5, 0.5, 0);
		Location location23 = player.getLocation().add(.5, 0.5, .5);
		Location location24 = player.getLocation().add(.5, 0.5, -.5);
		Location location25 = player.getLocation().add(-.5, 0.5, 0);
		Location location26 = player.getLocation().add(-.5, 0.5, .5);
		Location location27 = player.getLocation().add(-.5, 0.5, -.5);

		particleType.display(location1, 0, 0, 0, 1, 1);
		particleType.display(location10, 0, 0, 0, 1, 1);
		particleType.display(location19, 0, 0, 0, 1, 1);
		particleType.display(location2, 0, 0, 0, 1, 1);
		particleType.display(location11, 0, 0, 0, 1, 1);
		particleType.display(location20, 0, 0, 0, 1, 1);
		particleType.display(location3, 0, 0, 0, 1, 1);
		particleType.display(location12, 0, 0, 0, 1, 1);
		particleType.display(location21, 0, 0, 0, 1, 1);
		particleType.display(location4, 0, 0, 0, 1, 1);
		particleType.display(location13, 0, 0, 0, 1, 1);
		particleType.display(location22, 0, 0, 0, 1, 1);
		particleType.display(location5, 0, 0, 0, 1, 1);
		particleType.display(location14, 0, 0, 0, 1, 1);
		particleType.display(location23, 0, 0, 0, 1, 1);
		particleType.display(location6, 0, 0, 0, 1, 1);
		particleType.display(location15, 0, 0, 0, 1, 1);
		particleType.display(location24, 0, 0, 0, 1, 1);
		particleType.display(location7, 0, 0, 0, 1, 1);
		particleType.display(location16, 0, 0, 0, 1, 1);
		particleType.display(location25, 0, 0, 0, 1, 1);
		particleType.display(location8, 0, 0, 0, 1, 1);
		particleType.display(location17, 0, 0, 0, 1, 1);
		particleType.display(location26, 0, 0, 0, 1, 1);
		particleType.display(location9, 0, 0, 0, 1, 1);
		particleType.display(location18, 0, 0, 0, 1, 1);
		particleType.display(location27, 0, 0, 0, 1, 1);
	}

	@Override
	public IParticleAction copy() {
		return new RandomAction();
	}
}
