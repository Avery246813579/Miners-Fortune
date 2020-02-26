package me.avery246813579.minersfortune.core.menus.vanity.mount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import me.avery246813579.minersfortune.core.entities.vanity.IVanityEntity;
import me.avery246813579.minersfortune.core.entities.vanity.types.IHorse;
import me.avery246813579.minersfortune.core.itemstack.types.DyeStack;
import me.avery246813579.minersfortune.core.itemstack.types.EggStack;
import me.avery246813579.minersfortune.core.menus.IMenu;
import me.avery246813579.minersfortune.core.menus.IMenuItem;
import me.avery246813579.minersfortune.core.menus.IMenus;
import me.avery246813579.minersfortune.core.players.IPlayer;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;
import me.avery246813579.minersfortune.core.players.perks.IMountPerks;
import me.avery246813579.minersfortune.util.MessageUtil;

public class MountMenu extends IMenu {
	public MountMenu() {
		super(createItem(Material.DIAMOND_BARDING, ChatColor.YELLOW + "Mounts", new String[] { "Customize and summon a horse", "that you can ride into", "the sunset!" }), "Trail Menu", 45, "Activated {ITEM} trail!");

		iMenuItems.add(new IMenuItem(0, 10, new DyeStack(ChatColor.GREEN + "Variant", DyeColor.LIME, new ArrayList<String>(Arrays.asList("Change the type of horse!", "Want a skeleton or undead", "horse? This menu is where", "you do that!"))), false) {
			@Override
			protected void action(Player player) {
				IMenus.variantMenu.openMenu(player, 0);
			}
		});

		iMenuItems.add(new IMenuItem(0, 12, new DyeStack(ChatColor.GREEN + "Color", DyeColor.LIME, new ArrayList<String>(Arrays.asList("Change the color of your", "horse!"))), false) {
			@Override
			protected void action(Player player) {
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant == Variant.HORSE) {
					IMenus.colorMenu.openMenu(player, 0);
				}
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant != Variant.HORSE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.RED + "Color");
					int amount = itemStack.getAmount();

					Dye dye = new Dye();
					dye.setColor(DyeColor.MAGENTA);
					itemStack = dye.toItemStack();

					List<String> lore = itemMeta.getLore();
					lore.add("");
					lore.add(ChatColor.RED + "Menu only avaliable when Variant");
					lore.add(ChatColor.RED + "type is Horse!");
					itemMeta.setLore(lore);
					itemStack.setAmount(amount);

					itemStack.setItemMeta(itemMeta);
				}

				return itemStack;
			}
		});

		iMenuItems.add(new IMenuItem(0, 14, new DyeStack(ChatColor.GREEN + "Style", DyeColor.LIME, new ArrayList<String>(Arrays.asList("Change the style of your", "horse!"))), false) {
			@Override
			protected void action(Player player) {
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant == Variant.HORSE) {
					IMenus.styleMenu.openMenu(player, 0);
				}
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant != Variant.HORSE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.RED + "Style");
					int amount = itemStack.getAmount();

					Dye dye = new Dye();
					dye.setColor(DyeColor.MAGENTA);
					itemStack = dye.toItemStack();

					List<String> lore = itemMeta.getLore();
					lore.add("");
					lore.add(ChatColor.RED + "Menu only avaliable when Variant");
					lore.add(ChatColor.RED + "type is Horse!");
					itemMeta.setLore(lore);
					itemStack.setAmount(amount);

					itemStack.setItemMeta(itemMeta);
				}

				return itemStack;
			}
		});

		iMenuItems.add(new IMenuItem(0, 16, new DyeStack(ChatColor.GREEN + "Armor", DyeColor.LIME, new ArrayList<String>(Arrays.asList("Change the armor of your", "horse!"))), false) {
			@Override
			protected void action(Player player) {
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant == Variant.HORSE) {
					IMenus.armorMenu.openMenu(player, 0);
				}
			}

			@Override
			public ItemStack getItem(Player player) {
				ItemStack itemStack = super.getItem(player);
				Variant variant = IPlayerHandler.findIPlayer(player).getMountPerks().getVariant();
				if (variant != Variant.HORSE) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					itemMeta.setDisplayName(ChatColor.RED + "Armor");
					int amount = itemStack.getAmount();

					Dye dye = new Dye();
					dye.setColor(DyeColor.MAGENTA);
					itemStack = dye.toItemStack();

					List<String> lore = itemMeta.getLore();
					lore.add("");
					lore.add(ChatColor.RED + "Menu only avaliable when Variant");
					lore.add(ChatColor.RED + "type is Horse!");
					itemMeta.setLore(lore);
					itemStack.setAmount(amount);

					itemStack.setItemMeta(itemMeta);
				}

				return itemStack;
			}
		});

		iMenuItems.add(new IMenuItem(0, 20, new DyeStack(ChatColor.GREEN + "Strength", DyeColor.LIME, new ArrayList<String>(Arrays.asList("Change the strength of your", "horse! Want your horse to", "jump higher? This menu is where", "you do that!"))), false) {
			@Override
			protected void action(Player player) {
				IMenus.strengthMenu.openMenu(player, 0);
			}
		});

		iMenuItems.add(new IMenuItem(0, 22, new DyeStack(ChatColor.GREEN + "Name", DyeColor.LIME, new ArrayList<String>(Arrays.asList("Change the name of your", "horse!"))), false) {
			@Override
			protected void action(Player player) {
				IMenus.nameMenu.openMenu(player, 0);
			}
		});

		iMenuItems.add(new IMenuItem(0, 24, new DyeStack(ChatColor.GREEN + "Togglable", DyeColor.LIME, new ArrayList<String>(Arrays.asList("Change the toggable variables", "of your horse! Want to change the", " age or give your mule a chest?", "This menu is where you do that!"))), false) {
			@Override
			protected void action(Player player) {
			}
		});

		iMenuItems.add(new IMenuItem(0, 30, new EggStack(ChatColor.GREEN + "Spawn", EntityType.CREEPER, new ArrayList<String>(Arrays.asList("Spawn your mount!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getMount() == null) {
					IMountPerks perks = iPlayer.getMountPerks();
					IHorse horse = new IHorse(perks.getName(), perks.getVariant());

					horse.setBaby(perks.isBaby());
					horse.setStrength(perks.getJumpHeight());

					if (perks.getVariant() == Variant.HORSE) {
						horse.setStyle(perks.getStyle());
						horse.setColor(perks.getColor());
					}

					if (perks.getVariant() == Variant.DONKEY || perks.getVariant() == Variant.MULE) {
						horse.setChest(perks.isChest());
					}

					if (perks.getArmor() != null) {
						horse.setArmor(new ItemStack(perks.getArmor()));
					}

					horse.spawn(player.getLocation());
					iPlayer.setMount(new IVanityEntity(horse, player));
					MessageUtil.sendMessage("Mount", player, "You have spawned in a mount!");
				} else {
					MessageUtil.sendMessage("Mount", player, "Mount already spawned! Despawn your mount to spawn a new one!");
				}
			}
		});

		iMenuItems.add(new IMenuItem(0, 32, new EggStack(ChatColor.GREEN + "Despawn", EntityType.MUSHROOM_COW, new ArrayList<String>(Arrays.asList("Despawn your mount!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getMount() != null) {
					IVanityEntity entity = iPlayer.getMount();
					iPlayer.setMount(null);
					entity.getIEntity().despawn();
					MessageUtil.sendMessage("Mount", player, "Mount despawned!");
				} else {
					MessageUtil.sendMessage("Mount", player, "No mount to despawn!");
				}
			}
		});
	}
}
