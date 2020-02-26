package me.avery246813579.minersfortune.core.particles;

import me.avery246813579.minersfortune.MinersFortune;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class IParticleAction implements Runnable{
	private int runState;
	private long runTicks = 5L;
	protected Player player;
	protected Location location;
	protected IParticleType particleType;
	
	public abstract IParticleAction copy();
	
	public void loadDetails(Location location, IParticleType particleType){
		this.location = location;
		this.particleType = particleType;
	}
	
	public void loadDetails(Player player, IParticleType particleType){
		this.player = player;
		this.particleType = particleType;
	}
	
	public Location getLocation(){
		if(location != null){
			return location.clone();
		}else{
			return player.getLocation().clone();
		}
	}
	
	public IParticleAction(long runTicks){	
		this.runTicks = runTicks;
	}
	
	public void start(){
		runState = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), this, runTicks, runTicks);
	}
	
	public void stop(){
		Bukkit.getScheduler().cancelTask(runState);
	}
}
