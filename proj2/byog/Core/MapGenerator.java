
package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.ArrayList;

public class MapGenerator {
    private int HEIGHT;
    private int WIDTH;

    public static final Random RANDOM = new Random();
    private long SEED;
    private TETile[][] world;
    private TETile[][] positionOftheRoom;
    private static ArrayList<Room> Rooms;
    private static final int maxRoomLength = 8;
    private static final int minRoomLength = 3;
    public Position player;
    public Position door;

    public MapGenerator(int width, int height, long seed) {
        this.HEIGHT = height;
        this.WIDTH = width;
        world = new TETile[WIDTH][HEIGHT];
        positionOftheRoom = new TETile[WIDTH][HEIGHT];
        this.SEED = seed;
        RANDOM.setSeed(seed);
    }

    public TETile[][] mapGenerator() {
        do {
            initializeWorldArray();
            Rooms = new ArrayList<>();
            randomlyBuildRoom();
            Hallway.generateHallWay(world, Rooms, SEED); // Make sure Rooms is either passed to this function or is
                                                         // globally
            // accessible
        } while (!Room.checkAllRoomsHaveHallway(Rooms)); // Assuming this function is moved here or is accessible

        /* modifyWalls(world); */
        GeneratePlayer();
        generateDoor();
        return world;
    }

    private void GeneratePlayer() {
        while (true) {
            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);
            if (Tileset.FLOOR.equals(world[x][y])) {
                player = new Position(x, y);
                world[x][y] = Tileset.PLAYER;
                return;
            }
        }
    }

    private void initializeWorldArray() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                positionOftheRoom[i][j] = Tileset.NOTHING;
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private boolean nearFLOOR(int x, int y) {
        if (x + 1 < WIDTH && Tileset.FLOOR.equals(world[x + 1][y])) {
            return true;
        }
        if (x - 1 >= 0 && Tileset.FLOOR.equals(world[x - 1][y])) {
            return true;
        }
        if (y + 1 < HEIGHT && Tileset.FLOOR.equals(world[x][y + 1])) {
            return true;
        }
        if (y - 1 >= 0 && Tileset.FLOOR.equals(world[x][y - 1])) {
            return true;
        }
        return false;
    }

    public static void modifyWalls(TETile[][] world) {
        int width = world.length;
        int height = world[0].length;

        // Create a copy of the world to keep track of original states
        TETile[][] worldCopy = new TETile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                worldCopy[x][y] = world[x][y];
            }
        }

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                if (worldCopy[x][y] == Tileset.WALL) {
                    int floorCount = 0;

                    // Check immediate neighbors: top, bottom, left, right
                    int[][] directions = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
                    for (int[] direction : directions) {
                        int dx = direction[0];
                        int dy = direction[1];
                        if (worldCopy[x + dx][y + dy] == Tileset.FLOOR) {
                            floorCount++;
                        }
                    }

                    if (floorCount >= 3) {
                        world[x][y] = Tileset.FLOOR;
                    }
                }
            }
        }
    }

    private void generateDoor() {
        while (true) {
            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);
            if (Tileset.WALL.equals(world[x][y]) && nearFLOOR(x, y)) {
                world[x][y] = Tileset.LOCKED_DOOR;
                return;
            }
        }
    }

    private boolean isOutbound(int x, int y, int roomWidth, int roomHeight) {
        for (int i = x; i <= x + roomWidth - 1; i++) {
            for (int j = y; j <= y + roomHeight - 1; j++) {
                if (i >= WIDTH - 1 || j >= HEIGHT - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOverlap(int x, int y, int roomWidth, int roomHeight) {
        for (int i = x; i <= x + roomWidth; i++) {
            for (int j = y; j <= y + roomHeight; j++) {
                if (world[i][j] == Tileset.WALL || world[i][j] == Tileset.FLOOR) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOverlaporOutbound(int x, int y, int roomWidth, int roomHeight) {
        if (isOutbound(x, y, roomWidth, roomHeight) || isOverlap(x, y, roomWidth, roomHeight)) {
            return true;
        }
        return false;
    }

    private void buildRoom(int x, int y, int roomWidth, int roomHeight) {
        if (isOutbound(x, y, roomWidth, roomHeight)) {
            return;
        }
        int roomType = RANDOM.nextInt(5) + 1;
        switch (roomType) {
            case 1:
                for (int i = x; i <= x + roomWidth - 1; i++) {
                    for (int j = y; j <= y + roomHeight - 1; j++) {
                        if (i == x || j == y || i == x + roomWidth - 1 || j == y + roomHeight - 1) {
                            world[i][j] = Tileset.WALL;
                            continue;
                        }
                        world[i][j] = Tileset.FLOOR;
                        positionOftheRoom[i][j] = Tileset.FLOOR;
                    }
                }
                return;
            case 2:
                if (roomWidth > roomHeight) {
                    roomWidth = roomHeight;
                }
                for (int i = x; i <= x + roomWidth - 1; i++) {
                    for (int j = y; j <= y + roomWidth - 1; j++) { // Corrected here
                        if (i == x || j == y || i == x + roomWidth - 1 || j == y + roomWidth - 1) { // Corrected here
                            world[i][j] = Tileset.WALL;
                            continue;
                        }
                        world[i][j] = Tileset.FLOOR;
                        positionOftheRoom[i][j] = Tileset.FLOOR;
                    }
                }
                return;
            case 3:
                for (int i = x; i <= x + roomWidth - 1; i++) {
                    for (int j = y; j <= y + roomHeight - 1; j++) {
                        if (i == x || j == y || i == x + roomWidth - 1 || j == y + roomHeight - 1) {
                            world[i][j] = Tileset.WALL;
                            continue;
                        }
                        world[i][j] = Tileset.FLOOR;
                        positionOftheRoom[i][j] = Tileset.FLOOR;
                    }
                }
                return;
            case 4:
                for (int i = x; i <= x + roomWidth - 1; i++) {
                    for (int j = y; j <= y + roomHeight - 1; j++) {
                        if (i == x || j == y || i == x + roomWidth - 1 || j == y + roomHeight - 1) {
                            world[i][j] = Tileset.WALL;
                            continue;
                        }
                        world[i][j] = Tileset.FLOOR;
                        positionOftheRoom[i][j] = Tileset.FLOOR;
                    }
                }
                return;
            case 5:
                for (int i = x; i <= x + roomWidth - 1; i++) {
                    for (int j = y; j <= y + roomHeight - 1; j++) {
                        if (i == x || j == y || i == x + roomWidth - 1 || j == y + roomHeight - 1) {
                            world[i][j] = Tileset.WALL;
                            continue;
                        }
                        world[i][j] = Tileset.FLOOR;
                        positionOftheRoom[i][j] = Tileset.FLOOR;
                    }
                }
                return;
        }
    }

    private void randomlyBuildRoom() {
        int maxAttempt = 100;
        int attempt = 0;
        while (attempt < maxAttempt) {
            int roomWidth = RANDOM.nextInt(5, 10);
            int roomHeight = RANDOM.nextInt(5, 10);

            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);

            if (!isOverlaporOutbound(x, y, roomWidth, roomHeight)) {
                buildRoom(x, y, roomWidth, roomHeight);
                Rooms.add(new Room(x, y, roomWidth, roomHeight));

            }
            attempt++;
        }
    }

}
