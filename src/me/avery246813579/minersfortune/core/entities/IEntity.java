package me.avery246813579.minersfortune.core.entities;

import net.minecraft.server.v1_7_R4.EntityInsentient;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public abstract class IEntity {
	private Entity entity;
	private String name;
	private EntityType type;
	private boolean canPickupItems = false;

	public IEntity(String name, EntityType type){
		this.name = name;
		this.type = type;
	}
	
	public abstract void spawn(Location location);

	public void despawn() {
		entity.remove();
	}

	public boolean moveTo(Location target, float speed) {
		return ((EntityInsentient) ((CraftLivingEntity) entity).getHandle()).getNavigation().a(target.getX(), target.getY(), target.getZ(), speed);
	}
	
	public boolean isOutsideDistance(Location entity, int distance){
		if(entity.distance(this.entity.getLocation()) > distance) {
			return true;
		}
		
		return false;
	}
	
	public void setEntity(Entity entity){
		this.entity = entity;
	}
	
	public Entity getEntity(){
		return entity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public boolean isCanPickupItems() {
		return canPickupItems;
	}

	public void setCanPickupItems(boolean canPickupItems) {
		this.canPickupItems = canPickupItems;
	}
}
