import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Gameloop implements ActionListener
{
    public void actionPerformed(ActionEvent arg0)
    {
        if(Keyboard.isKeyDown(KeyEvent.VK_W))
        {
            GameLogic.movePlayer(0, -1);
        }
        if(Keyboard.isKeyDown(KeyEvent.VK_A))
        {
            GameLogic.movePlayer(-1, 0);
        }
        if(Keyboard.isKeyDown(KeyEvent.VK_D))
        {
            GameLogic.movePlayer(1, 0);
        }
        if(Keyboard.isKeyDown(KeyEvent.VK_S))
        {
            GameLogic.movePlayer(0, 1);
        }

    }
}
