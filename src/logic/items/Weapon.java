package logic.items;

public class Weapon extends Equipment
{
	private final int damage;

	public Weapon(String name, String displayName, int damage, int durability, int price)
	{
		super(name, displayName, "Damage: "+damage+"   Durability: "+durability+"/"+durability, durability, price);
		this.damage = damage;
	}

	public void reduceDurability()
	{
		if(durability > 0)
		{
			this.durability--;
			this.description = "Damage: "+damage+" Durability: "+durability+"/"+totalDurability;
		}
	}

	public void repair()
	{
		this.durability = this.totalDurability;
		this.description = "Damage: "+damage+" Durability: "+durability+"/"+totalDurability;

	}

	public int getDamage()
	{
		if (durability > 0) return damage;
		return 0;
	}
}
