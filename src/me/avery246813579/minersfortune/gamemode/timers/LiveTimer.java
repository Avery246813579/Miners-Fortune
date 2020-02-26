package me.avery246813579.minersfortune.gamemode.timers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameTeam;
import me.avery246813579.minersfortune.gamemode.GameTimer;
import me.avery246813579.minersfortune.gamemode.GameType;
import me.avery246813579.minersfortune.gamemode.GameWinType;
import me.avery246813579.minersfortune.gamemode.conditions.ConditionHandler;
import me.avery246813579.minersfortune.gamemode.conditions.PlayerInvincibility;
import me.avery246813579.minersfortune.menus.MenuHandler;
import me.avery246813579.minersfortune.util.StatusBarApi;
import me.avery246813579.minersfortune.util.Util;

public class LiveTimer extends GameTimer {
	int colorInt = 0;

	private static boolean gameIsOver = false;

	public LiveTimer() {
		super(1500);
	}

	@Override
	protected void onScheduleEnd(int timeState) {
		Bukkit.getScheduler().cancelTask(timeState);
		GameManager.startEnd();
	}

	@Override
	protected void onRunnableTick(int timeLeft) {
		if(GameManager.getGameConditions().contains(ConditionHandler.getPlayerCompass())){
			for(Player player : Bukkit.getOnlinePlayers()){
				if(player.getItemInHand().getType() == Material.COMPASS){
					ConditionHandler.getPlayerCompass().sendNearestPlayer(player);
				}
			}
		}
		
		if (GameManager.getGameType() == GameType.ArcherGames) {
			if (timeLeft == 301 || timeLeft == 302 || timeLeft == 303 || timeLeft == 304 || timeLeft == 305 || timeLeft == 310 || timeLeft == 315 || timeLeft == 330 || timeLeft == 345 || timeLeft == 360 || timeLeft == 420) {
				GameManager.broadcastGameMessage(ChatColor.YELLOW + "Teleporting all players to spawn in " + (timeLeft - 300) + " seconds!");
			}

			if (timeLeft == 901 || timeLeft == 902 || timeLeft == 903 || timeLeft == 904 || timeLeft == 905 || timeLeft == 910 || timeLeft == 915 || timeLeft == 930 || timeLeft == 945 || timeLeft == 960 || timeLeft == 920) {
				if ((timeLeft - 900) >= 60) {
					GameManager.broadcastGameMessage(ChatColor.YELLOW + "Teleporting all players to spawn in " + (timeLeft - 900) / 60 + " minutes!");
				} else {
					GameManager.broadcastGameMessage(ChatColor.YELLOW + "Teleporting all players to spawn in " + (timeLeft - 900) + " seconds!");
				}
			}

			if (timeLeft == 900) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.teleport(GameManager.getTeams().get(0).getSpawn());
				}
			}

