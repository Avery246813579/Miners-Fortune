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

public class LootChests extends GameCondition implements Listener {
	public LootChests() {
		Bukkit.getPluginManager().registerEvents(this, MinersFortune.getPlugin());
	}

	/** Variables **/
	private List<ItemStack> items;
	private Map<World, Set<Vector>> resetChests = new WeakHashMap<World, Set<Vector>>();
	Random random = new Random();

	public void resetCondition(String set) {
		resetChests.clear();
		items = new ArrayList<ItemStack>();

		/** Materials | Entries 2/3 **/
		addItem("Iron Ingot", Material.IRON_INGOT, LootItemType.Material, new int[]{1}, 2);
		addItem("Gold Ingot", Material.GOLD_INGOT, LootItemType.Material, new int[]{1}, 3);
		
		/** Craftable | Entries 3 **/
		addItem("Flint", Material.FLINT, LootItemType.Craftable, new int[]{1, 2}, 3);
		addItem("String", Material.STRING, LootItemType.Craftable, new int[]{1, 2}, 3);
		addItem("Empty Bowl", Material.BOWL, LootItemType.Craftable, new int[]{1}, 3);
		addItem("Chicken's Feather", Material.FEATHER, LootItemType.Craftable, new int[]{1, 2}, 3);
		addItem("Wheat", Material.WHEAT, LootItemType.Craftable, new int[]{1, 2, 3}, 3);
		addItem("Oak Stick", Material.STICK, LootItemType.Craftable, new int[]{1}, 3);
		addItem("Brown Mushroom", Material.BROWN_MUSHROOM, LootItemType.Craftable, new int[]{1}, 3);
		addItem("Red Mushroom", Material.RED_MUSHROOM, LootItemType.Craftable, new int[]{1}, 3);

		/** Food | Entries 2 **/
		addItem("Baked Potato", Material.BAKED_POTATO, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Bread", Material.BREAD, LootItemType.Consumable, new int[]{1}, 2);
		addItem(ChatColor.RED + "Stolen Carrot", Material.CARROT_ITEM, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Fried Chicken", Material.COOKED_CHICKEN, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Smoked Fish", Material.COOKED_FISH, LootItemType.Consumable, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Bacon", Material.PORK, LootItemType.Consumable, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Grandma's Cookie", Material.COOKIE, LootItemType.Consumable, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Watermelon", Material.MELON, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Chicken Broth", Material.MUSHROOM_SOUP, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Pumpkin Pie", Material.PUMPKIN_PIE, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Potato", Material.POTATO_ITEM, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Red Apple", Material.APPLE, LootItemType.Consumable, new int[]{1}, 2);
		addItem(ChatColor.RED + "Zombie Flesh", Material.ROTTEN_FLESH, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Filet mignon", Material.COOKED_BEEF, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Raw Steak", Material.RAW_BEEF, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Raw Chicken", Material.RAW_CHICKEN, LootItemType.Consumable, new int[]{1}, 2);
		addItem("Raw Fish", Material.RAW_CHICKEN, LootItemType.Consumable, new int[]{1}, 2);

		/** Weapons | Entries 2 **/
		addItem("Oak Axe", Material.WOOD_AXE, LootItemType.Weapon, new int[]{1}, 3);
		addItem("Oak Sword", Material.WOOD_SWORD, LootItemType.Weapon, new int[]{1}, 3);
		addItem("Stone Axe", Material.STONE_AXE, LootItemType.Weapon, new int[]{1}, 2);
		addItem("Stone Sword", Material.STONE_SWORD, LootItemType.Weapon, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Cast Iron Axe", Material.IRON_AXE, LootItemType.Weapon, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Cast Iron Sword", Material.IRON_SWORD, LootItemType.Weapon, new int[]{1}, 1);
	
		/** Armor | Entries 1/2/3 **/
		addItem("Chainmail Helmet", Material.CHAINMAIL_HELMET, LootItemType.Wearable, new int[]{1}, 2);
		addItem("Chainmail Chestplate", Material.CHAINMAIL_CHESTPLATE, LootItemType.Wearable, new int[]{1}, 2);
		addItem("Chainmail Leggings", Material.CHAINMAIL_LEGGINGS, LootItemType.Wearable, new int[]{1}, 2);
		addItem("Chainmail Boots", Material.CHAINMAIL_BOOTS, LootItemType.Wearable, new int[]{1}, 2);
		addItem("Leather Helmet", Material.LEATHER_HELMET, LootItemType.Wearable, new int[]{1}, 2);
		addItem("Leather Chestplate", Material.LEATHER_CHESTPLATE, LootItemType.Wearable, new int[]{1}, 2);
		addItem("Leather Leggings", Material.LEATHER_LEGGINGS, LootItemType.Wearable, new int[]{1}, 2);
		addItem("Leather Boots", Material.LEATHER_BOOTS, LootItemType.Wearable, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Kings Helmet", Material.GOLD_HELMET, LootItemType.Wearable, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Kings Chestplate", Material.GOLD_CHESTPLATE, LootItemType.Wearable, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Kings Leggings", Material.GOLD_LEGGINGS, LootItemType.Wearable, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Kings Boots", Material.GOLD_BOOTS, LootItemType.Wearable, new int[]{1}, 2);
		addItem(ChatColor.GOLD + "Cast Iron Helmet", Material.IRON_HELMET, LootItemType.Wearable, new int[]{1}, 1);
		addItem(ChatColor.GOLD + "Cast Iron Chestplate", Material.IRON_CHESTPLATE, LootItemType.Wearable, new int[]{1}, 1);
		addItem(ChatColor.GOLD + "Cast Iron Leggings", Material.IRON_LEGGINGS, LootItemType.Wearable, new int[]{1}, 1);
		addItem(ChatColor.GOLD + "Cast Iron", Material.IRON_BOOTS, LootItemType.Wearable, new int[]{1}, 1);
		addItem(ChatColor.YELLOW + "Avery's Helmet", Material.GOLD_HELMET, LootItemType.Wearable, new int[]{1}, 1);

		/** Placeable | Entries 2 **/
		addItem("Spider Web", Material.WEB, LootItemType.Placeable, new int[]{1}, 2);
		addItem("Fallen Leave", Material.LEAVES, LootItemType.Placeable, new int[]{1}, 2);
		addItem("Nether Blocker", Material.NETHERRACK, LootItemType.Placeable, new int[]{1}, 2);

		/** Utility **/
		addItem("Shears", Material.SHEARS, LootItemType.Tool, new int[]{1}, 2);
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
