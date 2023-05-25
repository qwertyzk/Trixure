import java.util.List;
import java.util.ArrayList;

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
    List<Item> items;
    Consumable consumable;

    // getteri setteri privati

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
        LVL = 1;
        EXP = 0;

        skill_1 = new Skill(1);
        skill_2 = new Skill(2);
        skill_3 = new Skill(0);
        skill_4 = new Skill(0);

        items = new ArrayList<>();
        consumable = new Consumable();
    }

    void check_exp()
    {
        while(EXP >= 100){
            LVL++;
            EXP -= 100;
            ATK += 3;
            DEF += 3;
            ACC += 3;
            DEX += 3;
            LUC += 3;
            SPD += 3;
            MAX_HP += 5;
            CURRENT_HP = MAX_HP;
            MAX_SP += 5;
            CURRENT_SP = MAX_SP;
        }
    }
}
