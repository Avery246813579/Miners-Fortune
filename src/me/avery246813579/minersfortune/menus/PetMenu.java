package me.avery246813579.minersfortune.menus;

import java.util.HashMap;
import java.util.Map;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;
import net.minecraft.server.v1_7_R4.EntityInsentient;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PetMenu extends Menu implements Runnable {
	private static Map<Player, Entity> pets = new HashMap<Player, Entity>();

	public PetMenu() {
		super(createItem(Material.BONE, ChatColor.YELLOW + "Pets", new String[] { ChatColor.GRAY + "Click to access" }), "Pet Menu");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(MinersFortune.getPlugin(), this, 20L, 20L);
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory petInventory = Bukkit.createInventory(null, 54, "Pet Menu");
		petInventory.setItem(10, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 93), ChatColor.YELLOW + "Chicken", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+"  }));
		petInventory.setItem(11, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 95), ChatColor.YELLOW + "Wolf", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		petInventory.setItem(12, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 55), ChatColor.YELLOW + "Slime", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		petInventory.setItem(13, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 90), ChatColor.YELLOW + "Pig", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		petInventory.setItem(14, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 91), ChatColor.YELLOW + "Sheep", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		petInventory.setItem(15, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 62), ChatColor.YELLOW + "Magma Cube", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+"  }));
		petInventory.setItem(16, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 98), ChatColor.YELLOW + "Ocelot", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));

		petInventory.setItem(19, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 65), ChatColor.YELLOW + "Bat", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		petInventory.setItem(20, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 96), ChatColor.YELLOW + "Mooshroom", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		petInventory.setItem(21, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 50), ChatColor.YELLOW + "Creeper", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));

		return new Inventory[] { petInventory };
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		EntityType entityType = null;

		if (item.getType() == Material.MONSTER_EGG) {
			if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Chicken")){
				if(hasRank(player, Ranks.IRON)){
					entityType = EntityType.CHICKEN;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Wolf")){
				if(hasRank(player, Ranks.IRON)){
					entityType = EntityType.WOLF;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Slime")){
				if(hasRank(player, Ranks.IRON)){
					entityType = EntityType.SLIME;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Pig")){
				if(hasRank(player, Ranks.IRON)){
					entityType = EntityType.PIG;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Sheep")){
				if(hasRank(player, Ranks.GOLD)){
					entityType = EntityType.SHEEP;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Magma Cube")){
				if(hasRank(player, Ranks.GOLD)){
					entityType = EntityType.MAGMA_CUBE;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Ocelot")){
				if(hasRank(player, Ranks.GOLD)){
					entityType = EntityType.OCELOT;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Bat")){
				if(hasRank(player, Ranks.DIAMOND)){
					entityType = EntityType.BAT;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Mooshroom")){
				if(hasRank(player, Ranks.DIAMOND)){
					entityType = EntityType.MUSHROOM_COW;
				}
			}else if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Creeper")){
				if(hasRank(player, Ranks.DIAMOND)){
					entityType = EntityType.CREEPER;
				}
			}else{
				player.closeInventory();
				return;
			}
		} else {
			return;
		}

		if (entityType != null) {
			if (pets.containsKey(player)) {
				Entity entity = pets.get(player);
				pets.remove(player);
				entity.remove();
			}

			LivingEntity petEntity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), entityType);
			if(entityType == EntityType.OCELOT){
				Ocelot ocelot = (Ocelot) petEntity;
				ocelot.setTamed(true);
				ocelot.setOwner(player);
			}

			petEntity.setCustomName(RankManager.findPlayerRank(player).getChatColor() + player.getName() + "'s Pet");
			petEntity.setCustomNameVisible(true);
			pets.put(player, petEntity);
			
			sendMessae(player, ChatColor.YELLOW + "You have spawned in a " + item.getItemMeta().getDisplayName() + " pet!");
		}else{
			player.closeInventory();
			return;
		}
		
		player.closeInventory();
	}

	public static boolean livingEntityMoveTo(LivingEntity livingEntity, Location target, float speed) {
		return ((EntityInsentient) ((CraftLivingEntity) livingEntity).getHandle()).getNavigation().a(target.getX(), target.getY(), target.getZ(), speed);
	}

	@Override
	public void run() {
		if (MenuHandler.isUseVanity()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (pets.containsKey(player)) {
					Entity pet = pets.get(player);
					
					if(isOutsideSquare(player.getLocation(), pet.getLocation(), 2)){
						if(isOutsideSquare(player.getLocation(), pet.getLocation(), 10)){
							pet.teleport(player.getLocation());
						}else{
							livingEntityMoveTo((LivingEntity) pet, player.getLocation(), 1F);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		if (MenuHandler.isUseVanity()) {
			if(pets.containsValue(event.getEntity())){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onEntityTarget(EntityTargetLivingEntityEvent event){
		if (MenuHandler.isUseVanity()) {
			if(pets.containsValue(event.getEntity())){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		if(MenuHandler.isUseVanity()){
			if(pets.containsKey(event.getPlayer())){
				pets.get(event.getPlayer()).remove();
				pets.remove(event.getPlayer());
			}
		}
	}
	
	public boolean isOutsideSquare(Location player, Location entity, int distance){
		if(player.distance(entity) > distance) {
			return true;
		}
		
		return false;
	}

	public static Map<Player, Entity> getPets() {
		return pets;
	}

	public static void setPets(Map<Player, Entity> pets) {
		PetMenu.pets = pets;
	}
	
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
