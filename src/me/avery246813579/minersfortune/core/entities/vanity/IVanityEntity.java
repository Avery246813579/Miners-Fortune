package me.avery246813579.minersfortune.core.entities.vanity;

import me.avery246813579.minersfortune.core.entities.IEntity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class IVanityEntity{
	private Player owner;
	private IEntity IEntity;
	
	public IVanityEntity(IEntity iEntity, Player owner){
		this.owner = owner;
		this.IEntity = iEntity;
		
		IVanityEntityHandler.getiVanityEntities().add(this);
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public IEntity getIEntity() {
		return IEntity;
	}

	public void setIEntity(IEntity iEntity) {
		IEntity = iEntity;
	}
	
	public Entity getEntity(){
		return IEntity.getEntity();
	}
}
