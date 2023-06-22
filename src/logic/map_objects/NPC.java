package logic.map_objects;

import java.util.Random;

public class NPC extends MapObject
{
    int tip;

    public NPC(String name, int posX, int posY, int max_tip, Random randomizer)
    {
        super(name, posX, posY, false);
        this.tip = Math.abs(randomizer.nextInt()) % max_tip;
    }

    public int getTip()
    {
        return tip;
    }
}
