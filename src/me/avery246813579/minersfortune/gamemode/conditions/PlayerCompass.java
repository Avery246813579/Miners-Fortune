package me.avery246813579.minersfortune.gamemode.conditions;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;

public class PlayerCompass extends GameCondition implements Listener {
	public PlayerCompass() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	@Override
	public void resetCondition(String conditition) {
	}

	@Override
	public void stopCondition() {
		
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getItem() == null){
			return;
		}
		
		if(!(event.getItem().getType() == Material.COMPASS)){
			return;
		}
		
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			if(!GameManager.findPlayer(p).isAlive()){
				return;
			}
			
			double distance = 10000.0D;
			Player victim = null;
			List<Player> getAlivePlayers = new ArrayList<Player>();
			for(Player player : Bukkit.getOnlinePlayers()){
				if(GameManager.findPlayer(player).isAlive()){
					getAlivePlayers.add(player);
				}
			}
			
			for (Player tracking : getAlivePlayers) {
				double hisDist = tracking.getPlayer().getLocation().distance(p.getLocation());
				if (GameManager.findPlayer(tracking).isAlive() && (hisDist < distance) && (hisDist > 15.0D)) {
					victim = tracking.getPlayer();
					distance = tracking.getPlayer().getLocation().distance(p.getLocation());
				}
			}
			if (victim != null) {
				p.sendMessage(ChatColor.GREEN + "Tracking >> " + ChatColor.YELLOW + "Tracking player " + victim.getName() + "!  They are " + (int) distance + " blocks away!");
				p.setCompassTarget(victim.getLocation().clone());
			} else {
				p.sendMessage(ChatColor.GREEN + "Tracking >> " + ChatColor.YELLOW + "No players in range, Pointing to spawn");
				p.setCompassTarget(p.getWorld().getSpawnLocation());
			}
		}
	}
	
	public void sendNearestPlayer(Player player){
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			if(!GameManager.findPlayer(player).isAlive()){
				return;
			}
			
			double distance = 10000.0D;
			Player victim = null;
			List<Player> getAlivePlayers = new ArrayList<Player>();
			for(Player players : Bukkit.getOnlinePlayers()){
				if(GameManager.findPlayer(players).isAlive()){
					getAlivePlayers.add(players);
				}
			}
			
			for (Player tracking : getAlivePlayers) {
				double hisDist = tracking.getPlayer().getLocation().distance(player.getLocation());
				if (GameManager.findPlayer(tracking).isAlive() && (hisDist < distance) && (hisDist > 15.0D)) {
					victim = tracking.getPlayer();
					distance = tracking.getPlayer().getLocation().distance(player.getLocation());
				}
			}
			if (victim != null) {
				sendActionBar(player, ChatColor.GREEN + "Tracking >> " + ChatColor.YELLOW + "Tracking player " + victim.getName() + "!  They are " + (int) distance + " blocks away!");
				player.setCompassTarget(victim.getLocation().clone());
			} else {
				sendActionBar(player, ChatColor.GREEN + "Tracking >> " + ChatColor.YELLOW + "No players in range, Pointing to spawn");
				player.setCompassTarget(player.getWorld().getSpawnLocation());
			}
		}
	}
	
	public static void sendActionBar(Player p, String msg) {
		String s = ChatColor.translateAlternateColorCodes('&', msg);
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}
}
