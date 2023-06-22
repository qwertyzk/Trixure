package logic;

import java.util.Random;

import javax.swing.Timer;

import gui.Renderer;
import logic.map_objects.*;
import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;
import logic.level.*;
import logic.text.MessageBox;
import resources.Items;
import resources.Textures;


public class GameLogic
{
	private static Timer timer;
	private static Random randomizer;
	private static Player player;
	private static Dungeon dungeon;
	private static Room currentRoom;
	private static Monster[] activeMonsters;
	private static MessageBox messageBox;
	private static boolean onTitleScreen;
	private static boolean onWinScreen;

	public static void startGame() {
		Textures.init();
		
		init();
		
		timer = new Timer(20, new GameLoop());
		timer.start();
	}
	
	private static void init() {
		randomizer = new Random();
		dungeon = new Dungeon(randomizer);
		currentRoom = dungeon.getRoom(0);
		player = new Player( 2, 8);
		activeMonsters = currentRoom.getMonsters();
		messageBox = new MessageBox();
		
		onTitleScreen = true;
		onWinScreen = false;
	}

	public static void movePlayer(int dirX, int dirY) {
		onTitleScreen = false;


		if(player.isInventoryOpen() || player.isShopOpen() || player.isBlacksmithOpen())
			return;

		
		if(checkIfPlayerDied() || onWinScreen == true)
			return;
		
		if(detectMonsterToFight(dirX, dirY)) {
			checkIfPlayerDied();
			return;
		}
		
		switch(getTileInFrontOfEntity(player, dirX, dirY).getName()) {
		case "floor":
		case "blood":
			player.setPosition(player.getPosX()+dirX, player.getPosY()+dirY);
			break;
		case "stairs":
			currentRoom = dungeon.getNextRoom();
			player.setPosition(currentRoom.getStartPosX(), currentRoom.getStartPosY());
			activeMonsters = currentRoom.getMonsters();
			messageBox.addMessage("You went into a new floor!");
			player.addFloorCleared();
			break;
		case "trap":
			switch (currentRoom.getDifficulty()){
				case 1 -> player.damage(randomizer.nextInt(3)+2);
				case 2 -> player.damage(randomizer.nextInt(8)+5);
				case 3 -> player.damage(randomizer.nextInt(17)+8);
			}
			player.setPosition(player.getPosX()+dirX, player.getPosY()+dirY);
			currentRoom.disarmTrap(player.getPosX(), player.getPosY());
			messageBox.addMessage("You ran into a trap and you took damage!");
			break;
		case "locked_door":
			messageBox.addMessage("You need a key to open this door...");
			break;
		case "dragon":
			onWinScreen = true;
			break;
		}
		moveMonsters();
		checkIfPlayerDied();
	}

	private static MapObject getTileInFrontOfEntity(Entity entity, int dirX, int dirY) {
		return currentRoom.getTileAt(entity.getPosX()+dirX, entity.getPosY()+dirY);
	}
	
	public static void openPlayerInventory() {
		if(player.isShopOpen() || player.isBlacksmithOpen())
			return;

		if(player.getHealth() > 0)
			player.setInventoryOpen(!player.isInventoryOpen());
	}

	public static void openNPC() {
		if(player.isInventoryOpen())
			return;

		if(player.getHealth() > 0)
		{
			int[] dx = {1,-1,0,0};
			int[] dy = {0, 0, 1, -1};

			for(int i=0; i<4; ++i)
			{
				MapObject obiekt = currentRoom.getTileAt(player.getPosX()+dx[i], player.getPosY()+dy[i]);
				if(currentRoom.getTileAt(player.getPosX()+dx[i], player.getPosY()+dy[i]).getName() == "villager" ) {
					player.setShopOpen(!player.isShopOpen());
					player.chooseShop((Shop) obiekt);
				}

				else if(currentRoom.getTileAt(player.getPosX()+dx[i], player.getPosY()+dy[i]).getName() == "blacksmith" ) {
					player.setBlacksmithOpen(!player.isBlacksmithOpen());
					player.chooseBlacksmith((NPC) obiekt);
				}
			}
		}
	}

	public static void handleInteraction() {
		System.out.println("[Main][GameLogic]: Looking for an item to pickup");
		pickupItem(player.getPosX()+1, player.getPosY());
		pickupItem(player.getPosX()-1, player.getPosY());
		pickupItem(player.getPosX(), player.getPosY()+1);
		pickupItem(player.getPosX(), player.getPosY()-1);
	}
	
