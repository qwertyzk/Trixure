import java.util.List;

public class Protagonist extends Character
{
    int LVL;
    int EXP;
    int MAX_SP;
    int CURRENT_SP;
    int LUC;   
    
    Skill skill_1;
    Skill skill_2;
    Skill skill_3;
    Skill skill_4;

    List<Integer> items;

    public Protagonist(int hp, int sp, int atk, int def, int acc, int dex, int luc, int spd)
    {
        MAX_HP = hp;
        CURRENT_HP = hp;
        MAX_SP = sp;
        CURRENT_SP = sp;
        ATK = atk;
        DEF = def;
        ACC = acc;
        DEX = dex;
        LUC = luc;
        SPD = spd;
    }
}
