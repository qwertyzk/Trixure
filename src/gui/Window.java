package gui;

import javax.swing.JFrame;

public class Window {

	public static final int WIDTH = 1500;
	public static final int HEIGHT = 800;
	
	private static JFrame window;
	private static Screen screen;
	
	/**Initializes a JFrame object<br>
	 * Called at beginning of main*/
	public static void create() {
		window = new JFrame("Roguelike Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(20, 20, WIDTH, HEIGHT);
		window.setResizable(false);
		
		screen = new Screen();
		window.add(screen);
		
		System.out.println("[Gui][Window]: Created window");
	}
	
	/**Calls JFrame#setVisible<br>
	 * Called when initialization is done*/
	public static void setVisible() {
		if(window!=null) window.setVisible(true);
		System.out.println("[Gui][Window]: Window set to visible");
	}
}