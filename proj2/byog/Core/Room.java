package byog.Core;

import java.util.ArrayList;

public class Room {
    private int x;
    private int y;
    private int HEIGHT;
    private int WIDTH;
    public boolean hasHallwayConnected = false;

    public Room(int x, int y, int HEIGHT, int WIDTH) {
        this.x = x;
        this.y = y;
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
    }

    public static boolean checkAllRoomsHaveHallway(ArrayList<Room> Rooms) {
        for (Room room : Rooms) {
            if (!room.hasHallwayConnected) {
                return false;
            }
        }
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

}
