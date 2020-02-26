package me.avery246813579.minersfortune.menus;

import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.core.players.IPlayer;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;

public class MenuHandler {
	/** Classes **/
	MinersFortune plugin;

	public static MorphMenu MorphMenu = new MorphMenu();
	public static CostumeMenu CostumeMenu = new CostumeMenu();
	public static GadgetMenu GadgetMenu = new GadgetMenu();
	public static MusicMenu MusicMenu = new MusicMenu();
	public static PetMenu PetMenu = new PetMenu();
	public static FireworkMenu FireworkMenu = new FireworkMenu();
	public static MountMenu MountMenu = new MountMenu();
	public static VanityMenu vanityMenu = new VanityMenu();
	public static PlayerMenu playerMenu = new PlayerMenu();
	public static SpectatorMenu spectatorMenu = new SpectatorMenu();
	public static TeamMenu teamMenu = new TeamMenu();
	public static KitMenu kitMenu = new KitMenu();
	public static BuyKitMenu buyKitMenu = new BuyKitMenu();
	public static LobbyMenu lobbyMenu = new LobbyMenu();
	public static StatsMenu statsMenu = new StatsMenu();

	/*
	 * Types: - Morphs - Mounts - Gadgets - Firework - Pets
	 * 
	 * - Trail - Music - Wardrobe
	 * 
	 * ######### #+#+#+#+# ##+#+#+## ######### ###+#+###
	 */

	/** Menus **/

	/** Variables **/
	private static boolean useVanity = true;

	public MenuHandler(MinersFortune plugin) {
		this.plugin = plugin;
	}

	public static void resetPlayer(Player player) {
		if (me.avery246813579.minersfortune.menus.MountMenu.getMounts().containsKey(player)) {
			me.avery246813579.minersfortune.menus.MountMenu.getMounts().get(player).remove();
			me.avery246813579.minersfortune.menus.MountMenu.getMounts().remove(player);
		}

		if (me.avery246813579.minersfortune.menus.PetMenu.getPets().containsKey(player)) {
			me.avery246813579.minersfortune.menus.PetMenu.getPets().get(player).remove();
			me.avery246813579.minersfortune.menus.PetMenu.getPets().remove(player);
		}

		IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
		if (iPlayer.getiParticle() != null) {
			iPlayer.getiParticle().stopEffect();
		}
	}

	public static boolean isUseVanity() {
		return useVanity;
	}

	public static void setUseVanity(boolean useVanity) {
		MenuHandler.useVanity = useVanity;
	}
}