	private static void pickupItem(int itemPosX, int itemPosY) {
		switch(currentRoom.getTileAt(itemPosX, itemPosY).getName()) {
		case "hp_potion_tile":
			if(player.giveItem(Items.Consumable.HP_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a health potion!");
			}
			else {
				messageBox.addMessage("Your inventory is full!");
			}
			break;
		case "max_potion_tile":
			if(player.giveItem(Items.Consumable.MAX_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a max potion!");
			}
			else {
				messageBox.addMessage("Your inventory is full!");
			}
			break;
		case "str_potion_tile":
			if(player.giveItem(Items.Consumable.STRENGTH_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a strength potion!");
			}
			else {
				messageBox.addMessage("Your inventory is full!");
			}
			break;
		case "def_potion_tile":
			if(player.giveItem(Items.Consumable.DEFENCE_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a defence potion!");
			}
			else {
				messageBox.addMessage("Your inventory is full!");
			}
			break;
		case "myst_potion_tile":
			if(player.giveItem(Items.Consumable.MYSTERIOUS_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a mysterious potion!");
			}
			else {
				messageBox.addMessage("Your inventory is full!");
			}
			break;
		case "gold_bag":
			int g = randomizer.nextInt(7) + 5;
			player.giveGold(g);
			currentRoom.removeCollectible(itemPosX, itemPosY);
			messageBox.addMessage("You picked up a bag containing "+g+" gold!");
			break;
		case "key_tile":
			if(player.giveItem(Items.Consumable.KEY.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a key!");
			}
			else {
				messageBox.addMessage("Your inventory is full!");
			}
			break;
		case "chest":
			Chest c = (Chest) currentRoom.getTileAt(itemPosX, itemPosY);
			if(player.giveItem(c.getLoot()))
			{
				player.giveGold(c.getGold());
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You found a new item and some gold!");
			}
			else messageBox.addMessage("Your inventory is full");
		}
	}

	public static void handleLeftClick(int mouseX, int mouseY) {
		System.out.println("[Main][GameLogic]: Handling mouse click");
		if(player.isInventoryOpen()) {
			if(Renderer.inventorySlot1.contains(mouseX, mouseY)) {
				usePlayerItem(0);
			}
			else if(Renderer.inventorySlot2.contains(mouseX, mouseY)) {
				usePlayerItem(1);
			}
			else if(Renderer.inventorySlot3.contains(mouseX, mouseY)) {
				usePlayerItem(2);
			}

			else if(Renderer.inventoryBin1.contains(mouseX, mouseY)) {
				player.removeItem(0);
			}
			else if(Renderer.inventoryBin2.contains(mouseX, mouseY)) {
				player.removeItem(1);
			}
			else if(Renderer.inventoryBin3.contains(mouseX, mouseY)) {
				player.removeItem(2);
			}
		}

		if(player.isShopOpen()) {
			if (Renderer.shopSlot1.contains(mouseX, mouseY)) {
				buyItem(player.getCurrentShop(), 0);
			} else if (Renderer.shopSlot2.contains(mouseX, mouseY)) {
				buyItem(player.getCurrentShop(), 1);
			} else if (Renderer.shopSlot3.contains(mouseX, mouseY)) {
				buyItem(player.getCurrentShop(), 2);
			}
		}

		if(player.isBlacksmithOpen()) {
			if (Renderer.blacksmithSlot1.contains(mouseX, mouseY)) {
				repairItem(0);
			} else if (Renderer.blacksmithSlot2.contains(mouseX, mouseY)) {
				repairItem(1);
			}
		}
		
		if(player.getHealth() <= 0 || onWinScreen == true) {
			init();
		}
	}

	public static void repairItem(int i)
	{
		int durabilityDiff;
		switch (i) {
			case 0 -> {
				durabilityDiff = player.getWeapon().getTotalDurability() - player.getWeapon().getDurability();
				if (durabilityDiff > 0) {

					if (player.getRepairPriceWeapon() <= player.getGold()) {
						player.getWeapon().repair();
						player.giveGold(-player.getRepairPriceWeapon());
						messageBox.addMessage("You had your weapon repaired!");
					} else {
						messageBox.addMessage("You don't have enough money...");
					}

				} else {
					messageBox.addMessage("Your weapon is not broken...");
				}
			}
			case 1 -> {
				durabilityDiff = player.getArmor().getTotalDurability() - player.getArmor().getDurability();
				if (durabilityDiff > 0) {
					if (player.getRepairPriceArmor() <= player.getGold()) {
						player.getArmor().repair();
						player.giveGold(-player.getRepairPriceArmor());
						messageBox.addMessage("You had your armor repaired!");
					} else {
						messageBox.addMessage("You don't have enough money...");
					}

				} else {
					messageBox.addMessage("Your armor is not broken...");
				}
			}
		}

	}

	public static void buyItem(Shop sklep, int i)
	{
		Item item = sklep.getShopItem(i);

		if(player.getGold() >= item.getPrice())
		{
			if(player.giveItem(item))
			{
				player.giveGold( -item.getPrice());
				sklep.removeItem(i);
				messageBox.addMessage("You bought new item!");
			}
			else
			{
				messageBox.addMessage("Your inventory is full!");
			}
		}
		else {
			messageBox.addMessage("You don't have enough money...");
		}

	}

	private static void usePlayerItem(int index) {
		Item item = player.getInventoryItem(index);

		if(item instanceof Weapon)
		{
			player.equipWeapon((Weapon) item);
		}
		else if(item instanceof Armor)
		{
			player.equipArmor((Armor) item);
		}
		else{
			if(item == null) return;

			if(item.getName() == "hp_potion") {
				player.heal(10);
				messageBox.addMessage("You drank a red potion and you recovered health!");
			}
			else if(item.getName() == "max_potion") {
				player.increaseHealth(5);
				messageBox.addMessage("You drank a max potion and increased max health!");
			}
			else if(item.getName() == "str_potion") {
				player.increaseStrength(3);
				messageBox.addMessage("You drank a strength potion and increased strength!");
			}
			else if(item.getName() == "def_potion") {
				player.increaseDefence(3);
				messageBox.addMessage("You drank a strength potion and increased strength!");
			}
			else if(item.getName() == "myst_potion") {
				switch (randomizer.nextInt(8)) {
					case 0:
						player.heal(15);
						messageBox.addMessage("You drank a potion and you recovered health!");
						break;
					case 1:
						player.increaseHealth(7);
						messageBox.addMessage("You drank a potion and increased max health!");
						break;
					case 2:
						player.increaseStrength(4);
						messageBox.addMessage("You drank a potion and increased strength!");
						break;
					case 3:
						player.increaseDefence(4);
						messageBox.addMessage("You drank a potion and increased strength!");
						break;
					case 4:
						player.damage(10);
						messageBox.addMessage("You drank a potion, but it hurt you...");
						break;
					case 5:
						player.increaseHealth(-5);
						messageBox.addMessage("You drank a potion, but decreased max health...");
						break;
					case 6:
						player.increaseStrength(-3);
						messageBox.addMessage("You drank a potion, but decreased strength...");
						break;
					case 7:
						player.increaseDefence(-3);
						messageBox.addMessage("You drank a potion, but decreased defence...");
						break;
				}
			}
			else if(item.getName() == "key") {
				if(currentRoom.getTileAt(player.getPosX()+1, player.getPosY()).getName() == "door") {
					currentRoom.openDoor(player.getPosX()+1, player.getPosY());
				}
				else if(currentRoom.getTileAt(player.getPosX()-1, player.getPosY()).getName() == "door") {
					currentRoom.openDoor(player.getPosX()-1, player.getPosY());
				}
				else if(currentRoom.getTileAt(player.getPosX(), player.getPosY()+1).getName() == "door") {
					currentRoom.openDoor(player.getPosX(), player.getPosY()+1);
				}
				else if(currentRoom.getTileAt(player.getPosX(), player.getPosY()-1).getName() == "door") {
					currentRoom.openDoor(player.getPosX(), player.getPosY()-1);
				}
				else {
					messageBox.addMessage("You can't use this item this way...");
					return;
				}
				messageBox.addMessage("You used the key to open the door!");
			}
		}
		player.removeItem(index);
	}

	// wrzucic trap obok floor i blood
	private static void moveMonsters() {
		for(Monster monster : activeMonsters) {
			if(monster.getHealth() <= 0)
				continue;
			
			if(!monster.shouldChasePlayer()) {
				switch(randomizer.nextInt(4)) {
				case 0:
					if(currentRoom.thereIsMonsterHere(monster.getPosX()+1, monster.getPosY())) {
						return;
					}
					else if(monster.getPosX()+1 == player.getPosX() && monster.getPosY() == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!");
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
						break;
					}
					if(getTileInFrontOfEntity(monster, 1, 0).getName() == "floor" ||
							getTileInFrontOfEntity(monster, 1, 0).getName() == "blood") {
						monster.setPosition(monster.getPosX()+1, monster.getPosY());
						break;
					}
				case 1:
					if(currentRoom.thereIsMonsterHere(monster.getPosX()-1, monster.getPosY())) {
						return;
					}
					else if(monster.getPosX()-1 == player.getPosX() && monster.getPosY() == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!");
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
						break;
					}	
					if(getTileInFrontOfEntity(monster, -1, 0).getName() == "floor" ||
							getTileInFrontOfEntity(monster, -1, 0).getName() == "blood") {
						monster.setPosition(monster.getPosX()-1, monster.getPosY());
						break;
					}
				case 2:
					if(currentRoom.thereIsMonsterHere(monster.getPosX(), monster.getPosY()+1)) {
						return;
					}
					else if(monster.getPosX() == player.getPosX() && monster.getPosY()+1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!");
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
						break;
					}	
					if(getTileInFrontOfEntity(monster, 0, 1).getName() == "floor" ||
							getTileInFrontOfEntity(monster, 0, 1).getName() == "blood") {
						monster.setPosition(monster.getPosX(), monster.getPosY()+1);
						break;
					}
				case 3:
					if(currentRoom.thereIsMonsterHere(monster.getPosX(), monster.getPosY()-1)) {
						return;
					}
					else if(monster.getPosX() == player.getPosX() && monster.getPosY()-1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!");
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
						break;
					}	
					if(getTileInFrontOfEntity(monster, 0, -1).getName() == "floor" ||
							getTileInFrontOfEntity(monster, 0, -1).getName() == "blood") {
						monster.setPosition(monster.getPosX(), monster.getPosY()-1);
						break;
					}
				}
			} else {
				float angCoeff = -((float)player.getPosY()-(float)monster.getPosY())/((float)player.getPosX()-(float)monster.getPosX());
				
				if(angCoeff>-1 && angCoeff<1 && player.getPosX()>monster.getPosX()) {
					if(monster.getPosX()+1 == player.getPosX() && monster.getPosY() == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!");
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, 1, 0).getName() == "floor" ||
							getTileInFrontOfEntity(monster, 1, 0).getName() == "blood") {
						monster.setPosition(monster.getPosX()+1, monster.getPosY());
					}
				}
				else if(angCoeff>-1 && angCoeff<1 && player.getPosX()<monster.getPosX()) {
					if(monster.getPosX()-1 == player.getPosX() && monster.getPosY() == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!");
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, -1, 0).getName() == "floor" ||
							getTileInFrontOfEntity(monster, -1, 0).getName() == "blood") {
						monster.setPosition(monster.getPosX()-1, monster.getPosY());
					}
				}
				else if((angCoeff>1 || angCoeff<-1) && player.getPosY()>monster.getPosY()) {
					if(monster.getPosX() == player.getPosX() && monster.getPosY()+1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!");
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, 0, 1).getName() == "floor" ||
							getTileInFrontOfEntity(monster, 0, 1).getName() == "blood") {
						monster.setPosition(monster.getPosX(), monster.getPosY()+1);
					}
				}
				else if((angCoeff>1 || angCoeff<-1) && player.getPosY()<monster.getPosY()) {
					if(monster.getPosX() == player.getPosX() && monster.getPosY()-1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!");
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);

					}
					else if(getTileInFrontOfEntity(monster, 0, -1).getName() == "floor" ||
							getTileInFrontOfEntity(monster, 0, -1).getName() == "blood") {
						monster.setPosition(monster.getPosX(), monster.getPosY()-1);
					}
				}
			}
		}
	}

	private static boolean detectMonsterToFight(int dirX, int dirY) {
		if(currentRoom.getMonsterAt(player.getPosX()+dirX, player.getPosY()+dirY) != null) {
			
			Monster fight = currentRoom.getMonsterAt(player.getPosX()+dirX, player.getPosY()+dirY);
			fight.damage(player.getStrength() - fight.getDefence()/3);

			
			if(fight.getHealth() <= 0) { //Monster is dead
				currentRoom.killMonster(fight.getPosX(), fight.getPosY());
				int g = randomizer.nextInt(8) + 7;
				player.giveGold(g);
				player.getWeapon().reduceDurability();
				player.kill_count();
				messageBox.addMessage("You killed a monster and it dropped "+g+" gold!");
			}
			else { //Monster is still alive after attack
				messageBox.addMessage("You attacked the monster and he attacked you back!");
				player.getArmor().reduceDurability();
				player.getWeapon().reduceDurability();
				player.damage(fight.getStrength() - player.getDefence()/3);
			}
			
			return true;
		}
		return false;
	}
	
	private static boolean checkIfPlayerDied() {
		if(player.getHealth() <= 0) {
			messageBox.addMessage("You perished in the dungeon!");
			return true;
		}
		return false;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static Room getCurrentFloor() {
		return currentRoom;
	}
	
	public static Monster[] getMonsters() {
		return activeMonsters;
	}
	
	public static MessageBox getMessageBox() {
		return messageBox;
	}
	
	public static boolean isOnTitleScreen() {
		return onTitleScreen;
	}

	public static boolean isOnWinScreen() {
		return onWinScreen;
	}
}
