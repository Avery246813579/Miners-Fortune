package me.avery246813579.minersfortune.gamemode.listeners;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.scoreboard.IScoreboardHandler;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameTeam;
import me.avery246813579.minersfortune.gamemode.GameTeamTypes;
import me.avery246813579.minersfortune.gamemode.GameType;
import me.avery246813579.minersfortune.gamemode.conditions.ConditionHandler;
import me.avery246813579.minersfortune.gamemode.conditions.OutsideMap;
import me.avery246813579.minersfortune.menus.MenuHandler;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.GameStatsTable;
import me.avery246813579.minersfortune.sql.tables.GlobalStatsTable;
import me.avery246813579.minersfortune.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener {
	/** Varaibles **/
	JavaPlugin plugin;
	private static boolean isFrozen = false;
	private Map<Player, Date> joinedDates = new HashMap<Player, Date>();

	public PlayerListener() {
		plugin = GameManager.getPlugin();
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		joinedDates.put(player, new Date());

		if (GameManager.getGameType() != null) {
			if (GameManager.getGameState() != GameState.Live) {
				player.teleport(GameManager.GameLocations.LOBBY.getLocation());
			}
		}
		if (GameManager.getGameType() != null) {
			GameManager.getGamePlayers().add(new GamePlayer(player));
		}

		if (RankManager.findPlayerRank(event.getPlayer()).getDonationNumber() >= Ranks.DIAMOND.getDonationNumber()) {
			for (Player players : Bukkit.getOnlinePlayers()) {
				if (GameManager.getGameState() != GameState.Live) {
					players.sendMessage(ChatColor.GREEN + "Join >> " + ChatColor.YELLOW + player.getName() + " has joined!");
				}
			}
		}

		if (GameManager.getGameType() != null) {
			for (Player players : Bukkit.getOnlinePlayers()) {
				if (GameManager.getGameState() == GameState.Live) {
					if (GameManager.findPlayer(players).isAlive()) {
						player.showPlayer(players);
						players.hidePlayer(player);
					} else {
						player.hidePlayer(players);
						players.hidePlayer(player);
					}
				} else {
					player.showPlayer(players);
					players.showPlayer(player);
				}
			}
		}

		event.setJoinMessage(null);
		if (GameManager.getGameState() == GameState.Prepare && GameManager.isStarting()) {
			GameManager.selectTeam(player);

			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
			player.sendMessage(ChatColor.GREEN + "Gamemode ➜ " + ChatColor.YELLOW + GameManager.getGameType().getDisplayName());
			player.sendMessage("");
			player.sendMessage(ChatColor.GREEN + "How to play ➜ " + ChatColor.YELLOW + GameManager.getDescription()[0]);
			player.sendMessage(ChatColor.YELLOW + GameManager.getDescription()[1]);
			player.sendMessage(ChatColor.YELLOW + GameManager.getDescription()[2]);
			player.sendMessage(ChatColor.YELLOW + GameManager.getDescription()[3]);
			player.sendMessage("");
			player.sendMessage(ChatColor.GREEN + "Map ➜ " + ChatColor.YELLOW + GameManager.getMapTable().getMapName() + ChatColor.GREEN + " Created by ➜ " + ChatColor.YELLOW + GameManager.getMapTable().getMapCreator());
			player.sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");

			GameManager.findPlayer(player).resetManualPlayer();

			GameTeam gameTeam = GameManager.findTeam(player);
			if (gameTeam.getPlayers().contains(player)) {
				Location location = gameTeam.getSpawn().clone().add(0.5D, 0.5D, 0.5D);
				location.setPitch(0);

				try {
					player.teleport(location);
				} catch (Exception ex) {
					ex.printStackTrace();
					player.teleport(location);
				}
			}
		}

		if (GameManager.getGameState() == GameState.Live) {
			GameManager.findPlayer(player).makeSpectator();
		}

		IScoreboardHandler.updatePlayerTeamScoreboard(player);
		IScoreboardHandler.updatePlayers();
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		if (GameManager.getGameState() == GameState.Live) {
			if (GameManager.findPlayer(player) != null) {
				GamePlayer gamePlayer = GameManager.findPlayer(player);
				if (gamePlayer.isAlive()) {
					player.damage(10000);
					GameManager.getGamePlayers().remove(gamePlayer);
				}
			}

			if (GameManager.findTeam(player) != null) {
				GameManager.findTeam(player).removePlayer(player);
			}
		}

		Date date = joinedDates.get(player);
		joinedDates.remove(player);
		Date currentDate = new Date();

		GlobalStatsTable globalStatsTable = MinersFortune.getPlugin().getSqlHandler().getGlobalStats(MinersFortune.getPlugin().getSqlHandler().getPlayerId(player.getName()));
		globalStatsTable.setTime_online(globalStatsTable.getTime_online() + (int) ((currentDate.getTime() - date.getTime()) / 1000));
		MinersFortune.getPlugin().getSqlHandler().saveGlobalStats(globalStatsTable);

		event.setQuitMessage(null);
		GameManager.getGamePlayers().remove(GameManager.findPlayer(player));
		IScoreboardHandler.updateLeave();
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (GameManager.getGameState() != GameState.Live) {
			event.setCancelled(true);
		}

		if (event.getEntity() instanceof Player) {
			if (!GameManager.findPlayer((Player) event.getEntity()).isAlive()) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (GameManager.getGameState() != GameState.Live) {
			event.setCancelled(true);
		}

		if (MinersFortune.isCanBuild()) {
			return;
		}

		if (MinersFortune.getPlayersCanBuild().contains(event.getPlayer())) {
			return;
		}

		if (!GameManager.findPlayer(event.getPlayer()).isAlive()) {
			event.setCancelled(true);
		}

		if (event.getBlock().getType() == Material.GLASS) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockExplode(BlockBurnEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (event.getBlock().getType() == Material.GLASS) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockDamag(BlockDamageEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (event.getBlock().getType() == Material.GLASS) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (GameManager.getGameState() != GameState.Live) {
			event.setCancelled(true);
		}

		if (MinersFortune.isCanBuild()) {
			return;
		}

		if (MinersFortune.getPlayersCanBuild().contains(event.getPlayer())) {
			return;
		}

		if (!GameManager.findPlayer(event.getPlayer()).isAlive()) {
			event.setCancelled(true);
		}

		if (event.getBlock().getType() == Material.CHEST) {
			event.getPlayer().sendMessage(ChatColor.GREEN + "Game >> " + ChatColor.YELLOW + "You can not place chests.");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (GameManager.getGameState() != GameState.Live) {
			event.setCancelled(true);
		}

		if (!GameManager.findPlayer(event.getPlayer()).isAlive()) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (GameManager.getGameState() != GameState.Live) {
			event.setCancelled(true);
		}

		if (!GameManager.findPlayer(event.getPlayer()).isAlive()) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (event.getEntity() instanceof Player) {
			if (event.getCause() == DamageCause.VOID) {
				if (GameManager.getGameState() != GameState.Live) {
					((Player) event.getEntity()).teleport(GameManager.GameLocations.LOBBY.getLocation());
				} else {
					if (GameManager.findPlayer((Player) event.getEntity()).isAlive()) {
						((Player) event.getEntity()).damage(100000);
					} else {
						((Player) event.getEntity()).teleport(GameManager.GameLocations.LOBBY.getLocation());
					}
				}
			}

			if (GameManager.getGameState() != GameState.Live) {
				event.setCancelled(true);
				return;
			}

			Player player = (Player) event.getEntity();
			if (!GameManager.findPlayer(player).isAlive()) {
				event.setCancelled(true);
				return;
			}
		}
	}

	@EventHandler
	public void onInventoryInteract(InventoryInteractEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (!GameManager.findPlayer((Player) event.getWhoClicked()).isAlive()) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (event.getEntity() instanceof EnderPearl) {
			Player player = (Player) event.getEntity().getShooter();

			if (GameManager.getGameConditions().contains(ConditionHandler.getOutsideMap())) {
				if (!OutsideMap.getBlocks().contains(player.getLocation().getBlock().getRelative(BlockFace.DOWN))) {
					player.teleport(GameManager.getMapTable().getMp());
				}
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if(GameManager.getGameState() != GameState.Live){
			
		}

		if (GameManager.getGameConditions().contains(ConditionHandler.getNoDropItems())) {
			event.getDrops().clear();
		}

		for (ItemStack itemStack : event.getDrops()) {
			player.getWorld().dropItem(player.getLocation(), itemStack);
		}

		for (Player players : Bukkit.getOnlinePlayers()) {
			if (player.getKiller() != null) {
				players.sendMessage(ChatColor.GREEN + "Death >> " + ChatColor.GRAY + GameManager.findTeam(player).getColor() + player.getName() + ChatColor.YELLOW + " has been killed by " + GameManager.findTeam(player.getKiller()).getColor() + player.getKiller().getName() + ChatColor.YELLOW + "!");
			} else {
				players.sendMessage(ChatColor.GREEN + "Death >> " + ChatColor.GRAY + GameManager.findTeam(player).getColor() + player.getName() + ChatColor.YELLOW + " has been eliminated!");
			}
		}

		if (GameManager.getGameType() == null) {
			return;
		}

		GameStatsTable statsTable = GameManager.findPlayer(player).getGameStatsTable();
		if (GameManager.getGameType() != GameType.Domination) {
			GameManager.findPlayer(player).makeSpectator();
			statsTable.setDeaths(statsTable.getDeaths() + 1);
		} else {
			GameManager.findPlayer(player).respawn();
			statsTable.setDeaths(statsTable.getDeaths() + 1);
		}

		GameManager.findPlayer(player).saveStatsTable();
		GamePlayer gamePlayer = GameManager.findPlayer(player);
		if (gamePlayer.getKiller() != null) {
			GameStatsTable gameStats = GameManager.findPlayer(gamePlayer.getKiller()).getGameStatsTable();
			gameStats.setKills(gameStats.getKills() + 1);
			GameManager.findPlayer(gamePlayer.getKiller()).saveStatsTable();
			GameManager.findPlayer(gamePlayer.getKiller()).setKills(GameManager.findPlayer(gamePlayer.getKiller()).getKills() + 1);
			gamePlayer.setKiller(null);
		}

		IScoreboardHandler.updatePlayers();
		GameManager.checkIfWinner(player);

		event.getDrops().clear();
		player.setHealth(20.0);
		event.setDeathMessage("");
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (!(event.getDamager() instanceof Player) && !(event.getDamager() instanceof Arrow)) {
			event.setCancelled(true);
			return;
		}

		if (!(event.getEntity() instanceof Player)) {
			event.setCancelled(true);
			return;
		}

		if (event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			if (!(event.getEntity() instanceof Player)) {
				return;
			}

			if (!GameManager.findPlayer((Player) event.getEntity()).isAlive()) {
				Vector velocity = arrow.getVelocity();

				Player shooter = (Player) arrow.getShooter();
				Player damaged = (Player) event.getEntity();

				damaged.sendMessage(ChatColor.GREEN + "Clipping >> " + ChatColor.YELLOW + "You have clipped an arrow, moving you out of it's flight path!");
				damaged.teleport(damaged.getLocation().add(0, 5, 0));
				damaged.setFlying(true);

				Arrow newArrow = shooter.launchProjectile(Arrow.class);
				newArrow.setShooter(shooter);
				newArrow.setVelocity(velocity);
				newArrow.setBounce(false);

				arrow.remove();
				event.setCancelled(true);
			}
		}

		if (event.getDamager() instanceof Player) {
			if (!GameManager.findPlayer((Player) event.getDamager()).isAlive()) {
				event.setCancelled(true);
				return;
			}
		}

		if (!GameManager.findPlayer((Player) event.getEntity()).isAlive()) {
			event.setCancelled(true);
			return;
		}

		if (event.getDamager() instanceof Player) {
			if (GameManager.findTeam((Player) event.getDamager()).getPlayers().contains((Player) event.getEntity()) && GameManager.findTeam((Player) event.getEntity()).getGameTeamTypes() != GameTeamTypes.SOLO) {
				event.setCancelled(true);
				return;
			}
		}

		/**
		 * North z goes down | East X goes up | South Z goes up | west x goes
		 * down
		 **/

		if (event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();

			if (arrow.getShooter() instanceof Player) {
				GameManager.findPlayer((Player) event.getEntity()).setKiller((Player) arrow.getShooter());
			}
		} else {
			GameManager.findPlayer((Player) event.getEntity()).setKiller((Player) event.getDamager());
		}
	}

	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (isFrozen) {
			if (((event.getTo().getX() != event.getFrom().getX()) || (event.getTo().getZ() != event.getFrom().getZ()))) {
				event.setTo(event.getFrom());
				return;
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (!GameManager.findPlayer(event.getPlayer()).isAlive()) {
			if (!(event.getRightClicked() instanceof Player)) {
				return;
			}

			if (!GameManager.findPlayer((Player) event.getRightClicked()).isAlive()) {
				return;
			}

			// event.getPlayer().openInventory(((Player)
			// event.getRightClicked()).getInventory());
		}

		if (event.getRightClicked().getType() != EntityType.VILLAGER) {
			return;
		}

		event.setCancelled(true);
		LivingEntity livingEntity = (LivingEntity) event.getRightClicked();
		if (livingEntity.getCustomName().equalsIgnoreCase(ChatColor.YELLOW + "Select Team")) {
			MenuHandler.teamMenu.openMenu(event.getPlayer(), 0);
		} else if (livingEntity.getCustomName().equalsIgnoreCase(ChatColor.YELLOW + "Select Kit")) {
			MenuHandler.kitMenu.openMenu(event.getPlayer(), 0);
		} else if (livingEntity.getCustomName().equalsIgnoreCase(ChatColor.YELLOW + "Buy Kit")) {
			MenuHandler.buyKitMenu.openMenu(event.getPlayer(), 0);
		} else if (livingEntity.getCustomName().equalsIgnoreCase(ChatColor.YELLOW + "Coming Soon")) {
			event.getPlayer().sendMessage(ChatColor.GREEN + "Secret >> " + ChatColor.RED + "This feature is coming soon!");
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (!GameManager.findPlayer(event.getPlayer()).isAlive() && event.getItem() == null) {
			event.setCancelled(true);
		}

		if (GameManager.isStarting()) {
			event.setCancelled(true);
		}

		if (event.getItem() != null) {
			if (event.getItem().getType() == Material.WOOD_DOOR) {
				if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Back to hub")) {
					Util.sendPM(event.getPlayer(), new String[] { "Connect", "hub" });
				}
			}
		}

		if (event.getClickedBlock() != null) {
			if (GameManager.findPlayer(event.getPlayer()).isAlive() && GameManager.getGameType() == GameType.SearchAndDestroy) {
				if (event.getClickedBlock().getType() == Material.CHEST || event.getClickedBlock().getType() == Material.FURNACE || event.getClickedBlock().getType() == Material.HOPPER) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		if (GameManager.getGameType() == null) {
			return;
		}

		if (event.getEntity().getShooter() instanceof Player) {
			if (!GameManager.findPlayer((Player) event.getEntity().getShooter()).isAlive()) {
				event.setCancelled(true);
			}
		}
	}

	public static boolean isFrozen() {
		return isFrozen;
	}

	public static void setFrozen(boolean isFrozen) {
		PlayerListener.isFrozen = isFrozen;
	}

	public Map<Player, Date> getJoinedDates() {
		return joinedDates;
	}

	public void setJoinedDates(Map<Player, Date> joinedDates) {
		this.joinedDates = joinedDates;
	}
}
