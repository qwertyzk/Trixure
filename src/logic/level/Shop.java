package logic.level;

import resources.Items;
import logic.items.Item;


import java.util.Random;

public class Shop extends MapObject
{
    public static final int SHOP_SIZE = 3;
    int tip;
    private final Item[] shopItems;

    public Shop(int posX, int posY, Random randomizer, int tip, int difficulty)
    {
        super("villager", posX, posY, false);
        this.tip = Math.abs(randomizer.nextInt()) % tip;
        this.shopItems = new Item[SHOP_SIZE];

        Item item = null;
        for(int i = 0 ; i < SHOP_SIZE; i++)
        {
            switch (randomizer.nextInt(3)) {
                case 0 -> item = Items.Weapons.randomWeapon(randomizer.nextInt(), difficulty);
                case 1 -> item = Items.Armors.randomArmor(randomizer.nextInt(), difficulty);
                case 2 -> item = Items.Consumable.randomItem(randomizer.nextInt());
            }
            item.setPrice(item.getPrice() + this.tip);
            this.shopItems[i] = item;
        }
    }

    public Item getShopItem(int index)
    {
        try {
            return shopItems[index];
        } catch(ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void removeItem(int index)
    {
        try {
            this.shopItems[index] = null;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("\n[Shop]: ArrayIndexOutOfBoundsException\n");
        }
    }
}
