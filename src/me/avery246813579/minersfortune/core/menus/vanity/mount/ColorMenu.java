package me.avery246813579.minersfortune.core.menus.vanity.mount;

import java.util.ArrayList;
import java.util.Arrays;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Horse.Color;
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

public class ColorMenu extends IMenu {
	public ColorMenu() {
		super(createItem(Material.DIAMOND_BARDING, "", new String[] { "" }), "Mount Color", 45, "Changed Color to {ITEM}!");
		this.giveDefaults = false;

		iMenuItems.add(new IMenuItem(0, 10, new DyeStack(ChatColor.RED + "White", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a white horse?", "Then this is the Color for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setColor(Color.WHITE);
				IMenus.colorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Color color = IPlayerHandler.findIPlayer(player).getMountPerks().getColor();
				if (color == Color.WHITE) {
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

		iMenuItems.add(new IMenuItem(0, 12, new DyeStack(ChatColor.RED + "Creamy", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a creamy colored horse?", "Then this is the Color for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setColor(Color.CREAMY);
				IMenus.colorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Color color = IPlayerHandler.findIPlayer(player).getMountPerks().getColor();
				if (color == Color.CREAMY) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Creamy");
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
		
		iMenuItems.add(new IMenuItem(0, 14, new DyeStack(ChatColor.RED + "Chestnut", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a chestnut colored horse?", "Then this is the Color for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setColor(Color.CHESTNUT);
				IMenus.colorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Color color = IPlayerHandler.findIPlayer(player).getMountPerks().getColor();
				if (color == Color.CHESTNUT) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Chestnut");
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
		
		iMenuItems.add(new IMenuItem(0, 16, new DyeStack(ChatColor.RED + "Brown", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a brown horse?", "Then this is the Color for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setColor(Color.BROWN);
				IMenus.colorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Color color = IPlayerHandler.findIPlayer(player).getMountPerks().getColor();
				if (color == Color.BROWN) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Brown");
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
		
		
		iMenuItems.add(new IMenuItem(0, 20, new DyeStack(ChatColor.RED + "Black", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a black horse?", "Then this is the Color for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setColor(Color.BLACK);
				IMenus.colorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Color color = IPlayerHandler.findIPlayer(player).getMountPerks().getColor();
				if (color == Color.BLACK) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Black");
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
		
		iMenuItems.add(new IMenuItem(0, 22, new DyeStack(ChatColor.RED + "Gray", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a gray horse?", "Then this is the Color for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setColor(Color.BLACK);
				IMenus.colorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Color color = IPlayerHandler.findIPlayer(player).getMountPerks().getColor();
				if (color == Color.GRAY) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Gray");
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
		
		iMenuItems.add(new IMenuItem(0, 24, new DyeStack(ChatColor.RED + "Dark Brown", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a dark brown horse?", "Then this is the Color for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setColor(Color.DARK_BROWN);
				IMenus.colorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Color color = IPlayerHandler.findIPlayer(player).getMountPerks().getColor();
				if (color == Color.DARK_BROWN) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Dark Brown");
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
