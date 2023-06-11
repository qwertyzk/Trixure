package logic.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.entities.Monster;

import javax.management.monitor.MonitorSettingException;

public class Room {

	private MapObject[][] room;
	
	private int startPosX;
	private int startPosY;
	
	private List<Monster> monsters;

	public Room(String[] levelData, int startPosX, int startPosY, Random randomizer) {
		room = new MapObject[levelData.length][];
		this.monsters = new ArrayList<Monster>();

		this.startPosX = startPosX;
		this.startPosY = startPosY;
		
		for(int y=0;y<levelData.length;y++) {
			room[y] = new MapObject[levelData[y].length()];
			
			for(int x=0;x<levelData[y].length();x++) {
				switch(levelData[y].charAt(x)) {
				case '#':
					room[y][x] = new MapObject("wall", x, y);
					break;
				case '.':
					room[y][x] = new MapObject("floor", x, y);
					break;
				case '^':
					room[y][x] = new MapObject("stairs", x, y);
					break;
				case ',':
					room[y][x] = new MapObject("trap", x, y);
					break;
				case 'p':
					room[y][x] = new MapObject("red_potion", x, y);
					break;
				case 'G':
					room[y][x] = new MapObject("gold_bag", x, y);
					break;
				case '!':
					room[y][x] = new MapObject("key", x, y);
					break;
				case '/':
					room[y][x] = new MapObject("locked_door", x, y);
					break;
				case 'M':
					room[y][x] = new MapObject("floor", x, y);
					this.monsters.add(Monster.Type.ToMonster(Monster.Type.randomType(randomizer.nextInt()), x, y));
					break;
				case 'T':
					// tu będzie kiedyś chest.
					room[y][x] = new MapObject("red_potion", x, y);
					break;

				}
			}
		}


	}
	
	public int getSizeX() {
		return room[0].length;
	}
	
	public int getSizeY() {
		return room.length;
	}
	
	public MapObject getTileAt(int x, int y) {
		return room[y][x];
	}
	
	public int getStartPosX() {
		return startPosX;
	}
	
	public int getStartPosY() {
		return startPosY;
	}
	
	public Monster[] getMonsters() {
		Monster[] other = new Monster[monsters.size()];
		other = monsters.toArray(other);
		return other;
	}

	
	public Monster getMonsterAt(int x, int y) {
		for(Monster monster : monsters) {
			if(monster == null)
				return null;
			
			if(monster.getPosX() == x && monster.getPosY() == y)
				return monster;
		}
		return null;
	}

	
	/**Turns a trap tile into a room tile
	 * @param x - X coordinate of trap
	 * @param y - Y coordinate of trap
	 * @return True if a trap at (x;y) was disarmed, false if there was no trap there
	 */
	public boolean disarmTrap(int x, int y) {
		if(room[y][x].getName() == "trap") {
			room[y][x] = new MapObject("floor", x, y);
			return true;
		}
		return false;
	}
	
	/**Turns a collectible tile into a normal tile
	 * @param x - X coordinate of collectible
	 * @param y - Y coordinate of collectible
	 * @return True if a collectible at (x;y) was removed, false if there was no collectible there
	 */
	public boolean removeCollectible(int x, int y) {
		switch(room[y][x].getName()) {
		case "red_potion":
		case "key":
		case "gold_bag":
			room[y][x] = new MapObject("floor", x, y);
			return true;
		}
		return false;
	}
	
	/**Turns a door tile into a room tile
	 * @param x - X coordinate of door
	 * @param y - Y coordinate of door
	 * @return True if a door at (x;y) was opened, false if there was no door there
	 */
	public boolean openDoor(int x, int y) {
		if(room[y][x].getName() == "locked_door") {
			room[y][x] = new MapObject("floor", x, y);
			return true;
		}
		return false;
	}
	
	/**Deletes a monster
	 * @param x - X coordinate of monster
	 * @param y - Y coordinate of monster
	 * @return True if a monster at (x;y) was killed, false if there was no monster there
	 */
	public boolean killMonster(int x, int y) {
		for(int i=0;i<monsters.size();i++) {
			if(monsters.get(i).getPosX() == x && monsters.get(i).getPosY() == y) {
				monsters.remove(i);
				System.out.println("[GameLogic][Room]: Monster killed");
				return true;
			}
		}
		return false;
	}
	
	public boolean thereIsMonsterHere(int x, int y) {
		for(int i=0;i<monsters.size();i++) {
			if(monsters.get(i).getPosX() == x && monsters.get(i).getPosY() == y)
				return true;
		}
		return false;
	}
}