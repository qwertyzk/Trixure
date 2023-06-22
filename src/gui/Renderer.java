package gui;

import logic.items.Equipment;
import logic.level.Room;
import logic.map_objects.MapObject;
import logic.map_objects.Monster;
import logic.map_objects.Player;
import logic.map_objects.Shop;
import logic.text.MessageBox;
import resources.Textures;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

	private final int zoomLevel;

	//Shop slots
	public static final Rectangle shopSlot1 = new Rectangle(257, 310, 420, 60);
	public static final Rectangle shopSlot2 = new Rectangle(257, 379, 420, 60);
	public static final Rectangle shopSlot3 = new Rectangle(257, 447, 420, 60);

	//Blacksmith slots
	public static final Rectangle blacksmithSlot1 = new Rectangle(257, 345, 420, 60);
	public static final Rectangle blacksmithSlot2 = new Rectangle(257, 412, 420, 60);


	//Inventory slots
	public static final Rectangle inventorySlot1 = new Rectangle(302, 382, 340, 60);
	public static final Rectangle inventorySlot2 = new Rectangle(302, 450, 340, 60);
	public static final Rectangle inventorySlot3 = new Rectangle(302, 519, 340, 60);

	//Inventory bins
	public static final Rectangle inventoryBin1 = new Rectangle(650, 382, 60, 60);
	public static final Rectangle inventoryBin2 = new Rectangle(650, 450, 60, 60);
	public static final Rectangle inventoryBin3 = new Rectangle(650, 519, 60, 60);

	//Equipment
	public static final Rectangle weaponSlot = new Rectangle(302, 224, 340, 60);
	public static final Rectangle armorSlot = new Rectangle(302, 292, 340, 60);

	public Renderer() {
		this.zoomLevel = 2;
	}

	public void renderPlayer(Graphics graphics)
	{
		BufferedImage sprite = Textures.getSprite("player");
		
		int newX = (Window.WIDTH/2)-(sprite.getWidth()/2)*zoomLevel;
		int newY = (Window.HEIGHT/2)-(sprite.getHeight()/2)*zoomLevel;
		graphics.drawImage(sprite, newX, newY, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
	}

	public void renderLevel(Room roomData, Player player, Graphics graphics)
	{
		for(int y = 0; y < roomData.getSizeY(); y++)
		{
			for(int x = 0; x < roomData.getSizeX(); x++)
			{
				BufferedImage sprite = Textures.getSprite(roomData.getTileAt(x, y).getName());

				int newX = centerPlayerX(sprite, roomData.getTileAt(x, y), player);
				int newY = centerPlayerY(sprite, roomData.getTileAt(x, y), player);

				graphics.drawImage(sprite, newX, newY, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
			}
		}
	}

	public void renderMonsters(Monster[] monsters, Player player, Graphics graphics)
	{
		for(Monster monster : monsters)
		{
			BufferedImage sprite = Textures.getSprite(monster.getName());

			int newX = centerPlayerX(sprite, monster, player);
			int newY = centerPlayerY(sprite, monster, player) ;

			if(monster.getHealth() > 0)
				graphics.drawImage(sprite, newX, newY, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
		}
	}
	
	private int centerPlayerX(BufferedImage sprite, MapObject mapObject, Player player) {
		return mapObject.getPosX()*sprite.getWidth()*zoomLevel + ((Window.WIDTH/2)-player.getPosX()*sprite.getWidth()*zoomLevel-(sprite.getWidth()/2)*zoomLevel);
	}

	private int centerPlayerY(BufferedImage sprite, MapObject mapObject, Player player) {
		return mapObject.getPosY()*sprite.getHeight()*zoomLevel + ((Window.HEIGHT/2)-player.getPosY()*sprite.getHeight()*zoomLevel-(sprite.getHeight()/2)*zoomLevel);
	}

	public void renderStatus(Player player, Graphics2D graphics)
	{
		BufferedImage sprite = Textures.getSprite("status");
		graphics.drawImage(sprite, 10, 10, sprite.getWidth(), sprite.getHeight(), null);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Dialog", Font.PLAIN, 18));

		graphics.drawString("HP: "+player.getHealth()+"/"+player.getMaxHealth(), 30, 90);
		graphics.drawString("STR: "+player.getStrength(), 30, 120);
		graphics.drawString("DEF: "+player.getDefence(), 30, 150);
		graphics.drawString("Gold: "+player.getGold(), 30, 180);
		graphics.drawString("Floors: "+player.getFloorsCleared(), 30, 210);
		graphics.drawString("Kills: "+player.getMonsters_killed(), 30, 240);
	}

	public void renderUI(Player player, Room roomData, Graphics2D graphics, Point mousePosition)
	{
		renderStatus(player, graphics);

		for (int y = 0; y < roomData.getSizeY(); y++)
		{
			for (int x = 0; x < roomData.getSizeX(); x++)
			{
				if (roomData.getTileAt(x, y).isCollectible()
						|| roomData.getTileAt(x, y).getName() == "villager"
						|| roomData.getTileAt(x, y).getName() == "blacksmith")
				{

					int newX = roomData.getTileAt(x, y).getPosX() * 32 * zoomLevel + ((Window.WIDTH / 2) - player.getPosX() * 32 * zoomLevel - (32 / 2) * zoomLevel);
					int newY = roomData.getTileAt(x, y).getPosY() * 32 * zoomLevel + ((Window.HEIGHT / 2) - player.getPosY() * 32 * zoomLevel - (32 / 2) * zoomLevel);

					if ((player.getPosX() == x - 1 && player.getPosY() == y)
							|| (player.getPosX() == x + 1 && player.getPosY() == y)
							|| (player.getPosX() == x && player.getPosY() == y - 1)
							|| (player.getPosX() == x && player.getPosY() == y + 1))
					{
						String letter = (roomData.getTileAt(x, y).getName() == "villager" || roomData.getTileAt(x, y).getName() == "blacksmith") ? "T" : "E";
						BufferedImage sprite = Textures.getSprite(letter);
						graphics.drawImage(sprite, newX + 8 * zoomLevel, newY - 8 * zoomLevel, sprite.getWidth() * zoomLevel, sprite.getHeight() * zoomLevel, null);
					}
				}
			}
		}

		//Render shop
		if (player.isShopOpen())
			renderShop(graphics, mousePosition, player);

		//Render inventory
		if (player.isInventoryOpen())
			renderInventory(graphics, mousePosition, player);

		//Render blacksmith
		if (player.isBlacksmithOpen())
			renderBlacksmith(graphics, mousePosition, player);

		//Death screen (wyświetlany w UI żeby było widać planszę w tle)
		if(player.getHealth() <= 0)
			renderDeathScreen(graphics);
	}

	private void renderShop(Graphics2D graphics, Point mousePosition, Player player)
	{
		BufferedImage sprite = Textures.getSprite("shop");

		int x = (Window.WIDTH - sprite.getWidth()) / 2;
		int y = (Window.HEIGHT - sprite.getHeight()) / 2;

		graphics.drawImage(sprite, x, y, null);

		drawSlot(graphics, mousePosition, shopSlot1);
		drawSlot(graphics, mousePosition, shopSlot2);
		drawSlot(graphics, mousePosition, shopSlot3);

		//Shop items
		drawShopItems(graphics, player);

	}
	private void renderInventory(Graphics2D graphics, Point mousePosition, Player player)
	{
		BufferedImage sprite = Textures.getSprite("inventory");

		int x = (Window.WIDTH - sprite.getWidth()) / 2;
		int y = (Window.HEIGHT - sprite.getHeight()) / 2;

		graphics.drawImage(sprite, x, y, null);

		//Inventory slots
		drawSlot(graphics, mousePosition, inventorySlot1);
		drawSlot(graphics, mousePosition, inventorySlot2);
		drawSlot(graphics, mousePosition, inventorySlot3);

		//Bin slots
		drawBin(graphics, mousePosition, inventoryBin1);
		drawBin(graphics, mousePosition, inventoryBin2);
		drawBin(graphics, mousePosition, inventoryBin3);

		//Equipment
		drawEq(graphics, player.getWeapon(), weaponSlot);
		drawEq(graphics, player.getArmor(), armorSlot);

		//Inventory items
		drawInventoryItems(graphics, player);
	}

	private void renderBlacksmith(Graphics2D graphics, Point mousePosition, Player player)
	{
		BufferedImage blacksmith = Textures.getSprite("blacksmith_screen");

		int x = (Window.WIDTH - blacksmith.getWidth()) / 2;
		int y = (Window.HEIGHT - blacksmith.getHeight()) / 2;

		graphics.drawImage(blacksmith, x, y, null);

		drawSlot(graphics, mousePosition, blacksmithSlot1);
		drawSlot(graphics, mousePosition, blacksmithSlot2);

		drawEq(graphics, player.getWeapon(), blacksmithSlot1);
		drawEq(graphics, player.getArmor(), blacksmithSlot2);

		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));

		if(player.getRepairPriceWeapon() == 0)
			graphics.drawString("---", blacksmithSlot1.x + 447, blacksmithSlot1.y+36);
		else
			graphics.drawString(String.valueOf(player.getRepairPriceWeapon()), blacksmithSlot1.x + 438, blacksmithSlot1.y+36);

		if(player.getRepairPriceArmor() == 0)
			graphics.drawString("---", blacksmithSlot2.x + 447, blacksmithSlot2.y+36);
		else
			graphics.drawString(String.valueOf(player.getRepairPriceArmor()), blacksmithSlot2.x + 438, blacksmithSlot2.y+36);
	}

	private void drawBin(Graphics2D graphics, Point mousePosition, Rectangle slot)
	{
		if(slot.contains(mousePosition))
		{
			graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(slot.x, slot.y, slot.width, slot.height, 10, 10);
		}

		BufferedImage sprite = Textures.getSprite("bin");
		graphics.drawImage(sprite, slot.x + 7, slot.y+7, sprite.getWidth()*3, sprite.getHeight()*3, null);
	}

	private void drawSlot(Graphics2D graphics, Point mousePosition, Rectangle slot)
	{
		if(slot.contains(mousePosition))
		{
			graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(slot.x, slot.y, slot.width, slot.height, 10, 10);
			graphics.drawRoundRect(slot.x, slot.y, 60, slot.height, 10, 10);
		}
	}

	private void drawEq(Graphics2D graphics, Equipment eq, Rectangle slot)
	{
		BufferedImage sprite = Textures.getSprite(eq.getName());

		graphics.drawImage(sprite, slot.x + 7, slot.y + 7, sprite.getWidth() * 3, sprite.getHeight() * 3, null);

		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
		graphics.drawString(eq.getDisplayName(), slot.x + 70, slot.y + 22);

		graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
		graphics.drawString(eq.getDescription(), slot.x + 70, slot.y + 47);
	}

	private void drawInventoryItems(Graphics2D graphics, Player player)
	{
		for (int i = 0; i < Player.INVENTORY_SIZE; i++)
		{
			if (player.getInventoryItem(i) != null)
			{
				BufferedImage sprite = Textures.getSprite(player.getInventoryItem(i).getName());

				graphics.drawImage(sprite, inventorySlot1.x + 6, 387 + i * 68, sprite.getWidth() * 3, sprite.getHeight() * 3, null);

				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(player.getInventoryItem(i).getDisplayName(), inventorySlot1.x + 70, 405 + i * 68);

				graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
				graphics.drawString(player.getInventoryItem(i).getDescription(), inventorySlot1.x + 70, 430 + i * 68);
			}
		}
	}

	private void drawShopItems(Graphics2D graphics, Player player)
	{
		for (int i = 0; i < Shop.SHOP_SIZE; i++) {
			if (player.getCurrentShop().getShopItem(i) != null)
			{
				BufferedImage sprite = Textures.getSprite(player.getCurrentShop().getShopItem(i).getName());

				graphics.drawImage(sprite, shopSlot1.x + 6, 315 + i * 68, sprite.getWidth() * 3, sprite.getHeight() * 3, null);

				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(player.getCurrentShop().getShopItem(i).getDisplayName(), shopSlot1.x + 70, 335 + i * 68);

				graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
				graphics.drawString(player.getCurrentShop().getShopItem(i).getDescription(), shopSlot1.x + 70, 360 + i * 68);

				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(String.valueOf(player.getCurrentShop().getShopItem(i).getPrice()), shopSlot1.x + 438, 348 + i * 68);
			}
		}
	}

	public void renderMessageBox(MessageBox message, Graphics graphics)
	{
		List<String> list = message.getMessage();
		if (list == null) return;

		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));

		for(int i=0; i < MessageBox.LIST_SIZE; ++i)
		{
			if(i < list.size())
				graphics.drawString(list.get(i), 7, 700+20*i);
		}
	}

	public void renderTitleScreen(Graphics graphics)
	{
		BufferedImage sprite = Textures.getSprite("trixure");
		int x = (Window.WIDTH - sprite.getWidth()) / 2;

		graphics.drawImage(sprite, x ,220, null);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Dialog", Font.BOLD, 26));

		String text = "Wciśnij coś, ale nie cokolwiek...";
		int textX = (Window.WIDTH - graphics.getFontMetrics().stringWidth(text)) / 2;
		graphics.drawString(text, textX, 700);

		graphics.setColor(Color.GRAY);
		graphics.setFont(new Font("Dialog", Font.PLAIN, 18));

		graphics.drawString("Language: Ponglish", 15, 25);

		String autoren = "Autoren: Zuzanna \"Trivium\" Kurnicka , Dominik \"Fraxure\" Biernacki";
		int autorenX = (Window.WIDTH - graphics.getFontMetrics().stringWidth(autoren)) / 2;
		graphics.drawString(autoren, autorenX, 750);
	}

	public void renderWinScreen(Player player, Graphics graphics)
	{
		Image icon = new ImageIcon("res/textures/cards.gif").getImage();
		BufferedImage sprite = Textures.getSprite("win_status");

		int x = (Window.WIDTH - sprite.getWidth()) / 2;
		int y = (Window.HEIGHT - sprite.getHeight()) / 2;

		graphics.drawImage(icon, 0, 60, 1000, 631, null);
		graphics.drawImage(sprite, x,y, sprite.getWidth(), sprite.getHeight(), null);

		renderStatus(player, (Graphics2D) graphics);
	}

	public void renderDeathScreen(Graphics graphics)
	{
		BufferedImage sprite = Textures.getSprite("lose_status");

		int x = (Window.WIDTH - sprite.getWidth()) / 2;
		int y = (Window.HEIGHT - sprite.getHeight()) / 2;

		graphics.drawImage(sprite, x , y , null);
	}
}
