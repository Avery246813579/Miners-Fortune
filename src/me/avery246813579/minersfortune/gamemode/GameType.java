package me.avery246813579.minersfortune.gamemode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.avery246813579.minersfortune.gamemode.conditions.ConditionHandler;
import me.avery246813579.minersfortune.gamemode.kits.KitHandler;

public enum GameType {
	/** Enums **/
	ArcherGames("Archer Games", 25, 4,
			Arrays.asList(GameTeamTypes.SOLO),
			new ArrayList<GameKit>(Arrays.asList(KitHandler.kitGod)),
			new String[] {"A fight to the death against 23 other players!", "Survive by using all of your items!", "Buying item cost credits!", "You recieve 50 credits per win!"},
			new ArrayList<GameCondition>(Arrays.asList(ConditionHandler.getPlayerInvincibility(), ConditionHandler.getPlayerCompass(), ConditionHandler.getAgLootChests())),
			Arrays.asList(GameWinType.LastManStanding)),
	SearchAndDestroy("Search & Destroy" , 40, 4,
			Arrays.asList(GameTeamTypes.RED, GameTeamTypes.BLUE),
			new ArrayList<GameKit>(Arrays.asList(KitHandler.kitTrooper, KitHandler.kitLongBow, KitHandler.kitShortbow, KitHandler.kitGhost, KitHandler.kitNinja, KitHandler.kitSDJuggernaut, KitHandler.kitRewind)),
			new String[] {"Plant the bomb or kill all enemy team to win!", "Buy items off signs to improve your skills!", "You recieve 25 credits per win!", ""},
			new ArrayList<GameCondition>(Arrays.asList(ConditionHandler.getBombCondition(), ConditionHandler.getNoBlockInteraction(), ConditionHandler.getNoDropItems(), ConditionHandler.getNoHunger(), ConditionHandler.getOutsideMap(), ConditionHandler.getSteakHeal())),
			Arrays.asList(GameWinType.LastTeamStanding)),
	Domination("Domination" , 10, 4,
			Arrays.asList(GameTeamTypes.RED, GameTeamTypes.BLUE),
			new ArrayList<GameKit>(Arrays.asList(KitHandler.kitWarrior, KitHandler.kitJuggernaut, KitHandler.kitSniper)),
			new String[] {"Capture all three capture points to gain points.", "First team to get 500 points wins the game.", "The more players on a point, the faster it's captured!", "You revieve 50 Credits per win!"},
			new ArrayList<GameCondition>(Arrays.asList(ConditionHandler.getControlPoints(), ConditionHandler.getNoBlockInteraction(), ConditionHandler.getNoDropItems(), ConditionHandler.getNoInventoryInteract(), ConditionHandler.getNoHunger())),
			Arrays.asList(GameWinType.PlayForPoints)),
	SurvivalGames("Survival Games", 25, 4,
			Arrays.asList(GameTeamTypes.SOLO),
			new ArrayList<GameKit>(Arrays.asList(KitHandler.kitSurvival)),
			new String[] {"A fight to the death against 23 other players", "Survival by looting chests and killing players!", "Use placeable blocks wisely!", "You recieve 50 Credits per win!"},
			new ArrayList<GameCondition>(Arrays.asList(ConditionHandler.getBorderCondition(), ConditionHandler.getLootChests(), ConditionHandler.getNoBlockInteraction())),
			Arrays.asList(GameWinType.LastManStanding));
	
	/** Variables **/
	private List<GameCondition> conditions;
	private int maxPlayers, minPlayers;
	private List<GameWinType> winTypes;
	private List<GameTeamTypes> teams;
	private String[] description;
	private List<GameKit> kits;
	private String displayName;
	
	GameType(String displayName, int maxPlayers, int minPlayers, List<GameTeamTypes> teams, List<GameKit> kits, String[] description, List<GameCondition> conditions, List<GameWinType> gameWinTypes){
		this.displayName = displayName;
		this.maxPlayers = maxPlayers;
		this.minPlayers = minPlayers;
		this.teams = teams;
		this.kits = kits;
		this.description = description;
		this.conditions = conditions;
		this.winTypes = gameWinTypes;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public List<GameTeamTypes> getTeams() {
		return teams;
	}

	public void setTeams(List<GameTeamTypes> teams) {
		this.teams = teams;
	}

	public List<GameKit> getKits() {
		return kits;
	}

	public void setKits(List<GameKit> kits) {
		this.kits = kits;
	}

	public List<GameCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<GameCondition> conditions) {
		this.conditions = conditions;
	}

	public String[] getDescription() {
		return description;
	}

	public void setDescription(String[] description) {
		this.description = description;
	}

	public List<GameWinType> getWinTypes() {
		return winTypes;
	}

	public void setWinTypes(List<GameWinType> winTypes) {
		this.winTypes = winTypes;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}
}
