
import javax.swing.JFrame;
import java.awt.*;

public class Window
{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    private static JFrame window;
    private static Screen screen;

    public static void makeWindow()
    {
        window = new JFrame("szubi dubi");

        window.setBounds(50, 50, WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screen = new Screen();
        window.add(screen);
    }

    public static void setVisible()
    {
        if(window != null)
            window.setVisible(true);
    }


}
