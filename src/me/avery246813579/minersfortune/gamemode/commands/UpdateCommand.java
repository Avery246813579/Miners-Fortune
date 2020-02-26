package me.avery246813579.minersfortune.gamemode.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.gamemode.GameState;
import me.avery246813579.minersfortune.gamemode.GameTeamTypes;
import me.avery246813579.minersfortune.gamemode.GameType;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.MapTable;
import me.avery246813579.minersfortune.sql.tables.TeamTable;

public class UpdateCommand extends GameCommand{
	List<Location> locations = new ArrayList<Location>();
	TeamTable teamTable;
	Player setting;
	
	public UpdateCommand() {
		super("update", Ranks.ADMIN, new ArrayList<GameState>(Arrays.asList(GameState.Limbow)));
	}

	@Override
	protected void runCommand(CommandSender sender, String[] args) {
		if(args.length < 1){
			sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Update (Team|Map) (Create|Place|Active|Midpoint|Set|Finish) (Team|Map) [Gamemode] [Map] [ActiveStatus]");
			return;
		}
		
		if(args[0].equalsIgnoreCase("team")){
			if(args[1].equalsIgnoreCase("create")){
				if(!(args.length == 5)){
					sendCommandMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Update (Team|Map) (Create|Place|Reset|Add|Set|Finish) (Team|Map) [Gamemode] [Map]");
					return;
				}
				
				GameType gameType = null;
				for(GameType type : GameType.values()){
					if(type.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(args[3])){
						gameType = type;
					}
				}
				
				if(gameType == null){
					sendCommandMessage(sender, ChatColor.RED + "Gametype could not be found!");
					return;
				}

				if(MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", "")) == null){
					sendCommandMessage(sender, ChatColor.RED + "This gamemode does not have any maps!");
					return;
				}
				
				MapTable mapTable = null;
				for(MapTable table : MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", ""))){
					if(table.getMapName().replaceAll(" ", "").equalsIgnoreCase(args[4])){
						mapTable = table;
					}
				}
				
				if(mapTable == null){
					sendCommandMessage(sender, ChatColor.RED + "Map Table could not be found!");
					return;
				}
				
				GameTeamTypes gameTeamTypes = null;
				for(GameTeamTypes teamTypes : GameTeamTypes.values()){
					if(teamTypes.toString().equalsIgnoreCase(args[2])){
						gameTeamTypes = teamTypes;
					}
				}
				
				if(gameTeamTypes == null){
					sendCommandMessage(sender, ChatColor.RED + "Team could not be found!");
					return;
				}
				
				if(!gameType.getTeams().contains(gameTeamTypes)){
					sendCommandMessage(sender, ChatColor.RED + "Team is not part of this gamemode!");
					return;
				}
				
				if(!mapTable.getGameType().getDisplayName().replaceAll(" ", "").equalsIgnoreCase(gameType.getDisplayName().replaceAll(" ", ""))){
					sendCommandMessage(sender, ChatColor.RED + "This gamemode is not associated with this map!");
					return;
				}
				
				if(MinersFortune.getPlugin().getSqlHandler().getTeam(mapTable.getMapId(), gameTeamTypes.toString()) != null){
					sendCommandMessage(sender, ChatColor.RED + "Team already exists in the database!");
					return;
				}
				
				MinersFortune.getPlugin().getSqlHandler().createTeam(mapTable.getMapId(), gameTeamTypes.toString(), gameTeamTypes.getChatColor().toString(), "NONE");
				sendCommandMessage(sender, ChatColor.YELLOW + gameTeamTypes.toString() + " team has been created for " + mapTable.getMapName() + "!");
			}else if(args[1].equalsIgnoreCase("set")){
				if(setting != null){
					sendCommandMessage(sender, ChatColor.RED + "Someone is already setting locations!");
					return;
				}

				GameType gameType = null;
				for(GameType type : GameType.values()){
					if(type.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(args[3])){
						gameType = type;
					}
				}
				
				if(gameType == null){
					sendCommandMessage(sender, ChatColor.RED + "Gametype could not be found!");
					return;
				}

				if(MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", "")) == null){
					sendCommandMessage(sender, ChatColor.RED + "This gamemode does not have any maps!");
					return;
				}
				
				MapTable mapTable = null;
				for(MapTable table : MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", ""))){
					if(table.getMapName().replaceAll(" ", "").equalsIgnoreCase(args[4])){
						mapTable = table;
					}
				}
				
				if(mapTable == null){
					sendCommandMessage(sender, ChatColor.RED + "Map Table could not be found!");
					return;
				}
				
				GameTeamTypes gameTeamTypes = null;
				for(GameTeamTypes teamTypes : GameTeamTypes.values()){
					if(teamTypes.toString().equalsIgnoreCase(args[2])){
						gameTeamTypes = teamTypes;
					}
				}
				
				if(gameTeamTypes == null){
					sendCommandMessage(sender, ChatColor.RED + "Team could not be found!");
					return;
				}
				
				if(!gameType.getTeams().contains(gameTeamTypes)){
					sendCommandMessage(sender, ChatColor.RED + "Team is not part of this gamemode!");
					return;
				}
				
				if(!mapTable.getGameType().getDisplayName().replaceAll(" ", "").equalsIgnoreCase(gameType.getDisplayName().replaceAll(" ", ""))){
					sendCommandMessage(sender, ChatColor.RED + "This gamemode is not associated with this map!");
					return;
				}
				
				if(MinersFortune.getPlugin().getSqlHandler().getTeam(mapTable.getMapId(), gameTeamTypes.toString()) == null){
					sendCommandMessage(sender, ChatColor.RED + "Team does not exist in the database!");
					return;
				}
				
				sendCommandMessage(sender, ChatColor.YELLOW + "You are now setting locations for this team!");
				setting = (Player) sender;
				locations.clear();
				teamTable = null;
				remain();

				teamTable = MinersFortune.getPlugin().getSqlHandler().getTeam(mapTable.getMapId(), gameTeamTypes.toString());
			}else if(args[1].equalsIgnoreCase("finish")){
				if(setting != (Player) sender){
					sendCommandMessage(sender, ChatColor.RED + "You are not setting anything at the moment!");
					return;
				}
				
				String stringLocation = "";
				for(Location location : locations){
					stringLocation = stringLocation + "," + location.getBlockX() + "_" + location.getBlockY() + "_" + location.getBlockZ() + "_" + (int)location.getYaw() + "_" + (int)location.getPitch();
				}
				
				stringLocation = stringLocation.substring(1, stringLocation.length());
				
				setting = null;
				teamTable.setSpawns(stringLocation);
				MinersFortune.getPlugin().getSqlHandler().saveTeam(teamTable);
				sendCommandMessage(sender, ChatColor.YELLOW + "You have updated the locations!");
			}else if(args[1].equalsIgnoreCase("place")){
				if(setting != (Player) sender){
					sendCommandMessage(sender, ChatColor.RED + "You are not setting anything at the moment!");
					return;
				}
				
				Location location = ((Player) sender).getLocation();
				location.setPitch(0);
				locations.add(location);
				sendCommandMessage(sender, ChatColor.YELLOW + "You have added the " + locations.size() + " location!");
			}else if(args[1].equalsIgnoreCase("midpoint")){
				GameType gameType = null;
				for(GameType type : GameType.values()){
					if(type.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(args[3])){
						gameType = type;
					}
				}
				
				if(gameType == null){
					sendCommandMessage(sender, ChatColor.RED + "Gametype could not be found!");
					return;
				}

				if(MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", "")) == null){
					sendCommandMessage(sender, ChatColor.RED + "This gamemode does not have any maps!");
					return;
				}
				
				MapTable mapTable = null;
				for(MapTable table : MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", ""))){
					if(table.getMapName().replaceAll(" ", "").equalsIgnoreCase(args[4])){
						mapTable = table;
					}
				}
				
				if(mapTable == null){
					sendCommandMessage(sender, ChatColor.RED + "Map Table could not be found!");
					return;
				}
				
				GameTeamTypes gameTeamTypes = null;
				for(GameTeamTypes teamTypes : GameTeamTypes.values()){
					if(teamTypes.toString().equalsIgnoreCase(args[2])){
						gameTeamTypes = teamTypes;
					}
				}
				
				if(gameTeamTypes == null){
					sendCommandMessage(sender, ChatColor.RED + "Team could not be found!");
					return;
				}
				
				if(!gameType.getTeams().contains(gameTeamTypes)){
					sendCommandMessage(sender, ChatColor.RED + "Team is not part of this gamemode!");
					return;
				}
				
				if(!mapTable.getGameType().getDisplayName().replaceAll(" ", "").equalsIgnoreCase(gameType.getDisplayName().replaceAll(" ", ""))){
					sendCommandMessage(sender, ChatColor.RED + "This gamemode is not associated with this map!");
					return;
				}
				
				if(MinersFortune.getPlugin().getSqlHandler().getTeam(mapTable.getMapId(), gameTeamTypes.toString()) == null){
					sendCommandMessage(sender, ChatColor.RED + "Team does not exist in the database!");
					return;
				}
				
				Player player = (Player) sender;
				Location location = player.getLocation();
				sendCommandMessage(sender, ChatColor.YELLOW + "You have updated this maps midpoint!");
				String stringLocation = location.getBlockX() + "_" + location.getBlockY() + "_" + location.getBlockZ() + "_" + (int)location.getYaw() + "_" + (int)location.getPitch();;
				mapTable.setMidPoint(stringLocation);
				MinersFortune.getPlugin().getSqlHandler().saveMap(mapTable);
			}else if(args[1].equalsIgnoreCase("active")){
				GameType gameType = null;
				for(GameType type : GameType.values()){
					if(type.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(args[3])){
						gameType = type;
					}
				}
				
				if(gameType == null){
					sendCommandMessage(sender, ChatColor.RED + "Gametype could not be found!");
					return;
				}

				if(MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", "")) == null){
					sendCommandMessage(sender, ChatColor.RED + "This gamemode does not have any maps!");
					return;
				}
				
				MapTable mapTable = null;
				for(MapTable table : MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", ""))){
					if(table.getMapName().replaceAll(" ", "").equalsIgnoreCase(args[4])){
						mapTable = table;
					}
				}
				
				if(mapTable == null){
					sendCommandMessage(sender, ChatColor.RED + "Map Table could not be found!");
					return;
				}
				
				GameTeamTypes gameTeamTypes = null;
				for(GameTeamTypes teamTypes : GameTeamTypes.values()){
					if(teamTypes.toString().equalsIgnoreCase(args[2])){
						gameTeamTypes = teamTypes;
					}
				}
				
				if(gameTeamTypes == null){
					sendCommandMessage(sender, ChatColor.RED + "Team could not be found!");
					return;
				}
				
				if(!gameType.getTeams().contains(gameTeamTypes)){
					sendCommandMessage(sender, ChatColor.RED + "Team is not part of this gamemode!");
					return;
				}
				
				if(!mapTable.getGameType().getDisplayName().replaceAll(" ", "").equalsIgnoreCase(gameType.getDisplayName().replaceAll(" ", ""))){
					sendCommandMessage(sender, ChatColor.RED + "This gamemode is not associated with this map!");
					return;
				}
				
				if(MinersFortune.getPlugin().getSqlHandler().getTeam(mapTable.getMapId(), gameTeamTypes.toString()) == null){
					sendCommandMessage(sender, ChatColor.RED + "Team does not exist in the database!");
					return;
				}
				
				sendCommandMessage(sender, ChatColor.YELLOW + "You have updated this maps active status!");
				mapTable.setActive(Boolean.parseBoolean(args[5]));
				MinersFortune.getPlugin().getSqlHandler().saveMap(mapTable);
			}
		}else if(args[0].equalsIgnoreCase("condition")){
			 if(args[1].equalsIgnoreCase("add")){
					if(setting != null){
						sendCommandMessage(sender, ChatColor.RED + "Someone is already setting locations!");
						return;
					}

					GameType gameType = null;
					for(GameType type : GameType.values()){
						if(type.getDisplayName().replaceAll(" ", "").equalsIgnoreCase(args[3])){
							gameType = type;
						}
					}
					
					if(gameType == null){
						sendCommandMessage(sender, ChatColor.RED + "Gametype could not be found!");
						return;
					}

					if(MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", "")) == null){
						sendCommandMessage(sender, ChatColor.RED + "This gamemode does not have any maps!");
						return;
					}
					
					MapTable mapTable = null;
					for(MapTable table : MinersFortune.getPlugin().getSqlHandler().getAllMaps(gameType.getDisplayName().replaceAll(" ", ""))){
						if(table.getMapName().replaceAll(" ", "").equalsIgnoreCase(args[4])){
							mapTable = table;
						}
					}
					
					if(mapTable == null){
						sendCommandMessage(sender, ChatColor.RED + "Map Table could not be found!");
						return;
					}
					
					GameTeamTypes gameTeamTypes = null;
					for(GameTeamTypes teamTypes : GameTeamTypes.values()){
						if(teamTypes.toString().equalsIgnoreCase(args[2])){
							gameTeamTypes = teamTypes;
						}
					}
					
					if(gameTeamTypes == null){
						sendCommandMessage(sender, ChatColor.RED + "Team could not be found!");
						return;
					}
					
					if(!gameType.getTeams().contains(gameTeamTypes)){
						sendCommandMessage(sender, ChatColor.RED + "Team is not part of this gamemode!");
						return;
					}
					
					if(!mapTable.getGameType().getDisplayName().replaceAll(" ", "").equalsIgnoreCase(gameType.getDisplayName().replaceAll(" ", ""))){
						sendCommandMessage(sender, ChatColor.RED + "This gamemode is not associated with this map!");
						return;
					}

					if(MinersFortune.getPlugin().getSqlHandler().getTeam(mapTable.getMapId(), gameTeamTypes.toString()) == null){
						sendCommandMessage(sender, ChatColor.RED + "Team does not exist in the database!");
						return;
					}
					
					if(MinersFortune.getPlugin().getSqlHandler().getCondition(mapTable.getMapId()) == null){
						MinersFortune.getPlugin().getSqlHandler().createCondition(mapTable.getMapId(), args[5]);
					}else{
						String mapC = MinersFortune.getPlugin().getSqlHandler().getCondition(mapTable.getMapId());
						MinersFortune.getPlugin().getSqlHandler().deleteCondition(mapTable.getMapId());
						mapC = mapC + " " + args[5];
						MinersFortune.getPlugin().getSqlHandler().createCondition(mapTable.getMapId(), mapC);
					}
					
					sendCommandMessage(sender, ChatColor.YELLOW + "You have set the new game condition!");
			 }
		}
	}
	
	public void remain(){
		new BukkitRunnable(){
			public void run() {
				if(setting != null){
					sendCommandMessage(setting, ChatColor.RED + "You are still setting locations for a map! Please do not forget or game could crash!"); 
					sendCommandMessage(setting, ChatColor.RED + "To leave setting mode, use the command /update team finish");
					remain();
					return;
				}
			}
		}.runTaskLater(MinersFortune.getPlugin(), 1200);
	}
}
