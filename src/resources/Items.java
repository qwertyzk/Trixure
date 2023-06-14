package resources;

import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;
import logic.level.Room;

import java.util.Random;

public class Items {

	private static final int NUMBER_OF_CONSUMABLES = 2;
	private static final int NUMBER_OF_WEAPONS = 3;
	private static final int NUMBER_OF_ARMORS = 3;

	public enum Consumable
	{
		HP_POTION("hp_potion", "Health potion", "Restores 10 HP"),
		KEY("small_key", "Small key", "Can be used once to open a locked door");

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

		public Item toItem()
		{
			return new Item(name, displayName, description);
		}
	}

	public enum Weapons
	{
		SHORT_SWORD("short_sword", "Short Sword", 5, 10),
		LONG_SWORD("long_sword", "Long Sword", 7, 20),
		SCYTHE("scythe", "Scythe", 10, 10);
		private String name;
		private String displayName;
		private int damage;
		private int durability;

		Weapons(String name, String displayName, int damage, int durability)
		{
			this.name = name;
			this.displayName = displayName;
			this.damage = damage;
			this.durability = durability;
		}

		public static Item randomWeapon(int i)
		{
			Weapons[] weapons = values();
			Weapons temporary = weapons[Math.abs(i) % NUMBER_OF_WEAPONS];
			return new Weapon(temporary.name, temporary.displayName, temporary.damage, temporary.durability);
		}

		public Weapon toWeapon()
		{
			return new Weapon(name, displayName, damage, durability);
		}
	}

	public enum Armors
	{
		LIGHT_ARMOR("light_armor", "Light Armor", 4, 10),
		HEAVY_ARMOR("heavy_armor", "Heavy Armor", 8, 20),
		THORNMAIL("thornmail", "Thornmail", 12, 10);
		private String name;
		private String displayName;
		private int defence;
		private int durability;

		Armors(String name, String displayName, int defence, int durability)
		{
			this.name = name;
			this.displayName = displayName;
			this.defence = defence;
			this.durability = durability;
		}

		public static Item randomArmor(int i)
		{
			Armors[] armors = values();
			Armors temporary = armors[Math.abs(i) % NUMBER_OF_ARMORS];
			return new Armor(temporary.name, temporary.displayName, temporary.defence, temporary.durability);
		}

		public Armor toArmor()
		{
			return new Armor(name, displayName, defence, durability);
		}
	}
}
