package me.avery246813579.minersfortune.core.particles;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class IParticle {
	protected IParticleAction ParticleAction;
	protected IParticleType ParticleType;
	protected Location location;
	protected Player player;
	
	public IParticle(Player player, IParticleActions iParticleActions, IParticleType iParticleType){
		this.player = player;
		this.ParticleAction = iParticleActions.getiParticleAction().copy();
		this.ParticleType = iParticleType;
		
		ParticleAction.loadDetails(player, iParticleType);
	}
	
	public IParticle(Location location, IParticleActions iParticleActions, IParticleType iParticleType){
		this.location = location;
		this.ParticleAction = iParticleActions.getiParticleAction().copy();
		this.ParticleType = iParticleType;
		
		ParticleAction.loadDetails(location, iParticleType);
	}
	
	public void startEffect(){
		ParticleAction.start();
	}
	
	public void stopEffect(){
		ParticleAction.stop();
	}
}
