public abstract class Entity extends MapObject
{
    protected int health;
    protected int totalHealth;
    protected int strength;
    protected int defence;

    public Entity(String name, int posX, int posY, int health, int strength, int defence)
    {
        super(name, posX, posY);
        this.health = health;
        this.totalHealth = health;
        this.strength = strength;
        this.defence = defence;
    }

    public void takeDamage(int amount)
    {

    }

    public int getHealth()
    {
        return health;
    }

    public int getDefence()
    {
        return defence;
    }
    public int getStrength()
    {
        return strength;
    }
}
