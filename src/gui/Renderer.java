package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import logic.entities.Monster;
import logic.entities.Player;
import logic.level.Room;
import logic.level.MapObject;
import logic.text.MessageBox;
import resources.Textures;

public class Renderer {

	private final int zoomLevel;

	public static final Rectangle shop = new Rectangle(150, 50, 1200, 500);

	public static final Rectangle shopSlot1 = new Rectangle(760, 150, 580, 60);
	public static final Rectangle shopSlot2 = new Rectangle(760, 220, 580, 60);
	public static final Rectangle shopSlot3 = new Rectangle(760, 290, 580, 60);

	public static final Rectangle inventory = new Rectangle(150, 50, 1200, 500);
	
	public static final Rectangle inventorySlot1 = new Rectangle(760, 150, 580, 60);
	public static final Rectangle inventorySlot2 = new Rectangle(760, 220, 580, 60);
	public static final Rectangle inventorySlot3 = new Rectangle(760, 290, 580, 60);

	public static final Rectangle weaponSlot = new Rectangle(160, 150, 580, 60);
	public static final Rectangle armorSlot = new Rectangle(160, 220, 580, 60);

	public static final Rectangle messageBox = new Rectangle(500, 600, 600, 50);
	
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
		if(monsters == null) return;
		
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

		graphics.setColor(Color.BLACK);
		graphics.fillRoundRect(5, 5, 100, 150, 10, 10);
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(5, 5, 100, 150, 10, 10);
		
		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
		graphics.drawString("- Player -", 10, 25);
		graphics.setFont(new Font("Dialog", Font.PLAIN, 16));
		graphics.drawString("HP: "+player.getHealth()+"/"+player.getMaxHealth(), 10, 45);
		graphics.drawString("STR: "+player.getStrength(), 10, 65);
		graphics.drawString("DEF: "+player.getDefence(), 10, 80);
		graphics.drawString("Gold: "+player.getGold(), 10, 100);
		graphics.drawString("Floors: "+player.getFloorsCleared(), 10, 120);
		
		for(int y = 0; y< roomData.getSizeY(); y++) {
			for(int x = 0; x< roomData.getSizeX(); x++) {
				if(roomData.getTileAt(x, y).isCollectible() || roomData.getTileAt(x, y).getName() == "wiesniak") {
					int drawPosX = roomData.getTileAt(x, y).getPosX()*32*zoomLevel + ((Window.WIDTH/2)-player.getPosX()*32*zoomLevel-(32/2)*zoomLevel);
					int drawPosY = roomData.getTileAt(x, y).getPosY()*32*zoomLevel + ((Window.HEIGHT/2)-player.getPosY()*32*zoomLevel-(32/2)*zoomLevel);
					
					if((player.getPosX() == x-1 && player.getPosY() == y) || (player.getPosX() == x+1 && player.getPosY() == y) || (player.getPosX() == x && player.getPosY() == y-1) || (player.getPosX() == x && player.getPosY() == y+1)) {
						String letter =  (roomData.getTileAt(x, y).getName() == "wiesniak") ? "M" : "E";
						BufferedImage sprite = Textures.getSprite(letter);
						graphics.drawImage(sprite, drawPosX+8*zoomLevel, drawPosY-8*zoomLevel, sprite.getWidth()*zoomLevel, sprite.getHeight()*zoomLevel, null);
					}
				}
			}
		}
		if(player.isShopOpen()) {
			//Inventory
			graphics.setColor(Color.BLACK);
			graphics.fillRoundRect(shop.x, shop.y, shop.width, shop.height, 10, 10);
			graphics.setColor(Color.WHITE);
			graphics.drawRoundRect(shop.x, shop.y, shop.width, shop.height, 10, 10);

			graphics.setFont(new Font("Dialog", Font.PLAIN, 40));
			graphics.drawString("- Shop -", 160, 90);
			graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
			graphics.drawString("GOLD: " + player.getGold() , 160, 120);

			if(shopSlot1.contains(mousePosition))
				graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(shopSlot1.x, shopSlot1.y, shopSlot1.width, shopSlot1.height, 10, 10);
			graphics.drawRoundRect(shopSlot1.x, shopSlot1.y, 60, shopSlot1.height, 10, 10);

			graphics.setStroke(new BasicStroke(1));

			if(shopSlot2.contains(mousePosition))
				graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(shopSlot2.x, shopSlot2.y, shopSlot2.width, shopSlot2.height, 10, 10);
			graphics.drawRoundRect(shopSlot2.x, shopSlot2.y, 60, shopSlot2.height, 10, 10);

			graphics.setStroke(new BasicStroke(1));

			if(shopSlot3.contains(mousePosition))
				graphics.setStroke(new BasicStroke(3));
			graphics.drawRoundRect(shopSlot3.x, shopSlot3.y, shopSlot3.width, shopSlot3.height, 10, 10);
			graphics.drawRoundRect(shopSlot3.x, shopSlot3.y, 60, shopSlot3.height, 10, 10);

		}
		
