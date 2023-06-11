package roguelike.logic.entities;

import roguelike.resources.Layouts;

public class Monster extends Entity {

	private Type type;
	private boolean chasePlayer;
	public static final int NUMBER_OF_TYPES = 3;
	
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
		BAT("bat", 7, 2, 0, false),
		RAT("rat", 11, 2, 0, false),
		GHOST("ghost", 13, 3, 1, false);
		
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

		public static Type randomType(int i)
		{
			Type[] type = values();
			return type[((i % NUMBER_OF_TYPES) + 3) % NUMBER_OF_TYPES];
		}

		public static Monster ToMonster(Type t, int posX, int posY)
		{
			return new Monster(t, posX, posY);
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
