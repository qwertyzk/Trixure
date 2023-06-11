import javax.swing.Timer;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class GameLogic
{
    private static Player player;
    private static Room currentRoom = new Room(Levels.Layouts.layout0.getLayout(),
            Levels.Layouts.layout0.getStartingPosX(), Levels.Layouts.layout0.getStartingPosY());

    private static ArrayList<Mob> currentMobs;
    private static MessageBox messageBox;
    private static Timer timer;
    private static Random randomizer;

    private static boolean onTitleScreen;

    public static void initGame()
    {
        randomizer = new Random();

       // currentRoom = tower.getFloor(0);
        player = new Player(2, 6);
        currentMobs = currentRoom.getMobs();
        messageBox = new MessageBox();

        onTitleScreen = true;
        player = new Player(200, 20);
        timer = new Timer(20, new Gameloop());
        timer.start();
    }

    private static boolean checkIfPlayerDied()
    {
        if(player.getHealth() <= 0)
        {
            messageBox.addMessage("no i zdechło.", 600);
            return true;
        }
        return false;
    }

    public static void openPlayerInventory()
    {
        if(player.getHealth() > 0)
            player.setInventoryOpen(!player.isInventoryOpen());
    }

    public static void handleInteration() {
        System.out.println("[Main][GameLogic]: Looking for an item to pickup");
        pickupItem(player.getPosX()+1, player.getPosY());
        pickupItem(player.getPosX()-1, player.getPosY());
        pickupItem(player.getPosX(), player.getPosY()+1);
        pickupItem(player.getPosX(), player.getPosY()-1);
    }
    private static void pickupItem(int itemPosX, int itemPosY) {
        switch(currentRoom.getObjectAt(itemPosX, itemPosY).getName()) {
            case "picasso":
                if(player.takeItem(Items.HP_POTION))
                {
                    currentRoom.removeCollectible(itemPosX, itemPosY);
                    messageBox.addMessage("You picked up a picassososos!", 100);
                }
                else {
                    messageBox.addMessage("Your inventory is full!", 50);
                }
                break;
                }
        }


    //zwraca true jesli doszlo do walki
    private static boolean Fight(int dirX, int dirY) {
        if(currentRoom.getMobAt(player.getPosX()+dirX, player.getPosY()+dirY) != null)
        {
            Mob mob = currentRoom.getMobAt(player.getPosX()+dirX, player.getPosY()+dirY);
            mob.takeDamage(player.getStrength() - mob.getDefence()/3);

    
            if(mob.getHealth() <= 0)
            { //Monster is dead
                currentRoom.killMob(mob.getPosX(), mob.getPosY());
                int g = randomizer.nextInt(12)+8;
                player.takeGold(g);
                messageBox.addMessage("zabiwszy potwora. dostajesz "+g+" PLN!", 80);
            }
            else
            { //Monster is still alive after attack
                messageBox.addMessage("Chcieliście wydymać freda, to teraz fred wydyma was.", 80);

                player.takeDamage(mob.getStrength() - player.getDefence()/3);
            }

            return true;
        }
        return false;
    }
    public static void movePlayer(int dx, int dy)
    {
        onTitleScreen = false;

        if (player.isInventoryOpen())
            return;

        if (checkIfPlayerDied())
            return;

        if (Fight(dx, dy))
        {
            checkIfPlayerDied();
            return;
        }

        switch ((currentRoom.getObjectAt(player.getPosX()+dx, player.getPosY()+dy)).getName())
        {
            case "empty":
                player.setPosition(player.getPosX() + dx, player.getPosY() + dy);
                break;
            case "wall":
                messageBox.addMessage("You ran into a wall!", 20);
                break;
            case "stairs":
               // currentRoom = tower.getNextFloor();
                player.setPosition(currentRoom.getStartPosX(), currentRoom.getStartPosY());
                currentMobs = currentRoom.getMobs();
                messageBox.addMessage("You went into a new floor!", 100);
             //   player.addFloorCleared();
                break;
            case "trap":
                player.takeDamage(randomizer.nextInt(2) + 1);
                player.setPosition(player.getPosX() + dx, player.getPosY() + dy);
                currentRoom.removeTrap(player.getPosX(), player.getPosY());
                messageBox.addMessage("You ran into a trap and you took damage!", 100);
                break;
            case "locked_door":
                messageBox.addMessage("You need a key to open this door...", 100);
                break;
        }
    }

    public static boolean isOnTitleScreen()
    {
        return onTitleScreen;
    }

    public static Player getPlayer()
    {
        return player;
    }

    public static ArrayList<Mob> getMobs()
    {
        return currentMobs;
    }

    public static MessageBox getMessageBox()
    {
        return messageBox;
    }
    public static Room getCurrentRoom()
    {
        return currentRoom;
    }

    public static void handleLeftClick(int mouseX, int mouseY)
    {
        System.out.println("[Main][GameLogic]: Handling mouse click");
        if(player.isInventoryOpen())
        {
            if(Renderer.inventorySlot1.contains(mouseX, mouseY))
            {
                usePlayerItem(0);
            }
            else if(Renderer.inventorySlot2.contains(mouseX, mouseY)) {
                usePlayerItem(1);
            }
            else if(Renderer.inventorySlot3.contains(mouseX, mouseY)) {
                usePlayerItem(2);
            }
        }

        /*if(player.getHealth() <= 0)
        {
            init();
        }*/
    }

    private static void usePlayerItem(int index) {
        Item item = player.getInventoryItem(index);

        if (item == null) return;

        if (item == Items.HP_POTION) {
            player.heal(10);
            messageBox.addMessage("You drank a red potion and you recovered health!", 150);
        }
    }


}
