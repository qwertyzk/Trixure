public class Mob extends Entity
{
    private Type type;

    public Mob(Type type, int posX, int posY)
    {
        super(type.getName(), posX, posY, type.getHp(), type.getStr(), type.getDef());
        this.type = type;
    }

    public enum Type
    {
        mob1("mob1", 1, 1, 1);

        private String name;
        private int hp;
        private int str;
        private int def;

        Type(String name, int hp, int str, int def)
        {
            this.name = name;
            this.hp = hp;
            this.str = str;
            this.def = def;
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
    }
}
