package me.avery246813579.minersfortune.listeners;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.handlers.SignHandler;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.AccountTable;
import me.avery246813579.minersfortune.sql.tables.BanTable;
import me.avery246813579.minersfortune.sql.tables.ServerTable;
import me.avery246813579.minersfortune.util.UUIDUtil;
import me.avery246813579.minersfortune.util.Util;

public class PlayerListener implements Listener {
	/** Classes **/
	private static boolean canSpeak = true;
	MinersFortune plugin;

	public PlayerListener(MinersFortune plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (this.plugin.getSqlHandler().getAccount(player) == null) {
			plugin.getSqlHandler().createAccount(player.getName(), plugin.getUuids().get(player).toString());
		}

		this.plugin.getSqlHandler().loadPlayerSql(this.plugin.getSqlHandler().getPlayerId(player));

		RankManager.applyPrefix(player);

		ServerTable serverTable = this.plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setOnlinePlayers(Bukkit.getOnlinePlayers().length);
		this.plugin.getSqlHandler().saveServer(serverTable);
		
		if(!MinersFortune.getVanished().isEmpty()){
			for(Player players : MinersFortune.getVanished()){
				player.hidePlayer(players);
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		/** Saves Server SQL **/
		ServerTable serverTable = plugin.getSqlHandler().getServer(Bukkit.getServerName());
		serverTable.setOnlinePlayers((Bukkit.getOnlinePlayers().length - 1));
		plugin.getSqlHandler().saveServer(serverTable);

		plugin.getUuids().remove(event.getPlayer());
		
		if(!MinersFortune.getVanished().isEmpty()){
			for(Player players : MinersFortune.getVanished()){
				event.getPlayer().showPlayer(players);
			}
		}
	}

	@EventHandler
	public void onPreLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();

		/** Creates and stores Player UUID **/
		UUIDUtil fetcher = new UUIDUtil(Arrays.asList(new String[] { player.getName() }));

		Map<String, UUID> response = null;
		try {
			response = fetcher.call();
		} catch (Exception e) {
			e.printStackTrace();
		}

		plugin.getUuids().put(player, response.get(player.getName()));
		if (plugin.getSqlHandler().getAccount(player) == null) {
			plugin.getSqlHandler().createAccount(player.getName(), plugin.getUuids().get(player).toString());
		}

		AccountTable accountTable = plugin.getSqlHandler().getAccount(player);
		BanTable banTable = MinersFortune.getPlugin().getSqlHandler().getBanned(accountTable.getPlayer_id());
		if (banTable.isBanned()) {
			Date currentDate = new Date();
			event.setResult(Result.KICK_OTHER);
			event.setKickMessage(ChatColor.RED + "You have been banned until: " + ChatColor.YELLOW + banTable.getBanned().split(" ")[1] + "\n" + ChatColor.RED + "Reason: " + ChatColor.YELLOW + banTable.getBanMessage() + "\n" + ChatColor.RED + "Current Date: " + ChatColor.YELLOW
					+ currentDate.toString() + ChatColor.YELLOW + "\n" + ChatColor.RED + "Date Format: " + ChatColor.YELLOW + "Hour/Minute/Month/Day/Year");
			return;
		}

		/** Checks for staff whitelist **/
		ServerTable serverTable = this.plugin.getSqlHandler().getServer(Bukkit.getServerName());
		if (serverTable.isWhitelist()) {
			Ranks rank = RankManager.findPlayerRank(player);
			if (!rank.isStaffed() && rank != Ranks.BUILDER && rank != Ranks.BETA_TESTER) {
				event.setResult(Result.KICK_OTHER);
				event.setKickMessage(ChatColor.RED + "This server is whitelisted for only staff members!");
			}
		}

	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		if (!canSpeak) {
			GameManager.sendGameMessage(player, ChatColor.RED + "You can not chat at this moment!");
			event.setCancelled(true);
			return;
		}

		event.setFormat("%s: " + ChatColor.RESET + "%s");

		AccountTable accountTable = plugin.getSqlHandler().getAccount(player);
		BanTable banTable = MinersFortune.getPlugin().getSqlHandler().getBanned(accountTable.getPlayer_id());

		if (banTable.isMuted()) {
			event.setCancelled(true);
			player.sendMessage(ChatColor.GREEN + "Mute >> " + ChatColor.YELLOW + "You can't talk because of a mute! Reason: " + banTable.getMuteMessage());
		}
	}

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		if (!event.getPlayer().hasPermission("serversigns.admin")) {
			return;
		}

		if ((!event.getLine(0).equalsIgnoreCase("[server]")) || (event.getLine(1).isEmpty())) {
			return;
		}

		SignHandler.createSign(event.getBlock().getLocation(), event.getLine(1));
		event.getPlayer().sendMessage(ChatColor.GREEN + "You have placed a server sign!");
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block b = event.getClickedBlock();

		if (b == null) {
			return;
		}

		if ((b.getType() == Material.WALL_SIGN) || (b.getType() == Material.SIGN_POST) || b.getType() == Material.SIGN_POST) {
			Sign sign = (Sign) b.getState();
			if (sign.getLine(0).contains("[Full-") || sign.getLine(0).contains("[Spec-") || sign.getLine(0).contains("[Join-") || sign.getLine(0).contains("[DM-")) {
				String[] st = sign.getLine(0).split("-");
				Util.sendPM(player, new String[] { "Connect", st[1].substring(0, st[1].length() - 1) });
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.SIGN || event.getBlock().getType() == Material.SIGN_POST) {
			Sign sign = (Sign) event.getBlock().getState();

			if (SignHandler.getSigns().containsKey(sign)) {
				SignHandler.deleteSign(event.getBlock().getLocation());
				event.getPlayer().sendMessage(ChatColor.GREEN + "You have deleted a server sign!");
			}
		}
	}

	public static boolean isCanSpeak() {
		return canSpeak;
	}

	public static void setCanSpeak(boolean canSpeak) {
		PlayerListener.canSpeak = canSpeak;
	}
}
