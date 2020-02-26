package me.avery246813579.minersfortune.core.players;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.MinersFortune;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class IPlayerHandler implements Listener{
	public static List<IPlayer> IPlayers = new ArrayList<IPlayer>();
	
	public IPlayerHandler(){
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}
	
	public static IPlayer findIPlayer(Player player){
		for(IPlayer iPlayer : IPlayers){
			if(iPlayer.getPlayer() == player){
				return iPlayer;
			}
		}
		
		return null;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event){
		IPlayer iPlayer = findIPlayer(event.getPlayer());
		
		if(iPlayer.getiParticle() != null){
			iPlayer.getiParticle().stopEffect();
		}
		
		IPlayers.remove(iPlayer);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event){
		new IPlayer(event.getPlayer());
	}
	
	
	public static void addPlayer(IPlayer iPlayer){
		IPlayers.add(iPlayer);
	}
	
	public void removePlayer(Player player){
		
	}
}
