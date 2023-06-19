package logic.level;

public class MapObject
{
	private final String name;
	protected int worldPosX;
	protected int worldPosY;
	private final boolean collectible;

	public MapObject(String name, int posX, int posY, boolean collectible)
	{
		this.name = name;
		this.worldPosX = posX;
		this.worldPosY = posY;
		this.collectible = collectible;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPosX() {
		return worldPosX;
	}
	
	public int getPosY() {
		return worldPosY;
	}
	
	public boolean isCollectible() {
		return collectible;
	}
}
