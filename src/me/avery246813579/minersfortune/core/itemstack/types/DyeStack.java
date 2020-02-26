package me.avery246813579.minersfortune.core.itemstack.types;

import java.util.List;

import me.avery246813579.minersfortune.core.itemstack.IItemStack;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class DyeStack extends IItemStack{
	private DyeColor color;
	
	public DyeStack(String name, DyeColor color) {
		super(name, Material.INK_SACK);
		
		this.color = color;
	}
	
	public DyeStack(String name, DyeColor color, List<String> lore){
		super(name, Material.INK_SACK, lore);
		
		this.color = color;
	}
	
	@Override
	public ItemStack getItem() {
		ItemStack itemStack = super.getItem();
		int amount = itemStack.getAmount();
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		Dye dye = new Dye();
		dye.setColor(color);
		itemStack = dye.toItemStack();
		itemStack.setItemMeta(itemMeta);
		itemStack.setAmount(amount);
		
		return itemStack;
	}
}
