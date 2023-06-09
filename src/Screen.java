import javax.swing.JPanel;
import java.awt.*;

public class Screen extends JPanel
{
    private Renderer renderer;

    public Screen()
    {
        super();
        //this.setFocusable(true);
        this.addKeyListener(new Keyboard());
        this.addMouseListener(new Mouse());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
       // Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        g.fillRect(0,0, Window.WIDTH, Window.HEIGHT);


        if(GameLogic.isOnTitleScreen())
        {
            renderer.renderTitleScreen(graphics);
        }
        else
        {
            renderer.renderRoom(GameLogic.getCurrentRoom(), GameLogic.getPlayer(), g);
            renderer.renderPlayer(GameLogic.getPlayer(), g);
            renderer.renderMonsters(GameLogic.getMobs(), GameLogic.getPlayer(), g);
          //  renderer.renderLight(GameLogic.getCurrentFloor(), GameLogic.getPlayer(), (Graphics2D) g);
            renderer.renderUI(GameLogic.getPlayer(), GameLogic.getCurrentRoom(), (Graphics2D) g, this.getMouseLocation());
            renderer.renderMessageBox(GameLogic.getMessageBox(), g);
        }

        repaint();
    }

    public Point getMouseLocation()
    {
        if(getMousePosition() != null)
            return getMousePosition();
        else
            return new Point(-1, -1);
    }
}
