import java.awt.*;


public class Renderer
{
    private static final int ZOOM = 3;

    public static void renderEntity(MapObject entityData, Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(entityData.getPosX(), entityData.getPosY(), ZOOM*32,ZOOM*32);
    }
}

