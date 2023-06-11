public class Player extends Entity
{
    public static final int INVENTORY_SIZE = 3;
    private Item[] inventory;
    private boolean inventoryOpen;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    private int gold;


    public Player(int posX, int posY)
    {
        super("player", posX, posY, 20, 1, 0);
        this.inventoryOpen = false;
        this.inventory = new Item[INVENTORY_SIZE];
        this.gold = 0;
        // defaultowy equipment (to do)
    }

    public boolean takeItem(Item item)
    {
        for(int i=0; i<INVENTORY_SIZE; i++)
        {
            if(this.inventory[i] == null)
            {
                this.inventory[i] = item;
                return true;
            }
        }
        return false;
    }

    public Item getInventoryItem(int index) {
        try {
            return inventory[index];
        } catch(ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void takeGold(int g)
    {
       this.gold += g;
    }

    public void removeItem(int id)
    {
        //tu trzeba usiasc i pomyslec
        try
        {
            this.inventory[id] = null;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return;
        }
    }

    public void increaseHealth(int amount)
    {
        this.totalHealth += amount;
        this.health += amount;
    }

    public int getStrength()
    {
        // niedafaultowy getter
        return 1;
    }

    public int getDefence()
    {
        return 1;
    }

    public void heal(int amount)
    {
        this.health += amount;
        if(health>this.totalHealth)
            this.health = this.totalHealth;
    }

    public void equipWeapon(Weapon weapon)
    {
        this.equippedWeapon = new Weapon(weapon.getName(), weapon.getDisplayName(), weapon.getDamage());
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = new Armor(armor.getName(), armor.getDisplayName(), armor.getDefence());
    }

    public Weapon getWeapon()
    {
        return this.equippedWeapon;
    }

    public Armor getArmor()
    {
        return this.equippedArmor;
    }

    public boolean isInventoryOpen()
    {
        return inventoryOpen;
    }

    public void setInventoryOpen(boolean inventoryOpen)
    {
        this.inventoryOpen = inventoryOpen;
    }
    // analogicznie damageWeapon

    // inne getteri i setteri
}
