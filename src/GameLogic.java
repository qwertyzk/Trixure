import javax.swing.Timer;

public class GameLogic
{
    private static MapObject player;
    private static Timer timer;

    public static void initGame()
    {
        player = new MapObject("player", 200, 20);
        timer = new Timer(20, new Gameloop());
        timer.start();
    }

    public static void movePlayer(int dx, int dy)
    {
        player.setPosition(player.getPosX()+32*dx, player.getPosY()+32*dy);
    }

    public static MapObject getPlayer()
    {
        return player;
    }
}
