import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;

public class Screen extends JPanel
{
    private Renderer renderer;

    public Screen()
    {
        super();
        //this.setFocusable(true); chuj wie po co to
        this.addKeyListener(new Keyboard());
        this.addMouseListener(new Mouse());

        this.renderer = new Renderer();
        System.out.println("[Gui][GameScreen]: ekran sie zrobil");
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        //Graphics2D g = (Graphics2D) graphics;
        super.paintComponent(g);

        try {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);


            if (GameLogic.isOnTitleScreen())
            {
                renderer.renderTitleScreen(g);
            }
            else
            {
                renderer.renderObjectsInRoom(GameLogic.getCurrentRoom(), g);
                renderer.renderPlayer(GameLogic.getPlayer(), g);
                renderer.renderMobs(GameLogic.getMobs(), g);
                //  renderer.renderUI(GameLogic.getPlayer(), GameLogic.getCurrentRoom(), g, this.getMouseLocation());
                renderer.renderMessageBox(GameLogic.getMessageBox(), g);
            }
        }
        catch (Exception e)
        {
            System.err.println("\n[Logic][GameLoop]: zesralo sie\n");
            e.printStackTrace();
            System.exit(-1);
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
