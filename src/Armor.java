public class Armor extends Equipment
{
    private int defence;

    public Armor(String name, String displayName, int defence, int durability)
    {
        super(name, displayName, "Defence: "+defence+"   Durability: "+durability+"/"+durability, durability);
        this.defence = defence;
    }

    // getteri
}
