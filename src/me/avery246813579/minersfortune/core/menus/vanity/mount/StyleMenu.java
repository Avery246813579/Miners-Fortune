package me.avery246813579.minersfortune.core.menus.vanity.mount;

import java.util.ArrayList;
import java.util.Arrays;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Horse.Style;
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

public class StyleMenu extends IMenu {
	public StyleMenu() {
		super(createItem(Material.DIAMOND_BARDING, "", new String[] { "" }), "Mount Style", 45, "Changed Color to {ITEM}!");
		this.giveDefaults = false;

		iMenuItems.add(new IMenuItem(0, 11, new DyeStack(ChatColor.RED + "None", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with no style?", "Then this is the Style for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setStyle(Style.NONE);
				IMenus.styleMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Style style = IPlayerHandler.findIPlayer(player).getMountPerks().getStyle();
				if (style == Style.NONE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "None");
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
		
		iMenuItems.add(new IMenuItem(0, 13, new DyeStack(ChatColor.RED + "White", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with a white style?", "Then this is the Style for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setStyle(Style.WHITE);
				IMenus.styleMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Style style = IPlayerHandler.findIPlayer(player).getMountPerks().getStyle();
				if (style == Style.WHITE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "White");
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
		
		iMenuItems.add(new IMenuItem(0, 15, new DyeStack(ChatColor.RED + "Whitefield", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with a whitefield", "style? Then this is the Style", "for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setStyle(Style.WHITEFIELD);
				IMenus.styleMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Style style = IPlayerHandler.findIPlayer(player).getMountPerks().getStyle();
				if (style == Style.WHITEFIELD) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Whitefield");
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
		
		iMenuItems.add(new IMenuItem(0, 19, new DyeStack(ChatColor.RED + "White Dots", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with white dots?", "Then this is the Style for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setStyle(Style.WHITE_DOTS);
				IMenus.styleMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Style style = IPlayerHandler.findIPlayer(player).getMountPerks().getStyle();
				if (style == Style.WHITE_DOTS) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "White Dots");
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

		iMenuItems.add(new IMenuItem(0, 25, new DyeStack(ChatColor.RED + "Black Dots", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with black dots?", "Then this is the Style for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setStyle(Style.BLACK_DOTS);
				IMenus.styleMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Style style = IPlayerHandler.findIPlayer(player).getMountPerks().getStyle();
				if (style == Style.BLACK_DOTS) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Black Dots");
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
