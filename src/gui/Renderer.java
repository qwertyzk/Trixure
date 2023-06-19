package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import logic.entities.Monster;
import logic.entities.Player;
import logic.level.Room;
import logic.level.MapObject;
import logic.level.Shop;
import logic.text.MessageBox;
import resources.Textures;

import javax.swing.*;
import javax.swing.ImageIcon;

public class Renderer {

	private final int zoomLevel; // konieczne?

	public static final Rectangle shopSlot1 = new Rectangle(257, 310, 420, 60);
	public static final Rectangle shopSlot2 = new Rectangle(257, 379, 420, 60);
	public static final Rectangle shopSlot3 = new Rectangle(257, 447, 420, 60);


	public static final Rectangle inventorySlot1 = new Rectangle(302, 382, 340, 60);
	public static final Rectangle inventorySlot2 = new Rectangle(302, 450, 340, 60);
	public static final Rectangle inventorySlot3 = new Rectangle(302, 519, 340, 60);

	public static final Rectangle inventoryBin1 = new Rectangle(650, 382, 60, 60);
	public static final Rectangle inventoryBin2 = new Rectangle(650, 450, 60, 60);
	public static final Rectangle inventoryBin3 = new Rectangle(650, 519, 60, 60);

	public static final Rectangle weaponSlot = new Rectangle(302, 224, 340, 60);
	public static final Rectangle armorSlot = new Rectangle(302, 292, 340, 60);

	public static final Rectangle messageBox = new Rectangle(0, Window.HEIGHT-150, 1000, 150);

	public Renderer() {
		this.zoomLevel = 2;
	}

	public void renderPlayer(Graphics graphics) {
		BufferedImage sprite = Textures.getSprite("player");
		
		int drawPosX = (Window.WIDTH/2)-(sprite.getWidth()/2)*zoomLevel;
		int drawPosY = (Window.HEIGHT/2)-(sprite.getHeight()/2)*zoomLevel;
		graphics.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
	}

	public void renderLevel(Room roomData, Player player, Graphics graphics) {
		for(int y = 0; y< roomData.getSizeY(); y++) {
			for(int x = 0; x< roomData.getSizeX(); x++) {
				BufferedImage sprite = Textures.getSprite(roomData.getTileAt(x, y).getName());

				int drawPosX = calculateOffsetX(sprite, roomData.getTileAt(x, y), player);
				int drawPosY = calculateOffsetY(sprite, roomData.getTileAt(x, y), player);

				graphics.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
			}
		}
	}

	public void renderMonsters(Monster[] monsters, Player player, Graphics graphics) {
		
		for(Monster monster : monsters) {
			BufferedImage sprite = Textures.getSprite(monster.getName());
			int drawPosX = calculateOffsetX(sprite, monster, player);
			int drawPosY = calculateOffsetY(sprite, monster, player) ;

			if(monster.getHealth() > 0)
				graphics.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
		}
	}
	
	private int calculateOffsetX(BufferedImage sprite, MapObject mapObject, Player player) {
		return mapObject.getPosX()*sprite.getWidth()*zoomLevel + ((Window.WIDTH/2)-player.getPosX()*sprite.getWidth()*zoomLevel-(sprite.getWidth()/2)*zoomLevel);
	}

	private int calculateOffsetY(BufferedImage sprite, MapObject mapObject, Player player) {
		return mapObject.getPosY()*sprite.getHeight()*zoomLevel + ((Window.HEIGHT/2)-player.getPosY()*sprite.getHeight()*zoomLevel-(sprite.getHeight()/2)*zoomLevel);
	}

