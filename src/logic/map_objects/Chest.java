package logic.map_objects;

import logic.items.Item;
import resources.Items;
import java.util.Random;

public class Chest extends MapObject
{
    private Item loot;
    private final int gold;
    public Chest(int posX, int posY, Random randomizer, int difficulty)
    {
        super("chest", posX, posY, false);
        switch (randomizer.nextInt(3)) {
            case 0 -> loot = Items.Weapons.randomWeapon(randomizer.nextInt(), difficulty);
            case 1 -> loot = Items.Armors.randomArmor(randomizer.nextInt(), difficulty);
            case 2 -> loot = Items.Consumable.randomItem(randomizer.nextInt());
        }
        gold = randomizer.nextInt(10) + 5;
    }

    public Item getLoot() {
        return loot;
    }

    public int getGold() {
        return gold;
    }
}
