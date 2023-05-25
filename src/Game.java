public class Game
{
    int current_floor = 1;
    public Game()
    {
        Protagonist protagonist = new Protagonist();
        Floor floor = new Floor(current_floor);

    }
}
