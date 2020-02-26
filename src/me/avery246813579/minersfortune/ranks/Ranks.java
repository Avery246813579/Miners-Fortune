package me.avery246813579.minersfortune.ranks;

import org.bukkit.ChatColor;

public enum Ranks {
	/** Ranks **/
	OWNER("Owner", ChatColor.YELLOW ,ChatColor.YELLOW + "[" + ChatColor.WHITE + "Owner" + ChatColor.YELLOW + "] ", 4, 6, true, true),
	HEAD_ADMIN("Head Admin", ChatColor.RED ,ChatColor.RED + "[" + ChatColor.WHITE + "Head Admin" + ChatColor.RED + "] ", 3, 6, true, true),
	ADMIN("Admin", ChatColor.RED ,ChatColor.RED + "[" + ChatColor.WHITE + "Admin" + ChatColor.RED + "] ", 3, 5, true, true),
	HEAD_MODERATOR("Head Moderator", ChatColor.BLUE ,ChatColor.BLUE + "[" + ChatColor.WHITE + "Head Mod" + ChatColor.BLUE + "] ", 2, 5, true, true),
	MODERATOR("Moderator", ChatColor.BLUE ,ChatColor.BLUE + "[" + ChatColor.WHITE + "Mod" + ChatColor.BLUE + "] ", 2, 5, true, true),
	HELPER("Helper", ChatColor.GREEN ,ChatColor.GREEN + "[" + ChatColor.WHITE + "Helper" + ChatColor.GREEN + "] ", 1, 0, true, true),
	EMERALD("Emerald", ChatColor.DARK_GREEN ,ChatColor.DARK_GREEN + "[" + ChatColor.WHITE + "Emerald" + ChatColor.DARK_GREEN + "] ", 0, 4, true, false),
	DIAMOND("Diamond", ChatColor.DARK_AQUA ,ChatColor.DARK_AQUA + "[" + ChatColor.WHITE + "Diamond" + ChatColor.DARK_AQUA + "] ", 0, 3, true, false),
	GOLD("Gold", ChatColor.GOLD ,ChatColor.GOLD + "[" + ChatColor.WHITE + "Gold" + ChatColor.GOLD + "] ", 0, 2, true, false),
	IRON("Iron", ChatColor.DARK_GRAY ,ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "Iron" + ChatColor.DARK_GRAY + "] ", 0, 1, true, false),
	BETA_TESTER("Beta Tester", ChatColor.DARK_GREEN ,ChatColor.DARK_GREEN + "[" + ChatColor.WHITE + "Beta Tester" + ChatColor.DARK_GREEN + "] ", 0, 0, false, false),
	YOUTUBER("Youtuber", ChatColor.WHITE ,ChatColor.WHITE + "[" + ChatColor.DARK_RED + "You" + ChatColor.WHITE + "tuber" + ChatColor.DARK_RED + "] " + ChatColor.WHITE, 0, 3, true, false),
	STREAMER("Twitch Master", ChatColor.LIGHT_PURPLE ,ChatColor.LIGHT_PURPLE + "[" + ChatColor.WHITE + "Twitch Master" + ChatColor.LIGHT_PURPLE + "] ", 0, 3, true, false),
	BUILDER("Builder", ChatColor.DARK_GREEN ,ChatColor.DARK_GREEN + "[" + ChatColor.WHITE + "Builder" + ChatColor.DARK_GREEN + "] ", 0, 0, false, false),
	DEFAULT("Default", ChatColor.GRAY ,ChatColor.GRAY + "", 0, 0, false, false);

	/** Variables **/
	private boolean isRanked, isStaffed;
	private String name, prefix;
	private ChatColor chatColor;
	private int rankNumber, donationNumber;
	
	Ranks(String name, ChatColor chatColor, String prefix, int rankNumber, int donationNumber, boolean isRanked, boolean isStaffed){
		this.name = name;
		this.chatColor = chatColor;
		this.prefix = prefix;
		this.rankNumber = rankNumber;
		this.isRanked = isRanked;
		this.isStaffed = isStaffed;
		this.donationNumber = donationNumber;
	}

	public int getRankNumber() {
		return rankNumber;
	}

	public void setRankNumber(int rankNumber) {
		this.rankNumber = rankNumber;
	}

	public int getDonationNumber(){
		return donationNumber;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRanked() {
		return isRanked;
	}

	public void setRanked(boolean isRanked) {
		this.isRanked = isRanked;
	}

	public boolean isStaffed() {
		return isStaffed;
	}

	public void setStaffed(boolean isStaffed) {
		this.isStaffed = isStaffed;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}

	public void setChatColor(ChatColor chatColor) {
		this.chatColor = chatColor;
	}
}
