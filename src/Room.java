import java.util.ArrayList;
import java.util.List;

public class Room
{
    private MapObject[][] room;
    private int startingPosX;
    private int startingPosY;
    private ArrayList<Mob> mobs;

    public Room(String[] ASCII_lines, int startingPosX, int startingPosY, Mob... mobs)
    {
        room = new MapObject[ASCII_lines.length][];

        for (int y = 0; y < ASCII_lines.length; y++) {
            room[y] = new MapObject[ASCII_lines[y].length()];

            for (int x = 0; x < ASCII_lines[y].length(); x++) {
                switch (ASCII_lines[y].charAt(x)) {
                    case '#':
                        room[y][x] = new MapObject("wall", x, y);
                        break;
                    case '.':
                        room[y][x] = new MapObject("empty", x, y);
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
                    case '!':
                        room[y][x] = new MapObject("key", x, y);
                        break;
                    case '/':
                        room[y][x] = new MapObject("locked_door", x, y);
                        break;
                    case 'T':
                        room[y][x] = new MapObject("chest", x, y);
                        break;
                }
            }
        }

        this.startingPosX = startingPosX;
        this.startingPosY = startingPosY;

        this.mobs = new ArrayList<Mob>();
        for (Mob m : mobs)
        {
            this.mobs.add(m);
        }
    }

    //removes collectible item from the room
    public boolean removeCollectible(int x, int y)
    {
        if(room[y][x].isCollectible())
        {
            room[y][x] = new MapObject("empty", x, y);
            return true;
        }
        return false;
    }

    public boolean openDoor(int x, int y)
    {
        if(room[y][x].getName() == "locked_door")
        {
            room[y][x] = new MapObject("empty", x, y);
            return true;
        }
        return false;
    }

    public boolean killMob(int x, int y)
    {
        for(int i=0; i<mobs.size();i++)
        {
            if(mobs.get(i).getPosX() == x && mobs.get(i).getPosY() == y)
            {
                //nie usuwa z planszy bo robi to już moveplayer
                mobs.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean isMobHere(int x, int y)
    {
        for(int i=0; i<mobs.size(); i++)
        {
            if(mobs.get(i).getPosX() == x && mobs.get(i).getPosY() == y)
                return true;
        }
        return false;
    }

    public Mob getMobAt(int x, int y)
    {
        for(Mob monster : mobs)
        {
            if(monster == null)
                return null;

            if(monster.getPosX() == x && monster.getPosY() == y)
                return monster;
        }
        return null;
    }

    // przekazujemy oryginal tablicy czy kopię?
    public ArrayList<Mob> getMobs()
    {
        return this.mobs;
    }

    public boolean removeTrap(int x, int y)
    {
        if(room[y][x].getName() == "trap")
        {
            room[y][x] = new MapObject("empty", x, y);
            return true;
        }
        return false;
    }

    public int getSizeX()
    {
        return room[0].length;
    }

    public int getSizeY()
    {
        return room.length;
    }

    public MapObject getObjectAt(int x, int y)
    {
        return room[y][x];
    }

    public int getStartPosX()
    {
        return startingPosX;
    }

    public int getStartPosY()
    {
        return startingPosY;
    }
}
