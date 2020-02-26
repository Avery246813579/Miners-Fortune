package me.avery246813579.minersfortune.gamemode.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameTeam;
import me.avery246813579.minersfortune.gamemode.timers.LiveTimer;
import me.avery246813579.minersfortune.sql.tables.SearchAndDestroyTable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BombCondition extends GameCondition implements Listener {
	private Map<GameTeam, Location> bombLocation = new HashMap<GameTeam, Location>();
	private Map<GameTeam, GameTeam> plantedTeam = new HashMap<GameTeam, GameTeam>();
	private Map<GameTeam, Boolean> activeBombs = new HashMap<GameTeam, Boolean>();
	private Map<GameTeam, Integer> bombTimer = new HashMap<GameTeam, Integer>();
	private Map<GameTeam, Integer> bombTime = new HashMap<GameTeam, Integer>();
	private Map<Player, Integer> playerTicks = new HashMap<Player, Integer>();
	private Map<Player, Integer> playerTimer = new HashMap<Player, Integer>();
	private Map<Player, Integer> playerTime = new HashMap<Player, Integer>();
	private List<Player> isPlanting = new ArrayList<Player>();

	public BombCondition() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	public void resetCondition(String condition) {
		bombLocation.clear();
		plantedTeam.clear();
		activeBombs.clear();
		bombTimer.clear();
		bombTime.clear();
		playerTicks.clear();
		playerTime.clear();
		playerTimer.clear();
		isPlanting.clear();

		String[] strings = condition.split(" ");
		
		for (GameTeam gameTeam : GameManager.getTeams()) {
			activeBombs.put(gameTeam, false);

			for(String s : strings){
				String[] st = s.split(",");
				
				if(st[0].equalsIgnoreCase(gameTeam.getName())){
					bombLocation.put(gameTeam, new Location(Bukkit.getWorld("GameWorld"), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3])));
				}
			}
		}
	}
	
	@Override
	public void stopCondition(){

	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (GameManager.getGameConditions().contains(this)) {
			final Player player = event.getPlayer();
			
			if (!GameManager.findPlayer(player).isAlive()) {
				return;
			}

			if (player.getItemInHand() == null) {
				return;
			}

			if (player.getItemInHand().getType() != Material.BLAZE_POWDER) {
				return;
			}

			if (event.getClickedBlock() == null) {
				return;
			}

			if (event.getClickedBlock().getType() != Material.TNT) {
				return;
			}

			final GameTeam team = GameManager.findTeam(player);
			for (final GameTeam gameTeam : GameManager.getTeams()) {
				if (bombLocation.get(gameTeam).getX() == event.getClickedBlock().getLocation().getX() && bombLocation.get(gameTeam).getY() == event.getClickedBlock().getLocation().getY() && bombLocation.get(gameTeam).getZ() == event.getClickedBlock().getLocation().getZ()) {
					if (activeBombs.get(gameTeam) == true) {
						if (team == gameTeam) {
							if (isPlanting.contains(player)) {
								int ticks = playerTicks.get(player);
								playerTicks.remove(player);
								playerTicks.put(player, ticks + 1);
							} else {
								playerTime.put(player, 0);
								playerTicks.put(player, 1);
								isPlanting.add(player);

								int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), new Runnable() {
									public void run() {
										if (GameManager.getGameState() != GameState.Live) {
											Bukkit.getScheduler().cancelTask(playerTimer.get(player));
											return;
										}

										int ticks = playerTicks.get(player);
										int state = playerTimer.get(player);
										int time = playerTime.get(player);

										if (ticks >= 4 && time == 0) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.1F);
										} else if (ticks >= 9 && time == 1) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.2F);
										} else if (ticks >= 14 && time == 2) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.3F);
										} else if (ticks >= 19 && time == 3) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.4F);
										} else if (ticks >= 24 && time == 4) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.5F);
										} else if (ticks >= 29 && time == 5) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.6F);
										} else if (ticks >= 34 && time == 6) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.7F);
										} else if (ticks >= 39 && time == 7) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.8F);
										} else if (ticks >= 43 && time == 8) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.9F);
										} else if (ticks >= 47 && time == 9) {
											player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 2.0F, 1.0F);
											player.setExp(1.0F);
											activeBombs.remove(gameTeam);
											activeBombs.put(gameTeam, false);
											Bukkit.getScheduler().cancelTask(bombTimer.get(gameTeam));
											bombTimer.remove(gameTeam);
											bombTime.remove(gameTeam);
											broadcastBombMessage(ChatColor.YELLOW + player.getName() + " has defused the " + team.getName() + " teams bomb!");
											SearchAndDestroyTable searchAndDestroyTable = (SearchAndDestroyTable) GameManager.findPlayer(player).getGameStatsTable();
											searchAndDestroyTable.setBomb_defuses(searchAndDestroyTable.getBomb_defuses() + 1);
											GameManager.findPlayer(player).saveStatsTable();
											
											player.setExp(0.0F);
										} else {
											sendBombMessage(player, ChatColor.RED + "Defusing has been cancelled!");
											Bukkit.getScheduler().cancelTask(state);
											playerTicks.remove(player);
											playerTimer.remove(player);
											playerTime.remove(player);
											isPlanting.remove(player);
											player.setExp(0.0F);
											return;
										}

										if (activeBombs.get(gameTeam) == false) {
											Bukkit.getScheduler().cancelTask(state);
											playerTicks.remove(player);
											playerTimer.remove(player);
											playerTime.remove(player);
											isPlanting.remove(player);
											player.setExp(0.0F);
										}

										playerTime.remove(player);
										playerTime.put(player, time + 1);
									}
								}, 20, 20);

								playerTimer.put(player, i);
							}
						} else {
							sendBombMessage(player, ChatColor.RED + "This bomb is already planted!");
						}
					} else {
						if (team != gameTeam) {
							if (isPlanting.contains(player)) {
								int ticks = playerTicks.get(player);
								playerTicks.remove(player);
								playerTicks.put(player, ticks + 1);
							} else {
								playerTime.put(player, 0);
								playerTicks.put(player, 1);
								isPlanting.add(player);

								int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), new Runnable() {
									public void run() {
										if (GameManager.getGameState() != GameState.Live) {
											Bukkit.getScheduler().cancelTask(playerTimer.get(player));
											return;
										}

										int ticks = playerTicks.get(player);
										int state = playerTimer.get(player);
										int time = playerTime.get(player);

										if (ticks >= 4 && time == 0) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.1F);
										} else if (ticks >= 9 && time == 1) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.2F);
										} else if (ticks >= 14 && time == 2) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.3F);
										} else if (ticks >= 19 && time == 3) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.4F);
										} else if (ticks >= 24 && time == 4) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.5F);
										} else if (ticks >= 29 && time == 5) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.6F);
										} else if (ticks >= 34 && time == 6) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.7F);
										} else if (ticks >= 39 && time == 7) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.8F);
										} else if (ticks >= 43 && time == 8) {
											player.getWorld().playSound(player.getLocation(), Sound.FIZZ, 2.0F, 1.0F);
											player.setExp(0.9F);
										} else if (ticks >= 47 && time == 9) {
											player.getWorld().playSound(player.getLocation(), Sound.ANVIL_LAND, 2.0F, 1.0F);
											player.setExp(1.0F);
											activeBombs.remove(gameTeam);
											activeBombs.put(gameTeam, true);
											broadcastBombMessage(ChatColor.YELLOW + player.getName() + " has planted the " + team.getName() + " teams bomb!");
											Bukkit.getScheduler().cancelTask(state);
											plantedTeam.put(gameTeam, team);
											playerTicks.remove(player);
											playerTimer.remove(player);
											playerTime.remove(player);
											isPlanting.remove(player);
											addBombTime(gameTeam);
											SearchAndDestroyTable searchAndDestroyTable = (SearchAndDestroyTable) GameManager.findPlayer(player).getGameStatsTable();
											searchAndDestroyTable.setBomb_arms(searchAndDestroyTable.getBomb_arms() + 1);
											GameManager.findPlayer(player).saveStatsTable();
											player.setExp(0.0F);
											return;
										} else {
											sendBombMessage(player, ChatColor.RED + "Planting has been cancelled!");
											Bukkit.getScheduler().cancelTask(state);
											playerTicks.remove(player);
											playerTimer.remove(player);
											playerTime.remove(player);
											isPlanting.remove(player);
											player.setExp(0.0F);
											return;
										}

										if (activeBombs.get(gameTeam) == true) {
											Bukkit.getScheduler().cancelTask(state);
											playerTicks.remove(player);
											playerTimer.remove(player);
											playerTime.remove(player);
											isPlanting.remove(player);
											player.setExp(0.0F);
										}

										playerTime.remove(player);
										playerTime.put(player, time + 1);
									}
								}, 20, 20);

								playerTimer.put(player, i);
							}
						} else {
							sendBombMessage(player, ChatColor.RED + "You can not plant your own bomb!");
						}
					}
				}
			}
		}
	}

	public void addBombTime(final GameTeam gameTeam) {
		int bt = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), new Runnable() {
			public void run() {
				if (GameManager.getGameState() != GameState.Live) {
					Bukkit.getScheduler().cancelTask(bombTimer.get(gameTeam));
					return;
				}

				int timeLeft = 45;

				if (bombTime.get(gameTeam) != null) {
					timeLeft = bombTime.get(gameTeam);
					bombTime.remove(gameTeam);
				}

				if ((timeLeft % 15 == 0 && timeLeft != 45 && timeLeft != 0) || timeLeft == 10 || timeLeft <= 5) {
					broadcastBombMessage("" + ChatColor.YELLOW + timeLeft + " seconds left until the " + gameTeam.getColor() + gameTeam.getName() + ChatColor.YELLOW + " teams bomb explodes!");
					
					for (Player player : Bukkit.getOnlinePlayers()) {
						player.playSound(player.getLocation(), Sound.ANVIL_LAND, 2.0F, 1.0F);
					}
				}

				timeLeft--;

				if (timeLeft != 0) {
					bombTime.put(gameTeam, timeLeft);
				} else {
					broadcastBombMessage("" + ChatColor.YELLOW + "The " + gameTeam.getColor() + gameTeam.getName() + ChatColor.YELLOW + " teams bomb exploded!");

					for (Player player : Bukkit.getOnlinePlayers()) {
						player.playSound(player.getLocation(), Sound.EXPLODE, 2.0F, 1.0F);
					}
					
					Bukkit.getScheduler().cancelTask(bombTimer.get(gameTeam));
					bombTime.remove(gameTeam);
					bombTimer.remove(gameTeam);
					GameManager.setWinner(plantedTeam.get(gameTeam));
					LiveTimer.setGameIsOver(true);
				}
			}
		}, 20, 20);

		this.bombTimer.put(gameTeam, bt);
	}

	public void sendBombMessage(Player player, String message) {
		player.sendMessage(ChatColor.GREEN + "Bomb >> " + ChatColor.GRAY + message);
	}

	public void broadcastBombMessage(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(ChatColor.GREEN + "Bomb >> " + ChatColor.GRAY + message);
		}
	}

	public Map<GameTeam, Boolean> getActiveBombs() {
		return activeBombs;
	}

	public void setActiveBombs(Map<GameTeam, Boolean> activeBombs) {
		this.activeBombs = activeBombs;
	}
}
