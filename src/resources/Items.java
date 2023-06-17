package resources;

import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;
import logic.level.Room;

import java.util.Random;

public class Items {

	private static final int NUMBER_OF_CONSUMABLES = 6;
	private static final int NUMBER_OF_WEAPONS = 5;
	private static final int NUMBER_OF_ARMORS = 5;

	public enum Consumable
	{
		HP_POTION("hp_potion", "Health potion", "Restores 10 HP", 10),
		MAX_POTION("max_potion", "Max Potion", "Increases max HP by 5", 10),
		STRENGTH_POTION("str_potion", "Strength Potion", "Increases Strength by 3", 10 ),
		DEFENCE_POTION("def_potion", "Defence Potion", "Increases Defence by 3", 10),
		MYSTERIOUS_POTION("myst_potion", "Mysterious Potion", "The effect remains unknown...", 10),
		KEY("key", "Key", "Can be used once to open a locked door", 10);

		private String name;
		private String displayName;
		private String description;
		private int price;

		Consumable(String name, String displayName, String description, int price)
		{
			this.name = name;
			this.displayName = displayName;
			this.description = description;
			this.price = price;
		}

		public static Item randomItem(int i)
		{
			Consumable[] consumables = values();
			Consumable temporary = consumables[Math.abs(i) % NUMBER_OF_CONSUMABLES];
			return new Item(temporary.name, temporary.displayName, temporary.description, temporary.price);
		}

		public Item toItem()
		{
			return new Item(name, displayName, description,price);
		}
	}

	public enum Weapons
	{
		SHORT_SWORD("short_sword", "Short Sword", 5, 10,3),
		LONG_SWORD("long_sword", "Long Sword", 7, 20,3),
		SCYTHE("scythe", "Scythe", 10, 10,3),
		CHAKRAM("chakram", "Chakram", 8, 12,3),
		AXE("axe", "Axe", 12, 8,3);
		private String name;
		private String displayName;
		private int damage;
		private int durability;
		private int price;

		Weapons(String name, String displayName, int damage, int durability, int price)
		{
			this.name = name;
			this.displayName = displayName;
			this.damage = damage;
			this.durability = durability;
			this.price = price;
		}

		public static Item randomWeapon(int i)
		{
			Weapons[] weapons = values();
			Weapons temporary = weapons[Math.abs(i) % NUMBER_OF_WEAPONS];
			return new Weapon(temporary.name, temporary.displayName, temporary.damage, temporary.durability, temporary.price);
		}

		public Weapon toWeapon()
		{
			return new Weapon(name, displayName, damage, durability,price);
		}
	}

	public enum Armors
	{
		LIGHT_ARMOR("light_armor", "Light Armor", 4, 10, 3),
		HEAVY_ARMOR("heavy_armor", "Heavy Armor", 7, 20,3),
		THORNMAIL("thornmail", "Thornmail", 10, 10,3),
		PLATED_ARMOR("plated_armor", "Plated Armor", 8, 15,3),
		GLASS_ARMOR("glass_armor", "Glass Armor", 12, 7,3);
		private String name;
		private String displayName;
		private int defence;
		private int durability;
		private int price;

		Armors(String name, String displayName, int defence, int durability, int price)
		{
			this.name = name;
			this.displayName = displayName;
			this.defence = defence;
			this.durability = durability;
			this.price = price;
		}

		public static Item randomArmor(int i)
		{
			Armors[] armors = values();
			Armors temporary = armors[Math.abs(i) % NUMBER_OF_ARMORS];
			return new Armor(temporary.name, temporary.displayName, temporary.defence, temporary.durability, temporary.price);
		}

		public Armor toArmor()
		{
			return new Armor(name, displayName, defence, durability,price);
		}
	}
}
