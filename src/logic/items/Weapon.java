package logic.items;

public class Weapon extends Equipment
{
	private int damage;

	public Weapon(String name, String displayName, int damage, int durability)
	{
		super(name, displayName, "Damage: "+damage+"   Durability: "+durability+"/"+durability, durability);
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

	public int getDamage()
	{
		if (durability > 0) return damage;
		return 0;
	}
}

