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

public class ArmorMenu extends IMenu {
	public ArmorMenu() {
		super(createItem(Material.DIAMOND_BARDING, "", new String[] { "" }), "Trail Menu", 36, "Changed Color to {ITEM}!");
		this.giveDefaults = false;

		iMenuItems.add(new IMenuItem(0, 10, new DyeStack(ChatColor.RED + "None", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with no?", " armor? Then this is the", "armor for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setArmor(null);
				IMenus.armorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Material armor = IPlayerHandler.findIPlayer(player).getMountPerks().getArmor();
				if (armor == null) {
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
		
		iMenuItems.add(new IMenuItem(0, 12, new DyeStack(ChatColor.RED + "Iron", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with iron?", " armor? Then this is the", "armor for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setArmor(Material.IRON_BARDING);
				IMenus.armorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Material armor = IPlayerHandler.findIPlayer(player).getMountPerks().getArmor();
				if (armor == Material.IRON_BARDING) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Iron");
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
		
		iMenuItems.add(new IMenuItem(0, 14, new DyeStack(ChatColor.RED + "Gold", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with gold?", " armor? Then this is the", "armor for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setArmor(Material.GOLD_BARDING);
				IMenus.armorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Material armor = IPlayerHandler.findIPlayer(player).getMountPerks().getArmor();
				if (armor == Material.GOLD_BARDING) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Gold");
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
		
		iMenuItems.add(new IMenuItem(0, 16, new DyeStack(ChatColor.RED + "Diamond", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a horse with diamond?", " armor? Then this is the", "armor for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setArmor(Material.DIAMOND_BARDING);
				IMenus.armorMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Material armor = IPlayerHandler.findIPlayer(player).getMountPerks().getArmor();
				if (armor == Material.DIAMOND_BARDING) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Diamond");
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

		iMenuItems.add(new IMenuItem(0, 22, new IItemStack(ChatColor.RED + "Back to mount menu ➜", Material.BED, new ArrayList<String>(Arrays.asList("Click to go back to", "the mount menu!"))), false) {
			@Override
			protected void action(Player player) {
				IMenus.mountMenu.openMenu(player, 0);
			}
		});
	}
}
