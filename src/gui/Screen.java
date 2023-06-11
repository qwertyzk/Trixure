package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import logic.GameLogic;

public class Screen extends JPanel {

	private Renderer renderer;
	
	/**Creates a new GameScreen object<br>
	 * Extension of JPanel*/
	public Screen() {
		super(); //wazne
		this.setFocusable(true); //wazne
		this.addKeyListener(new Keyboard());
		this.addMouseListener(new Mouse());
		
		this.renderer = new Renderer();
		
		System.out.println("[Gui][GameScreen]: Created GameScreen");
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		try {
			
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
	
			if(GameLogic.isOnTitleScreen()) {
				renderer.renderTitleScreen(graphics);
			} else {
				renderer.renderLevel(GameLogic.getCurrentFloor(), GameLogic.getPlayer(), graphics);
				renderer.renderPlayer(GameLogic.getPlayer(), graphics);
				renderer.renderMonsters(GameLogic.getMonsters(), GameLogic.getPlayer(), graphics);
				renderer.renderUI(GameLogic.getPlayer(), GameLogic.getCurrentFloor(), (Graphics2D) graphics, this.getMouseLocation());
				renderer.renderMessageBox(GameLogic.getMessageBox(), graphics);
			}
		} catch (Exception e) {
			System.err.println("\n[Logic][GameLoop]: Uncaught exception in render system!\n");
			e.printStackTrace();
			System.exit(-1);
		}
		
		repaint();
	}
	
	public Point getMouseLocation() {
		Point d =  this.getMousePosition();

		if(d != null)
			return d;
		else
			return new Point(-1, -1);
	}
}