package me.avery246813579.minersfortune.gamemode.kits.perks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.google.common.collect.Lists;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePerk;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.conditions.PlayerInvincibility;
import me.avery246813579.minersfortune.gamemode.kits.KitHandler;

public class ArrowExplosionPerk extends GamePerk {
	private List<Material> nonExplodable;
	
	public ArrowExplosionPerk() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
		nonExplodable = new ArrayList<Material>(Arrays.asList(Material.GLASS, Material.IRON_BLOCK, Material.NETHER_BRICK, Material.COBBLESTONE, Material.STONE, Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.LAPIS_ORE, Material.SMOOTH_BRICK, Material.GOLD_BLOCK, Material.LAPIS_BLOCK, Material.BEACON, Material.ENDER_STONE, Material.SMOOTH_STAIRS, Material.MOSSY_COBBLESTONE, Material.COBBLESTONE_STAIRS, Material.COBBLE_WALL));
	}

	@Override
	protected void giveOnSpawn(Player player) {
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if (GameManager.getGameState() == GameState.Live && !PlayerInvincibility.isOnInvincibility()) {
			if (!(event.getEntity() instanceof Arrow)) {
				return;
			}

			if (!(event.getEntity().getShooter() instanceof Player)) {
				return;
			}

			if (!(GameManager.findPlayer((Player) event.getEntity().getShooter()).getKit() == KitHandler.kitGod)) {
				return;
			}

			event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 2.0F);
			event.getEntity().remove();
		}
	}

	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		List<Block> remove = Lists.newArrayList();
		for (Block block : event.blockList()) {
			if(nonExplodable.contains(block.getType())){
				remove.add(block);
			}
		}
	
		event.blockList().removeAll(remove);
	}
	
	@Override
	protected void remove() {
	}
	
	@Override
	protected void onEnable() {
	}
}
