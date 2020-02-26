package me.avery246813579.minersfortune.gamemode.conditions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameCondition;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameKit.ItemType;
import me.avery246813579.minersfortune.util.Enchants;

public class AgLootChests extends GameCondition implements Listener {
	public AgLootChests() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	/** Variables **/
	private List<ItemStack> items;
	private Map<World, Set<Vector>> resetChests = new WeakHashMap<World, Set<Vector>>();
	Random random = new Random();

	public void resetCondition(String set) {
		items = new ArrayList<ItemStack>();

		items.add(createItemStacK(ChatColor.YELLOW + "God's Sword", Material.DIAMOND_SWORD, ItemType.Weapon, new String[]{ChatColor.GRAY + "Sword for the gods."}, 1, new Enchantment[]{Enchantment.DAMAGE_UNDEAD, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ARTHROPODS, Enchantment.FIRE_ASPECT}, new int[]{7, 7, 7,2}));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Bow", Material.BOW, ItemType.Weapon, new String[]{ChatColor.GRAY + "Bow for the gods."}, 1, new Enchantment[]{Enchantment.ARROW_INFINITE, Enchantment.ARROW_FIRE, Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_DAMAGE}, new int[]{1,1,2,7}));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Pick", Material.DIAMOND_PICKAXE, ItemType.Tool, new String[]{ChatColor.GRAY + "Pick for the gods."}, 1, new Enchantment[]{Enchantment.DIG_SPEED}, new int[]{5}));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Pearls", Material.ENDER_PEARL, ItemType.Tool, new String[]{ChatColor.GRAY + "Pearls for the gods."}, 2));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Speed Potion", new ItemStack(Material.POTION, 2, (byte) 8226), ItemType.Consumable, new String[]{ChatColor.GRAY + "A drink for the gods."}, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Strength Potion", new ItemStack(Material.POTION, 2, (byte) 8233), ItemType.Consumable, new String[]{ChatColor.GRAY + "A drink for the gods."}, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Apple", new ItemStack(Material.GOLDEN_APPLE, 2, (byte) 1), ItemType.Consumable, new String[]{ChatColor.GRAY + "A meal for the gods."}, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Pork", Material.PORK, ItemType.Consumable, new String[]{ChatColor.GRAY + "A meal for the gods."}, 10));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Compass", Material.COMPASS, ItemType.Tool, new String[]{ChatColor.GRAY + "A navigation device for the gods."}, 1));
		items.add(createItemStacK(ChatColor.YELLOW + "God's Arrow", Material.ARROW, ItemType.Tool, new String[]{ChatColor.GRAY + "A item for the god's bow."}, 1));
		items.add(createItemStacK("God's Boots", Material.DIAMOND_BOOTS, ItemType.Wearable, new String[]{"Best protection for a god"}, 1, new Enchantment[]{Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FALL, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE}, new int[]{7,7,7,7,7}));
		items.add(createItemStacK("God's Leggings", Material.DIAMOND_LEGGINGS, ItemType.Wearable, new String[]{"Best protection for a god"}, 1, new Enchantment[]{Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE}, new int[]{7,7,7,7}));
		items.add(createItemStacK("God's Chestplate", Material.DIAMOND_CHESTPLATE, ItemType.Wearable, new String[]{"Best protection for a god"}, 1, new Enchantment[]{Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE}, new int[]{7,7,7,7}));
		items.add(createItemStacK("God's Helmet", Material.DIAMOND_HELMET, ItemType.Wearable, new String[]{"Best protection for a god"}, 1, new Enchantment[]{Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.OXYGEN}, new int[]{7,7,7,7,7}));
	}
	
	@Override
	public void stopCondition(){

	}

	public void addItem(String name, Material material, LootItemType itemType, int[] amount, int entries) {
		ItemStack itemStack = new ItemStack(material, amount[random.nextInt(amount.length) + 0]);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		
		for(int i = 0; i < entries; i++){
			items.add(itemStack);
		}
	}
	
	public ItemStack createItemStacK(String name, Material material, ItemType itemType, String[] stringLore, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());

		for (String s : stringLore) {
			lore.add(s);
		}

		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}
	
	public ItemStack createItemStacK(String name, ItemStack itemStack, ItemType itemType, String[] stringLore, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());

		for (String s : stringLore) {
			lore.add(s);
		}

		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);

		return itemStack;
	}
	
	public ItemStack createItemStacK(String name, Material material, ItemType itemType, String[] stringLore, int amount, Enchantment enchantment, int level) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());

		for (String s : stringLore) {
			lore.add(s);
		}

		itemStack.addUnsafeEnchantment(enchantment, level);

		itemStack = Enchants.updateEnchants(itemStack, lore, name);
		return itemStack;
	}

	public ItemStack createItemStacK(String name, Material material, ItemType itemType, String[] stringLore, int amount, Enchantment[] enchantments, int[] levels) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + name);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + itemType.toString());

		for (String s : stringLore) {
			lore.add(s);
		}

		int i = 0;
		for (Enchantment enchantment : enchantments) {
			int level = levels[i];
			itemStack.addUnsafeEnchantment(enchantment, level);
			i++;
		}
		
		itemStack = Enchants.updateEnchants(itemStack, lore, name);

		return itemStack;
	}

	public ItemStack[] getItems(Chest chest) {
		ItemStack[] items = new ItemStack[chest.getBlockInventory().getSize()];
		int amountOfItems = random.nextInt(5) + 1;
		for (int i = 0; i < amountOfItems; i++) {
			int itemNumber = random.nextInt(this.items.size()) + 0;
			int place = random.nextInt(chest.getBlockInventory().getSize()) + 0;
			items[place] = this.items.get(itemNumber);
		}
		return items;
	}

	private Set<Vector> getVectorSet(World world) {
		Set<Vector> result = resetChests.get(world);

		if (result == null) {
			result = new HashSet<Vector>();
			resetChests.put(world, result);
		}
		return result;
	}

	@EventHandler
	public void onPlayerInventoryInteract(PlayerInteractEvent event) {
		if (GameManager.getGameConditions().contains(this) && GameManager.getGameState() == GameState.Live) {
			Player player = event.getPlayer();
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.CHEST) {
				if (GameManager.findPlayer(player).isAlive()) {
					Vector vector = event.getClickedBlock().getLocation().toVector();
					Set<Vector> set = getVectorSet(event.getPlayer().getWorld());

					if (!set.contains(vector)) {
						Chest chest = ((Chest) event.getClickedBlock().getState());
						ItemStack[] items = getItems(chest);
						chest.getBlockInventory().setContents(items);
						set.add(vector);
					}
				}
			}
		}
	}

	public enum LootItemType {
		Consumable, Material, Craftable, Placeable, Wearable, Weapon, Potion, Tool;
	}
}