		if(player.isInventoryOpen()) {
			//Inventory
			graphics.setColor(Color.BLACK);
			graphics.fillRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);
			graphics.setColor(Color.WHITE);
			graphics.drawRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);
			
			graphics.setFont(new Font("Dialog", Font.PLAIN, 40));
			graphics.drawString("- Inventory -", 160, 90);
			graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
			graphics.drawString("HP: "+player.getHealth()+"/"+player.getMaxHealth()+"     STR: "+player.getStrength()+"   DEF: "+player.getDefence()+"     Gold: "+player.getGold(), 160, 120);
			
			//Inventory slots
			if(inventorySlot1.contains(mousePosition))
				graphics.setStroke(new BasicStroke(3));
			
			graphics.drawRoundRect(inventorySlot1.x, inventorySlot1.y, inventorySlot1.width, inventorySlot1.height, 10, 10);
			graphics.drawRoundRect(inventorySlot1.x, inventorySlot1.y, 60, inventorySlot1.height, 10, 10);
			
			graphics.setStroke(new BasicStroke(1));
			
			if(inventorySlot2.contains(mousePosition))
				graphics.setStroke(new BasicStroke(3));
			
			graphics.drawRoundRect(inventorySlot2.x, inventorySlot2.y, inventorySlot2.width, inventorySlot2.height, 10, 10);
			graphics.drawRoundRect(inventorySlot2.x, inventorySlot2.y, 60, inventorySlot2.height, 10, 10);
			
			graphics.setStroke(new BasicStroke(1));
			
			if(inventorySlot3.contains(mousePosition))
				graphics.setStroke(new BasicStroke(3));
			
			graphics.drawRoundRect(inventorySlot3.x, inventorySlot3.y, inventorySlot3.width, inventorySlot3.height, 10, 10);
			graphics.drawRoundRect(inventorySlot3.x, inventorySlot3.y, 60, inventorySlot3.height, 10, 10);
			
			graphics.setStroke(new BasicStroke(1));
			
			graphics.drawRoundRect(weaponSlot.x, weaponSlot.y, weaponSlot.width, weaponSlot.height, 10, 10);
			graphics.drawRoundRect(weaponSlot.x, weaponSlot.y, 60, weaponSlot.height, 10, 10);
			
			if(player.getWeapon() != null) {
				BufferedImage sprite = Textures.getSprite(player.getWeapon().getName());
				graphics.drawImage(sprite, weaponSlot.x+7, weaponSlot.y+7, sprite.getWidth()*3, sprite.getHeight()*3, null);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(player.getWeapon().getDisplayName(), weaponSlot.x+65, weaponSlot.y+22);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
				graphics.drawString(player.getWeapon().getDescription(), weaponSlot.x+65, weaponSlot.y+48);
			}
			
			graphics.drawRoundRect(armorSlot.x, armorSlot.y, armorSlot.width, armorSlot.height, 10, 10);
			graphics.drawRoundRect(armorSlot.x, armorSlot.y, 60, armorSlot.height, 10, 10);
			
			if(player.getArmor() != null) {
				BufferedImage sprite = Textures.getSprite(player.getArmor().getName());
				graphics.drawImage(sprite, armorSlot.x+7, armorSlot.y+7, sprite.getWidth()*3, sprite.getHeight()*3, null);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
				graphics.drawString(player.getArmor().getDisplayName(), armorSlot.x+65, armorSlot.y+22);
				graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
				graphics.drawString(player.getArmor().getDescription(), armorSlot.x+65, armorSlot.y+48);
			}
			
			//Inventory items
			for(int i=0;i<Player.INVENTORY_SIZE;i++) {
				if(player.getInventoryItem(i) != null) {
					BufferedImage sprite = Textures.getSprite(player.getInventoryItem(i).getName());
					graphics.drawImage(sprite, inventorySlot1.x+7, 157+i*70, sprite.getWidth()*3, sprite.getHeight()*3, null);
					
					graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
					graphics.drawString(player.getInventoryItem(i).getDisplayName(), inventorySlot1.x+65, 170+i*70);
					graphics.setFont(new Font("Dialog", Font.PLAIN, 15));
					graphics.drawString(player.getInventoryItem(i).getDescription(), inventorySlot1.x+65, 195+i*70);
				}
			}
		}
		
		//Death screen
		if(player.getHealth() <= 0) {
			graphics.setColor(Color.BLACK);
			graphics.fillRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);
			graphics.setColor(Color.WHITE);
			graphics.drawRoundRect(inventory.x, inventory.y, inventory.width, inventory.height, 10, 10);

			graphics.setFont(new Font("Dialog", Font.PLAIN, 40));
			graphics.drawString("You perished in the dungeon...", 200, 130);
			
			graphics.setFont(new Font("Dialog", Font.PLAIN, 30));
			graphics.drawString("Floors cleared: "+player.getFloorsCleared(), 200, 200);
			graphics.drawString("Gold obtained: "+player.getGold(), 200, 240);
			graphics.drawString("Click to restart game", 200, 350);
		}
	}
	

	public void renderMessageBox(MessageBox message, Graphics graphics) {
		if(message.getMessage() == null || message.getTime() <= 0)
			return;
		
		graphics.setColor(Color.BLACK);
		graphics.fillRoundRect(messageBox.x, messageBox.y, messageBox.width, messageBox.height, 10, 10);
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(messageBox.x, messageBox.y, messageBox.width, messageBox.height, 10, 10);

		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		//Center text
		try {
			int textPosX = messageBox.x + (messageBox.width - graphics.getFontMetrics().stringWidth(message.getMessage())) / 2;
			int textPosY = messageBox.y + ((messageBox.height - graphics.getFontMetrics().getHeight()) / 2) + graphics.getFontMetrics().getAscent();		
			graphics.drawString(message.getMessage(), textPosX, textPosY);
		} catch(NullPointerException e) {
			return;
		}
	}


	public void renderTitleScreen(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.drawRoundRect(50, 50, Window.WIDTH-150, Window.HEIGHT-150, 10, 10);
		graphics.setFont(new Font("Dialog", Font.PLAIN, 40));
		graphics.drawString("slonskie erytrocyty", 100, 100);
		graphics.setFont(new Font("Dialog", Font.PLAIN, 20));
		graphics.drawString("upadek pierwszej", 100, 150);
		graphics.drawString("rzeczypospolitej polskiej", 100, 180);

		graphics.drawString("wcisnij cos ale nie cokolwiek", 200, 350);
	}

}
