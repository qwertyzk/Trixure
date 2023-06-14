package logic.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import resources.Layouts;

public class Tower {

	private final int TOWER_SIZE = 10;
	private List<Room> rooms;
	private int roomAt;

	public Tower(Random randomizer) {
		this.rooms = new ArrayList<>();
		this.roomAt = 0;
		this.rooms.add(Layouts.Layouts2.layout0.ToRoom(randomizer));

		while(rooms.size() < TOWER_SIZE) {
			this.rooms.add(Layouts.Layouts2.randomType(randomizer));
		}
	}
	public Room getRoom(int index) {
		return rooms.get(index);
	}

	public Room getNextRoom() {
		roomAt++;
		
		if(roomAt == rooms.size())
			roomAt--;
		
		return rooms.get(roomAt);
	}
}