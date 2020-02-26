package me.avery246813579.minersfortune.core.itemstack;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IItemStack {
	private List<Enchantment> enchants = new ArrayList<Enchantment>();
	private List<String> lore = new ArrayList<String>();
	private Material material;
	private double durability;
	private String name;
	private int amount;
	
	public IItemStack(String name, Material material) {
		this.name = name;
		this.material = material;
	}
	
	public IItemStack(String name, Material material, List<String> lore){
		this.name = name;
		this.material = material;
		this.lore = lore;
	}
	
	public ItemStack getItem(){
		ItemStack itemStack = new ItemStack(material);
		if(amount > 1){
			itemStack.setAmount(amount);
		}
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		
		if(!lore.isEmpty()){
			itemMeta.setLore(lore);
		}
		
		if(!enchants.isEmpty()){
			for(Enchantment enchantment : enchants){
				itemMeta.addEnchant(enchantment, 1, true);
			}
		}
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public List<Enchantment> getEnchants() {
		return enchants;
	}
	
	public void setEnchants(List<Enchantment> enchants) {
		this.enchants = enchants;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getDurability() {
		return durability;
	}

	public void setDurability(double durability) {
		this.durability = durability;
	}
}
