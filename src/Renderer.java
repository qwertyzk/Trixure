import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;


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

    public void renderPlayer(Player playerData, Graphics g)
    {
        BufferedImage sprite = Textures.getSprite("player");
        g.drawImage(sprite, playerData.getPosX(), playerData.getPosY(), sprite.getWidth()*ZOOM, sprite.getHeight()*ZOOM, null);
    }
    public void renderMobs(ArrayList<Mob> mobs, Graphics g)
    {
        if(mobs == null) return;

        for (Mob mob : mobs)
        {
            BufferedImage sprite = Textures.getSprite(mob.getName());
            if(mob.getHealth() > 0)
                g.drawImage(sprite, mob.getPosX(), mob.getPosY(), sprite.getWidth()*ZOOM, sprite.getHeight()*ZOOM, null);
        }
    }

    public void renderObjectsInRoom(Room roomData, Graphics g)
    {
        for(int y=0; y<roomData.getSizeY(); y++)
        {
            for(int x=0; x<roomData.getSizeX(); x++)
            {
                BufferedImage sprite = Textures.getSprite(roomData.getObjectAt(x, y).getName());
                g.drawImage(sprite, x*32, y*32, sprite.getWidth()*ZOOM, sprite.getHeight()*ZOOM, null);
            }
        }
    }

    public void renderMessageBox(MessageBox message, Graphics graphics) {
        if(message.getMessage() == null || message.getTime() <= 0)
            return;

        graphics.setColor(Color.BLACK);
        graphics.fillRoundRect(messageBox.x, messageBox.y, messageBox.width, messageBox.height, 10, 10);
        graphics.setColor(Color.WHITE);
        graphics.drawRoundRect(messageBox.x, messageBox.y, messageBox.width, messageBox.height, 10, 10);

        graphics.setFont(new Font("Dialog", Font.PLAIN, 20));

        //Center text
        try {
            int textPosX = messageBox.x + (messageBox.width - graphics.getFontMetrics().stringWidth(message.getMessage())) / 2;
            int textPosY = messageBox.y + ((messageBox.height - graphics.getFontMetrics().getHeight()) / 2) + graphics.getFontMetrics().getAscent();
            graphics.drawString(message.getMessage(), textPosX, textPosY);
        } catch(NullPointerException e) {
            return;
        }
    }

    public void renderTitleScreen(Graphics graphics)
    {
        graphics.setColor(Color.WHITE);
        graphics.drawRoundRect(50, 50, Window.WIDTH-150, Window.HEIGHT-150, 10, 10);
        graphics.setFont(new Font("Dialog", Font.PLAIN, 40));
        graphics.drawString("ecie pecie", 100, 100);
        graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
        graphics.drawString("Press any button...", 200, 350);
    }

}

