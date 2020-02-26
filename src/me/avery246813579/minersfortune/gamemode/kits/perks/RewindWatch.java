package me.avery246813579.minersfortune.gamemode.kits.perks;

import java.util.HashMap;
import java.util.LinkedList;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameType;
import me.avery246813579.minersfortune.gamemode.kits.KitHandler;

public class RewindWatch extends GamePerk implements Runnable, Listener {
	public static HashMap<Player, LinkedList<Location>> rewind = new HashMap<Player, LinkedList<Location>>();
	public static HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	private int timeState;

	@Override
	protected void giveOnSpawn(Player player) {
	}

	public RewindWatch() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	@Override
	protected void onEnable() {
		rewind.clear();
		cooldown.clear();
		timeState = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), this, 20L, 20L);
	}

	@Override
	protected void remove() {
		Bukkit.getScheduler().cancelTask(timeState);
		rewind.clear();
		cooldown.clear();
	}

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			LinkedList<Location> playerHistory = (LinkedList<Location>) rewind.get(player);

			if (playerHistory == null) {
				playerHistory = new LinkedList<Location>();
				rewind.put(player, playerHistory);
				cooldown.put(player, Integer.valueOf(30));
			}

			if (((Integer) cooldown.get(player)).intValue() > 0) {
				int i = ((Integer) cooldown.get(player)).intValue();
				i--;
				cooldown.put(player, Integer.valueOf(i));
			}

			playerHistory.add(player.getLocation());
			if (playerHistory.size() > 30)
				playerHistory.remove();
		}
	}

	@EventHandler
	public void onInteracttingEvent(PlayerInteractEvent event) {
		if (GameManager.getGameType() != GameType.SearchAndDestroy) {
			return;
		}

		if (GameManager.getGameState() != GameState.Live) {
			return;
		}
		if (GameManager.findPlayer(event.getPlayer()).getKit() != KitHandler.kitRewind) {
			return;
		}

		if (event.getPlayer().getItemInHand().getType() != Material.WATCH) {
			return;
		}
		LinkedList<Location> history = (LinkedList<Location>) rewind.get(event.getPlayer());
		if ((history != null) && (history.size() > 0) && (((Integer) cooldown.get(event.getPlayer())).intValue() <= 0)) {
			event.getPlayer().teleport((Location) history.remove());
			event.getPlayer().sendMessage(ChatColor.GREEN + "Rewind >> " + ChatColor.YELLOW + "You have been teleported back in time.");
			history.clear();
			cooldown.put(event.getPlayer(), Integer.valueOf(30));
		} else {
			event.getPlayer().sendMessage(ChatColor.GREEN + "Rewind >> " + ChatColor.YELLOW + "You have to wait " + cooldown.get(event.getPlayer()) + " more seconds to use this.");
		}
	}

}
