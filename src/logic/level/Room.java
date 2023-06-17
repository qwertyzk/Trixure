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

	public Room(String[] levelData, int startPosX, int startPosY, Random randomizer, int difficulty) {
		room = new MapObject[levelData.length][];
		this.monsters = new ArrayList<>();

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
					room[y][x] = new MapObject("hp_potion_tile", x, y);
					break;
				case 'm':
					room[y][x] = new MapObject("max_potion_tile", x, y);
					break;
				case 's':
					room[y][x] = new MapObject("str_potion_tile", x, y);
					break;
				case 'd':
					room[y][x] = new MapObject("def_potion_tile", x, y);
					break;
				case '?':
					room[y][x] = new MapObject("myst_potion_tile", x, y);
					break;
				case 'G':
					room[y][x] = new MapObject("gold_bag", x, y);
					break;
				case '!':
					room[y][x] = new MapObject("key_tile", x, y);
					break;
				case '/':
					room[y][x] = new MapObject("door", x, y);
					break;
				case 'M':
					room[y][x] = new MapObject("floor", x, y);
					this.monsters.add(Monster.Type.randomType(randomizer.nextInt(), x, y, difficulty));
					break;
				case 'T':
					room[y][x] = new Chest("chest", x, y, randomizer);
					break;
				case 'W':
					room[y][x] = new Shop("wiesniak", x, y, 10); // tu max napiwek
					break;
				case 'K':
					room[y][x] = new MapObject("princess", x, y);
					break;
				case 'D':
					room[y][x] = new MapObject("dragon", x, y);
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

	public boolean disarmTrap(int x, int y) {
		if(room[y][x].getName() == "trap") {
			room[y][x] = new MapObject("floor", x, y);
			return true;
		}
		return false;
	}

	public boolean removeCollectible(int x, int y) {
		switch(room[y][x].getName()) {
		case "hp_potion_tile":
		case "max_potion_tile":
		case "myst_potion_tile":
		case "def_potion_tile":
		case "str_potion_tile":
		case "key_tile":
		case "gold_bag":
			room[y][x] = new MapObject("floor", x, y);
			return true;
		case "chest":
			room[y][x] = new MapObject("open_chest", x, y);
			return true;
		}
		return false;
	}

	public boolean openDoor(int x, int y) {
		if(room[y][x].getName() == "door") {
			room[y][x] = new MapObject("floor", x, y);
			return true;
		}
		return false;
	}

	public boolean killMonster(int x, int y) {
		for(int i=0;i<monsters.size();i++) {
			if(monsters.get(i).getPosX() == x && monsters.get(i).getPosY() == y) {
				monsters.remove(i);
				room[y][x] = new MapObject("blood", x, y);
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