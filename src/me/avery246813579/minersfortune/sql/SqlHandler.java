package me.avery246813579.minersfortune.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.sql.tables.AccountTable;
import me.avery246813579.minersfortune.sql.tables.ArcherGamesTable;
import me.avery246813579.minersfortune.sql.tables.BanTable;
import me.avery246813579.minersfortune.sql.tables.DominationTable;
import me.avery246813579.minersfortune.sql.tables.GlobalStatsTable;
import me.avery246813579.minersfortune.sql.tables.MapTable;
import me.avery246813579.minersfortune.sql.tables.MinerTable;
import me.avery246813579.minersfortune.sql.tables.SearchAndDestroyTable;
import me.avery246813579.minersfortune.sql.tables.ServerTable;
import me.avery246813579.minersfortune.sql.tables.SurvivalGamesTable;
import me.avery246813579.minersfortune.sql.tables.TeamTable;
import me.avery246813579.minersfortune.util.Util;

public class SqlHandler {
	/** Variables **/
	MinersFortune plugin;

	String SQL_USER = "frostbyt_site";
	String SQL_PASS = "t]T}1a!@()90";
	String SQL_DATA = "Minecraft";
	String SQL_HOST = "198.245.55.118:6446";

	// systemctl start mariadb.service

	public SqlHandler(MinersFortune plugin) {
		new TableCreator(this);
		
		this.plugin = plugin;
	}

