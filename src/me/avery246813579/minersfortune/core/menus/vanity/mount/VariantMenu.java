package me.avery246813579.minersfortune.core.menus.vanity.mount;

import java.util.ArrayList;
import java.util.Arrays;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Variant;
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

public class VariantMenu extends IMenu {
	public VariantMenu() {
		super(createItem(Material.DIAMOND_BARDING, "", new String[] { "" }), "Mount Variant", 45, "Changed Varient to {ITEM}!");
		this.giveDefaults = false;

		iMenuItems.add(new IMenuItem(0, 11, new DyeStack(ChatColor.RED + "Horse", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want just a regular horse?", "Then this is the Varient for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setVariant(Variant.HORSE);
				IMenus.variantMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant == Variant.HORSE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Horse");
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

		iMenuItems.add(new IMenuItem(0, 13, new DyeStack(ChatColor.RED + "Mule", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want a silly looking mule?", "Then this is the Varient for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setVariant(Variant.MULE);
				IMenus.variantMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant == Variant.MULE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Mule");
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

		iMenuItems.add(new IMenuItem(0, 15, new DyeStack(ChatColor.RED + "Donkey", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want to ride Shrek's best friend?", "Then this is the Varient for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setVariant(Variant.DONKEY);
				IMenus.variantMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant == Variant.DONKEY) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Donkey");
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

		iMenuItems.add(new IMenuItem(0, 19, new DyeStack(ChatColor.RED + "Undead", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want to ride a dead horse?", "Then this is the Varient for", "you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setVariant(Variant.UNDEAD_HORSE);
				IMenus.variantMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant == Variant.UNDEAD_HORSE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Undead");
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

		iMenuItems.add(new IMenuItem(0, 25, new DyeStack(ChatColor.RED + "Skeleton", DyeColor.MAGENTA, new ArrayList<String>(Arrays.asList("Want to ride a spookey scary", "skeleton horse? Then this is the", "Variant for you!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
				iPlayer.getMountPerks().setVariant(Variant.SKELETON_HORSE);
				IMenus.variantMenu.openMenu(player, 0);
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant == Variant.SKELETON_HORSE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.GREEN + "Skeleton");
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
