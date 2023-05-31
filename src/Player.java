public class Player extends Entity
{
    public static final int INVENTORY_SIZE = 3;
    private Item[] inventory;
    private boolean inventoryOpen;
    private Weapon equippedWeapon;
    private Armor equippedArmor;


    public Player(String name, int posX, int posY)
    {
        super(name, posX, posY, 20, 1, 0);
        this.inventoryOpen = false;
        this.inventory = new Item[INVENTORY_SIZE];
        // defaultowy equipment (to do)
    }

    public boolean addItem(Item item)
    {
        // (to do)
        return true;
    }

    public void removeItem(int id)
    {
        // (to do)
    }

    public void increaseHealth(int amount)
    {

    }

    public int getStrength()
    {
        // niedafaultowy getter
        return 1;
    }

    // analogicznie getter defenca

    public boolean damageArmor()
    {
        //(to do)
        return true;
    }

    // analogicznie damageWeapon

    // inne getteri i setteri
}
