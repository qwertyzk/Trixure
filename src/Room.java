import java.util.List;

public class Room
{
    private MapObject[][] room;
    private int startingPosX;
    private int startingPosY;
    private List<Mob> mobs;

    public Room(String[] ASCII_lines, int startingPosX, int startingPosY, Mob... mobs)
    {
        // płatne DLC a konstruktorem
    }


    //removes collectible item from tile
    public boolean removeCollectible(int x, int y)
    {
        return true;
    }

    public boolean killMob(int x, int y)
    {
        return true;
    }

    public boolean isMobHere(int x, int y)
    {
        return true;
    }
}
