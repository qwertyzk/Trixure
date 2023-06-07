public class MapObject
{
    private String name;
    private int posX;
    private int posY;
    private boolean collectible;

    public MapObject(String name, int posX, int posY)
    {
        this.name = name;
        this.posX = posX;
        this.posY = posY;

        if(name == "red_potion" || name == "gold_bag" || name == "key")
            this.collectible = true;
        // collectible po nazwie
    }

    public boolean isCollectible()
    {
        return collectible;
    }

    public void setPosition(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }
    public String getName()
    {
        return name;
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

}
