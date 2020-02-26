package me.avery246813579.minersfortune.core;

import org.bukkit.Bukkit;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.entities.vanity.IVanityEntityHandler;
import me.avery246813579.minersfortune.core.menus.IMenus;
import me.avery246813579.minersfortune.core.menus.TextInputHelper;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboardHandler;
import me.avery246813579.minersfortune.core.scoreboard.types.gamestate.EndScoreboard;
import me.avery246813579.minersfortune.core.scoreboard.types.gamestate.LimbowScoreboard;
import me.avery246813579.minersfortune.core.scoreboard.types.gamestate.PrepareScoreboard;
import me.avery246813579.minersfortune.core.scoreboard.types.gamestate.RecruitScoreboard;
import me.avery246813579.minersfortune.core.scoreboard.types.gametype.ArcherGamesScoreboard;
import me.avery246813579.minersfortune.core.scoreboard.types.gametype.DominationScoreboard;
import me.avery246813579.minersfortune.core.scoreboard.types.gametype.SearchAndDestroyScoreboard;
import me.avery246813579.minersfortune.core.scoreboard.types.gametype.SurvivalGamesScoreboard;

public class CoreLoader {
	public static TextInputHelper textInputHelper = new TextInputHelper();
	
	public CoreLoader(){
		/** Loads Scoreboards **/
		new ArcherGamesScoreboard();
		new SurvivalGamesScoreboard();
		new DominationScoreboard();
		new SearchAndDestroyScoreboard();
		
		new LimbowScoreboard();
		new RecruitScoreboard();
		new PrepareScoreboard();
		new EndScoreboard();
		
		/** Loading Players **/
		new IPlayerHandler();
		
		/** Loads Menus **/
		new IMenus();
		
		/** Loads Vanity Entities **/
		new IVanityEntityHandler();
		
		/** Loads TIH **/
	    Bukkit.getServer().getPluginManager().registerEvents(textInputHelper, MinersFortune.getPlugin());
	    Bukkit.getServer().getPluginManager().registerEvents(new IScoreboardHandler(), MinersFortune.getPlugin());
	}
}
