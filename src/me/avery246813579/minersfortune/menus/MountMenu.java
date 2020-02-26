package me.avery246813579.minersfortune.menus;

import java.util.HashMap;
import java.util.Map;

import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MountMenu extends Menu {
	private static Map<Player, Entity> mounts = new HashMap<Player, Entity>();

	public MountMenu() {
		super(createItem(Material.DIAMOND_BARDING, ChatColor.YELLOW + "Mounts", new String[] { ChatColor.GRAY + "Click to access" }), "Mount Menu");
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory mountInventory = Bukkit.createInventory(null, 54, "Mount Menu");

		mountInventory.setItem(10, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 100), ChatColor.YELLOW + "Horse", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		mountInventory.setItem(11, createItem(Material.HAY_BLOCK, ChatColor.YELLOW + "Mule", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		mountInventory.setItem(12, createItem(Material.GOLDEN_CARROT, ChatColor.YELLOW + "Donkey", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		mountInventory.setItem(13, createItem(Material.ROTTEN_FLESH, ChatColor.YELLOW + "Undead Horse", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		mountInventory.setItem(14, createItem(Material.BONE, ChatColor.YELLOW + "Skeleton Horse", new String[] { ChatColor.GRAY + "Click to spawn", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));

		return new Inventory[] { mountInventory };
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		Horse mountEntity = null;

		if (item.getType() == Material.MONSTER_EGG) {
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Horse")) {
				if (hasRank(player, Ranks.IRON)) {
					mountEntity = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				}
			} else {
				player.closeInventory();
				return;
			}
		} else if (item.getType() == Material.HAY_BLOCK) {
			if (hasRank(player, Ranks.GOLD)) {
				mountEntity = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				mountEntity.setVariant(Variant.MULE);
			}
		} else if (item.getType() == Material.GOLDEN_CARROT) {
			if (hasRank(player, Ranks.GOLD)) {
				mountEntity = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				mountEntity.setVariant(Variant.DONKEY);
			}
		} else if (item.getType() == Material.ROTTEN_FLESH) {
			if (hasRank(player, Ranks.DIAMOND)) {
				mountEntity = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				mountEntity.setVariant(Variant.UNDEAD_HORSE);
			}
		} else if (item.getType() == Material.BONE) {
			if (hasRank(player, Ranks.DIAMOND)) {
				mountEntity = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				mountEntity.setVariant(Variant.SKELETON_HORSE);
			}
		} else {
			return;
		}

		if (mountEntity != null) {
			if (mounts.containsKey(player)) {
				mounts.get(player).remove();
				mounts.remove(player);
			}

			mountEntity.setCustomName(RankManager.findPlayerRank(player).getChatColor() + player.getName() + "'s Mount");
			mountEntity.setCustomNameVisible(true);
			mountEntity.setTamed(true);
			mountEntity.setAdult();
			mountEntity.setOwner(player);
			mountEntity.getInventory().setSaddle(new ItemStack(Material.SADDLE));
			mounts.put(player, mountEntity);

			sendMessae(player, ChatColor.YELLOW + "You have spawned in a " + item.getItemMeta().getDisplayName() + " mount!");
		} else {
			player.closeInventory();
			return;
		}

		player.closeInventory();
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (MenuHandler.isUseVanity()) {
			if (mounts.containsValue(event.getEntity())) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onEntityTarget(EntityTargetLivingEntityEvent event) {
		if (MenuHandler.isUseVanity()) {
			if (mounts.containsValue(event.getEntity())) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (MenuHandler.isUseVanity()) {
			if (mounts.containsKey(event.getPlayer())) {
				mounts.get(event.getPlayer()).remove();
				mounts.remove(event.getPlayer());
			}
		}
	}

	public static Map<Player, Entity> getMounts() {
		return mounts;
	}

	public static void setMounts(Map<Player, Entity> mounts) {
		MountMenu.mounts = mounts;
	}
	
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