	public void executeQuery(String query) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String conn = "jdbc:mysql://" + this.SQL_HOST + "/" + this.SQL_DATA;
			connection = DriverManager.getConnection(conn, this.SQL_USER, this.SQL_PASS);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception exception) {
			exception.printStackTrace();

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String conn = "jdbc:mysql://" + this.SQL_HOST + "/" + this.SQL_DATA;
			return DriverManager.getConnection(conn, this.SQL_USER, this.SQL_PASS);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return null;
	}

	/****************************************
	 * 
	 * Accounts
	 * 
	 ***************************************/

	public void createAccount(String name, String uuid) {
		String insert = "INSERT INTO Accounts (name, uuid, ip, rank, credits, coins, rank_id, isStaff, isRanked) VALUES ('" + name + "', '" + uuid + "', 'Unknown', 'default', '100', '0', '0', '0', '0')";
		executeQuery(insert);
	}

	public AccountTable findAccount(String name) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Accounts` WHERE `name` = '" + name + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			AccountTable account = new AccountTable(r.getInt("player_id"), r.getString("name"), r.getString("uuid"), r.getString("ip"), r.getString("rank"), r.getInt("credits"), r.getInt("coins"), r.getInt("rank_id"), r.getBoolean("isStaff"), r.getBoolean("isRanked"));
			conn.close();
			stmt.close();
			r.close();
			return account;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public AccountTable getAccount(Player player) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Accounts` WHERE `uuid` = '" + plugin.getUuids().get(player) + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			AccountTable account = new AccountTable(r.getInt("player_id"), r.getString("name"), r.getString("uuid"), r.getString("ip"), r.getString("rank"), r.getInt("credits"), r.getInt("coins"), r.getInt("rank_id"), r.getBoolean("isStaff"), r.getBoolean("isRanked"));
			conn.close();
			stmt.close();
			r.close();
			return account;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	public AccountTable getAccount(int id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Accounts` WHERE `player_id` = '" + id + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			AccountTable account = new AccountTable(r.getInt("player_id"), r.getString("name"), r.getString("uuid"), r.getString("ip"), r.getString("rank"), r.getInt("credits"), r.getInt("coins"), r.getInt("rank_id"), r.getBoolean("isStaff"), r.getBoolean("isRanked"));
			conn.close();
			stmt.close();
			r.close();
			return account;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveAccount(AccountTable account) {
		String insert = "UPDATE Accounts SET name='" + account.getName() + "', rank='" + account.getRank() + "', ip='" + account.getIp() + "', isStaff='" + Util.getBooleanByteType(account.isStaff()) + "', isRanked='" + Util.getBooleanByteType(account.isRanked()) + "', credits='"
				+ account.getCredits() + "', rank_id='" + account.getRank_id() + "', coins='" + account.getCoins() + "' WHERE player_id='" + account.getPlayer_id() + "'";
		executeQuery(insert);
	}

	/****************************************
	 * 
	 * Miners
	 * 
	 ***************************************/

	public void createMiner(int i) {
		String insert = "INSERT INTO Miner (player_id, exp, emeralds, lastLocation, inventory, classType, masteries, relics, quests, spells, skills, vaultStorage) VALUES ('" + i + "', '0', '0', 'none', 'none', 'none', 'none', 'none', 'none', 'none', 'none', 'none')";
		executeQuery(insert);
	}

	public MinerTable getMiner(int i) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Miner` WHERE `player_id` = '" + i + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				createMiner(i);
				return getMiner(i);
			}

			MinerTable miner = new MinerTable(r.getInt("player_id"), r.getInt("exp"), r.getInt("emeralds"), r.getString("lastLocation"), r.getString("inventory"), r.getString("classType"), r.getString("masteries"), r.getString("relics"), r.getString("quests"), r.getString("spells"),
					r.getString("skills"), r.getString("vaultStorage"));
			conn.close();
			stmt.close();
			r.close();
			return miner;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveMiner(MinerTable miner) {
		String insert = "UPDATE Miner SET exp='" + miner.getExp() + "', emeralds='" + miner.getEmeralds() + "', lastLocation='" + miner.getLastLocation() + "', inventory='" + miner.getInventory() + "', classType='" + miner.getClassType() + "', masteries='" + miner.getMasteries() + "', relics='"
				+ miner.getRelics() + "', quests='" + miner.getQuests() + "', spells='" + miner.getSpells() + "', skills='" + miner.getSkills() + "', vaultStorage='" + miner.getVaultStorage() + "' WHERE player_id='" + miner.getPlayer_id() + "'";
		executeQuery(insert);
	}

	/****************************************
	 * 
	 * Maps
	 * 
	 ***************************************/

	public void createMap(String mapName, String mapCreator, String mapLocation, String gameType) {
		String insert = "INSERT INTO Maps (map_name, map_creator, map_location, gametype, midpoint, active) VALUES ('" + mapName + "', '" + mapCreator + "', '" + mapLocation + "', '" + gameType + "', 'Unknown', '0')";
		executeQuery(insert);
	}

	public MapTable getMap(String mapName) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Maps` WHERE `map_name` = '" + mapName + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			MapTable map = new MapTable(r.getInt("map_id"), r.getString("map_name"), r.getString("map_creator"), r.getString("map_location"), r.getString("gametype"), r.getString("midpoint"), r.getBoolean("active"));
			conn.close();
			stmt.close();
			r.close();
			return map;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public List<MapTable> getAllMaps(String gameType) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Maps` WHERE `gametype` = '" + gameType + "' ;");
			if (!r.isBeforeFirst()) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			List<MapTable> maps = new ArrayList<MapTable>();
			while (r.next()) {
				MapTable mapTable = new MapTable(r.getInt("map_id"), r.getString("map_name"), r.getString("map_creator"), r.getString("map_location"), r.getString("gametype"), r.getString("midpoint"), r.getBoolean("active"));
				maps.add(mapTable);
			}

			conn.close();
			stmt.close();
			r.close();
			return maps;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public List<MapTable> getAllActiveMaps(String gameType) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Maps` WHERE `gametype` = '" + gameType + "' ;");
			if (!r.isBeforeFirst()) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			List<MapTable> maps = new ArrayList<MapTable>();
			while (r.next()) {
				MapTable mapTable = new MapTable(r.getInt("map_id"), r.getString("map_name"), r.getString("map_creator"), r.getString("map_location"), r.getString("gametype"), r.getString("midpoint"), r.getBoolean("active"));
				if (mapTable.isActive()) {
					maps.add(mapTable);
				}
			}

			conn.close();
			stmt.close();
			r.close();
			return maps;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveMap(MapTable map) {
		String insert = "UPDATE Maps SET map_name='" + map.getMapName() + "', map_creator='" + map.getMapCreator() + "', map_location='" + map.getMapLocation() + "', gametype='" + map.getGameType() + "', midpoint='" + map.getMidPoint() + "', active='" + Util.getBooleanByteType(map.isActive())
				+ "' WHERE map_name='" + map.getMapName() + "'";
		executeQuery(insert);
	}

	public void deleteMap(String mapName) {
		String insert = "DELETE FROM Maps WHERE map_name='" + mapName + "'";
		executeQuery(insert);
	}

	/****************************************
	 * 
	 * Teams
	 * 
	 **************************************/

	public void createTeam(int mapId, String teamName, String teamColor, String teamSpawns) {
		String insert = "INSERT INTO Teams (map_id, team_name, team_color, team_spawns) VALUES ('" + mapId + "', '" + teamName + "', '" + teamColor + "', '" + teamSpawns + "')";
		executeQuery(insert);
	}

	public TeamTable getTeam(int mapId, String teamName) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Teams` WHERE `map_id` = '" + mapId + "' AND `team_name` = '" + teamName + "';");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			TeamTable team = new TeamTable(r.getInt("map_id"), r.getString("team_name"), r.getString("team_color"), r.getString("team_spawns"));
			conn.close();
			stmt.close();
			r.close();
			return team;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveTeam(TeamTable team) {
		String insert = "UPDATE Teams SET team_name='" + team.getName() + "', team_color='" + team.getColor() + "', team_spawns='" + team.getSpawns() + "' WHERE team_name='" + team.getName() + "' AND map_id='" + team.getMapId() + "'";
		executeQuery(insert);
	}

	/****************************************
	 * 
	 * Servers
	 * 
	 ***************************************/

	public void createServer(Server server) {
		String insert = "INSERT INTO Servers (server_name, server_status, gametype, game_map, online_players, max_players, current_game, whitelist) VALUES ('" + server.getServerName() + "', 'Limbow', 'Unknown', 'Unknown', '" + server.getOnlinePlayers().length + "', '" + server.getMaxPlayers()
				+ "', 'Unknown', '0')";
		executeQuery(insert);
	}

	public ServerTable getServer(String serverName) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Servers` WHERE `server_name` = '" + serverName + "';");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			ServerTable server = new ServerTable(r.getString("server_name"), r.getString("server_status"), r.getString("gametype"), r.getString("game_map"), r.getInt("online_players"), r.getInt("max_players"), r.getString("current_game"), r.getBoolean("whitelist"));
			conn.close();
			stmt.close();
			r.close();
			return server;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveServer(ServerTable server) {
		String insert = "UPDATE Servers SET server_name='" + server.getServerName() + "', server_status='" + server.getServerStatus() + "', gametype='" + server.getGameType() + "', game_map='" + server.getGameMap() + "', online_players='" + server.getOnlinePlayers() + "', max_players='"
				+ server.getMaxPlayers() + "', current_game='" + server.getCurrentGame() + "', whitelist='" + Util.getBooleanByteType(server.isWhitelist()) + "' WHERE server_name='" + server.getServerName() + "'";
		executeQuery(insert);
	}

	/****************************************
	 * 
	 * Kits
	 * 
	 ***************************************/

	public void createKit(int player_id, String gamemode, String kit_name) {
		String insert = "INSERT INTO Kits (player_id, gamemode, kit_name) VALUES ('" + player_id + "', '" + gamemode + "', '" + kit_name + "')";
		executeQuery(insert);
	}

	public List<String> getAllKits(int player_id, String gamemode) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Kits` WHERE `player_id` = '" + player_id + "' AND `gamemode` = '" + gamemode + "' ;");
			if (!r.isBeforeFirst()) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			List<String> kits = new ArrayList<String>();
			while (r.next()) {
				kits.add(r.getString("kit_name"));
			}

			conn.close();
			stmt.close();
			r.close();

			return kits;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/****************************************
	 * 
	 * Game Conditions
	 * 
	 ***************************************/

	public void createCondition(int map_id, String condition) {
		String insert = "INSERT INTO GameCondition (map_id, game_condition) VALUES ('" + map_id + "', '" + condition + "')";
		executeQuery(insert);
	}

	public String getCondition(int map_id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `GameCondition` WHERE `map_id` = '" + map_id + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				return null;
			}

			String condition = r.getString("game_condition");
			conn.close();
			stmt.close();
			r.close();

			return condition;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void deleteCondition(int map_id) {
		String insert = "DELETE FROM GameCondition WHERE map_id='" + map_id + "'";
		executeQuery(insert);
	}

	/****************************************
	 * 
	 * Banned
	 * 
	 ***************************************/

	public void createBanned(int player_id) {
		String insert = "INSERT INTO Bans (player_id, banned, muted) VALUES ('" + player_id + "', 'false', 'false')";
		executeQuery(insert);
	}

	public BanTable getBanned(int player_id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Bans` WHERE `player_id` = '" + player_id + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				createBanned(player_id);
				return getBanned(player_id);
			}

			BanTable banTable = new BanTable(r.getInt("player_id"), r.getString("banned"), r.getString("muted"));
			conn.close();
			stmt.close();
			r.close();

			return banTable;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveBan(BanTable banTable) {
		String insert = "UPDATE Bans SET banned='" + banTable.getBanned() + "', muted='" + banTable.getMuted() + "' WHERE player_id='" + banTable.getPlayer_id() + "'";
		executeQuery(insert);
	}

	/****************************************
	 * 
	 * Global Stats
	 * 
	 ***************************************/

	public void createGlobalStats(int player_id) {
		String insert = "INSERT INTO Stats_Global (player_id, time_online, games_played, credits_earned) VALUES ('" + player_id + "', '0', '0', '0')";
		executeQuery(insert);
	}

	public GlobalStatsTable getGlobalStats(int player_id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Stats_Global` WHERE `player_id` = '" + player_id + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				createGlobalStats(player_id);
				return getGlobalStats(player_id);
			}

			GlobalStatsTable globalStatsTable = new GlobalStatsTable(r.getInt("player_id"), r.getInt("time_online"), r.getInt("games_played"), r.getInt("credits_earned"));
			conn.close();
			stmt.close();
			r.close();

			return globalStatsTable;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveGlobalStats(GlobalStatsTable globalStatsTable) {
		String insert = "UPDATE Stats_Global SET time_online='" + globalStatsTable.getTime_online() + "', games_played='" + globalStatsTable.getGames_played() + "', credits_earned='" + globalStatsTable.getCredits_earned() + "' WHERE player_id='" + globalStatsTable.getPlayer_id() + "'";
		executeQuery(insert);
	}

	/****************************************
	 * 
	 * Archer Games Stats
	 * 
	 ***************************************/

	public void createArcherGamesStats(int player_id) {
		String insert = "INSERT INTO Stats_ArcherGames (player_id, wins, losses, kills, deaths, credits_earned) VALUES ('" + player_id + "', '0', '0', '0', '0', '0')";
		executeQuery(insert);
	}

	public ArcherGamesTable getArcherGamesStats(int player_id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Stats_ArcherGames` WHERE `player_id` = '" + player_id + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				createArcherGamesStats(player_id);
				return getArcherGamesStats(player_id);
			}

			ArcherGamesTable archerGamesTable = new ArcherGamesTable(r.getInt("player_id"), r.getInt("wins"), r.getInt("losses"), r.getInt("kills"), r.getInt("deaths"), r.getInt("credits_earned"));
			conn.close();
			stmt.close();
			r.close();

			return archerGamesTable;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveArcherGamesStats(ArcherGamesTable archerGamesTable) {
		String insert = "UPDATE Stats_ArcherGames SET wins='" + archerGamesTable.getWins() + "', losses='" + archerGamesTable.getLosses() + "', kills='" + archerGamesTable.getKills() + "', deaths='" + archerGamesTable.getDeaths() + "', credits_earned='" + archerGamesTable.getCredits_earned() + "' WHERE player_id='" + archerGamesTable.getPlayer_id() + "'";
		executeQuery(insert);
	}
	
	/****************************************
	 * 
	 * Search & Destroy Stats
	 * 
	 ***************************************/

	public void createSearchAndDestroyStats(int player_id) {
		String insert = "INSERT INTO Stats_SearchAndDestroy (player_id,  wins, losses, kills, deaths, credits_earned, bomb_arms, bomb_defuses) VALUES ('" + player_id + "', '0', '0', '0', '0', '0', '0', '0')";
		executeQuery(insert);
	}

	public SearchAndDestroyTable getSearchAndDestroyStats(int player_id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Stats_SearchAndDestroy` WHERE `player_id` = '" + player_id + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				createSearchAndDestroyStats(player_id);
				return getSearchAndDestroyStats(player_id);
			}

			SearchAndDestroyTable searchAndDestroyTable = new SearchAndDestroyTable(r.getInt("player_id"), r.getInt("wins"), r.getInt("losses"), r.getInt("kills"), r.getInt("deaths"), r.getInt("credits_earned"), r.getInt("bomb_arms"), r.getInt("bomb_defuses"));
			conn.close();
			stmt.close();
			r.close();

			return searchAndDestroyTable;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveSearchAndDestroyStats(SearchAndDestroyTable searchAndDestroyTable) {
		String insert = "UPDATE Stats_SearchAndDestroy SET wins='" + searchAndDestroyTable.getWins() + "', losses='" + searchAndDestroyTable.getLosses() + "', kills='" + searchAndDestroyTable.getKills() + "', deaths='" + searchAndDestroyTable.getDeaths() + "', credits_earned='" + searchAndDestroyTable.getCredits_earned() + "', bomb_arms='" + searchAndDestroyTable.getBomb_arms() + "', bomb_defuses='" + searchAndDestroyTable.getBomb_defuses() + "' WHERE player_id='" + searchAndDestroyTable.getPlayer_id() + "'";
		executeQuery(insert);
	}
	
	/****************************************
	 * 
	 * Survival Games Stats
	 * 
	 ***************************************/

	public void createSurvivalGamesStats(int player_id) {
		String insert = "INSERT INTO Stats_SurvivalGames (player_id, wins, losses, kills, deaths, credits_earned) VALUES ('" + player_id + "', '0', '0', '0', '0', '0')";
		executeQuery(insert);
	}

	public SurvivalGamesTable getSurvivalGamesStats(int player_id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Stats_SurvivalGames` WHERE `player_id` = '" + player_id + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				createSurvivalGamesStats(player_id);
				return getSurvivalGamesStats(player_id);
			}

			SurvivalGamesTable survivalGamesTable = new SurvivalGamesTable(r.getInt("player_id"), r.getInt("wins"), r.getInt("losses"), r.getInt("kills"), r.getInt("deaths"), r.getInt("credits_earned"));
			conn.close();
			stmt.close();
			r.close();

			return survivalGamesTable;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveSurvivalGamesStats(SurvivalGamesTable survivalGamesTable) {
		String insert = "UPDATE Stats_SurvivalGames SET wins='" + survivalGamesTable.getWins() + "', losses='" + survivalGamesTable.getLosses() + "', kills='" + survivalGamesTable.getKills() + "', deaths='" + survivalGamesTable.getDeaths() + "', credits_earned='" + survivalGamesTable.getCredits_earned() + "' WHERE player_id='" + survivalGamesTable.getPlayer_id() + "'";
		executeQuery(insert);
	}
	
	/****************************************
	 * 
	 * Survival Games Stats
	 * 
	 ***************************************/

	public void createDominationStats(int player_id) {
		String insert = "INSERT INTO Stats_Domination (player_id, wins, losses, kills, deaths, credits_earned, points_captured) VALUES ('" + player_id + "', '0', '0', '0', '0', '0', '0')";
		executeQuery(insert);
	}

	public DominationTable getDominationStats(int player_id) {
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM `Stats_Domination` WHERE `player_id` = '" + player_id + "' ;");
			r.last();
			if (r.getRow() == 0) {
				conn.close();
				stmt.close();
				r.close();
				createDominationStats(player_id);
				return getDominationStats(player_id);
			}

			DominationTable dominationTable = new DominationTable(r.getInt("player_id"), r.getInt("wins"), r.getInt("losses"), r.getInt("kills"), r.getInt("deaths"), r.getInt("credits_earned"), r.getInt("points_captured"));
			conn.close();
			stmt.close();
			r.close();

			return dominationTable;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void saveDominationSave(DominationTable dominationTable) {
		String insert = "UPDATE Stats_Domination SET wins='" + dominationTable.getWins() + "', losses='" + dominationTable.getLosses() + "', kills='" + dominationTable.getKills() + "', deaths='" + dominationTable.getDeaths() + "', credits_earned='" + dominationTable.getCredits_earned() + "', points_captured='" + dominationTable.getPoints_captured() + "' WHERE player_id='" + dominationTable.getPlayer_id() + "'";
		executeQuery(insert);
	}
	
	/****************************************
	 * 
	 * General
	 * 
	 ***************************************/

	public void addAction(int player_id, String action) {
		String insert = "INSERT INTO Activity (player_id, action) VALUES ('" + player_id + "', '" + action + "')";
		executeQuery(insert);
	}

	public void loadPlayerSql(int i) {
		getMiner(i);
	}

	public int getPlayerId(Player player) {
		return getAccount(player).getPlayer_id();
	}

	public int getPlayerId(String name) {
		return findAccount(name).getPlayer_id();
	}
}
