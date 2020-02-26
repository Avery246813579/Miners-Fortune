package me.avery246813579.minersfortune.gamemode.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameTimer;
import me.avery246813579.minersfortune.util.StatusBarApi;
import me.avery246813579.minersfortune.util.Util;

public class LobbyBarTimer extends GameTimer {
	/** Classes **/
	GameManager gameManager;

	/** Variables **/
	int colorInt = 0;

	public LobbyBarTimer(GameManager gameManager) {
		super(1);
		this.gameManager = gameManager;
	}

	@Override
	protected void onScheduleEnd(int timeState) {
		updateBar();

		if (gameManager.checkRecruiting() || GameManager.isForceStart()) {
			Bukkit.getScheduler().cancelTask(timeState);
			gameManager.stopRecruiting(timeState);
			return;
		}

		timeLeft = 1;
	}

	@Override
	protected void onRunnableTick(int timeLeft) {
		if(Bukkit.getOnlinePlayers().length >= GameManager.getGameType().getMinPlayers()){
			Bukkit.getScheduler().cancelTask(timeState);
			gameManager.stopRecruiting(timeState);
			return;
		}
		
		updateBar();
	}

	public void updateBar() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			int version = ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();

			if (version >= 47) {
				StatusBarApi.removeWitherBar(player);
				StatusBarApi.setWitherBar(player, "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + "IP: HUB.MINERSFORTUNE.NET | Website: WWW.MINERSFORTUNE.NET", 0.06F);
			} else {
				StatusBarApi.setDragonBar(player, "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + "IP: HUB.MINERSFORTUNE.NET | Website: WWW.MINERSFORTUNE.NET", 100);
			}
		}

		if (Bukkit.getWorld("world").getTime() >= 10000) {
			Bukkit.getWorld("world").setTime(1000);
		}

		if (Bukkit.getWorld("world").hasStorm() || Bukkit.getWorld("world").isThundering()) {
			Bukkit.getWorld("world").setStorm(false);
			Bukkit.getWorld("world").setThundering(false);
		}

		if (colorInt == (Util.getChatColors().size() - 1)) {
			colorInt = 0;
			return;
		}

		colorInt++;
	}
}
