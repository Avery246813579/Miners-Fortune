package me.avery246813579.minersfortune.core.menus.vanity;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.core.itemstack.IItemStack;
import me.avery246813579.minersfortune.core.menus.IMenu;
import me.avery246813579.minersfortune.core.menus.IMenuItem;
import me.avery246813579.minersfortune.core.particles.types.*;
import me.avery246813579.minersfortune.core.perk.IPerk;
import me.avery246813579.minersfortune.core.players.IPlayer;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;
import me.avery246813579.minersfortune.ranks.Ranks;

public class TrailMenu extends IMenu {
	public TrailMenu() {
		super(createItem(Material.MELON_SEEDS, ChatColor.YELLOW + "Trails", new String[] { "Want to have some particles", "follow you around? If yes, then", "trails are the right thing", "for you!" }), "Trail Menu", 54, "Activated {ITEM} trail!");

		iMenuItems.add(new IMenuItem(new IPerk(0, "", 100), 0, 10, new IItemStack("Rain Cloud", Material.WATER_BUCKET, new ArrayList<String>(Arrays.asList("Summon up a rain cloud", "that follows you around", "to rain on your parade!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new RainCloudEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		iMenuItems.add(new IMenuItem(new IPerk(1, "", 100), 0, 11, new IItemStack("Lava Cloud", Material.LAVA_BUCKET, new ArrayList<String>(Arrays.asList("Summon up a lava cloud", "that follows you around", "to burn down your parade!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new LavaCloudEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		iMenuItems.add(new IMenuItem(new IPerk(2, "", 100), 0, 12, new IItemStack("Love Hearts", Material.RED_ROSE, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new HeartSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Description Item Name
		 */
		iMenuItems.add(new IMenuItem(new IPerk(3, "", 100), 0, 13, new IItemStack("Magic Swirl", Material.POTION, new ArrayList<String>(Arrays.asList("NEED DESCRIPTION")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new MagicSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/**
		 * Description Name
		 */
		iMenuItems.add(new IMenuItem(new IPerk(4, "", 100), 0, 14, new IItemStack("Red Swirl", Material.REDSTONE, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new ReddustSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(5, "", 100), 0, 15, new IItemStack("Music Lover", Material.NOTE_BLOCK, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new NoteSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Name Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(6, "", 100), 0, 16, new IItemStack("Magic Box", Material.ENCHANTMENT_TABLE, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new MagicBoxEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Name Desciption
		 */
		iMenuItems.add(new IMenuItem(new IPerk(7, "", 100), 0, 19, new IItemStack("Spell Box", Material.BEDROCK, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new SpellBoxEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Name Desciption
		 */
		iMenuItems.add(new IMenuItem(new IPerk(8, "", 100), 0, 20, new IItemStack("Witch Box", Material.CAULDRON_ITEM, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new SpellSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Name Desciption
		 */
		iMenuItems.add(new IMenuItem(new IPerk(9, "", 100), 0, 21, new IItemStack("Portal Box", Material.PORTAL, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new PortalBoxEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Kinda name Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(10, "", 100), 0, 22, new IItemStack("Mushroom Box", Material.RED_MUSHROOM, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new TownBoxEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Name Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(11, "", 100), 0, 23, new IItemStack("Angry Effect", Material.BLAZE_POWDER, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new AngrySwirlEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(12, "", 100), 0, 24, new IItemStack("Halo", Material.EMERALD, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new HappySwirlEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Nameish Desciption
		 */
		iMenuItems.add(new IMenuItem(new IPerk(13, "", 100), 0, 25, new IItemStack("Snow Walk", Material.SNOW, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new SnowWalkEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(14, "", 100), 0, 28, new IItemStack("Rift Walk", Material.FIREWORK_CHARGE, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new RiftWalkEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Name Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(15, "", 100), 0, 29, new IItemStack("Flame Walk", Material.FIRE, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new FlameWalkEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/**
		 * Description Name Item
		 */
		iMenuItems.add(new IMenuItem(new IPerk(16, "", 100), 0, 30, new IItemStack("Spell Walk", Material.POTION, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new SpellWalkEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(17, "", 100), 0, 31, new IItemStack("Slime Trail", Material.SLIME_BALL, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new SlimeWalkEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Item Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(18, "", Ranks.DIAMOND), 0, 32, new IItemStack("Bad Day", Material.EMERALD, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new BadDayEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Desciption
		 */
		iMenuItems.add(new IMenuItem(new IPerk(19, "", Ranks.EMERALD), 0, 33, new IItemStack("Underworld", Material.SOUL_SAND, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new UnderworldEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		/*
		 * Description
		 */
		iMenuItems.add(new IMenuItem(new IPerk(20, "", 100), 0, 34, new IItemStack("God", Material.GOLDEN_APPLE, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!")))) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
				}

				iPlayer.setiParticle(new GodEffect(player));
				iPlayer.getiParticle().startEffect();
				player.closeInventory();
			}
		});

		iMenuItems.add(new IMenuItem(new IPerk(49, "", Ranks.DEFAULT), 0, 39, new IItemStack(ChatColor.RED + "Stop Trail", Material.GLASS, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!"))), false) {
			@Override
			protected void action(Player player) {
			}
		});

		iMenuItems.add(new IMenuItem(new IPerk(50, "", Ranks.DEFAULT), 0, 41, new IItemStack(ChatColor.RED + "Stop Trail", Material.GLASS, new ArrayList<String>(Arrays.asList("Show your love to someone", "by being surrounded by", "swirling hearts!"))), false) {
			@Override
			protected void action(Player player) {
				IPlayer iPlayer = IPlayerHandler.findIPlayer(player);

				if (iPlayer.getiParticle() != null) {
					iPlayer.getiParticle().stopEffect();
					sendMessae(player, "Trail turned off!");
				} else {
					sendMessae(player, "No trail to turn off!");
				}
			}
		});
	}
}
