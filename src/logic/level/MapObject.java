package logic.level;

public class MapObject
{

	private String name;
	
	protected int worldPosX;
	protected int worldPosY;
	private boolean collectible;

	public MapObject(String name, int posX, int posY) {
		this.name = name;
		this.worldPosX = posX;
		this.worldPosY = posY;
		
		if(name == "hp_potion_tile" || name == "gold_bag" || name == "key_tile" || name == "chest"
		|| name == "max_potion_tile" || name == "str_potion_tile" || name == "def_potion_tile" || name == "myst_potion_tile")
			this.collectible = true;
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
