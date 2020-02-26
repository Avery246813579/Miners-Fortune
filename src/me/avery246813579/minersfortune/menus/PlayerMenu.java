package me.avery246813579.minersfortune.menus;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.minersfortune.gamemode.GameManager;
import me.avery246813579.minersfortune.gamemode.GamePlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerMenu extends Menu{
	public PlayerMenu() {
		super(createItem(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), ChatColor.YELLOW + "Player Menu", new String[]{ChatColor.GRAY + "Click to access"}), "Player Menu");
		this.giveDefaultItems = false;
	}

	@Override
	protected Inventory[] createInventories() {
		Inventory firstInventory = Bukkit.createInventory(null, 54, "Player Menu");
		Inventory secondInventory = Bukkit.createInventory(null, 54, "Player Menu");
		
		List<Player> alivePlayers = new ArrayList<Player>();
		
		for(Player player : Bukkit.getOnlinePlayers()){
			if(GameManager.findPlayer(player).isAlive()){
				alivePlayers.add(player);
			}
		}
		
		int i = 10;
		Inventory currentUsing = firstInventory;
		for(Player player : alivePlayers){
			if(i == 17 || i == 26){
				i = i + 2;
			}
			
			if(i == 35){
				currentUsing = secondInventory;
				i = 10;
			}
			
			currentUsing.setItem(i, createItem(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), ChatColor.YELLOW + player.getName(), new String[]{ChatColor.GREEN + "Team: " + ChatColor.YELLOW + GameManager.findTeam(player).getName(), ChatColor.GREEN + "Kills: " + ChatColor.YELLOW + GameManager.findPlayer(player).getKills(), ChatColor.GREEN + "Kit: " + ChatColor.YELLOW + GameManager.findPlayer(player).getKit().getDisplayName()}));
			i++;
		}
		
		if(secondInventory == null){
			return new Inventory[]{firstInventory};
		}
		
		return new Inventory[]{firstInventory, secondInventory};
	}

	@Override
	protected void checkItem(Player player, ItemStack item) {
		Player selected = Bukkit.getPlayer(ChatColor.stripColor(item.getItemMeta().getDisplayName()));
		
		if(selected != null){
			GamePlayer gamePlayer = GameManager.findPlayer(selected);
			
			if(gamePlayer.isAlive()){
				player.teleport(selected.getLocation());
				sendSpecMessage(player, ChatColor.YELLOW + "You have teleported to " + selected.getName());
			}else{
				sendSpecMessage(player, ChatColor.RED + "Player is not alive!");
			}
		}else{
			sendSpecMessage(player, ChatColor.RED + "Player not found!");
		}
	}
	
	public static void sendSpecMessage(Player player, String message){
		player.sendMessage(ChatColor.GREEN + "Spec >> " + ChatColor.GRAY + message);
	}
	
	
	@Override
	protected Inventory[] createInventories(Player player) {
		return null;
	}
}
