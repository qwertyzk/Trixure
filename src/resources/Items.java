package resources;

import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;

public class Items {

	public static final Item HP_POTION = new Item("hp_potion", "Health potion", "Restores 10 HP");
	public static final Item KEY = new Item("small_key", "Small key", "Can be used once to open a locked door");

	public static final Weapon SHORT_SWORD = new Weapon("short_sword", "Short Sword", 5);

	
	public static final Armor LIGHT_ARMOR = new Armor("light_armor", "Light Armor", 4);

}
