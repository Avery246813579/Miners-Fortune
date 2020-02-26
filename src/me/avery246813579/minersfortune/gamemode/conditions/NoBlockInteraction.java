package me.avery246813579.minersfortune.gamemode.conditions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameType;

public class NoBlockInteraction extends GameCondition implements Listener {
	private List<Material> materialType = new ArrayList<Material>();

	public NoBlockInteraction() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	@Override
	public void resetCondition(String conditition) {
		if (GameManager.getGameType() == GameType.SurvivalGames) {
			materialType.add(Material.WEB);
			materialType.add(Material.LEAVES);
			materialType.add(Material.NETHERRACK);
		}
	}

	@Override
	public void stopCondition() {

	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			if (!materialType.contains(event.getBlock().getType())) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			if (!materialType.contains(event.getBlock().getType())) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockDamage(BlockDamageEvent event) {
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			if (!materialType.contains(event.getBlock().getType())) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockBurn(BlockBurnEvent event) {
		if (GameManager.getGameState() == GameState.Live && GameManager.getGameConditions().contains(this)) {
			if (!materialType.contains(event.getBlock().getType())) {
				event.setCancelled(true);
			}
		}
	}
}
