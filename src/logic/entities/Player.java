package logic.entities;

import logic.items.Armor;
import logic.items.Item;
import logic.items.Weapon;
import resources.Items;

public class Player extends Entity {

	public static final int INVENTORY_SIZE = 3;
	
	private Item[] inventory;
	private boolean inventoryOpen;
	private boolean shopOpen;

	private Weapon weaponEquipped;
	private Armor armorEquipped;

	private int gold;
	private int floors;
	
	public Player(String name, int posX, int posY) {
		super(name, posX, posY, 20);
		this.inventory = new Item[INVENTORY_SIZE];
		this.inventoryOpen = false;
		this.shopOpen = false;
		this.weaponEquipped = Items.Weapons.SHORT_SWORD.toWeapon();
		this.armorEquipped = Items.Armors.LIGHT_ARMOR.toArmor();
		this.strength = 1;
		this.defence = 0;
		this.gold = 0;
		this.floors = 0;
	}

	public boolean giveItem(Item item) {
		for(int i=0;i<INVENTORY_SIZE;i++) {
			if(this.inventory[i] == null) {
				this.inventory[i] = item;
				return true;
			}
		}
		return false;
	}
	
	/**Removes an item from the player's inventory
	 * @param index - Inventory slot
	 */
	public void removeItem(int index) {
		try {
			this.inventory[index] = null;
		} catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
	public Item getInventoryItem(int index) {
		try {
			return inventory[index];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void setInventoryOpen(boolean inventoryOpen) {
		this.inventoryOpen = inventoryOpen;
	}
	
	public boolean isInventoryOpen() {
		return inventoryOpen;
	}

	public void setShopOpen(boolean shopOpen) {
		this.shopOpen = shopOpen;
	}
	public boolean isShopOpen() {
		return shopOpen;
	}
	
	public void giveGold(int amount) {
		this.gold += amount;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void addFloorCleared() {
		this.floors++;
	}
	
	public int getFloorsCleared() {
		return floors;
	}
	
	public void increaseHealth(int amount) {
		this.maxHealth += amount;
		this.health += amount;
	}

	public void increaseStrength(int amount) {
		this.strength += amount;
	}

	public void increaseDefence(int amount) {
		this.defence += amount;
	}

	
	@Override
	public int getStrength() {
		int str = super.getStrength();
		if(this.weaponEquipped != null) str += this.weaponEquipped.getDamage();
		return str;
	}
	
	@Override
	public int getDefence() {
		int def = super.getDefence();
		if(this.armorEquipped != null) def += this.armorEquipped.getDefence();
		return def;
	}
	
	public void equipWeapon(Weapon weapon) {
		this.weaponEquipped = new Weapon(weapon.getName(), weapon.getDisplayName(), weapon.getDamage(), weapon.getDurability());
	}
	
	public void equipArmor(Armor armor)
	{
		this.armorEquipped = new Armor(armor.getName(), armor.getDisplayName(), armor.getDefence(), armor.getDurability());
	}

	public Weapon getWeapon() {
		return this.weaponEquipped;
	}

	public Armor getArmor() {
		return this.armorEquipped;
	}

}