			if (timeLeft == 300) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.teleport(GameManager.getTeams().get(0).getSpawn());
				}
			}
		}

		if (timeLeft == 600 || timeLeft == 300 || timeLeft == 60) {
			GameManager.broadcastGameMessage(ChatColor.YELLOW + "Game ending in " + timeLeft / 60 + " minutes!");
		}

		if (timeLeft == 45 || timeLeft == 30 || timeLeft == 15 || timeLeft == 10 || timeLeft <= 5) {
			GameManager.broadcastGameMessage(ChatColor.YELLOW + "Game ending in " + timeLeft + " seconds!");
		}

		if (winCondition()) {
			Bukkit.getScheduler().cancelTask(timeState);
			GameManager.startEnd();
			return;
		}

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (!GameManager.findPlayer(player).isAlive()) {
				if (!player.getInventory().contains(Material.COMPASS)) {
					player.getInventory().setItem(0, MenuHandler.spectatorMenu.getItem());
				}
			}
		}

		if (GameManager.getGameType() == GameType.Domination) {
			for (GameTeam gameTeam : GameManager.getTeams()) {
				int alive = 0;

				for (Player player : gameTeam.getPlayers()) {
					if (Bukkit.getPlayer(player.getName()) != null) {
						alive++;
					}
				}

				if (alive <= 0) {
					GameManager.broadcastGameMessage("Game has become unfair! Server resetting!");
					GameManager.resetGame();
				}
			}
		}

		updateBar();
	}

	public boolean winCondition() {
		if (GameManager.getWinTypes() != null) {
			for (GameWinType gameWinType : GameManager.getWinTypes()) {
				if (gameWinType == GameWinType.LastTeamStanding) {
					List<GameTeam> stillAlive = new ArrayList<GameTeam>();

					for (GameTeam gameTeam : GameManager.getTeams()) {
						for (Player player : gameTeam.getPlayers()) {
							GamePlayer gamePlayer = GameManager.findPlayer(player);

							if (gamePlayer.isAlive()) {
								if (!stillAlive.contains(gameTeam)) {
									stillAlive.add(gameTeam);
								}
							}
						}
					}

					if (stillAlive.size() <= 1) {
						GameManager.setWinner(stillAlive.get(0));
						return true;
					} else {
						GameManager.setWinner(GameManager.getTeams().get(0));
					}
				}
			}
		}

		if (gameIsOver) {
			return true;
		}

		return false;
	}

	public void updateBar() {
		String barMessage = "";

		if (GameManager.getGameType() == GameType.Domination) {
			return;
		} else {
			if (GameManager.getGameType() == GameType.ArcherGames && PlayerInvincibility.isOnInvincibility()) {
				return;
			} else if (GameManager.getGameType() == GameType.SearchAndDestroy) {
				boolean active = false;
				GameTeam game = null;

				for (GameTeam gameTeam : GameManager.getTeams()) {
					if (ConditionHandler.getBombCondition().getActiveBombs().get(gameTeam)) {
						if (game != null) {
							barMessage = "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + gameTeam.getName() + " and " + game.getName() + " bombs are active!";
						} else {
							barMessage = "" + Util.getChatColors().get(colorInt) + ChatColor.BOLD + gameTeam.getName() + " bomb is active!";
							game = gameTeam;
							active = true;
						}
					}
				}

				if (!active) {
					barMessage = ChatColor.YELLOW + "Alive:";

					for (GameTeam gameTeam : GameManager.getTeams()) {
						int alive = 0;

						if (!gameTeam.getPlayers().isEmpty()) {
							for (Player player : gameTeam.getPlayers()) {
								if (GameManager.findPlayer(player).isAlive()) {
									alive++;
								}
							}
						}

						barMessage = barMessage + " " + gameTeam.getColor() + gameTeam.getName() + ": " + alive;
					}
				}
			} else if (barMessage == "") {
				barMessage = ChatColor.YELLOW + "Alive:";

				for (GameTeam gameTeam : GameManager.getTeams()) {
					int alive = 0;

					for (Player player : gameTeam.getPlayers()) {
						if (GameManager.findPlayer(player).isAlive()) {
							alive++;
						}
					}

					barMessage = barMessage + " " + gameTeam.getColor() + gameTeam.getName() + ": " + alive;
				}
			}
		}

		if (colorInt == (Util.getChatColors().size() - 1)) {
			colorInt = 0;
			return;
		}

		colorInt++;

		for (Player player : Bukkit.getOnlinePlayers()) {
			int version = ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();

			if (version >= 47) {
				StatusBarApi.removeWitherBar(player);
				StatusBarApi.setWitherBar(player, barMessage, 100);
			} else {
				StatusBarApi.setDragonBar(player, barMessage, 100);
			}
		}
	}

	public static boolean isGameIsOver() {
		return gameIsOver;
	}

	public static void setGameIsOver(boolean gameIsOver) {
		LiveTimer.gameIsOver = gameIsOver;
	}
}
