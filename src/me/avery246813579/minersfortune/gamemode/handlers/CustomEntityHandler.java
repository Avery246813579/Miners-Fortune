package me.avery246813579.minersfortune.gamemode.handlers;

import java.util.HashMap;
import java.util.Map;
import me.avery246813579.minersfortune.MinersFortune;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomEntityHandler implements Listener {
	private static Map<Entity, Location> customEntities = new HashMap<Entity, Location>();
	private static Map<Entity, String> names = new HashMap<Entity, String>();

	public CustomEntityHandler() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());

		/**Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), new Runnable() {
			@Override
			public void run() {
				Iterator<Entry<Entity, Location>> it = customEntities.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<Entity, Location> pairs = (Map.Entry<Entity, Location>) it.next();

					if (!pairs.getKey().isDead()) {
						pairs.getKey().setVelocity(new Vector(0D, 0D, 0D));
						pairs.getKey().teleport(pairs.getValue());
					}else{
						Entity e = pairs.getKey();
						Entity entity = pairs.getValue().getWorld().spawnEntity(pairs.getValue(), pairs.getKey().getType());
						customEntities.remove(e);
						LivingEntity livingEntity = (LivingEntity) entity;
						livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 300));
						livingEntity.setCustomName(names.get(e));
						livingEntity.setCustomNameVisible(true);
						livingEntity.setRemoveWhenFarAway(false);
						livingEntity.setHealth(20.0);
						livingEntity.setMaxHealth(20.0);
						customEntities.put(entity, pairs.getValue());
						String name = names.get(e);
						names.remove(e);
						names.put(entity, name);
						e.remove();
					}
				}
			}
		}, 20L, 20L);**/
	}

	public static void createCustomEntity(String displayName, Location location, EntityType entityType) {
		Entity entity = location.getWorld().spawnEntity(location, entityType);
		LivingEntity livingEntity = (LivingEntity) entity;
		livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000000, 300));
		livingEntity.setCustomName(displayName);
		livingEntity.setCustomNameVisible(true);
		livingEntity.setRemoveWhenFarAway(false);
		livingEntity.setHealth(20.0);
		livingEntity.setMaxHealth(20.0);
		customEntities.put(livingEntity, location);
		names.put(entity, displayName);
	}

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (customEntities.containsKey(event.getEntity())) {
			event.setCancelled(true);
		}
	}
}
