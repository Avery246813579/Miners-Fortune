package me.avery246813579.minersfortune.gamemode.conditions;

public class ConditionHandler {
	private static LootChests lootChests = new LootChests();
	private static BombCondition bombCondition = new BombCondition();
	private static ControlPoints controlPoints = new ControlPoints();
	private static PlayerInvincibility playerInvincibility = new PlayerInvincibility();
	private static NoBlockInteraction noBlockInteraction = new NoBlockInteraction();
	private static NoDropItems noDropItems = new NoDropItems();
	private static NoInventoryInteract noInventoryInteract = new NoInventoryInteract();
	private static NoHunger noHunger = new NoHunger();
	private static PlayerCompass playerCompass = new PlayerCompass();
	private static AgLootChests agLootChests = new AgLootChests();
	private static OutsideMap outsideMap = new OutsideMap();
	private static SteakHeal steakHeal = new SteakHeal();
	private static BorderCondition borderCondition = new BorderCondition();
	
	public static LootChests getLootChests() {
		return lootChests;
	}

	public static void setLootChests(LootChests lootChests) {
		ConditionHandler.lootChests = lootChests;
	}

	public static BombCondition getBombCondition() {
		return bombCondition;
	}

	public static void setBombCondition(BombCondition bombCondition) {
		ConditionHandler.bombCondition = bombCondition;
	}

	public static ControlPoints getControlPoints() {
		return controlPoints;
	}

	public static void setControlPoints(ControlPoints controlPoints) {
		ConditionHandler.controlPoints = controlPoints;
	}

	public static PlayerInvincibility getPlayerInvincibility() {
		return playerInvincibility;
	}

	public static void setPlayerInvincibility(PlayerInvincibility playerInvincibility) {
		ConditionHandler.playerInvincibility = playerInvincibility;
	}

	public static NoBlockInteraction getNoBlockInteraction() {
		return noBlockInteraction;
	}

	public static void setNoBlockInteraction(NoBlockInteraction noBlockInteraction) {
		ConditionHandler.noBlockInteraction = noBlockInteraction;
	}

	public static NoDropItems getNoDropItems() {
		return noDropItems;
	}

	public static void setNoDropItems(NoDropItems noDropItems) {
		ConditionHandler.noDropItems = noDropItems;
	}

	public static NoInventoryInteract getNoInventoryInteract() {
		return noInventoryInteract;
	}

	public static void setNoInventoryInteract(NoInventoryInteract noInventoryInteract) {
		ConditionHandler.noInventoryInteract = noInventoryInteract;
	}

	public static NoHunger getNoHunger() {
		return noHunger;
	}

	public static void setNoHunger(NoHunger noHunger) {
		ConditionHandler.noHunger = noHunger;
	}

	public static PlayerCompass getPlayerCompass() {
		return playerCompass;
	}

	public static void setPlayerCompass(PlayerCompass playerCompass) {
		ConditionHandler.playerCompass = playerCompass;
	}

	public static AgLootChests getAgLootChests() {
		return agLootChests;
	}

	public static void setAgLootChests(AgLootChests agLootChests) {
		ConditionHandler.agLootChests = agLootChests;
	}

	public static OutsideMap getOutsideMap() {
		return outsideMap;
	}

	public static void setOutsideMap(OutsideMap outsideMap) {
		ConditionHandler.outsideMap = outsideMap;
	}

	public static SteakHeal getSteakHeal() {
		return steakHeal;
	}

	public static void setSteakHeal(SteakHeal steakHeal) {
		ConditionHandler.steakHeal = steakHeal;
	}

	public static BorderCondition getBorderCondition() {
		return borderCondition;
	}

	public static void setBorderCondition(BorderCondition borderCondition) {
		ConditionHandler.borderCondition = borderCondition;
	}
}
