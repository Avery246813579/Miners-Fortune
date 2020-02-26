package me.avery246813579.minersfortune.menus;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GameTeam;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TeamMenu extends Menu {
	public TeamMenu() {
		super(createItem(Material.WOOL, ChatColor.YELLOW + "Team Menu", new String[] { "Click to access" }), "Team Menu");
		this.giveDefaultItems = false;
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory inventory = Bukkit.createInventory(null, 36, "Team Menu");
		if (GameManager.getTeams().size() == 1) {
			inventory.setItem(13, createItem(new ItemStack(Material.STAINED_CLAY, 1, (byte) GameManager.getTeams().get(0).getTeamColor()), GameManager.getTeams().get(0).getColor() + GameManager.getTeams().get(0).getName(), new String[] {}));
		}

		if (GameManager.getTeams().size() == 2) {
			int teamNumber = 0;

			for (GameTeam gameTeam : GameManager.getTeams()) {
				if (teamNumber == 0) {
					inventory.setItem(11, createItem(new ItemStack(Material.STAINED_CLAY, 1, gameTeam.getTeamColor()), gameTeam.getColor() + gameTeam.getName(), new String[] {}));
					teamNumber++;
				} else {
					inventory.setItem(15, createItem(new ItemStack(Material.STAINED_CLAY, 1, gameTeam.getTeamColor()), gameTeam.getColor() + gameTeam.getName(), new String[] {}));
				}
			}
		}

		inventory.setItem(22, createItem(Material.WOOD_DOOR, ChatColor.YELLOW + "Back to Menu", new String[] { ChatColor.GRAY + "Return to lobby menu" }));

		return new Inventory[] { inventory };
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void checkItem(Player player, ItemStack item) {
		if (item.getType() == Material.WOOD_DOOR) {
			player.closeInventory();
			MenuHandler.lobbyMenu.openMenu(player, 0);
		}

		if (item.getType() == Material.STAINED_CLAY) {
			for (GameTeam gameTeam : GameManager.getTeams()) {
				if (item.getData().getData() == gameTeam.getTeamColor()) {
					player.closeInventory();
					gameTeam.addQueue(player);
				}
			}
		}
	}

	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
