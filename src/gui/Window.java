package gui;

import javax.swing.*;


public class Window {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	private static JFrame window;
	private static Screen screen;

	public static void create() {

		window = new JFrame("Trixure");

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(20, 20, WIDTH, HEIGHT);
		window.setResizable(false);
		
		screen = new Screen();
		window.add(screen);
		
		System.out.println("[Gui][Window]: Created window");
	}


	public static void setVisible()
	{
		if(window!=null) window.setVisible(true);
		System.out.println("[Gui][Window]: Window set to visible");
	}
}
