package me.avery246813579.minersfortune.gamemode;

import org.bukkit.ChatColor;

public enum GameTeamTypes {
	RED(ChatColor.RED, (byte) 14),
	BLUE(ChatColor.BLUE, (byte) 11),
	SOLO(ChatColor.GOLD, (byte) 4);
	
	private ChatColor chatColor;
	private byte color;
	
	private GameTeamTypes(ChatColor chatColor, byte color) {
		this.chatColor = chatColor;
		this.color = color;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}

	public void setChatColor(ChatColor chatColor) {
		this.chatColor = chatColor;
	}

	public byte getColor() {
		return color;
	}

	public void setColor(byte color) {
		this.color = color;
	}
}
