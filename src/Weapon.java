public class Weapon extends Equipment
{
    private int damage;

    public Weapon(String name, String displayName, int damage, int durability)
    {
        super(name, displayName, "Damage: "+damage+"   Durability: "+durability+"/"+durability, durability);
        this.damage = damage;
    }

    public int getDamage()
    {
        return damage;
    }
    // getteri
}
