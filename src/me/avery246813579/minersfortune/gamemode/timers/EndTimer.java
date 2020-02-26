package me.avery246813579.minersfortune.gamemode.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameTimer;
import me.avery246813579.minersfortune.sql.tables.GlobalStatsTable;
import me.avery246813579.minersfortune.util.StatusBarApi;
import me.avery246813579.minersfortune.util.Util;

public class EndTimer extends GameTimer {
	int colorInt = 0;

	public EndTimer() {
		super(20);
	}

	@Override
	protected void onScheduleEnd(int timeState) {
		Bukkit.getScheduler().cancelTask(timeState);
		GameManager.endGame();
	}

	@Override
	protected void onRunnableTick(int timeLeft) {
		updateBar();

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (timeLeft == 19) {
				player.sendMessage("");
				player.sendMessage("");
				player.sendMessage("");
				player.sendMessage("");
				player.sendMessage("");
				player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
			}
			if (timeLeft == 18) {
				player.sendMessage(ChatColor.GREEN + "Gamemode ➜ " + ChatColor.YELLOW + GameManager.getGameType().getDisplayName());
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
			}
			if (timeLeft == 17) {
				if (GameManager.getWinners().size() == 1) {
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage(ChatColor.GREEN + "Winner ➜ " + ChatColor.YELLOW + GameManager.getWinners().get(0).getName());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (GameManager.getWinner() != null) {
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage(ChatColor.GREEN + "Wining Team ➜ " + ChatColor.YELLOW + GameManager.getWinner().getName());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (GameManager.getWinners().size() > 1) {
					player.sendMessage("");
					if (GameManager.getWinners().size() >= 1)
						player.sendMessage(ChatColor.GREEN + "First Place ➜ " + ChatColor.YELLOW + GameManager.getWinners().get(0).getName());
					else
						player.sendMessage("");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}
			}
			if (timeLeft == 16) {
				if (GameManager.getWinners().size() == 1) {
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage(ChatColor.GREEN + "Map ➜ " + ChatColor.YELLOW + GameManager.getMapTable().getMapName() + ChatColor.GREEN + " Created by ➜ " + ChatColor.YELLOW + GameManager.getMapTable().getMapCreator());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (GameManager.getWinner() != null) {
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage(ChatColor.GREEN + "Map ➜ " + ChatColor.YELLOW + GameManager.getMapTable().getMapName() + ChatColor.GREEN + " Created by ➜ " + ChatColor.YELLOW + GameManager.getMapTable().getMapCreator());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (GameManager.getWinners().size() > 1) {
					if (GameManager.getWinners().size() >= 2)
						player.sendMessage(ChatColor.GREEN + "Second Place ➜ " + ChatColor.YELLOW + GameManager.getWinners().get(1).getName());
					else
						player.sendMessage("");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}
			}
			if (timeLeft == 15) {
				if (GameManager.getWinners().size() == 1) {
					player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (GameManager.getWinner() != null) {
					player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (GameManager.getWinners().size() > 1) {
					if (GameManager.getWinners().size() >= 3)
						player.sendMessage(ChatColor.GREEN + "Third Place ➜ " + ChatColor.YELLOW + GameManager.getWinners().get(2).getName());
					else
						player.sendMessage("");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}
			}
			if (timeLeft == 14) {
				if (GameManager.getWinners().size() > 1) {
					player.sendMessage("");
					player.sendMessage(ChatColor.GREEN + "Map ➜ " + ChatColor.YELLOW + GameManager.getMapTable().getMapName() + ChatColor.GREEN + " Created by ➜ " + ChatColor.YELLOW + GameManager.getMapTable().getMapCreator());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}
			}
			if (timeLeft == 13) {
				if (GameManager.getWinners().size() > 1) {
					player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}
			}

			if (timeLeft == 10) {
				GameManager.findPlayer(player).giveRespectedItems();

				if (GameManager.findPlayer(player).isPlayedGame()) {
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage("");
					player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}
			}

			if (GameManager.findPlayer(player).isPlayedGame()) {
				if (timeLeft == 9) {
					player.sendMessage(ChatColor.GREEN + "Player ➜ " + ChatColor.YELLOW + player.getName());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}
				if (timeLeft == 8) {
					GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player));
					globalStatsTable.setCredits_earned(globalStatsTable.getCredits_earned() + (int) (GameManager.findPlayer(player).getKills() * GameManager.getWinTypes().get(0).getCreditsPerElimination()));
					MinersFortune.getPlugin().getSqlHandler().saveGlobalStats(globalStatsTable);

					player.sendMessage("");
					player.sendMessage(ChatColor.GREEN + "For " + GameManager.findPlayer(player).getKills() + " kills ➜ " + ChatColor.YELLOW + GameManager.findPlayer(player).getKills() * GameManager.getWinTypes().get(0).getCreditsPerElimination() + " Credits");
					GameManager.findPlayer(player).payPlayer(GameManager.findPlayer(player).getKills() * GameManager.getWinTypes().get(0).getCreditsPerElimination());
					GameManager.findPlayer(player).getGameStatsTable().setCredits_earned(GameManager.findPlayer(player).getGameStatsTable().getCredits_earned() + (int) (GameManager.findPlayer(player).getKills() * GameManager.getWinTypes().get(0).getCreditsPerElimination()));
					GameManager.findPlayer(player).saveStatsTable();
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (timeLeft == 7) {
					if (GameManager.getWinners().contains(player)) {
						if (GameManager.getWinners().indexOf(player) == 0) {
							GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player));
							globalStatsTable.setCredits_earned(globalStatsTable.getCredits_earned() + GameManager.getWinTypes().get(0).getCreditsPerWin());
							MinersFortune.getPlugin().getSqlHandler().saveGlobalStats(globalStatsTable);
							
							player.sendMessage(ChatColor.GREEN + "For First place ➜ " + ChatColor.YELLOW + GameManager.getWinTypes().get(0).getCreditsPerWin() + " Credits");
							GameManager.findPlayer(player).payPlayer(GameManager.getWinTypes().get(0).getCreditsPerWin());
							GameManager.findPlayer(player).getGameStatsTable().setWins(GameManager.findPlayer(player).getGameStatsTable().getWins() + 1);
							GameManager.findPlayer(player).getGameStatsTable().setCredits_earned(GameManager.findPlayer(player).getGameStatsTable().getCredits_earned() + GameManager.getWinTypes().get(0).getCreditsPerWin());
							if (GameManager.findPlayer(player).getGameStatsTable().getLosses() != 0) {
								GameManager.findPlayer(player).getGameStatsTable().setLosses(GameManager.findPlayer(player).getGameStatsTable().getWins() - 1);
							}
							GameManager.findPlayer(player).saveStatsTable();
						} else if (GameManager.getWinners().indexOf(player) == 1) {
							GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player));
							globalStatsTable.setCredits_earned(globalStatsTable.getCredits_earned() + (int) (GameManager.getWinTypes().get(0).getCreditsPerWin() * .75));
							MinersFortune.getPlugin().getSqlHandler().saveGlobalStats(globalStatsTable);
							
							player.sendMessage(ChatColor.GREEN + "For Second place ➜ " + ChatColor.YELLOW + (GameManager.getWinTypes().get(0).getCreditsPerWin() * .75) + " Credits");
							GameManager.findPlayer(player).getGameStatsTable().setCredits_earned(GameManager.findPlayer(player).getGameStatsTable().getCredits_earned() + (int) (GameManager.getWinTypes().get(0).getCreditsPerWin() * .75));
							GameManager.findPlayer(player).saveStatsTable();
							GameManager.findPlayer(player).payPlayer((int) (GameManager.getWinTypes().get(0).getCreditsPerWin() * .75));
						} else if (GameManager.getWinners().indexOf(player) == 2) {
							GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player));
							globalStatsTable.setCredits_earned(globalStatsTable.getCredits_earned() + (int) (GameManager.getWinTypes().get(0).getCreditsPerWin() * .50));
							MinersFortune.getPlugin().getSqlHandler().saveGlobalStats(globalStatsTable);
							
							player.sendMessage(ChatColor.GREEN + "For Third place ➜ " + ChatColor.YELLOW + (GameManager.getWinTypes().get(0).getCreditsPerWin() * .50) + " Credits");
							GameManager.findPlayer(player).getGameStatsTable().setCredits_earned(GameManager.findPlayer(player).getGameStatsTable().getCredits_earned() + (int) (GameManager.getWinTypes().get(0).getCreditsPerWin() * .50));
							GameManager.findPlayer(player).saveStatsTable();
							GameManager.findPlayer(player).payPlayer((int) (GameManager.getWinTypes().get(0).getCreditsPerWin() * .50));
						}
					} else if (GameManager.getWinner() != null) {
						if (GameManager.getWinner().getPlayers().contains(player)) {
							GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player));
							globalStatsTable.setCredits_earned(globalStatsTable.getCredits_earned() + (int) (GameManager.getWinTypes().get(0).getCreditsPerWin()));
							MinersFortune.getPlugin().getSqlHandler().saveGlobalStats(globalStatsTable);
							
							player.sendMessage(ChatColor.GREEN + "For Winning ➜ " + ChatColor.YELLOW + (GameManager.getWinTypes().get(0).getCreditsPerWin()) + " Credits");
							GameManager.findPlayer(player).payPlayer((int) (GameManager.getWinTypes().get(0).getCreditsPerWin()));
							GameManager.findPlayer(player).getGameStatsTable().setWins(GameManager.findPlayer(player).getGameStatsTable().getWins() + 1);
							GameManager.findPlayer(player).getGameStatsTable().setCredits_earned(GameManager.findPlayer(player).getGameStatsTable().getCredits_earned() + GameManager.getWinTypes().get(0).getCreditsPerWin());
							if (GameManager.findPlayer(player).getGameStatsTable().getLosses() != 0) {
								GameManager.findPlayer(player).getGameStatsTable().setLosses(GameManager.findPlayer(player).getGameStatsTable().getWins() - 1);
							}
							GameManager.findPlayer(player).saveStatsTable();
						}
					} else {
						player.sendMessage(ChatColor.GREEN + "For LOSING ➜ " + ChatColor.YELLOW + "Shame and Humility");
					}
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (timeLeft == 6) {
					player.sendMessage(ChatColor.GREEN + "For Playing ➜ " + ChatColor.YELLOW + GameManager.getWinTypes().get(0).getCreditsForPlaying() + " Credits");
					GameManager.findPlayer(player).payPlayer(GameManager.getWinTypes().get(0).getCreditsForPlaying());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
					
					GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player));
					globalStatsTable.setCredits_earned(globalStatsTable.getCredits_earned() + (int) (GameManager.getWinTypes().get(0).getCreditsForPlaying()));
					MinersFortune.getPlugin().getSqlHandler().saveGlobalStats(globalStatsTable);
					
					GameManager.findPlayer(player).getGameStatsTable().setCredits_earned(GameManager.findPlayer(player).getGameStatsTable().getCredits_earned() + (int) GameManager.getWinTypes().get(0).getCreditsForPlaying());
					GameManager.findPlayer(player).saveStatsTable();
				}

				if (timeLeft == 5) {
					player.sendMessage("");
					player.sendMessage(ChatColor.GREEN + "Credits ➜ " + ChatColor.YELLOW + MinersFortune.getPlugin().getSqlHandler().getAccount(player).getCredits());
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}

				if (timeLeft == 4) {
					player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2.0F, 1.0F);
				}
			}
		}
	}

	public void updateBar() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			int version = ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();

			if (version >= 47) {
				if (GameManager.getWinners().size() != 0) {
					StatusBarApi.removeWitherBar(player);
					StatusBarApi.setWitherBar(player, "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + GameManager.getWinners().get(0).getName() + " has won!", 0.06F);
				} else {
					StatusBarApi.removeWitherBar(player);
					StatusBarApi.setWitherBar(player, "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + GameManager.getWinner().getName() + " team has won!", 100);
				}

			} else {
				if (GameManager.getWinners().size() != 0) {
					StatusBarApi.setDragonBar(player, "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + GameManager.getWinners().get(0).getName() + " has won!", 100);
				} else {
					StatusBarApi.setDragonBar(player, "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + GameManager.getWinner().getName() + " team has won!", 100);
				}
			}

			if (colorInt == (Util.getChatColors().size() - 1)) {
				colorInt = 0;
				return;
			}

			colorInt++;
		}
	}
}
