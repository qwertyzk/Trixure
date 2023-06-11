package logic;

import java.util.Random;

import javax.swing.Timer;

import gui.Renderer;
import logic.entities.Entity;
import logic.entities.Monster;
import logic.entities.Player;
import logic.items.Item;
import logic.level.Room;
import logic.level.MapObject;
import logic.level.Tower;
import logic.text.MessageBox;
import resources.Items;
import resources.Textures;

public class GameLogic {

	private static Timer timer;
	
	private static Random randomizer;
	
	private static Player player;
	private static Tower tower;
	private static Room currentRoom;
	private static Monster[] activeMonsters;
	private static MessageBox messageBox;
	
	private static boolean onTitleScreen;
	
	/**Call at beginning of main
	 * Initializes objects and read files*/
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
		player = new Player("player", 2, 8);
		activeMonsters = currentRoom.getMonsters();
		messageBox = new MessageBox();
		
		onTitleScreen = true;
	}

	
	/**Changes player position of given dirX and dirY
	 * @param dirX - Movement on X axis
	 * @param dirY - Movement on Y axis
	 */
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
	
	/**Gets tile in current floor in front of player
	 * @param dirX - +1 or -1 if player moves on x axis
	 * @param dirY - +1 or -1 if player moves on y axis
	 * @return the tile at pos (player.posX+dirX;player.posY+dirY)
	 */
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
			if( currentRoom.getTileAt(player.getPosX()+1, player.getPosY()).getName() == "wiesniak" ||
				currentRoom.getTileAt(player.getPosX()-1, player.getPosY()).getName() == "wiesniak" ||
				currentRoom.getTileAt(player.getPosX(), player.getPosY()+1).getName() == "wiesniak" ||
				currentRoom.getTileAt(player.getPosX(), player.getPosY()-1).getName() == "wiesniak")
				player.setShopOpen(!player.isShopOpen());
		}
	}
	
	/**Called when 'E' key is pressed<br>
	 * Tests if there's a collectible around the player and take it
	 */
	public static void handleInteration() {
		System.out.println("[Main][GameLogic]: Looking for an item to pickup");
		pickupItem(player.getPosX()+1, player.getPosY());
		pickupItem(player.getPosX()-1, player.getPosY());
		pickupItem(player.getPosX(), player.getPosY()+1);
		pickupItem(player.getPosX(), player.getPosY()-1);
	}
	
	private static void pickupItem(int itemPosX, int itemPosY) {
		switch(currentRoom.getTileAt(itemPosX, itemPosY).getName()) {
		case "red_potion":
			if(player.giveItem(Items.HP_POTION)) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a red potion!", 1);
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
			if(player.giveItem(Items.KEY)) {
				currentRoom.removeCollectible(itemPosX, itemPosY);
				messageBox.addMessage("You picked up a key!", 1);
			}
			else {
				messageBox.addMessage("Your inventory is full!", 1);
			}
			break;
		}
	}
	
	/**Called in Mouse#mouseReleased<br>
	 * Checks if inventory is open and uses an item
	 * @param mouseX - Mouse position x
	 * @param mouseY - Mouse position y
	 */
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
		}
		
		if(player.getHealth() <= 0) {
			init();
		}
	}
	
	private static void usePlayerItem(int index) {
		Item item = player.getInventoryItem(index);
		
		if(item == null) return;
		
		if(item == Items.HP_POTION) {
			player.heal(10);
			messageBox.addMessage("You drank a red potion and you recovered health!", 1);
		}
		else if(item == Items.KEY) {
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
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, 1, 0).getName() == "floor") {
						monster.setPosition(monster.getPosX()+1, monster.getPosY());
					}
				}
				else if(angCoeff>-1 && angCoeff<1 && player.getPosX()<monster.getPosX()) {
					if(monster.getPosX()-1 == player.getPosX() && monster.getPosY() == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, -1, 0).getName() == "floor") {
						monster.setPosition(monster.getPosX()-1, monster.getPosY());
					}
				}
				else if((angCoeff>1 || angCoeff<-1) && player.getPosY()>monster.getPosY()) {
					if(monster.getPosX() == player.getPosX() && monster.getPosY()+1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.damage(monster.getStrength() - player.getDefence()/3);
					}
					else if(getTileInFrontOfEntity(monster, 0, 1).getName() == "floor") {
						monster.setPosition(monster.getPosX(), monster.getPosY()+1);
					}
				}
				else if((angCoeff>1 || angCoeff<-1) && player.getPosY()<monster.getPosY()) {
					if(monster.getPosX() == player.getPosX() && monster.getPosY()-1 == player.getPosY()) {
						messageBox.addMessage("A monster attacked you!", 1);
						player.damage(monster.getStrength() - player.getDefence()/3);

					}
					else if(getTileInFrontOfEntity(monster, 0, -1).getName() == "floor") {
						monster.setPosition(monster.getPosX(), monster.getPosY()-1);
					}
				}
			}
		}
	}
	
	/**Checks if there is a monster around the player, then starts the fight
	 * @param dirX - Direction the player wants to move on the X axis
	 * @param dirY - Direction the player wants to move on the X axis
	 * @return True if the fight happened, false if there was no monster nearby
	 */
	private static boolean detectMonsterToFight(int dirX, int dirY) {
		if(currentRoom.getMonsterAt(player.getPosX()+dirX, player.getPosY()+dirY) != null) {
			
			Monster fight = currentRoom.getMonsterAt(player.getPosX()+dirX, player.getPosY()+dirY);
			fight.damage(player.getStrength() - fight.getDefence()/3);

			
			if(fight.getHealth() <= 0) { //Monster is dead
				currentRoom.killMonster(fight.getPosX(), fight.getPosY());
				int g = randomizer.nextInt(12)+8;
				player.giveGold(g);
				messageBox.addMessage("You killed a monster and it dropped "+g+" gold!", 1);
			}
			else { //Monster is still alive after attack
				messageBox.addMessage("You attacked the monster and he attacked you back!", 1);
				
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
