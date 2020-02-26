package me.avery246813579.minersfortune.gamemode.conditions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.util.StatusBarApi;
import me.avery246813579.minersfortune.util.Util;

public class PlayerInvincibility extends GameCondition implements Runnable, Listener {
	private static boolean isOnInvincibility = false;
	int timeLeft, timeState, colorInt;

	public PlayerInvincibility() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	@Override
	public void resetCondition(String conditition) {
		isOnInvincibility = true;
		timeLeft = 45;

		timeState = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), this, 20L, 20L);
	}

	@Override
	public void stopCondition() {
		isOnInvincibility = false;
		Bukkit.getScheduler().cancelTask(timeState);
	}

	@Override
	public void run() {
		if (GameManager.getGameState() == GameState.Live) {
			sendBarMessage("" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + "Invincibility ends in " + timeLeft + " seconds");
		}

		if (timeLeft != 0) {
			timeLeft--;

			if ((timeLeft % 15 == 0 && timeLeft != 45 && timeLeft != 0) || timeLeft == 10 || timeLeft <= 5) {
				broadcastInvincibilityMessage(ChatColor.YELLOW + "Invincibility ends in " + timeLeft + " seconds!");
			}
		} else {
			isOnInvincibility = false;
			broadcastInvincibilityMessage(ChatColor.YELLOW + "Invincibility has ended!");
			Bukkit.getScheduler().cancelTask(timeState);
		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if (GameManager.getGameConditions().contains(this) && GameManager.getGameState() == GameState.Live) {
			if (isOnInvincibility) {
				event.setCancelled(true);
			}
		}
	}

	public void broadcastInvincibilityMessage(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(ChatColor.GREEN + "Invincibility >> " + ChatColor.GRAY + message);
		}
	}

	public void sendBarMessage(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			int version = ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();

			if (version >= 47) {
				StatusBarApi.removeWitherBar(player);
				StatusBarApi.setWitherBar(player, "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + message, 100);
			}else{
				StatusBarApi.setDragonBar(player, "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + message, 100);
			}

			if (colorInt == (Util.getChatColors().size() - 1)) {
				colorInt = 0;
				return;
			}

			colorInt++;
		}
	}

	public static boolean isOnInvincibility() {
		return isOnInvincibility;
	}

	public static void setOnInvincibility(boolean isOnInvincibility) {
		PlayerInvincibility.isOnInvincibility = isOnInvincibility;
	}
}
