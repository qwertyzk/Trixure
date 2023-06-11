public class Main
{
    public static void main(String[] args)
    {
        try
        {

            System.out.println("[Main]: Loading...");

            Window.makeWindow();
            Window.setVisible();

            GameLogic.initGame();

            System.out.println("[Main]: Loaded!");

        }
        catch(Exception e)
        {
            System.err.println("\n[Main]: zesralo sie\n");
            e.printStackTrace();
            System.exit(-1);
        }

    }
}