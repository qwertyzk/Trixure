import javax.swing.JFrame;

public class Window
{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    private static JFrame window;
    private static Screen screen;

    public static void makeWindow()
    {
        window = new JFrame("szubi dubi");

        window.setBounds(50, 50, WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screen = new Screen();
        window.add(screen);

        System.out.println("[Gui][Window]: okno sie zrobilo");
    }

    public static void setVisible()
    {
        if(window != null)
            window.setVisible(true);
        System.out.println("[Gui][Window]: okno widze w tym tenczu");
    }


}
