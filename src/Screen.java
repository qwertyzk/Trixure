import javax.swing.JPanel;
import java.awt.*;

public class Screen extends JPanel
{
    public Screen()
    {
        super();
        this.setFocusable(true);
        this.addKeyListener(new Keyboard());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
       // Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        g.fillRect(0,0, Window.WIDTH, Window.HEIGHT);



        Renderer.renderEntity(GameLogic.getPlayer(), g);

       repaint();
    }
}
