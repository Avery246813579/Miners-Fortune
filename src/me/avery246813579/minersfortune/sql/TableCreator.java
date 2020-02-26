package me.avery246813579.minersfortune.sql;

public class TableCreator {
	/** Classes **/
	SqlHandler plugin;
	
	/** Variables **/
	String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS Accounts (player_id INT AUTO_INCREMENT, name VARCHAR(20), uuid VARCHAR(40), ip VARCHAR(30), rank VARCHAR(15), credits INT(10), coins INT(10), rank_id INT(2), isStaff BOOL, isRanked BOOL, PRIMARY KEY (player_id));";
	String CREATE_MINER_TABLE = "CREATE TABLE IF NOT EXISTS Miner (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, exp INT(10), emeralds INT(10), lastLocation VARCHAR(150), inventory TEXT(300), classType VARCHAR(25), masteries VARCHAR(250), relics VARCHAR(250), quests TEXT(300), spells TEXT(300), skills TEXT(300), vaultStorage TEXT(600), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";
	String CREATE_MAPS_TABLE = "CREATE TABLE IF NOT EXISTS Maps (map_id INT NOT NULL AUTO_INCREMENT, map_name VARCHAR(30), map_creator VARCHAR(40), map_location VARCHAR(30), gametype VARCHAR(30), midpoint VARCHAR(100), active BOOL, PRIMARY KEY (map_id));";
	String CREATE_TEAM_TABLE = "CREATE TABLE IF NOT EXISTS Teams (id INT NOT NULL AUTO_INCREMENT, map_id INT NOT NULL, team_name VARCHAR(30), team_color VARCHAR(20), team_spawns TEXT(1000), PRIMARY KEY (id), FOREIGN KEY(map_id) REFERENCES Maps(map_id));";
	String CREATE_SERVER_TABLE = "CREATE TABLE IF NOT EXISTS Servers (id INT NOT NULL AUTO_INCREMENT, server_name VARCHAR(30), server_status VARCHAR(30), gametype VARCHAR(30), game_map VARCHAR(30), online_players INT(5), max_players INT(5), current_game VARCHAR(30), whitelist BOOL, PRIMARY KEY (id));";
	String CREATE_KIT_TABLE = "CREATE TABLE IF NOT EXISTS Kits (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, gamemode VARCHAR(30), kit_name VARCHAR(30), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";
	String CREATE_MAP_CONDITION_TABLE = "CREATE TABLE IF NOT EXISTS GameCondition (id INT NOT NULL AUTO_INCREMENT, map_id INT NOT NULL, game_condition TEXT(1000), PRIMARY KEY (id), FOREIGN KEY(map_id) REFERENCES Maps(map_id));";
	String CREATE_BANS_TABLE = "CREATE TABLE IF NOT EXISTS Bans (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, banned VARCHAR(250), muted VARCHAR(250), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";
	String CREATE_ACTION_TABLE = "CREATE TABLE IF NOT EXISTS Activity (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, action VARCHAR(250), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";

	String CREATE_GLOBAL_STATS_TABLE = "CREATE TABLE IF NOT EXISTS Stats_Global (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, time_online INT(11), games_played INT(11), credits_earned INT(11), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";
	String CREATE_ARCHER_GAMES_TABLE = "CREATE TABLE IF NOT EXISTS Stats_ArcherGames (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, wins INT(5), losses INT(5), kills INT(10), deaths INT(5), credits_earned INT(10), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";
	String CREATE_SEARCHANDDESTROY_TABLE = "CREATE TABLE IF NOT EXISTS Stats_SearchAndDestroy (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, wins INT(5), losses INT(5), kills INT(10), deaths INT(5), credits_earned INT(10), bomb_arms INT(5), bomb_defuses INT(5), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";
	String CREATE_SURVIVAL_GAMES_TABLE = "CREATE TABLE IF NOT EXISTS Stats_SurvivalGames (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, wins INT(5), losses INT(5), kills INT(10), deaths INT(5), credits_earned INT(10), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";
	String CREATE_DOMINATION_GAMES_TABLE = "CREATE TABLE IF NOT EXISTS Stats_Domination (id INT NOT NULL AUTO_INCREMENT, player_id INT NOT NULL, wins INT(5), losses INT(5), kills INT(10), deaths INT(5), credits_earned INT(10), points_captured INT(10), PRIMARY KEY (id), FOREIGN KEY(player_id) REFERENCES Accounts(player_id));";

	public TableCreator(SqlHandler plugin) {
		this.plugin = plugin;

		creatTables();
	}

	public void creatTables() {
		plugin.executeQuery(CREATE_ACCOUNT_TABLE);
		plugin.executeQuery(CREATE_MINER_TABLE);
		plugin.executeQuery(CREATE_MAPS_TABLE);
		plugin.executeQuery(CREATE_TEAM_TABLE);
		plugin.executeQuery(CREATE_SERVER_TABLE);
		plugin.executeQuery(CREATE_KIT_TABLE);
		plugin.executeQuery(CREATE_MAP_CONDITION_TABLE);
		plugin.executeQuery(CREATE_BANS_TABLE);
		plugin.executeQuery(CREATE_ACTION_TABLE);
		plugin.executeQuery(CREATE_GLOBAL_STATS_TABLE);
		plugin.executeQuery(CREATE_ARCHER_GAMES_TABLE);
		plugin.executeQuery(CREATE_SEARCHANDDESTROY_TABLE);
		plugin.executeQuery(CREATE_SURVIVAL_GAMES_TABLE);
		plugin.executeQuery(CREATE_DOMINATION_GAMES_TABLE);
	}
}
