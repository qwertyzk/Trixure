package logic.level;

import logic.items.Item;
import resources.Items;

import java.util.Random;

public class Chest extends MapObject{
    private Item loot;
    private final int gold;
    public Chest(String name, int posX, int posY, Random randomizer)
    {
        super(name, posX, posY);

        switch(randomizer.nextInt(3)) {
            case 0: // new weapon
                loot = Items.Weapons.randomWeapon(randomizer.nextInt());
                break;
            case 1: // new armor
                loot = Items.Armors.randomArmor(randomizer.nextInt());
                break;
            case 2: // new consumable
                loot = Items.Consumable.randomItem(randomizer.nextInt());
                break;
        }
        gold = randomizer.nextInt(10)+5;
    }

    public Item getLoot() {
        return loot;
    }

    public int getGold() {
        return gold;
    }
}
