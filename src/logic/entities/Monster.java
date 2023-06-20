package logic.entities;

public class Monster extends Entity
{
	private final boolean chasePlayer;
	public static final int NUMBER_OF_TYPES_AT_TIME = 4;
	
	public Monster(Type type, int posX, int posY)
	{
		super(type.getName(), posX, posY, type.getHp());
		this.strength = type.getStr();
		this.defence = type.getDef();
		this.chasePlayer = type.getChase();
	}
	
	public boolean shouldChasePlayer() {
		return chasePlayer;
	}

	public enum Type
	{
		BAT("bat", 10, 5, 3, false),
		SCORPION("scorpion", 9, 3, 4, false),
		GHOST("ghost", 8, 3, 2, true),
		HARPY("harpy", 10, 4, 4, false),
		MERMAID("mermaid" , 12, 10, 10, false),
		APPARITION("apparition", 10, 12, 7, true),
		GRYPHON("gryphon", 21, 9, 13, false),
		SPARTAN("spartan", 25, 15, 25, false),
		SKELETON_DRAKE("skeleton_drake", 40, 15, 15, false),
		CTHULHU_EYE("cthulhu_eye", 21, 18, 12, true);
		
		private final String name;
		private final int hp;
		private final int str;
		private final int def;
		private final boolean chase;
		
		Type(String name, int hp, int str, int def, boolean chase)
		{
			this.name = name;
			this.hp = hp;
			this.str = str;
			this.def = def;
			this.chase = chase;
		}

		public static Monster randomType(int i, int posX, int posY, int difficulty)
		{
			Type[] type = values();
			Type temp =  type[(Math.abs(i) % NUMBER_OF_TYPES_AT_TIME) + 3 * (difficulty - 1)];
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
