public abstract class Equipment extends Item
{
    protected int totalDurability;
    protected int durability;

    public Equipment(String name, String displayName, String description, int durability)
    {
        super(name, displayName, description);
        this.durability = durability;
        this.totalDurability = durability;
    }

    public void reduceDurability()
    {

    }

    // getteri
}