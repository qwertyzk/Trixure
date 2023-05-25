import java.util.ArrayList;
import java.util.List;

public class Floor
{
    int floor_number;
    List<List<Room>> map = new ArrayList<>();
    Room current_room;
    int boundaries;
    int common_room_count;
    int special_room_count;

    Room get_from_map(int x, int y)
    {
        return map.get(x).get(y);
    }

    public Floor(int number_floor)
    {
        floor_number = number_floor;
        boundaries = floor_number + 5;
        common_room_count = (floor_number * 4) + 3;
        special_room_count = 3 + floor_number;
        // generowanie mapy
        current_room = get_from_map(0, 0);
    }  
}
