package logic.level;

import resources.Items;
import logic.items.Item;


import java.util.Random;

public class Shop extends MapObject {

    public static final int SHOP_SIZE = 3;
    int napiwek;
    private Item[] shopItems;


    private static Random randomizer;
    public Shop(String name, int posX, int posY, int napiwek)
    {

        super(name, posX, posY);
        randomizer = new Random();
        this.napiwek = Math.abs(randomizer.nextInt()) % napiwek;

        this.shopItems = new Item[SHOP_SIZE];

        for(int i=0;i<SHOP_SIZE;i++)
        {
            Item item = Items.Consumable.randomItem(randomizer.nextInt());
            item.setPrice(item.getPrice() + this.napiwek);
            this.shopItems[i] = item;

        }
    }

    public Item getShopItem(int index) {
        try {
            return shopItems[index];
        } catch(ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void removeItem(int index) {
        try {
            this.shopItems[index] = null;
        } catch(ArrayIndexOutOfBoundsException e) {
            return;
        }
    }



}
