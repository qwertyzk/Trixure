package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
	private static boolean[] keys;
	private static int delay;
	public Keyboard()
	{
		keys = new boolean[100];
		delay = 40;
	}

	public void keyPressed(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = true;
	}
	public void keyReleased(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = false;
	}
	public static boolean isKeyDown(int key)
	{
		if(keys[key] == true && delay <= 0)
		{
			delay = 40;
			return true;
		}
		else
		{
			delay--;
			return false;
		}
	}
	public void keyTyped(KeyEvent arg0) {}
}
