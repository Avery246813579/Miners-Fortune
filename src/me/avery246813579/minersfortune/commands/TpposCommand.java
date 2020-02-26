package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.Ranks;

public class TpposCommand extends MinerCommand{
	public TpposCommand() {
		super("tppos", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length<= 2){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Tppos (X) (Y) (Z)");
		}else{
			try{
				Player player = (Player) sender;
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				int z = Integer.parseInt(args[2]);
				
				player.teleport(new Location(player.getWorld(), x, y, z));
				sendTeleportMessage(player, "Teleported to " + x + "," + y + "," + z + " in world " + player.getWorld().getName() + "!");
			}catch(Exception ex){ 
				sendCommandMessage(sender, ChatColor.RED + "Could not parse numbers!");
			}
		}
	}
}