	public void renderUI(Player player, Room roomData, Graphics2D graphics, Point mousePosition) {

		BufferedImage status = Textures.getSprite("status");
		graphics.drawImage(status, 10, 10, status.getWidth(), status.getHeight(), null);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Dialog", Font.PLAIN, 30));

		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
		graphics.drawString("HP: "+player.getHealth()+"/"+player.getMaxHealth(), 30, 90);
		graphics.drawString("STR: "+player.getStrength(), 30, 120);
		graphics.drawString("DEF: "+player.getDefence(), 30, 150);
		graphics.drawString("Gold: "+player.getGold(), 30, 180);
		graphics.drawString("Floors: "+player.getFloorsCleared(), 30, 210);
		
		for(int y = 0; y< roomData.getSizeY(); y++) {
			for(int x = 0; x< roomData.getSizeX(); x++) {
				if(roomData.getTileAt(x, y).isCollectible() || roomData.getTileAt(x, y).getName() == "villager") {
					int drawPosX = roomData.getTileAt(x, y).getPosX()*32*zoomLevel + ((Window.WIDTH/2)-player.getPosX()*32*zoomLevel-(32/2)*zoomLevel);
					int drawPosY = roomData.getTileAt(x, y).getPosY()*32*zoomLevel + ((Window.HEIGHT/2)-player.getPosY()*32*zoomLevel-(32/2)*zoomLevel);
					
					if((player.getPosX() == x-1 && player.getPosY() == y) || (player.getPosX() == x+1 && player.getPosY() == y) || (player.getPosX() == x && player.getPosY() == y-1) || (player.getPosX() == x && player.getPosY() == y+1)) {
						String letter =  (roomData.getTileAt(x, y).getName() == "villager") ? "T" : "E";
						BufferedImage sprite = Textures.getSprite(letter);
						graphics.drawImage(sprite, drawPosX+8*zoomLevel, drawPosY-8*zoomLevel, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
					}
				}
			}
		}
		if(player.isShopOpen()) {
			//Inventory
			BufferedImage shop = Textures.getSprite("shop");
			int x = (Window.WIDTH - shop.getWidth()) / 2;
			int y = (Window.HEIGHT - shop.getHeight()) / 2;
			graphics.drawImage(shop, x, y,  null);

			if(shopSlot1.contains(mousePosition)) {
				graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(shopSlot1.x, shopSlot1.y, shopSlot1.width, shopSlot1.height, 10, 10);
			graphics.drawRoundRect(shopSlot1.x, shopSlot1.y, 60, shopSlot1.height, 10, 10);
			}


			if(shopSlot2.contains(mousePosition)){
				graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(shopSlot2.x, shopSlot2.y, shopSlot2.width, shopSlot2.height, 10, 10);
			graphics.drawRoundRect(shopSlot2.x, shopSlot2.y, 60, shopSlot2.height, 10, 10);}


			if(shopSlot3.contains(mousePosition)){
				graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(shopSlot3.x, shopSlot3.y, shopSlot3.width, shopSlot3.height, 10, 10);
			graphics.drawRoundRect(shopSlot3.x, shopSlot3.y, 60, shopSlot3.height, 10, 10);}

			//Shop items
			for(int i=0;i< Shop.SHOP_SIZE;i++) {
				if(player.getCurrentShop().getShopItem(i) != null) {
					BufferedImage sprite = Textures.getSprite(player.getCurrentShop().getShopItem(i).getName());
					graphics.drawImage(sprite, shopSlot1.x+6, 315+i*68, sprite.getWidth()*3, sprite.getHeight()*3, null);

					graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
					graphics.drawString(player.getCurrentShop().getShopItem(i).getDisplayName(), shopSlot1.x+70, 335+i*68);
					graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
					graphics.drawString(player.getCurrentShop().getShopItem(i).getDescription(), shopSlot1.x+70, 360+i*68);
					graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
					graphics.drawString(String.valueOf(player.getCurrentShop().getShopItem(i).getPrice()), shopSlot1.x+444, 348+i*68);
				}
			}

		}
		
		if(player.isInventoryOpen()) {
			//Inventory
			BufferedImage binSprite = Textures.getSprite("bin");

			BufferedImage inv = Textures.getSprite("inventory");
			int x = (Window.WIDTH - inv.getWidth()) / 2;
			int y = (Window.HEIGHT - inv.getHeight()) / 2;
			graphics.drawImage(inv, x, y,  null);
			//Inventory slots
			drawSlot(graphics, mousePosition, binSprite, inventorySlot1, inventoryBin1);

			drawSlot(graphics, mousePosition, binSprite, inventorySlot2, inventoryBin2);

			drawSlot(graphics, mousePosition, binSprite, inventorySlot3, inventoryBin3);

			
			if(player.getWeapon() != null) {
				BufferedImage sprite = Textures.getSprite(player.getWeapon().getName());
				graphics.drawImage(sprite, weaponSlot.x+7, weaponSlot.y+7, sprite.getWidth()*3, sprite.getHeight()*3, null);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(player.getWeapon().getDisplayName(), weaponSlot.x+70, weaponSlot.y+22);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
				graphics.drawString(player.getWeapon().getDescription(), weaponSlot.x+70, weaponSlot.y+47);
			}

			
			if(player.getArmor() != null) {
				BufferedImage sprite = Textures.getSprite(player.getArmor().getName());
				graphics.drawImage(sprite, armorSlot.x+7, armorSlot.y+7, sprite.getWidth()*3, sprite.getHeight()*3, null);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(player.getArmor().getDisplayName(), armorSlot.x+70, armorSlot.y+22);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
				graphics.drawString(player.getArmor().getDescription(), armorSlot.x+70, armorSlot.y+47);
			}
			
			//Inventory items
			for(int i=0;i<Player.INVENTORY_SIZE;i++) {
				if(player.getInventoryItem(i) != null) {
					BufferedImage sprite = Textures.getSprite(player.getInventoryItem(i).getName());
					graphics.drawImage(sprite, inventorySlot1.x+6, 387+i*68, sprite.getWidth()*3, sprite.getHeight()*3, null);
					
					graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
					graphics.drawString(player.getInventoryItem(i).getDisplayName(), inventorySlot1.x+70, 405+i*68);
					graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
					graphics.drawString(player.getInventoryItem(i).getDescription(), inventorySlot1.x+70, 430+i*68);
				}
			}
		}
		
		//Death screen
		if(player.getHealth() <= 0) {

			BufferedImage sprite = Textures.getSprite("lose_status");
			int x = (Window.WIDTH - sprite.getWidth()) / 2;
			int y = (Window.HEIGHT - sprite.getHeight()) / 2;
			graphics.drawImage(sprite, x , y , null);

		}
	}

	private void drawSlot(Graphics2D graphics, Point mousePosition, BufferedImage binSprite, Rectangle inventorySlot1, Rectangle inventoryBin1) {
		if(inventorySlot1.contains(mousePosition)) {
			graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(inventorySlot1.x, inventorySlot1.y, inventorySlot1.width, inventorySlot1.height, 10, 10);
			graphics.drawRoundRect(inventorySlot1.x, inventorySlot1.y, 60, inventorySlot1.height, 10, 10);
		}


		if(inventoryBin1.contains(mousePosition)) {
			graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(inventoryBin1.x, inventoryBin1.y, inventoryBin1.width, inventoryBin1.height, 10, 10);
		}

		graphics.drawImage(binSprite, inventoryBin1.x + 7, inventoryBin1.y+7, binSprite.getWidth()*3, binSprite.getHeight()*3, null);

	}




	public void renderMessageBox(MessageBox message, Graphics graphics) {

		List<String> list = message.getMessage();
		if (list == null) return;

		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));

