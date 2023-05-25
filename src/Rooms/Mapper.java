package Rooms;
public class Mapper
{

    public Mapper()
    {
    
    }
    public Room metodaroomtypeToRum(RoomType rumtype)
    {
       return  switch(rumtype)
        {
            case COMMON -> new CommonRoom();
            default -> throw new IllegalArgumentException("saoifhcjaslkdjaoislfdhasiofjcdsoilcakspoghfasodclmndkugvjsajfchoifehfjmcsakjcfasosdaiohf");

            
        };
    }
} 