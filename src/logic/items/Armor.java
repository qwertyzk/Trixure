package logic.items;

public class Armor extends Equipment
{
	private final int defence;

	public Armor(String name, String displayName, int defence, int durability, int price)
	{
		super(name, displayName, "Defence: "+defence+"   Durability: "+durability+"/"+durability, durability, price);
		this.defence = defence;
	}

	public void reduceDurability()
	{
		if(durability > 0)
		{
			this.durability--;
			this.description = "Defence: "+defence+" Durability: "+durability+"/"+totalDurability;
		}
	}

	public int getDefence()
	{
		if(durability > 0) return defence;
		return 0;
	}
}
