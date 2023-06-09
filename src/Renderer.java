import java.awt.*;


public class Renderer
{
    private int ZOOM;

    public static final Rectangle inventory = new Rectangle(150, 50, 700, 500);

    public static final Rectangle inventorySlot1 = new Rectangle(510, 150, 330, 60);
    public static final Rectangle inventorySlot2 = new Rectangle(510, 220, 330, 60);
    public static final Rectangle inventorySlot3 = new Rectangle(510, 290, 330, 60);

    public static final Rectangle weaponSlot = new Rectangle(160, 150, 330, 60);
    public static final Rectangle armorSlot = new Rectangle(160, 220, 330, 60);

    public static final Rectangle messageBox = new Rectangle(200, 480, 600, 50);

    public Renderer()
    {
        this.ZOOM = 2;
    }

    public void renderPlayer(MapObject playerData, Graphics g)
    {
        BufferedImage sprite = Textures.getSprite("player");
        g.drawImage(sprite, playerData.getPosX(), playerData.getPosY(), sprite.getWidth()*ZOOM, sprite.getHeight()*ZOOM, null);
    }
    public void renderMobs(Mobs[] mobs, Graphics g)
    {
        if(mobs == null) return;

        for(Mob mob : mobs) {
            BufferedImage sprite = Textures.getSprite(mob.getName());
            if(mob.getHealth() > 0)
                g.drawImage(sprite, mob.getPosX(), mob.getPosY(), sprite.getWidth()*ZOOM, sprite.getHeight()*ZOOM, null);
        }
    }

    public void renderRoom(Room roomData, Graphics g)
    {
        for(int y=0; y<roomData.getSizeY(); y++)
        {
            for(int x=0; x<roomData.getSizeX(); x++)
            {
                BufferedImage sprite = Textures.getSprite(roomData.getTileAt(x, y).getName());
                g.drawImage(sprite, roomData.getPos, drawPosY, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
            }
        }
    }

}

