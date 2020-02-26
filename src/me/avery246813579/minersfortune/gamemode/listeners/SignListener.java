package me.avery246813579.minersfortune.gamemode.listeners;

import java.util.HashMap;
import java.util.List;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.sql.tables.AccountTable;
import me.avery246813579.minersfortune.util.Enchantments;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

public class SignListener implements Listener {
	@EventHandler
	public void onBlockSmash(BlockBreakEvent event) {
		if (isSign(event.getBlock()))
			event.setCancelled(true);
	}

	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		List<Block> remove = Lists.newArrayList();
		for (Block block : event.blockList())
			if (isSign(block))
				remove.add(block);
		event.blockList().removeAll(remove);
	}

	@EventHandler
	public void onBurn(BlockBurnEvent event) {
		if (isSign(event.getBlock()))
			event.setCancelled(true);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.isCancelled())
			return;
		if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && (event.getClickedBlock().getType() == Material.WALL_SIGN)) {
			org.bukkit.block.Sign sign = (org.bukkit.block.Sign) event.getClickedBlock().getState();

			if (!ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase(sign.getLine(0))) {
				event.getPlayer().sendMessage(signPurchase(event.getPlayer(), sign));
				event.setCancelled(true);
			}
		}
	}

	boolean isSign(Block block) {
		if (block.getType() == Material.WALL_SIGN) {
			org.bukkit.block.Sign sign = (org.bukkit.block.Sign) block.getState();

			if (!ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase(sign.getLine(0))) {
				return true;
			}
		}
		return false;
	}

	public static BlockFace signFacing(org.bukkit.block.Sign sign) {
		org.bukkit.material.Sign signData = (org.bukkit.material.Sign) sign.getData();
		return signData.getFacing();
	}

	@EventHandler
	public void blockPhysics(BlockPhysicsEvent event) {
		if (isSign(event.getBlock()))
			event.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	protected String signPurchase(Player p, Sign sign) {
		if(GameManager.getGameState() == GameState.Live){
		if (ChatColor.stripColor(sign.getLine(0)).equals("[Buy]")) {
			int amount = Integer.parseInt(sign.getLine(1));
			ItemStack item = null;
			try {
				item = MinersFortune.getItemDatabase().get(sign.getLine(2).toLowerCase());
			} catch (IndexOutOfBoundsException e) {
				System.out.println("unknownItemName");
			} catch (Exception e) {
				System.out.println("unknownItemName");
			}
			if (item == null)
				return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Error, Can't fetch item from database";
			int price = 0;
			if(sign.getLine(3).contains("$")){
				price = Integer.parseInt(sign.getLine(3).substring(1));
			}else{
				price = Integer.parseInt(sign.getLine(3).split(" ")[0]);
			}
			
			item.setAmount(amount);
			if (!canAfford(p, price))
				return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Can't afford to purchase this item!";
			if (canFit(p, new ItemStack[] { item })) {
				item.setAmount(1);
				for (int i = 0; i < amount; i++)
					p.getInventory().addItem(new ItemStack[] { item });
				AccountTable accountTable = MinersFortune.getPlugin().getSqlHandler().getAccount(p);
				accountTable.setCredits(accountTable.getCredits() - price);
				MinersFortune.getPlugin().getSqlHandler().saveAccount(accountTable);
				p.updateInventory();
				return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Item purchased for $" + price;
			}
			return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Can't fit in inventory";
		}
		if ((ChatColor.stripColor(sign.getLine(0)).equals("[Enchant]")) && (sign.getLine(1).equals("Any"))) {
			int price = Integer.parseInt(sign.getLine(3).substring(1));
			String[] wording = sign.getLine(2).split(":");
			if (wording.length > 0) {
				Enchantment enchant = Enchantments.getByName(wording[0]);
				if (enchant == null)
					return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Bad enchantment detected, Please notify a admin";
				int level = 1;
				if (wording.length > 1)
					level = Integer.parseInt(wording[1]);
				ItemStack item = p.getItemInHand();
				if ((item == null) || (item.getType() == Material.AIR) || (!enchant.canEnchantItem(item))) {
					return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Can't enchant item in hand!";
				}
				if (!canAfford(p, price))
					return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Can't afford enchantment!";
				if ((item.containsEnchantment(enchant)) && (item.getEnchantmentLevel(enchant) <= level))
					return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Item is already enchanted!";
				AccountTable accountTable = MinersFortune.getPlugin().getSqlHandler().getAccount(p);
				accountTable.setCredits(accountTable.getCredits() - price);
				MinersFortune.getPlugin().getSqlHandler().saveAccount(accountTable);
				item.addUnsafeEnchantment(enchant, level);
				p.updateInventory();
				return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Item enchanted for $" + price;
			}
			return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Unable to enchant";
		}

		return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "Money plugin failed somewhere..";
		}else{
			return ChatColor.GREEN + "Purchase >> " + ChatColor.YELLOW + "You can not purchase signs out of game!";
		}
	}

	private boolean canFit(Player p, ItemStack[] items) {
		Inventory inv = Bukkit.createInventory(null, p.getInventory().getContents().length);
		for (int i = 0; i < inv.getSize(); i++) {
			if ((p.getInventory().getItem(i) != null) && (p.getInventory().getItem(i).getType() != Material.AIR))
				inv.setItem(i, p.getInventory().getItem(i).clone());
		}
		for (ItemStack i : items) {
			HashMap<Integer, ItemStack> item = inv.addItem(new ItemStack[] { i });
			if ((item != null) && (!item.isEmpty())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean canAfford(Player player, int amount){
		AccountTable accountTable = MinersFortune.getPlugin().getSqlHandler().getAccount(player);
		if(accountTable.getCredits() >= amount){
			return true;
		}
		
		return false;
	}

}
