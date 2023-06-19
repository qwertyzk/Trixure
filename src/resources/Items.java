package resources;

import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;

public class Items
{
	private static final int NUMBER_OF_CONSUMABLES = 6;
	private static final int NUMBER_OF_WEAPONS_AT_TIME = 3;
	private static final int NUMBER_OF_ARMORS_AT_TIME = 3;

	public enum Consumable
	{
		HP_POTION("hp_potion", "Health potion", "Restores 10 HP", 100),
		MAX_POTION("max_potion", "Max Potion", "Increases max HP by 5", 200),
		STRENGTH_POTION("str_potion", "Strength Potion", "Increases Strength by 3", 150),
		DEFENCE_POTION("def_potion", "Defence Potion", "Increases Defence by 3", 150),
		MYSTERIOUS_POTION("myst_potion", "Mysterious Potion", "The effect remains unknown...", 100),
		KEY("key", "Key", "Can be used to open a locked door", 150);

		private final String name;
		private final String displayName;
		private final String description;
		private final int price;

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
			return new Item(name, displayName, description, price);
		}
	}

	public enum Weapons
	{
		SHORT_SWORD("short_sword", "Short Sword", 3, 10,50),
		DAGGER("dagger", "Dagger", 8, 12,100),
		LONG_SWORD("long_sword", "Long Sword", 12, 15,150),
		AXE("axe", "Axe", 15, 20,200),
		DIAMOND_SWORD("diamond_sword", "Diamond Sword", 20, 30,250);

		private final String name;
		private final String displayName;
		private final int damage;
		private final int durability;
		private final int price;

		Weapons(String name, String displayName, int damage, int durability, int price)
		{
			this.name = name;
			this.displayName = displayName;
			this.damage = damage;
			this.durability = durability;
			this.price = price;
		}

		public static Item randomWeapon(int i, int difficulty)
		{
			Weapons[] weapons = values();
			Weapons temporary = weapons[Math.abs(i) % NUMBER_OF_WEAPONS_AT_TIME + difficulty - 1];
			return new Weapon(temporary.name, temporary.displayName, temporary.damage, temporary.durability, temporary.price);
		}

		public Weapon toWeapon()
		{
			return new Weapon(name, displayName, damage, durability, price);
		}
	}

	public enum Armors
	{
		LIGHT_ARMOR("light_armor", "Light Armor", 3, 10, 50),
		GLASS_ARMOR("glass_armor", "Glass Armor", 8, 12,100),
		HEAVY_ARMOR("heavy_armor", "Heavy Armor", 12, 15,150),
		PLATED_ARMOR("plated_armor", "Plated Armor", 15, 20,200),
		DIAMOND_ARMOR("diamond_armor", "Diamond Armor", 20, 30,250);

		private final String name;
		private final String displayName;
		private final int defence;
		private final int durability;
		private final int price;

		Armors(String name, String displayName, int defence, int durability, int price)
		{
			this.name = name;
			this.displayName = displayName;
			this.defence = defence;
			this.durability = durability;
			this.price = price;
		}

		public static Item randomArmor(int i, int difficulty)
		{
			Armors[] armors = values();
			Armors temporary = armors[Math.abs(i) % NUMBER_OF_ARMORS_AT_TIME + difficulty - 1];
			return new Armor(temporary.name, temporary.displayName, temporary.defence, temporary.durability, temporary.price);
		}

		public Armor toArmor()
		{
			return new Armor(name, displayName, defence, durability, price);
		}
	}
}
