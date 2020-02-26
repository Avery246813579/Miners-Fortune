package me.avery246813579.minersfortune.gamemode;

import me.avery246813579.minersfortune.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class GameTimer implements Runnable {
	/** Variables **/
	public int timeLeft, timeState;
	JavaPlugin plugin;

	public GameTimer(int time) {
		plugin = GameManager.getPlugin();

		timeLeft = time;
		timeState = Util.getNextTimeState();
		timeState = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 20, 20);
	}

	public void run() {
		if(GameManager.isForceStop()){
			Bukkit.getScheduler().cancelTask(timeState);
			return;
		}
		
		if (timeLeft != 0) {
			onRunnableTick(timeLeft);
			timeLeft--;
		}else{
			onScheduleEnd(timeState);
		}
	}

	protected abstract void onScheduleEnd(int timeState);
	protected abstract void onRunnableTick(int timeLeft);
}
