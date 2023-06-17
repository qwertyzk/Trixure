package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import gui.Keyboard;

public class GameLoop implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {

			
			if(Keyboard.isKeyDown(KeyEvent.VK_W)) {
				GameLogic.movePlayer(0, -1); //Up
			}
			else if(Keyboard.isKeyDown(KeyEvent.VK_A)) {
				GameLogic.movePlayer(-1, 0); //Left

			}
			else if(Keyboard.isKeyDown(KeyEvent.VK_S)) {
				GameLogic.movePlayer(0, 1); //Down
			}
			else if(Keyboard.isKeyDown(KeyEvent.VK_D)) {
				GameLogic.movePlayer(1, 0); //Right

			}
			else if(Keyboard.isKeyDown(KeyEvent.VK_I)) {
				GameLogic.openPlayerInventory();
			}

			else if(Keyboard.isKeyDown(KeyEvent.VK_T)) {
				GameLogic.openShop();
			}

			else if(Keyboard.isKeyDown(KeyEvent.VK_E)) {
				GameLogic.handleInteraction();
			}
			
		} catch(Exception e) {
			System.err.println("\n[Logic][GameLoop]: Uncaught exception in game loop!\n");
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
