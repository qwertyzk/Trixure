package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import logic.GameLogic;

public class Mouse implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {GameLogic.handleLeftClick(arg0.getX(), arg0.getY());}
	
}
