package me.avery246813579.minersfortune.core.perk;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.core.players.IPlayer;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;
import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;

import org.bukkit.entity.Player;

public class IPerkHandler {
	public static List<IPerk> IPerks = new ArrayList<IPerk>();

	/**
	 * Id Types
	 * 
	 * Particle Effects: 0-49
	 * Mounts: 50-99
	 * 
	 * 
	 * 
	 */

	public static IPerk getPerk(int id) {
		for (IPerk iPerk : IPerks) {
			if (iPerk.getId() == id) {
				return iPerk;
			}
		}

		return null;
	}

	public static boolean hasPerk(Player player, int id) {
		IPerk iPerk = getPerk(id);

		if (iPerk != null) {
			if (iPerk.getFreeFor() != null) {
				if (iPerk.getFreeFor().getDonationNumber() <= RankManager.findPlayerRank(player).getDonationNumber()) {
					return true;
				}
			}

			if (RankManager.findPlayerRank(player) == Ranks.OWNER) {
				return true;
			}

			IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
			if(iPlayer.getIPerks().contains(iPerk)){
				return true;
			}
		}

		return false;
	}
}
