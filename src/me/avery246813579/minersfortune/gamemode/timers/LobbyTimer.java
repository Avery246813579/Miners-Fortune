package me.avery246813579.minersfortune.gamemode.timers;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameTimer;
import me.avery246813579.minersfortune.util.StatusBarApi;
import me.avery246813579.minersfortune.util.Util;

public class LobbyTimer extends GameTimer {
	int messageColor = 0;

	public LobbyTimer() {
		super(60);
	}

	@Override
	protected void onScheduleEnd(int timeState) {
		if (GameManager.getGameType().getMinPlayers() > Bukkit.getOnlinePlayers().length && !GameManager.isForceStart()) {
			GameManager.broadcastGameMessage("" + ChatColor.RED + ChatColor.BOLD + "Timer cancelled. Reason: Not enough players");
			Bukkit.getScheduler().cancelTask(timeState);
			new LobbyBarTimer(GameManager.getInstance());
			return;
		}

		Bukkit.getScheduler().cancelTask(timeState);
		GameManager.prepareGame();
	}

	@Override
	protected void onRunnableTick(int timeLeft) {
		if ((int) (GameManager.getGameType().getMaxPlayers() * .75) <= Bukkit.getOnlinePlayers().length && this.timeLeft > (int) (timeLeft * .25)) {
			timeLeft = (int) (timeLeft * .25);
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			if ((timeLeft % 15 == 0 && timeLeft != 0) || timeLeft == 10 || timeLeft <= 5) {
				player.sendMessage(ChatColor.GREEN + "Lobby >> " + ChatColor.YELLOW + "Game starting in " + timeLeft + "!");
			}

			int version = ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();

			if (version >= 47) {
				StatusBarApi.removeWitherBar(player);
				StatusBarApi.setWitherBar(player, "" + Util.getChatColors().get(messageColor) + ChatColor.BOLD + "  Match starts in " + timeLeft + " seconds ", 100);
			} else {
				StatusBarApi.setDragonBar(player, "" + Util.getChatColors().get(messageColor) + ChatColor.BOLD + "  Match starts in " + timeLeft + " seconds ", 100);
			}
			
			sendActionBar(player, "" + Util.getChatColors().get(messageColor) + ChatColor.BOLD + "  Match starts in " + timeLeft + " seconds ");

			if (timeLeft <= 5) {
				player.playSound(player.getLocation(), Sound.NOTE_PLING, 2.0F, 1.0F);
			}
		}

		if (messageColor == (Util.getChatColors().size() - 1)) {
			messageColor = 0;
			return;
		}

		messageColor++;
	}

	public static void sendActionBar(Player p, String msg) {
		String s = ChatColor.translateAlternateColorCodes('&', msg);
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}
}
