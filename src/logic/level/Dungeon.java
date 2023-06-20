package logic.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import resources.Layouts;

public class Dungeon
{
	private final static int EASY_LEVELS = 0;
	private final static int MEDIUM_LEVELS = 0;
	private final static int HARD_LEVELS = 0;
	private final List<Room> rooms;
	private int roomAt;

	public Dungeon(Random randomizer) {
		this.rooms = new ArrayList<>();
		this.roomAt = 0;
		this.rooms.add(Layouts.Layouts2.randomType(randomizer, 0));

		while(rooms.size() < EASY_LEVELS + 1) {
			this.rooms.add(Layouts.Layouts2.randomType(randomizer, 1));
		}

		while(rooms.size() < MEDIUM_LEVELS + EASY_LEVELS + 1) {
			this.rooms.add(Layouts.Layouts2.randomType(randomizer, 2));
		}

		while(rooms.size() < HARD_LEVELS + MEDIUM_LEVELS + EASY_LEVELS + 1) {
			this.rooms.add(Layouts.Layouts2.randomType(randomizer, 3));
		}

		this.rooms.add(Layouts.Layouts2.randomType(randomizer, 4));
	}
	public Room getRoom(int index) {
		return rooms.get(index);
	}

	public Room getNextRoom() {
		roomAt++;
		return rooms.get(roomAt);
	}
}
