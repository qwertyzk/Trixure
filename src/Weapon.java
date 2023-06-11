public class Weapon extends Equipment
{
    private int damage;

    public Weapon(String name, String displayName, int damage)
    {
        super(name, displayName, "Damage: "+damage);
        this.damage = damage;
    }

    public int getDamage()
    {
        return damage;
    }
    // getteri
}
