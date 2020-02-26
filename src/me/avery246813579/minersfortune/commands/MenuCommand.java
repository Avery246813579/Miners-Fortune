package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.core.menus.IMenus;
import me.avery246813579.minersfortune.ranks.Ranks;

public class MenuCommand extends MinerCommand{
	public MenuCommand() {
		super("menu", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Menu (Menu Name)");
		}else{
			Player player = (Player) sender;
			
			if(args[0].equalsIgnoreCase("Trail")){
				IMenus.trailMenu.openMenu(player, 0);
				sendCommandMessage(sender, ChatColor.YELLOW + "Trail Menu has been opened!");
			}else if(args[0].equalsIgnoreCase("Mount")){
				IMenus.mountMenu.openMenu(player, 0);
				sendCommandMessage(sender, ChatColor.YELLOW + "Trail Menu has been opened!");
			}else{
				sendCommandMessage(sender, ChatColor.RED + "Menu not found!");
			}
		}
	}
}
