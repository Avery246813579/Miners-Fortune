package me.avery246813579.minersfortune.core.itemstack.types;

import java.util.List;

import me.avery246813579.minersfortune.core.itemstack.IItemStack;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.SpawnEgg;

public class EggStack extends IItemStack{
	private EntityType type;
	
	public EggStack(String name, EntityType type) {
		super(name, Material.MONSTER_EGG);
		
		this.type = type;
	}
	
	public EggStack(String name, EntityType type, List<String> lore){
		super(name, Material.MONSTER_EGG, lore);
		
		this.type = type;
	}
	
	@Override
	public ItemStack getItem() {
		ItemStack itemStack = super.getItem();
		int amount = itemStack.getAmount();
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		SpawnEgg egg = new SpawnEgg();
		egg.setSpawnedType(type);
		itemStack = egg.toItemStack();
		itemStack.setAmount(amount);
		
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
