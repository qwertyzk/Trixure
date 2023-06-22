package logic.map_objects;

public abstract class Entity extends MapObject
{
	protected int health;
	protected int maxHealth;
	protected int strength;
	protected int defence;
	
	public Entity(String name, int posX, int posY, int health)
	{
		super(name, posX, posY, false);
		this.maxHealth = health;
		this.health = health;
	}
	
	public void setPosition(int dirX, int dirY)
	{
		this.worldPosX = dirX;
		this.worldPosY = dirY;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void heal(int amount) {
		this.health += amount;
		if(health>maxHealth)
			this.health = this.maxHealth;
	}
	
	public void damage(int amount)
	{
		if(amount <= 0) amount = 1;
		this.health -= amount;
	}
	
	public int getStrength() {
		return strength;
	}

	public int getDefence() {
		return defence;
	}
}
