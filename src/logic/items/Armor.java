package roguelike.logic.items;

public class Armor extends Equipment {

	private int defence;
	
	public Armor(String name, String displayName, int defence) {
		super(name, displayName, "Protection: "+defence);
		this.defence = defence;
	}
	public int getDefence() {
		return defence;
	}

}
