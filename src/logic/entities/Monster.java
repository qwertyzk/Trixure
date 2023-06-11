package logic.entities;

import resources.Layouts;

public class Monster extends Entity {

	private Type type;
	private boolean chasePlayer;
	public static final int NUMBER_OF_TYPES = 7;
	
	public Monster(Type type, int posX, int posY) {
		super(type.getName(), posX, posY, type.getHp());
		this.strength = type.getStr();
		this.defence = type.getDef();
		this.type = type;
		this.chasePlayer = type.getChase();
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean shouldChasePlayer() {
		return chasePlayer;
	}

	public enum Type {
		BAT("bat", 9, 3, 2, false),
		RAT("rat", 5, 7, 0, false),
		GHOST("ghost", 8, 4, 1, true),
		ORK("table", 15, 1, 8, false),
		SLIME("torch", 8, 3, 5, false),
		ZOMBIE("stick", 13, 3, 3, true),
		ANGRY_CHICKEN("chest" , 6, 6, 6, true);
		
		private String name;
		private int hp;
		private int str;
		private int def;
		private boolean chase;
		
		Type(String name, int hp, int str, int def, boolean chase) {
			this.name = name;
			this.hp = hp;
			this.str = str;
			this.def = def;
			this.chase = chase;
		}

		public static Monster randomType(int i, int posX, int posY)
		{
			Type[] type = values();
			Type temp =  type[Math.abs(i) % NUMBER_OF_TYPES];
			return new Monster(temp, posX, posY);
		}

		public String getName() {
			return name;
		}

		public int getHp() {
			return hp;
		}

		public int getStr() {
			return str;
		}

		public int getDef() {
			return def;
		}
		
		public boolean getChase() {
			return chase;
		}
	}
}
