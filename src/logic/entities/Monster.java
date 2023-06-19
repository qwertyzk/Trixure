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
		BAT("bat", 7, 4, 2, false),
		SCORPION("scorpion", 8, 3, 3, false),
		GHOST("ghost", 6, 2, 2, true),
		HARPY("harpy", 10, 3, 4, false),
		MERMAID("mermaid" , 12, 6, 6, false),
		APPARITION("apparition", 10, 7, 4, true),
		GRYPHON("gryphon", 20, 6, 8, false),
		SPARTAN("spartan", 15, 15, 15, false),
		SKELETON_DRAKE("skeleton_drake", 25, 12, 12, false),
		CTHULHU_EYE("cthulhu_eye", 13, 10, 8, true);
		
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
