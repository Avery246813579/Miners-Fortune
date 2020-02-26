package me.avery246813579.minersfortune.core.players;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.core.entities.vanity.IVanityEntity;
import me.avery246813579.minersfortune.core.particles.IParticle;
import me.avery246813579.minersfortune.core.perk.IPerk;
import me.avery246813579.minersfortune.core.players.perks.IMountPerks;

import org.bukkit.entity.Player;

public class IPlayer {
	private List<IPerk> IPerks = new ArrayList<IPerk>();
	private IParticle iParticle;
	
	/** Mount Stuff **/
	private IVanityEntity mount;
	private IMountPerks mountPerks;
	
	private Player player;
	
	public IPlayer(Player player){
		this.player = player;
		mountPerks = new IMountPerks(this);
		
		IPlayerHandler.addPlayer(this);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public IParticle getiParticle() {
		return iParticle;
	}

	public void setiParticle(IParticle iParticle) {
		this.iParticle = iParticle;
	}

	public List<IPerk> getIPerks() {
		return IPerks;
	}

	public void setIPerks(List<IPerk> iPerks) {
		IPerks = iPerks;
	}

	public IVanityEntity getMount() {
		return mount;
	}

	public void setMount(IVanityEntity mount) {
		this.mount = mount;
	}

	public IMountPerks getMountPerks() {
		return mountPerks;
	}

	public void setMountPerks(IMountPerks mountPerks) {
		this.mountPerks = mountPerks;
	}
}
