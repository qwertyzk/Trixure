package resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Textures
{
	private static HashMap<String, BufferedImage> sprites;

	public static void init() {
		sprites = new HashMap<>();
		File folder = new File("res/textures");
		
		for(File file : Objects.requireNonNull(folder.listFiles()))
		{
			try {
				sprites.put(file.getName().replaceAll(".png", ""), ImageIO.read(file));
			} catch (IOException e) {
				System.err.println("[Utils][Textures]: Exception reading "+file.getName());
			}
		}
		System.out.println("[Utils][Textures]: Finished reading sprite files");
	}

	public static BufferedImage getSprite(String name)
	{
		BufferedImage sprite = sprites.get(name);
		if(sprite != null) return sprite;
		else return sprites.get("error");
	}
}
