package logic;

import java.awt.*;
import java.util.Random;

import javax.swing.Timer;

import gui.Renderer;
import logic.entities.Entity;
import logic.entities.Monster;
import logic.entities.Player;
import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;
import logic.level.Room;
import logic.level.MapObject;
import logic.level.Tower;
import logic.text.MessageBox;
import resources.Items;
import resources.Textures;
import logic.level.Shop;


public class GameLogic {
	private static Timer timer;
	private static Random randomizer;
	private static Player player;
	private static Tower tower;
	private static Room currentRoom;
	private static Monster[] activeMonsters;
	private static MessageBox messageBox;
	private static boolean onTitleScreen;

	public static void startGame() {
		Textures.init();
		
		init();
		
		timer = new Timer(20, new GameLoop());
		timer.start();
	}
	
	private static void init() {
		randomizer = new Random();

		tower = new Tower(randomizer);
		currentRoom = tower.getRoom(0);
		player = new Player( 2, 8);
		activeMonsters = currentRoom.getMonsters();
		messageBox = new MessageBox();
		
		onTitleScreen = true;
	}

	public static void movePlayer(int dirX, int dirY) {
		onTitleScreen = false;
		
		if(player.isInventoryOpen() || player.isShopOpen())
			return;
		
		if(checkIfPlayerDied())
			return;
		
		if(detectMonsterToFight(dirX, dirY)) {
			checkIfPlayerDied();
			return;
		}
		
		switch(getTileInFrontOfEntity(player, dirX, dirY).getName()) {
		case "floor":
			player.setPosition(player.getPosX()+dirX, player.getPosY()+dirY);
			break;
		case "stairs":
			currentRoom = tower.getNextRoom();
			player.setPosition(currentRoom.getStartPosX(), currentRoom.getStartPosY());
			activeMonsters = currentRoom.getMonsters();
			messageBox.addMessage("You went into a new floor!", 1);
			player.addFloorCleared();
			break;
		case "trap":
			player.damage(randomizer.nextInt(2)+1);
			player.setPosition(player.getPosX()+dirX, player.getPosY()+dirY);
			currentRoom.disarmTrap(player.getPosX(), player.getPosY());
			messageBox.addMessage("You ran into a trap and you took damage!", 1);
			break;
		case "locked_door":
			messageBox.addMessage("You need a key to open this door...", 1);
			break;
		}
		moveMonsters();
		
		checkIfPlayerDied();
	}

	private static MapObject getTileInFrontOfEntity(Entity entity, int dirX, int dirY) {
		return currentRoom.getTileAt(entity.getPosX()+dirX, entity.getPosY()+dirY);
	}
	
	public static void openPlayerInventory() {
		if(player.getHealth() > 0)
			player.setInventoryOpen(!player.isInventoryOpen());
	}

	public static void openShop() {
		if(player.getHealth() > 0)
		{
			int[] dx = {1,-1,0,0};
			int[] dy = {0, 0, 1, -1};

			for(int i=0; i<4; ++i)
			{
				MapObject obiekt = currentRoom.getTileAt(player.getPosX()+dx[i], player.getPosY()+dy[i]);
				if(currentRoom.getTileAt(player.getPosX()+dx[i], player.getPosY()+dy[i]).getName() =="wiesniak" ) {
					player.setShopOpen(!player.isShopOpen());
					player.chooseShop((Shop) obiekt);
				}
			}


		}
	}

	public static void handleInteration() {
		System.out.println("[Main][GameLogic]: Looking for an item to pickup");
		pickupItem(player.getPosX()+1, player.getPosY());
		pickupItem(player.getPosX()-1, player.getPosY());
		pickupItem(player.getPosX(), player.getPosY()+1);
		pickupItem(player.getPosX(), player.getPosY()-1);
	}
	
