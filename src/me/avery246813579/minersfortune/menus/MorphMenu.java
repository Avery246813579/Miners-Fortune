package me.avery246813579.minersfortune.menus;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.ranks.Ranks;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MorphMenu extends Menu {
	public MorphMenu() {
		super(createItem(Material.MONSTER_EGG, ChatColor.YELLOW + "Morphs", new String[] { ChatColor.GRAY + "Click to access" }), "Morph Menu");
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory firstInventory = Bukkit.createInventory(null, 54, "Morph Menu");
		firstInventory.setItem(10, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 93), ChatColor.YELLOW + "Chicken", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		firstInventory.setItem(11, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 92), ChatColor.YELLOW + "Cow", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		firstInventory.setItem(12, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 91), ChatColor.YELLOW + "Sheep", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		firstInventory.setItem(13, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 90), ChatColor.YELLOW + "Pig", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		firstInventory.setItem(14, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 52), ChatColor.YELLOW + "Spider", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		firstInventory.setItem(15, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 54), ChatColor.YELLOW + "Zombie", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));
		firstInventory.setItem(16, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 51), ChatColor.YELLOW + "Skeleton", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_GRAY + "IRON+" }));

		firstInventory.setItem(19, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 98), ChatColor.YELLOW + "Ocelot", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		firstInventory.setItem(20, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 65), ChatColor.YELLOW + "Bat", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		firstInventory.setItem(21, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 94), ChatColor.YELLOW + "Squid", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		firstInventory.setItem(22, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 95), ChatColor.YELLOW + "Wolf", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		firstInventory.setItem(23, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 59), ChatColor.YELLOW + "Cave Spider", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		firstInventory.setItem(24, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 55), ChatColor.YELLOW + "Slime", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));
		firstInventory.setItem(25, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 50), ChatColor.YELLOW + "Creeper", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.GOLD + "GOLD+" }));

		firstInventory.setItem(28, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 100), ChatColor.YELLOW + "Horse", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		firstInventory.setItem(29, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 120), ChatColor.YELLOW + "Villager", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		firstInventory.setItem(30, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 96), ChatColor.YELLOW + "Mooshroom", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		firstInventory.setItem(31, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 58), ChatColor.YELLOW + "Enderman", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND" }));
		firstInventory.setItem(32, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 57), ChatColor.YELLOW + "Zombie Pigman", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		firstInventory.setItem(33, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 66), ChatColor.YELLOW + "Witch", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		firstInventory.setItem(34, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 60), ChatColor.YELLOW + "Silverfish", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));

		Inventory secondInventory = Bukkit.createInventory(null, 54, "Morph Menu");
		secondInventory.setItem(10, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 61), ChatColor.YELLOW + "Blaze", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(11, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 62), ChatColor.YELLOW + "Magma Cube", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(12, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 1), ChatColor.YELLOW + "Wither Skeleton", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(13, createItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 2), ChatColor.YELLOW + "Zombie Villager", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(14, createItem(Material.IRON_BLOCK, ChatColor.YELLOW + "Iron Golem", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(15, createItem(Material.SNOW_BLOCK, ChatColor.YELLOW + "Snow Golem", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(16, createItem(Material.ROTTEN_FLESH, ChatColor.YELLOW + "Undead Horse", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));

		secondInventory.setItem(19, createItem(Material.BONE, ChatColor.YELLOW + "Skeleton Horse", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(20, createItem(Material.HAY_BLOCK, ChatColor.YELLOW + "Mule", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(21, createItem(Material.GOLDEN_CARROT, ChatColor.YELLOW + "Donkey", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.DARK_AQUA + "DIAMOND+" }));
		secondInventory.setItem(22, createItem(Material.GHAST_TEAR, ChatColor.YELLOW + "Ghast", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.BLUE + "MOD+" }));
		secondInventory.setItem(23, createItem(Material.NETHER_STAR, ChatColor.YELLOW + "Wither", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.BLUE + "MOD+" }));
		secondInventory.setItem(24, createItem(Material.DRAGON_EGG, ChatColor.YELLOW + "Ender Dragon", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.RED + "ADMIN+" }));
		secondInventory.setItem(25, createItem(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), ChatColor.YELLOW + "Giant", new String[] { ChatColor.GRAY + "Click to disguise", " ", ChatColor.GRAY + "Exclusive: " + ChatColor.RED + "ADMIN+" }));

		return new Inventory[] { firstInventory, secondInventory };
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		DisguiseType disguiseType = null;

		if (item.getType() == Material.MONSTER_EGG) {
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Chicken")) {
				if (hasRank(player, Ranks.IRON)) {
					disguiseType = DisguiseType.CHICKEN;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Cow")) {
				if (hasRank(player, Ranks.IRON)) {
					disguiseType = DisguiseType.COW;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Sheep")) {
				if (hasRank(player, Ranks.IRON)) {
					disguiseType = DisguiseType.SHEEP;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Pig")) {
				if (hasRank(player, Ranks.IRON)) {
					disguiseType = DisguiseType.PIG;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Spider")) {
				if (hasRank(player, Ranks.IRON)) {
					disguiseType = DisguiseType.SPIDER;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Zombie")) {
				if (hasRank(player, Ranks.IRON)) {
					disguiseType = DisguiseType.ZOMBIE;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Skeleton")) {
				if (hasRank(player, Ranks.IRON)) {
					disguiseType = DisguiseType.SKELETON;
				}
			}

			else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Ocelot")) {
				if (hasRank(player, Ranks.GOLD)) {
					disguiseType = DisguiseType.OCELOT;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Bat")) {
				if (hasRank(player, Ranks.GOLD)) {
					disguiseType = DisguiseType.BAT;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Squid")) {
				if (hasRank(player, Ranks.GOLD)) {
					disguiseType = DisguiseType.SQUID;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Wolf")) {
				if (hasRank(player, Ranks.GOLD)) {
					disguiseType = DisguiseType.WOLF;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Cave Spider")) {
				if (hasRank(player, Ranks.GOLD)) {
					disguiseType = DisguiseType.CAVE_SPIDER;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Slime")) {
				if (hasRank(player, Ranks.GOLD)) {
					disguiseType = DisguiseType.SLIME;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Creeper")) {
				if (hasRank(player, Ranks.GOLD)) {
					disguiseType = DisguiseType.CREEPER;
				}
			}

			else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Horse")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.HORSE;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Villager")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.VILLAGER;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Mooshroom")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.MUSHROOM_COW;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Enderman")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					/**disguiseType = DisguiseType.ENDERMAN;**/
					sendMessae(player, ChatColor.RED + "Enderman disguise is crashing peoples clients! Its disabled at the moment!");
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Zombie Pigman")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.PIG_ZOMBIE;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Witch")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.WITCH;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Silverfish")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.SILVERFISH;
				}
			}

			else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Blaze")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.BLAZE;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Magma Cube")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.MAGMA_CUBE;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Wither Skeleton")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.WITHER_SKELETON;
				}
			} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Zombie Villager")) {
				if (hasRank(player, Ranks.DIAMOND)) {
					disguiseType = DisguiseType.ZOMBIE_VILLAGER;
				}
			} else {
				player.closeInventory();
				return;
			}
		} else if (item.getType() == Material.IRON_BLOCK) {
			if (hasRank(player, Ranks.DIAMOND)) {
				disguiseType = DisguiseType.IRON_GOLEM;
			}
		} else if (item.getType() == Material.SNOW_BLOCK) {
			if (hasRank(player, Ranks.DIAMOND)) {
				disguiseType = DisguiseType.SNOWMAN;
			}
		} else if (item.getType() == Material.ROTTEN_FLESH) {
			if (hasRank(player, Ranks.DIAMOND)) {
				disguiseType = DisguiseType.UNDEAD_HORSE;
			}
		} else if (item.getType() == Material.BONE) {
			if (hasRank(player, Ranks.DIAMOND)) {
				disguiseType = DisguiseType.SKELETON_HORSE;
			}
		} else if (item.getType() == Material.HAY_BLOCK) {
			if (hasRank(player, Ranks.DIAMOND)) {
				disguiseType = DisguiseType.MULE;
			}
		} else if (item.getType() == Material.GOLDEN_CARROT) {
			if (hasRank(player, Ranks.DIAMOND)) {
				disguiseType = DisguiseType.DONKEY;
			}
		} else if (item.getType() == Material.GHAST_TEAR) {
			if (hasRank(player, Ranks.MODERATOR)) {
				disguiseType = DisguiseType.GHAST;
			}
		} else if (item.getType() == Material.NETHER_STAR) {
			if (hasRank(player, Ranks.MODERATOR)) {
				disguiseType = DisguiseType.WITHER;
			}
		} else if (item.getType() == Material.DRAGON_EGG) {
			if (hasRank(player, Ranks.ADMIN)) {
				disguiseType = DisguiseType.ENDER_DRAGON;
			}
		} else if (item.getType() == Material.SKULL_ITEM) {
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Giant")) {
				if (hasRank(player, Ranks.ADMIN)) {
					disguiseType = DisguiseType.GIANT;
				}
			}
		} else {
			return;
		}

		if (disguiseType != null) {
			if (DisguiseAPI.isDisguised(player)) {
				DisguiseAPI.undisguiseToAll(player);
			}

			List<Player> onePointEightPlayers = new ArrayList<Player>();

			for (Player players : Bukkit.getOnlinePlayers()) {
				int version = ((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion();

				if (version < 47) {
					onePointEightPlayers.add(players);
				}
			}

			DisguiseAPI.disguiseToPlayers(player, new MobDisguise(disguiseType), onePointEightPlayers);
			sendMessae(player, ChatColor.YELLOW + "You have been disguised as a " + item.getItemMeta().getDisplayName() + "!");
		} else {
			player.closeInventory();
			return;
		}

		player.closeInventory();
	}
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
