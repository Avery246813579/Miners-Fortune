package me.avery246813579.minersfortune.commands;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.core.particles.types.AngrySwirlEffect;
import me.avery246813579.minersfortune.core.particles.types.BadDayEffect;
import me.avery246813579.minersfortune.core.particles.types.FlameWalkEffect;
import me.avery246813579.minersfortune.core.particles.types.GodEffect;
import me.avery246813579.minersfortune.core.particles.types.HappySwirlEffect;
import me.avery246813579.minersfortune.core.particles.types.HeartSwirlEffect;
import me.avery246813579.minersfortune.core.particles.types.LavaCloudEffect;
import me.avery246813579.minersfortune.core.particles.types.MagicBoxEffect;
import me.avery246813579.minersfortune.core.particles.types.MagicSwirlEffect;
import me.avery246813579.minersfortune.core.particles.types.NoteSwirlEffect;
import me.avery246813579.minersfortune.core.particles.types.PortalBoxEffect;
import me.avery246813579.minersfortune.core.particles.types.RainCloudEffect;
import me.avery246813579.minersfortune.core.particles.types.ReddustSwirlEffect;
import me.avery246813579.minersfortune.core.particles.types.RiftWalkEffect;
import me.avery246813579.minersfortune.core.particles.types.SlimeWalkEffect;
import me.avery246813579.minersfortune.core.particles.types.SnowWalkEffect;
import me.avery246813579.minersfortune.core.particles.types.SpellBoxEffect;
import me.avery246813579.minersfortune.core.particles.types.SpellSwirlEffect;
import me.avery246813579.minersfortune.core.particles.types.SpellWalkEffect;
import me.avery246813579.minersfortune.core.particles.types.TownBoxEffect;
import me.avery246813579.minersfortune.core.particles.types.UnderworldEffect;
import me.avery246813579.minersfortune.core.players.IPlayer;
import me.avery246813579.minersfortune.core.players.IPlayerHandler;
import me.avery246813579.minersfortune.ranks.Ranks;

public class EffectCommand extends MinerCommand{
	public EffectCommand() {
		super("effect", Ranks.ADMIN);
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length == 0){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Effect (Name/Stop)");
		}else{
			Player player = (Player) sender;
			IPlayer iPlayer = IPlayerHandler.findIPlayer(player);
			
			if(args[0].equalsIgnoreCase("stop")){
				if(iPlayer.getiParticle() != null){
					sendEffect(sender, "Effect has been stopped!");
					iPlayer.getiParticle().stopEffect();
					iPlayer.setiParticle(null);
				}else{
					sendEffect(sender, "No effect to be stopped!");
				}
			}else if(args[0].equalsIgnoreCase("LavaCloud")){
				sendEffect(sender, "Lava cloud effect has been started!");
	
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new LavaCloudEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("WaterCloud")){
				sendEffect(sender, "Water cloud effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new RainCloudEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("HeartSwirl")){
				sendEffect(sender, "Heart Swirl effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new HeartSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("AngrySwirl")){
				sendEffect(sender, "Angry Swirl effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new AngrySwirlEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("HappySwirl")){
				sendEffect(sender, "Happy Swirl effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new HappySwirlEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("MagicBox")){
				sendEffect(sender, "Magic Box effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new MagicBoxEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("SpellBox")){
				sendEffect(sender, "Spell Box effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new SpellBoxEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("RiftWalk")){
				sendEffect(sender, "Rift Walk effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new RiftWalkEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("SnowWalk")){
				sendEffect(sender, "Snow Walk effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new SnowWalkEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("MagicSwirl")){
				sendEffect(sender, "Magic Swirl effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new MagicSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("SpellSwirl")){
				sendEffect(sender, "Spell Swirl effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new SpellSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("SpellWalk")){
				sendEffect(sender, "Spell Walk effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new SpellWalkEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("PortalBox")){
				sendEffect(sender, "Portal Box effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new PortalBoxEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("Underworld")){
				sendEffect(sender, "Underworld effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new UnderworldEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("Badday")){
				sendEffect(sender, "Bad day effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new BadDayEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("RedSwirl")){
				sendEffect(sender, "Red Swirl effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new ReddustSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("NoteSwirl")){
				sendEffect(sender, "Note Swirl effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new NoteSwirlEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("FlameWalk")){
				sendEffect(sender, "Flame Walk effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new FlameWalkEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("SlimeWalk")){
				sendEffect(sender, "Slime Walk effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new SlimeWalkEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("God")){
				sendEffect(sender, "God effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new GodEffect(player));
				iPlayer.getiParticle().startEffect();
			}else if(args[0].equalsIgnoreCase("TownBox")){
				sendEffect(sender, "Town effect has been started!");
				
				if(iPlayer.getiParticle() != null){
					iPlayer.getiParticle().stopEffect();
				}
				
				iPlayer.setiParticle(new TownBoxEffect(player));
				iPlayer.getiParticle().startEffect();
			}else{
				sendCommandMessage(sender, ChatColor.RED + "Effect not found!");
			}
		}
	}
	
	public static void sendEffect(CommandSender sender, String message){
		sender.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Effects >> " + ChatColor.YELLOW + message);
	}}
