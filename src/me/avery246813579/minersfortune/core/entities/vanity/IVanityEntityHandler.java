package me.avery246813579.minersfortune.core.entities.vanity;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.entities.IEntity;
import me.avery246813579.minersfortune.core.players.IPlayer;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;
import me.avery246813579.minersfortune.util.MessageUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.HorseInventory;
import org.spigotmc.event.entity.EntityMountEvent;

public class IVanityEntityHandler implements Listener, Runnable {
	private static List<IVanityEntity> iVanityEntities = new ArrayList<IVanityEntity>();

	public IVanityEntityHandler() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());

		Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), this, 20L, 20L);
	}

	/*
	 * Checks the Vantity Entities and makes sure they are near their owner
	 */
	public void run() {
		for(IVanityEntity iVanityEntity : iVanityEntities){
			IEntity entity = iVanityEntity.getIEntity();
			
			if(entity.isOutsideDistance(iVanityEntity.getOwner().getLocation(), 4)){
				if(entity.isOutsideDistance(iVanityEntity.getOwner().getLocation(), 15)){
					entity.getEntity().teleport(iVanityEntity.getOwner().getLocation());
				}else{
					entity.moveTo(iVanityEntity.getOwner().getLocation(), 2F);
				}
			}
		}
	}

	/*
	 * Cancels event that a Vanity Entity is not mounted by the owner.
	 */
	@EventHandler
	public void onEntityMount(EntityMountEvent event) {
		Entity entity = event.getMount();

		if (contains(entity)) {
			IVanityEntity iVanityEntity = get(entity);
			
			if(event.getMount() instanceof Player){
				Player player = (Player) event.getMount();
				
				if(iVanityEntity.getOwner() != player){
					MessageUtil.sendMessage("Mount", player, "You can't mount another players animal!");
					event.setCancelled(true);
				}
			}
		}
	}

	/*
	 * Cancels event that a Vanity Entity gets damaged!
	 */
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (contains(event.getEntity())) {
			event.setCancelled(true);
		}
	}

	/*
	 * Cancels event that a Vanity Entity targets a entity
	 */
	@EventHandler
	public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent event) {
		if (contains(event.getEntity())) {
			event.setCancelled(true);
		}
	}
	
	/*
	 * Removes Vanity Entity when player leaves
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		IPlayer iPlayer = IPlayerHandler.findIPlayer(event.getPlayer());

		if (iPlayer.getMount() != null) {
			iPlayer.getMount().getIEntity().despawn();
			iVanityEntities.remove(iPlayer.getMount());
		}
	}

	/*
	 * Denies opening Mount Inventory!
	 */
	@EventHandler
	public void onInventoryInteract(InventoryOpenEvent event){
		if(event.getInventory() instanceof HorseInventory){
			Horse horse = (Horse) event.getInventory().getHolder();

			if(contains(horse)){
				event.setCancelled(true);
			}
		}
	}
	
	/*
	 * Checks if Entity is contained in Vanity Entities List
	 */
	public static boolean contains(Entity entity) {
		if (get(entity) != null) {
			return true;
		}

		return false;
	}

	/*
	 * Gets Vanity Entity from Entity List
	 */
	public static IVanityEntity get(Entity entity) {
		for (IVanityEntity iVanityEntity : iVanityEntities) {
			if (iVanityEntity.getEntity() == entity) {
				return iVanityEntity;
			}
		}

		return null;
	}

	public static List<IVanityEntity> getiVanityEntities() {
		return iVanityEntities;
	}

	public static void setiVanityEntities(List<IVanityEntity> iVanityEntities) {
		IVanityEntityHandler.iVanityEntities = iVanityEntities;
	}
}
