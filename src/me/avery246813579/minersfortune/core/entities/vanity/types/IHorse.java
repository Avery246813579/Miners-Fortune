package me.avery246813579.minersfortune.core.entities.vanity.types;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.inventory.ItemStack;

import me.avery246813579.minersfortune.core.entities.IEntity;

public class IHorse extends IEntity{
	private Variant variant = Variant.HORSE;
	private Color color = Color.GRAY;
	private Style style = Style.NONE;
	private double strength = 2.0D;
	private boolean chest, baby, ridable = true;
	private ItemStack armor;
	
	public IHorse(String name, Variant variant) {
		super(name, EntityType.HORSE);
	
		this.variant = variant;
	}
	
	public IHorse(String name, Variant variant, boolean baby) {
		super(name, EntityType.HORSE);
	
		this.variant = variant;
		this.baby = baby;
	}
	
	@Override
	public void spawn(Location location) {
		LivingEntity entity = (LivingEntity) location.getWorld().spawnEntity(location, getType());
		
		if(getName() != null){
			entity.setCustomNameVisible(true);
			entity.setCustomName(getName());
		}
		
		entity.setCanPickupItems(isCanPickupItems());
		
		Horse horse = (Horse) entity;
		horse.setVariant(variant);
		
		if(variant == Variant.HORSE){
			horse.setColor(color);
			horse.setStyle(style);
			horse.setJumpStrength(strength);
		}
		
		if(ridable){
			horse.setTamed(true);
			horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
			
			if(armor != null){
				horse.getInventory().setArmor(armor);
			}
		}
		
		if(baby){
			horse.setBaby();
		}else{
			horse.setAdult();
		}
		
		horse.setAgeLock(true);
		setEntity(horse);
	}

	public ItemStack getArmor() {
		return armor;
	}

	public void setArmor(ItemStack armor) {
		this.armor = armor;
	}

	public Variant getVariant() {
		return variant;
	}

	public void setVariant(Variant variant) {
		this.variant = variant;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public boolean isBaby() {
		return baby;
	}

	public void setBaby(boolean baby) {
		this.baby = baby;
	}

	public boolean isRidable() {
		return ridable;
	}

	public void setRidable(boolean ridable) {
		this.ridable = ridable;
	}

	public boolean isChest() {
		return chest;
	}

	public void setChest(boolean chest) {
		this.chest = chest;
	}
}
