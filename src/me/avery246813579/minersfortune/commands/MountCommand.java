package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.core.entities.vanity.IVanityEntity;
import me.avery246813579.minersfortune.core.entities.vanity.mounts.SkeletonHorseMount;
import me.avery246813579.minersfortune.core.players.IPlayer;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.util.MessageUtil;

public class MountCommand extends MinerCommand{
	public MountCommand() {
		super("mount", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Mount (Type) (Name)");
		}else{
			String name = null;
			
			if(args.length >= 2){
				name = args[1];
			}
			
			IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
			if(args[0].equalsIgnoreCase("skeleton")){
				IVanityEntity iVanityEntity;
				
				if(name != null){
					iVanityEntity = new SkeletonHorseMount(player, name);
				}else{
					iVanityEntity = new SkeletonHorseMount(player);
				}
			
				iPlayer.setMount(iVanityEntity);
				iVanityEntity.getIEntity().spawn(player.getLocation());
				MessageUtil.sendMessage("Mount", player, "You have spawned in a Skeleton Horse Mount!");
			}
		}
	}
}
