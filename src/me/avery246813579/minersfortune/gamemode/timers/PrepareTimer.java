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

public class PrepareTimer extends GameTimer {
	int messageColor = 0;

	public PrepareTimer() {
		super(10);
	}

	@Override
	protected void onScheduleEnd(int timeState) {
		Bukkit.getScheduler().cancelTask(timeState);
		GameManager.startGame();
	}

	@Override
	protected void onRunnableTick(int timeLeft) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			int version = ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();

			if (version >= 47) {
				StatusBarApi.removeWitherBar(player);
				StatusBarApi.setWitherBar(player, "" + Util.getChatColors().get(messageColor) + ChatColor.BOLD + "Match Starting - " + timeLeft + " seconds", 100);
			}else{
				StatusBarApi.setDragonBar(player, "" + Util.getChatColors().get(messageColor) + ChatColor.BOLD + "Match Starting - " + timeLeft + " seconds", 100);
			}
			
			sendActionBar(player, "" + Util.getChatColors().get(messageColor) + ChatColor.BOLD + "Match Starting - " + timeLeft + " seconds");
			
			if (timeLeft <= 5) {
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
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
