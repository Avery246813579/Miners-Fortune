package me.avery246813579.minersfortune.sql.tables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.avery246813579.minersfortune.MinersFortune;

public class BanTable {
	private int player_id;
	private String banned, muted;
	
	public BanTable(int player_id, String banned, String muted){
		this.player_id = player_id;
		this.banned = banned;
		this.muted = muted;
	}
	
	public boolean isMuted(){
		String[] mutedString = muted.split(" ");
		
		if(mutedString[0].equalsIgnoreCase("false")){
			return false;
		}
		
		if(mutedString[1].equalsIgnoreCase("forever")){
			return true;
		}
		
		Date date = new Date();
		if(getDate(mutedString[1]).before(date)){
			muted = "false";
			MinersFortune.getPlugin().getSqlHandler().saveBan(this);
			return false;
		}
		
		return true;
	}
	
	public boolean isBanned(){
		String[] bannedString = banned.split(" ");
		
		if(bannedString[0].equalsIgnoreCase("false")){
			return false;
		}
		
		if(bannedString[1].equalsIgnoreCase("forever")){
			return true;
		}
		
		Date date = new Date();
		if(getDate(bannedString[1]).before(date)){
			banned = "false";
			MinersFortune.getPlugin().getSqlHandler().saveBan(this);
			return false;
		}
		
		return true;
	}
	
	public String getBanMessage(){
		String[] bannedString = banned.split(" ");
		
		StringBuilder string = new StringBuilder();
		for (int x = 2; x < bannedString.length; x++) {

			string.append(bannedString[x]);

			if (x != bannedString.length - 1) {
				string.append(" ");
			}
		}
		
		return string.toString();
	}
	
	public String getMuteMessage(){
		String[] mutedString = muted.split(" ");
		
		StringBuilder string = new StringBuilder();
		for (int x = 2; x < mutedString.length; x++) {

			string.append(mutedString[x]);

			if (x != mutedString.length - 1) {
				string.append(" ");
			}
		}

		return string.toString();
	}
	
	public static Date getDate(String s) {
		SimpleDateFormat df = new SimpleDateFormat("HH/mm/MM/dd/yy");
		Date d = null;
		try {
			d = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getBanned() {
		return banned;
	}

	public void setBanned(String banned) {
		this.banned = banned;
	}

	public String getMuted() {
		return muted;
	}

	public void setMuted(String muted) {
		this.muted = muted;
	}
}
