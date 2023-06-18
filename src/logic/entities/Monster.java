package logic.entities;

public class Monster extends Entity {

	private Type type;
	private boolean chasePlayer;
	public static final int NUMBER_OF_TYPES_AT_TIME = 3;
	
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

	public enum Type { //dorób jeszcze 5 mobkow, dodatkowe teksturki sa oznaczone mob<n>.png, zmien nazwy na adekwatne
		BAT("bat", 6, 3, 2, false),
		RAT("rat", 5, 4, 3, false),
		GHOST("ghost", 7, 2, 2, true),
		ORK("ork", 12, 3, 4, false),
		SLIME("slime", 10, 4, 6, false),
		ZOMBIE("zombie", 20, 6, 5, true),
		ANGRY_CHICKEN("chicken" , 10, 10, 10, false);
		
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

		public static Monster randomType(int i, int posX, int posY, int difficulty)
		{
			Type[] type = values();
			Type temp =  type[(Math.abs(i) % NUMBER_OF_TYPES_AT_TIME) + (difficulty * 2) - 2];
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