	private static void pickupItem(int itemPosX, int itemPosY) {
		switch(currentRoom.getTileAt(itemPosX, itemPosY).getName()) {
		case "hp_potion":
			if(player.giveItem(Items.Consumable.HP_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a health potion!", 1);
			}
			else {
				messageBox.addMessage("Your inventory is full!", 1);
			}
			break;
		case "max_potion":
			if(player.giveItem(Items.Consumable.MAX_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a max potion!", 1);
			}
			else {
				messageBox.addMessage("Your inventory is full!", 1);
			}
			break;
		case "strength_potion":
			if(player.giveItem(Items.Consumable.STRENGTH_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a strength potion!", 1);
			}
			else {
				messageBox.addMessage("Your inventory is full!", 1);
			}
			break;
		case "defence_potion":
			if(player.giveItem(Items.Consumable.DEFENCE_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a defence potion!", 1);
			}
			else {
				messageBox.addMessage("Your inventory is full!", 1);
			}
			break;
		case "mysterious_potion":
			if(player.giveItem(Items.Consumable.MYSTERIOUS_POTION.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a mysterious potion!", 1);
			}
			else {
				messageBox.addMessage("Your inventory is full!", 1);
			}
			break;
		case "gold_bag":
			int g = randomizer.nextInt(5)+3;
			player.giveGold(g);
			currentRoom.removeCollectible(itemPosX, itemPosY);
			messageBox.addMessage("You picked up a bag containing "+g+" gold!", 1);
			break;
		case "key":
			if(player.giveItem(Items.Consumable.KEY.toItem())) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a key!", 1);
			}
			else {
				messageBox.addMessage("Your inventory is full!", 1);
			}
			break;
		case "chest":
			switch(randomizer.nextInt(5)) {
				case 0: // nothing
					messageBox.addMessage("You opened the chest, but it was empty...", 1);
					currentRoom.removeCollectible(itemPosX, itemPosY);
					break;
				case 1: // new weapon
					if(player.giveItem(Items.Weapons.randomWeapon(randomizer.nextInt()))){
						messageBox.addMessage("You opened the chest and found a new weapon!", 1);
						currentRoom.removeCollectible(itemPosX, itemPosY);
					}
					else
						messageBox.addMessage("Your inventory is full!", 1);
					break;
				case 2: // new armor
					if(player.giveItem(Items.Armors.randomArmor(randomizer.nextInt()))){
						messageBox.addMessage("You opened the chest and found a new armor!", 1);
						currentRoom.removeCollectible(itemPosX, itemPosY);
					}
					else
						messageBox.addMessage("Your inventory is full!", 1);
					break;
				case 3: // new consumable
					if(player.giveItem(Items.Consumable.randomItem(randomizer.nextInt()))) {
						messageBox.addMessage("You opened the chest and found something!", 1);
						currentRoom.removeCollectible(itemPosX, itemPosY);
					}
					else
						messageBox.addMessage("Your inventory is full!", 1);
					break;
				case 4: // gold
					int gold = randomizer.nextInt(9)+6;
					player.giveGold(gold);
					currentRoom.removeCollectible(itemPosX, itemPosY);
					messageBox.addMessage("You opened the chest and found "+gold+" gold!", 1);
					break;
			}
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
			if(Renderer.shopSlot1.contains(mouseX, mouseY)) {
				buyItem(player.getCurrentShop(), 0);
			}
			else if(Renderer.shopSlot2.contains(mouseX, mouseY)) {
				buyItem(player.getCurrentShop(), 1);
			}
			else if(Renderer.shopSlot3.contains(mouseX, mouseY)) {
				buyItem(player.getCurrentShop(), 2);
			}
		
		if(player.getHealth() <= 0) {
			init();
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
				player.takeGold( item.getPrice());
				sklep.removeItem(i);
				messageBox.addMessage("You bought new item!", 1);
			}
			else
			{
				messageBox.addMessage("Your inventory is full!", 1);
			}
		}
		else {
			messageBox.addMessage("You don't have enough money.", 1);
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
				messageBox.addMessage("You drank a red potion and you recovered health!", 1);
			}
			else if(item.getName() == "max_potion") {
				player.increaseHealth(5);
				messageBox.addMessage("You drank a max potion and increased max health!", 1);
			}
			else if(item.getName() == "strength_potion") {
				player.increaseStrength(3);
				messageBox.addMessage("You drank a strength potion and increased strength!", 1);
			}
			else if(item.getName() == "defence_potion") {
				player.increaseDefence(3);
				messageBox.addMessage("You drank a strength potion and increased strength!", 1);
			}
			else if(item.getName() == "mysterious_potion") {
				switch (randomizer.nextInt(6)) {
					case 0:
						player.heal(12);
						messageBox.addMessage("You drank a potion and you recovered health!", 1);
						break;
					case 1:
						player.increaseHealth(7);
						messageBox.addMessage("You drank a potion and increased max health!", 1);
						break;
					case 2:
						player.increaseStrength(4);
						messageBox.addMessage("You drank a potion and increased strength!", 1);
						break;
					case 3:
						player.increaseDefence(4);
						messageBox.addMessage("You drank a potion and increased strength!", 1);
						break;
					case 4:
					case 5:
						player.damage(5);
						break;
				}
			}
			else if(item.getName() == "small_key") {
				if(currentRoom.getTileAt(player.getPosX()+1, player.getPosY()).getName() == "locked_door") {
					currentRoom.openDoor(player.getPosX()+1, player.getPosY());
				}
				else if(currentRoom.getTileAt(player.getPosX()-1, player.getPosY()).getName() == "locked_door") {
					currentRoom.openDoor(player.getPosX()-1, player.getPosY());
				}
				else if(currentRoom.getTileAt(player.getPosX(), player.getPosY()+1).getName() == "locked_door") {
					currentRoom.openDoor(player.getPosX(), player.getPosY()+1);
				}
				else if(currentRoom.getTileAt(player.getPosX(), player.getPosY()-1).getName() == "locked_door") {
					currentRoom.openDoor(player.getPosX(), player.getPosY()-1);
				}
				else {
					messageBox.addMessage("You can't use this item this way...", 1);
					return;
				}
				messageBox.addMessage("You used the key to open the door!", 1);
			}
		}
		player.removeItem(index);
	}
	
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
						messageBox.addMessage("A monster attacked you!", 1);
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
						break;
					}	
					if(getTileInFrontOfEntity(monster, 1, 0).getName() == "floor") {
						monster.setPosition(monster.getPosX()+1, monster.getPosY());
						break;
					}
				case 1:
					if(currentRoom.thereIsMonsterHere(monster.getPosX()-1, monster.getPosY())) {
						return;
					}
					else if(monster.getPosX()-1 == player.getPosX() && monster.getPosY() == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
						break;
					}	
					if(getTileInFrontOfEntity(monster, -1, 0).getName() == "floor") {
						monster.setPosition(monster.getPosX()-1, monster.getPosY());
						break;
					}
				case 2:
					if(currentRoom.thereIsMonsterHere(monster.getPosX(), monster.getPosY()+1)) {
						return;
					}
					else if(monster.getPosX() == player.getPosX() && monster.getPosY()+1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
						break;
					}	
					if(getTileInFrontOfEntity(monster, 0, 1).getName() == "floor") {
						monster.setPosition(monster.getPosX(), monster.getPosY()+1);
						break;
					}
				case 3:
					if(currentRoom.thereIsMonsterHere(monster.getPosX(), monster.getPosY()-1)) {
						return;
					}
					else if(monster.getPosX() == player.getPosX() && monster.getPosY()-1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
						break;
					}	
					if(getTileInFrontOfEntity(monster, 0, -1).getName() == "floor") {
						monster.setPosition(monster.getPosX(), monster.getPosY()-1);
						break;
					}
				}
			} else {
				float angCoeff = -((float)player.getPosY()-(float)monster.getPosY())/((float)player.getPosX()-(float)monster.getPosX());
				
				if(angCoeff>-1 && angCoeff<1 && player.getPosX()>monster.getPosX()) {
					if(monster.getPosX()+1 == player.getPosX() && monster.getPosY() == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, 1, 0).getName() == "floor") {
						monster.setPosition(monster.getPosX()+1, monster.getPosY());
					}
				}
				else if(angCoeff>-1 && angCoeff<1 && player.getPosX()<monster.getPosX()) {
					if(monster.getPosX()-1 == player.getPosX() && monster.getPosY() == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, -1, 0).getName() == "floor") {
						monster.setPosition(monster.getPosX()-1, monster.getPosY());
					}
				}
				else if((angCoeff>1 || angCoeff<-1) && player.getPosY()>monster.getPosY()) {
					if(monster.getPosX() == player.getPosX() && monster.getPosY()+1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, 0, 1).getName() == "floor") {
						monster.setPosition(monster.getPosX(), monster.getPosY()+1);
					}
				}
				else if((angCoeff>1 || angCoeff<-1) && player.getPosY()<monster.getPosY()) {
					if(monster.getPosX() == player.getPosX() && monster.getPosY()-1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.getArmor().reduceDurability();
						player.damage(monster.getStrength() - player.getDefence()/3);

					}
					else if(getTileInFrontOfEntity(monster, 0, -1).getName() == "floor") {
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
				int g = randomizer.nextInt(12)+8;
				player.giveGold(g);
				player.getWeapon().reduceDurability();
				messageBox.addMessage("You killed a monster and it dropped "+g+" gold!", 1);
			}
			else { //Monster is still alive after attack
				messageBox.addMessage("You attacked the monster and he attacked you back!", 1);
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
			messageBox.addMessage("You perished in the dungeon!", 6);
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
}
