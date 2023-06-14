package logic.entities;

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
		RAT("rat", 5, 4, 0, false),
		GHOST("ghost", 8, 2, 1, true),
		ORK("table", 12, 1, 4, false),
		SLIME("torch", 8, 3, 5, false),
		ZOMBIE("lime_potion", 10, 1, 3, true),
		ANGRY_CHICKEN("chest" , 6, 4, 4, false);
		
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
