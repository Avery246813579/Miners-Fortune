package me.avery246813579.minersfortune.util;

import net.md_5.bungee.api.ChatColor;

public class IChatColor {
	static int randomColorCode = 0;
	
	public static ChatColor getRandomColor(){
		/** Increases color code **/
		randomColorCode++;
		
		if(randomColorCode > 16){
			randomColorCode = 1;
		}
		
		/** Returns color depending on the code **/
		switch(randomColorCode){
		case 1: return ChatColor.AQUA;
		case 2: return ChatColor.BLACK;
		case 3: return ChatColor.BLUE;
		case 4: return ChatColor.DARK_AQUA;
		case 5: return ChatColor.DARK_BLUE;
		case 6: return ChatColor.DARK_GRAY;
		case 7: return ChatColor.DARK_GREEN;
		case 8: return ChatColor.DARK_PURPLE;
		case 9: return ChatColor.DARK_RED;
		case 10: return ChatColor.GOLD;
		case 11: return ChatColor.GRAY;
		case 12: return ChatColor.GREEN;
		case 13: return ChatColor.LIGHT_PURPLE;
		case 14: return ChatColor.RED;
		case 15: return ChatColor.WHITE;
		case 16: return ChatColor.YELLOW;
		}
		
		return ChatColor.BLACK;
	}
}
