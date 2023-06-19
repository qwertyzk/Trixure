package logic.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import logic.entities.Monster;

public class Room
{
	private final MapObject[][] room;
	private final int startPosX;
	private final int startPosY;
	private final List<Monster> monsters;

	public Room(String[] levelData, int startPosX, int startPosY, Random randomizer, int difficulty) {
		room = new MapObject[levelData.length][];
		this.monsters = new ArrayList<>();

		this.startPosX = startPosX;
		this.startPosY = startPosY;
		
		for(int y = 0; y < levelData.length; y++) {
			room[y] = new MapObject[levelData[y].length()];

			for(int x = 0; x < levelData[y].length(); x++) {
				switch (levelData[y].charAt(x)) {
					case '#' -> room[y][x] = new MapObject("wall", x, y, false);
					case '.' -> room[y][x] = new MapObject("floor", x, y, false);
					case '^' -> room[y][x] = new MapObject("stairs", x, y, false);
					case ',' -> room[y][x] = new MapObject("trap", x, y, false);
					case 'p' -> room[y][x] = new MapObject("hp_potion_tile", x, y, true);
					case 'm' -> room[y][x] = new MapObject("max_potion_tile", x, y, true);
					case 's' -> room[y][x] = new MapObject("str_potion_tile", x, y, true);
					case 'd' -> room[y][x] = new MapObject("def_potion_tile", x, y, true);
					case '?' -> room[y][x] = new MapObject("myst_potion_tile", x, y, true);
					case 'G' -> room[y][x] = new MapObject("gold_bag", x, y, true);
					case '!' -> room[y][x] = new MapObject("key_tile", x, y, true);
					case '/' -> room[y][x] = new MapObject("door", x, y, false);
					case 'K' -> room[y][x] = new MapObject("princess", x, y, false);
					case 'D' -> room[y][x] = new MapObject("dragon", x, y, false);
					case 'T' -> room[y][x] = new Chest(x, y, randomizer, difficulty);
					case 'W' -> room[y][x] = new Shop(x, y,randomizer, 10, difficulty);
					case 'M' -> {
						room[y][x] = new MapObject("floor", x, y, false);
						this.monsters.add(Monster.Type.randomType(randomizer.nextInt(), x, y, difficulty));
					}
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
	
	public Monster[] getMonsters()
	{
		Monster[] other = new Monster[monsters.size()];
		other = monsters.toArray(other);
		return other;
	}

	public Monster getMonsterAt(int x, int y)
	{
		for(Monster monster : monsters)
		{
			if(monster == null)
				return null;
			
			if(monster.getPosX() == x && monster.getPosY() == y)
				return monster;
		}
		return null;
	}

	public void removeCollectible(int x, int y)
	{
		switch (room[y][x].getName())
		{
			case "hp_potion_tile", "max_potion_tile", "myst_potion_tile", "def_potion_tile", "str_potion_tile",
					"key_tile", "gold_bag" -> room[y][x] = new MapObject("floor", x, y, false);
			case "chest" -> room[y][x] = new MapObject("open_chest", x, y, false);
		}
	}

	public void disarmTrap(int x, int y)
	{
		if (room[y][x].getName().equals("trap"))
		{
			room[y][x] = new MapObject("floor", x, y, false);
		}
	}

	public void openDoor(int x, int y)
	{
		if(room[y][x].getName().equals("door"))
		{
			room[y][x] = new MapObject("floor", x, y, false);
		}
	}

	public void killMonster(int x, int y)
	{
		for(int i = 0; i < monsters.size(); i++)
		{
			if(monsters.get(i).getPosX() == x && monsters.get(i).getPosY() == y)
			{
				monsters.remove(i);
				room[y][x] = new MapObject("blood", x, y, false);
				System.out.println("[GameLogic][Room]: Monster killed");
				return;
			}
		}
	}

	public boolean thereIsMonsterHere(int x, int y)
	{
		for (Monster monster : monsters)
		{
			if (monster.getPosX() == x && monster.getPosY() == y)
				return true;
		}
		return false;
	}
}
