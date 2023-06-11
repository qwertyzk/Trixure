package resources;

import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;
import logic.level.Room;

import java.util.Random;

public class Items {

	private static final int NUMBER_OF_CONSUMABLES = 3;
	private static final int NUMBER_OF_WEAPONS = 3;
	private static final int NUMBER_OF_ARMORS = 3;

	// do wyjebania jak zaklepię itemki
	public static final Item HP_POTION = new Item("hp_potion", "Health potion", "Restores 10 HP");
	public static final Item KEY = new Item("small_key", "Small key", "Can be used once to open a locked door");
	public static final Weapon SHORT_SWORD = new Weapon("short_sword", "Short Sword", 5);
	public static final Armor LIGHT_ARMOR = new Armor("light_armor", "Light Armor", 4);

	public enum Consumable
	{
		// to do
		;
		private String name;
		private String displayName;
		private String description;

		Consumable(String name, String displayName, String description)
		{
			this.name = name;
			this.displayName = displayName;
			this.description = description;
		}

		public static Item randomItem(int i)
		{
			Consumable[] consumables = values();
			Consumable temporary = consumables[Math.abs(i) % NUMBER_OF_CONSUMABLES];
			return new Item(temporary.name, temporary.displayName, temporary.description);

		}
	}

	public enum Weapons
	{
		// to do
		;
		private String name;
		private String displayName;
		private int damage;

		Weapons(String name, String displayName, int damage)
		{
			this.name = name;
			this.displayName = displayName;
			this.damage = damage;
		}

		public static Item randomWeapon(int i)
		{
			Weapons[] weapons = values();
			Weapons temporary = weapons[Math.abs(i) % NUMBER_OF_WEAPONS];
			return new Weapon(temporary.name, temporary.displayName, temporary.damage);

		}
	}

	public enum Armors
	{
		// to do
		;
		private String name;
		private String displayName;
		private int defence;

		Armors(String name, String displayName, int defence)
		{
			this.name = name;
			this.displayName = displayName;
			this.defence = defence;
		}

		public static Item randomWeapon(int i)
		{
			Armors[] armors = values();
			Armors temporary = armors[Math.abs(i) % NUMBER_OF_ARMORS];
			return new Weapon(temporary.name, temporary.displayName, temporary.defence);
		}
	}
}
