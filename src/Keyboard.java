import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Keyboard implements KeyListener
{
    private static boolean[] keys;

    public Keyboard()
    {
        keys = new boolean[100];
    }

    public void keyPressed(KeyEvent arg0)
    {
        keys[arg0.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent arg0)
    {
        keys[arg0.getKeyCode()] = false;
    }

    public static boolean isKeyDown(int key)
    {
        return keys[key];
    }


    public void keyTyped(KeyEvent arg0) {}
}
