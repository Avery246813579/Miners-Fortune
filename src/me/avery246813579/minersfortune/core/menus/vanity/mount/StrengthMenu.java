package me.avery246813579.minersfortune.core.menus.vanity.mount;

import java.util.ArrayList;
import java.util.Arrays;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import me.avery246813579.minersfortune.core.itemstack.IItemStack;
import me.avery246813579.minersfortune.core.itemstack.types.DyeStack;
import me.avery246813579.minersfortune.core.menus.IMenu;
import me.avery246813579.minersfortune.core.menus.IMenuItem;
import me.avery246813579.minersfortune.core.menus.IMenus;
import me.avery246813579.minersfortune.core.players.IPlayer;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;

public class StrengthMenu extends IMenu {
	public StrengthMenu() {
		super(createItem(Material.DIAMOND_BARDING, "", new String[] { "" }), "Mount Strength", 45, "Changed Color to {ITEM}!");
		this.giveDefaults = false;

		iMenuItems.add(new IMenuItem(0, 11, new DyeStack(ChatColor.RED + "Low", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with low", "jump strength? Then this", "is the strength for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setJumpHeight(0.4D);
				IMenus.strengthMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				double jump = IPlayerHandler.findIPlayer(player).getMountPerks().getJumpHeight();
				if (jump == 0.4D) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Low");
					int amount = itemStack.getAmount();

					Dye dye = new Dye();
					dye.setColor(DyeColor.LIME);
					itemStack = dye.toItemStack();
					itemStack.setAmount(amount);

					itemStack.setItemMeta(itemMeta);
				}

				return itemStack;
			}
		});
		
		iMenuItems.add(new IMenuItem(0, 13, new DyeStack(ChatColor.RED + "Medium Low", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with medium low", "jump strength? Then this", "is the strength for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setJumpHeight(0.8D);
				IMenus.strengthMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				double jump = IPlayerHandler.findIPlayer(player).getMountPerks().getJumpHeight();
				if (jump == 0.8D) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Medium Low");
					int amount = itemStack.getAmount();

					Dye dye = new Dye();
					dye.setColor(DyeColor.LIME);
					itemStack = dye.toItemStack();
					itemStack.setAmount(amount);

					itemStack.setItemMeta(itemMeta);
				}

				return itemStack;
			}
		});
		
		iMenuItems.add(new IMenuItem(0, 15, new DyeStack(ChatColor.RED + "Medium", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with medium", "jump strength? Then this", "is the strength for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setJumpHeight(1.2D);
				IMenus.strengthMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				double jump = IPlayerHandler.findIPlayer(player).getMountPerks().getJumpHeight();
				if (jump == 1.2D) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Medium");
					int amount = itemStack.getAmount();

					Dye dye = new Dye();
					dye.setColor(DyeColor.LIME);
					itemStack = dye.toItemStack();
					itemStack.setAmount(amount);

					itemStack.setItemMeta(itemMeta);
				}

				return itemStack;
			}
		});
		
		iMenuItems.add(new IMenuItem(0, 19, new DyeStack(ChatColor.RED + "Medium High", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with medium high", "jump strength? Then this", "is the strength for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setJumpHeight(1.6D);
				IMenus.strengthMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				double jump = IPlayerHandler.findIPlayer(player).getMountPerks().getJumpHeight();
				if (jump == 1.6D) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Medium High");
					int amount = itemStack.getAmount();

					Dye dye = new Dye();
					dye.setColor(DyeColor.LIME);
					itemStack = dye.toItemStack();
					itemStack.setAmount(amount);

					itemStack.setItemMeta(itemMeta);
				}

				return itemStack;
			}
		});

		iMenuItems.add(new IMenuItem(0, 25, new DyeStack(ChatColor.RED + "High", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with high", "jump strength? Then this", "is the strength for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setJumpHeight(2D);
				IMenus.strengthMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				double jump = IPlayerHandler.findIPlayer(player).getMountPerks().getJumpHeight();
				if (jump == 2D) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "High");
					int amount = itemStack.getAmount();

					Dye dye = new Dye();
					dye.setColor(DyeColor.LIME);
					itemStack = dye.toItemStack();
					itemStack.setAmount(amount);

					itemStack.setItemMeta(itemMeta);
				}

				return itemStack;
			}
		});
		
		iMenuItems.add(new IMenuItem(0, 31, new IItemStack(ChatColor.RED + "Back to mount menu ➜", Material.BED, new ArrayList<String>(Arrays.asList("Click to go back to", "the mount menu!"))), false) {
			@Override
			protected void action(Player player) {
				IMenus.mountMenu.openMenu(player, 0);
			}
		});
	}
}