		for(int i=0; i<MessageBox.LIST_SIZE; ++i)
		{
			if(i< list.size())

				graphics.drawString(list.get(i), 7, 700+20*i);
		}
	}


	public void renderTitleScreen(Graphics graphics) {
		BufferedImage image = Textures.getSprite("trixure");
		int x = (Window.WIDTH - image.getWidth()) / 2;
		int y = (Window.HEIGHT - 2*image.getHeight()) / 2;
		graphics.drawImage(image, x ,y, null);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
		String txt = "Wciśnij coś, ale nie cokolwiek...";
		int textPosX = (Window.WIDTH - graphics.getFontMetrics().stringWidth(txt)) / 2;
		int textPosY = ((Window.HEIGHT - graphics.getFontMetrics().getHeight()) / 2) + 2*graphics.getFontMetrics().getAscent();
		graphics.drawString(txt, textPosX, textPosY);

		graphics.setColor(Color.GRAY);
		graphics.setFont(new Font("Dialog", Font.PLAIN, 18));
		graphics.drawString("Chosen language: ponglish", 15, 25);

	}

	public void renderWinScreen(Graphics graphics) {
		Image icon = new ImageIcon("res/textures/cards.gif").getImage();
		BufferedImage image = Textures.getSprite("win_status");

		int x = (Window.WIDTH - image.getWidth()) / 2;
		int y = (Window.HEIGHT - image.getHeight()) / 2;
		graphics.drawImage(icon, 0, 60, 1000, 631, null);
		graphics.drawImage(image, x,y, image.getWidth(), image.getHeight(), null);

	}

}
