package me.avery246813579.minersfortune.core.players.perks;

import me.avery246813579.minersfortune.core.players.IPlayer;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;

public class IMountPerks {
	private IPlayer iPlayer;
	private String name;
	private Variant variant = Variant.HORSE;
	private Style style = Style.NONE;
	private Color color = Color.BROWN;
	private double jumpHeight = 2D;
	private boolean chest = false , baby = false;
	private Material armor;
	
	public IMountPerks(IPlayer iPlayer){
		this.iPlayer = iPlayer;
	
		this.name = ChatColor.YELLOW + iPlayer.getPlayer().getName() + "'s Mount";
	}

	public IPlayer getiPlayer() {
		return iPlayer;
	}

	public void setiPlayer(IPlayer iPlayer) {
		this.iPlayer = iPlayer;
	}

	public Variant getVariant() {
		return variant;
	}

	public void setVariant(Variant variant) {
		this.variant = variant;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChest() {
		return chest;
	}

	public void setChest(boolean chest) {
		this.chest = chest;
	}

	public boolean isBaby() {
		return baby;
	}

	public void setBaby(boolean baby) {
		this.baby = baby;
	}

	public double getJumpHeight() {
		return jumpHeight;
	}

	public void setJumpHeight(double jumpHeight) {
		this.jumpHeight = jumpHeight;
	}

	public Material getArmor() {
		return armor;
	}

	public void setArmor(Material armor) {
		this.armor = armor;
	}
}
