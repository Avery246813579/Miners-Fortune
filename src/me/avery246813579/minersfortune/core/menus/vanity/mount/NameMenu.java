package me.avery246813579.minersfortune.core.menus.vanity.mount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.avery246813579.minersfortune.core.CoreLoader;
import me.avery246813579.minersfortune.core.itemstack.IItemStack;
import me.avery246813579.minersfortune.core.itemstack.types.DyeStack;
import me.avery246813579.minersfortune.core.menus.IMenu;
import me.avery246813579.minersfortune.core.menus.IMenuItem;
import me.avery246813579.minersfortune.core.menus.IMenus;
import me.avery246813579.minersfortune.core.menus.TextInputHandler;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;
import me.avery246813579.minersfortune.core.players.perks.IMountPerks;

public class NameMenu extends IMenu {
	public NameMenu() {
		super(createItem(Material.DIAMOND_BARDING, "", new String[] { "" }), "Mount Name", 36, "Changed Color to {ITEM}!");
		this.giveDefaults = false;

		iMenuItems.add(new IMenuItem(0, 11, new DyeStack(ChatColor.RED + "Mount Current Name:", DyeColor.LIME), false) {
			@Override
			protected void action(Player player) {
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				IMountPerks iMountPerks = IPlayerHandler.findIPlayer(player).getMountPerks();

				ItemMeta itemMeta = itemStack.getItemMeta();
				List<String> lore = new ArrayList<String>();
				lore.add(ChatColor.translateAlternateColorCodes('&', iMountPerks.getName()));
				itemMeta.setLore(lore);
				itemStack.setItemMeta(itemMeta);

				return itemStack;
			}
		});

		iMenuItems.add(new IMenuItem(0, 15, new DyeStack(ChatColor.RED + "Change Mount Name:", DyeColor.LIME, new ArrayList<String>(Arrays.asList("Click to change your mount's name!"))), false) {
			@Override
			protected void action(final Player player) {
				player.closeInventory();
				
				
		        CoreLoader.textInputHelper.requestInput(player, new TextInputHandler()
		        {
		          public void textEntered(String text) {
						IMountPerks iMountPerks = IPlayerHandler.findIPlayer(player).getMountPerks();
						iMountPerks.setName(text);
						IMenus.nameMenu.openMenu(player, 0);
		          }

		          public void cancelled() {
		          }
		        });
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
