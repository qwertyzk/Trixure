package logic.items;

public class Item
{

	protected String name;
	protected String displayName;
	protected String description;

	protected int price;
	
	public Item(String name, String displayName, String description, int price)
	{
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
}

