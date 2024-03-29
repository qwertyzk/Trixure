package logic.map_objects;

import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;
import resources.Items;

public class Player extends Entity
{
	public static final int INVENTORY_SIZE = 3;
	private final Item[] inventory;
	private boolean inventoryOpen;
	private boolean shopOpen;
	private boolean blacksmithOpen;
	private Shop currentShop;
	private Weapon weaponEquipped;
	private Armor armorEquipped;
	private int gold;
	private int floors;
	private int monsters_killed;
	private NPC currentBlacksmith;

	public Player(int posX, int posY)
	{
		super("player", posX, posY, 20);
		this.inventory = new Item[INVENTORY_SIZE];
		this.inventoryOpen = false;
		this.shopOpen = false;
		this.weaponEquipped = Items.Weapons.SHORT_SWORD.toWeapon();
		this.armorEquipped = Items.Armors.LIGHT_ARMOR.toArmor();
		this.strength = 3;
		this.defence = 3;
		this.gold = 0;
		this.floors = 0;
		this.currentShop = null;
	}

	public boolean giveItem(Item item)
	{
		for(int i = 0; i < INVENTORY_SIZE; i++)
		{
			if(this.inventory[i] == null)
			{
				this.inventory[i] = item;
				return true;
			}
		}
		return false;
	}

	public int getRepairPriceArmor()
	{
		int durabilityDiff = armorEquipped.getTotalDurability() - armorEquipped.getDurability();
		if (durabilityDiff > 0)
			return 10 * durabilityDiff + currentBlacksmith.getTip();

		return 0;
	}

	public int getRepairPriceWeapon()
	{
		int durabilityDiff = weaponEquipped.getTotalDurability() - weaponEquipped.getDurability();
		if (durabilityDiff > 0)
			return 10 * durabilityDiff + currentBlacksmith.getTip();

		return 0;
	}

	public void removeItem(int index)
	{
		try {
			this.inventory[index] = null;
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("\n[Player]: ArrayIndexOutOfBoundsException\n");
		}
	}
	
	public Item getInventoryItem(int index)
	{
		try {
			return inventory[index];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void setInventoryOpen(boolean inventoryOpen)
	{
		this.inventoryOpen = inventoryOpen;
	}
	
	public boolean isInventoryOpen()
	{
		return inventoryOpen;
	}

	public void setShopOpen(boolean shopOpen)
	{
		this.shopOpen = shopOpen;
	}

	public boolean isBlacksmithOpen()
	{
		return blacksmithOpen;
	}

	public void setBlacksmithOpen(boolean blacksmithOpen)
	{
		this.blacksmithOpen = blacksmithOpen;
	}

	public Shop getCurrentShop()
	{
		return currentShop;
	}

	public void chooseShop(Shop shop)
	{
		this.currentShop = shop;
	}

	public void chooseBlacksmith(NPC blacksmith)
	{
		this.currentBlacksmith = blacksmith;
	}

	public boolean isShopOpen()
	{
		return shopOpen;
	}
	
	public void giveGold(int amount)
	{
		this.gold += amount;
	}
	
	public int getGold()
	{
		return gold;
	}

	public void addFloorCleared()
	{
		this.floors++;
	}
	
	public int getFloorsCleared()
	{
		return floors;
	}
	
	public void increaseHealth(int amount)
	{
		this.maxHealth += amount;
		this.health += amount;
	}

	public void increaseStrength(int amount)
	{
		this.strength += amount;
	}

	public void increaseDefence(int amount)
	{
		this.defence += amount;
	}

	public int getStrength()
	{
		int str = super.getStrength();
		str += this.weaponEquipped.getDamage();
		return str;
	}

	public int getDefence()
	{
		int def = super.getDefence();
		def += this.armorEquipped.getDefence();
		return def;
	}
	
	public void equipWeapon(Weapon weapon)
	{
		this.weaponEquipped = new Weapon(weapon.getName(), weapon.getDisplayName(), weapon.getDamage(), weapon.getDurability(), weapon.getPrice());
	}
	
	public void equipArmor(Armor armor)
	{
		this.armorEquipped = new Armor(armor.getName(), armor.getDisplayName(), armor.getDefence(), armor.getDurability(), armor.getPrice());
	}

	public Weapon getWeapon() {
		return this.weaponEquipped;
	}

	public Armor getArmor() {
		return this.armorEquipped;
	}

	public void kill_count()
	{
		this.monsters_killed++;
	}

	public int getMonsters_killed() {
		return monsters_killed;
	}
}
