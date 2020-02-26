package me.avery246813579.minersfortune.listeners;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatListener implements Listener{
	@EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
        String message = event.getMessage();
        if(message.startsWith("/plugins ") || message.startsWith("/pl ") || message.startsWith("/bukkit:pl ") || message.startsWith("/bukkit:plugins ")){
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.YELLOW + "Plugins (3): " + ChatColor.GREEN + "Mineflex's stolen plugin" + ChatColor.WHITE + ", " + ChatColor.GREEN + "Hypixis's stolen plugin" + ChatColor.WHITE + ", " + ChatColor.GREEN + "TheDrive's Stolen plugin");
        }
        
        if(message.startsWith("/op ") || message.startsWith("/bukkit:op ")){
        	event.setCancelled(true);
        	event.getPlayer().sendMessage(ChatColor.YELLOW + "You have been opp... Oh wait... What is that? Alright we will tell him. Yeah you're never getting op.");
        }
        
        if(message.startsWith("/deop ") || message.startsWith("/bukkit:deop ")){
        	event.setCancelled(true);
        	event.getPlayer().sendMessage(ChatColor.YELLOW + "Why would you need to use this command?! Please tell me, this is very important!");
        }
        
        if(message.startsWith("/version ") || message.startsWith("/bukkit:version ") || message.startsWith("/bukkit:ver ") || message.startsWith("/bukkit:version ")){
        	event.setCancelled(true);
        	event.getPlayer().sendMessage(ChatColor.WHITE + "This server is running on Kitten Milk version 1.0.1 (MC: 1.0.0-9.9.9) (Implementing API version Rabbit Carrot)");
        }
        
        if(message.startsWith("/bukkit:ban ") || message.startsWith("/bukkit:ban-ip ") || message.startsWith("/ban-ip ")){
        	event.setCancelled(true);
        	event.getPlayer().sendMessage("");
        	event.getPlayer().sendMessage("");
        	event.getPlayer().sendMessage("");
        	event.getPlayer().sendMessage("");
        	event.getPlayer().sendMessage("");
        	event.getPlayer().sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
        	event.getPlayer().sendMessage(ChatColor.YELLOW + "To ban a player:");
        	event.getPlayer().sendMessage(ChatColor.GREEN + "Windows ➜ " + ChatColor.YELLOW + "Press and Hold Ctrl + F4");
        	event.getPlayer().sendMessage(ChatColor.GREEN + "Mac ➜ " + ChatColor.YELLOW + "Throw your computer out a window");
        	event.getPlayer().sendMessage(ChatColor.GREEN + "Linux ➜ " + ChatColor.YELLOW + "");
        	event.getPlayer().sendMessage(ChatColor.GREEN + "Other ➜ " + ChatColor.YELLOW + "What other operation systems are there? Please install one of the above.");
        	event.getPlayer().sendMessage(ChatColor.RED + "☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
        }
    }
}
