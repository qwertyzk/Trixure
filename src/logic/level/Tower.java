package logic.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import resources.Layouts;

public class Tower {

	private final int TOWER_SIZE = 10;
	private List<Room> rooms;
	private int roomAt;
	
	/**Creates a tower with the floors int the Levels class
	 * @param randomizer - Used to shuffle the tower
	 */
	public Tower(Random randomizer) {
		this.rooms = new ArrayList<Room>();
		this.roomAt = 0;
		this.rooms.add(Layouts.Layouts2.ToRoom(Layouts.Layouts2.layout0, randomizer));

		while(rooms.size() < TOWER_SIZE) {
			int choice = randomizer.nextInt(Layouts.NUMBER_OF_LAYOUTS);
			this.rooms.add(Layouts.Layouts2.ToRoom(Layouts.Layouts2.randomType(choice), randomizer));
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