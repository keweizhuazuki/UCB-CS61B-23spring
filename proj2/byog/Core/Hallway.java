package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;

public class Hallway {
    public static Random RANDOM; // Do not initialize it here.

    private static Position getRandomWallPosition(TETile[][] world) {
        int attempts = 0;
        while (attempts < 1000) {
            int startX = RANDOM.nextInt(world.length);
            int startY = RANDOM.nextInt(world[0].length);
            if (world[startX][startY] == Tileset.WALL) {
                return new Position(startX, startY);
            }
            attempts++;
        }
        return null;

    }

    private static boolean nearFloor(Position pos, TETile[][] world) {
        int x = pos.x;
        int y = pos.y;

        // Add boundary checks
        if (x + 1 < world.length && world[x + 1][y] == Tileset.FLOOR) {
            return true;
        }
        if (x - 1 >= 0 && world[x - 1][y] == Tileset.FLOOR) {
            return true;
        }
        if (y + 1 < world[0].length && world[x][y + 1] == Tileset.FLOOR) {
            return true;
        }
        if (y - 1 >= 0 && world[x][y - 1] == Tileset.FLOOR) {
            return true;
        }
        return false;
    }

    private static int deterType(Position pos, TETile[][] world) {
        int x = pos.x;
        int y = pos.y;

        if (x - 1 >= 0 && world[x - 1][y] == Tileset.FLOOR) {
            return 1;
        }
        if (x + 1 < world.length && world[x + 1][y] == Tileset.FLOOR) {
            return 2;
        }
        if (y - 1 >= 0 && world[x][y - 1] == Tileset.FLOOR) {
            return 3;
        }
        if (y + 1 < world[0].length && world[x][y + 1] == Tileset.FLOOR) {
            return 4;
        }
        return -1;
    }

    private static boolean insideBound(Position pos, int width, int height, int Hallywaytype, TETile[][] world) {
        int x = pos.x;
        int y = pos.y;

        if (Hallywaytype == 1) {
            return x + width - 1 < world.length;
        }
        if (Hallywaytype == 2) {
            return x - width + 1 > 0;
        }
        if (Hallywaytype == 3) {
            return y + height - 1 < world[0].length;
        }
        if (Hallywaytype == 4) {
            return y - height + 1 > 0;
        }
        return false;

    }

    private static void markRoomConnected(Position pos, ArrayList<Room> Rooms) {
        for (Room room : Rooms) {
            if (pos.x >= room.getX() && pos.x <= room.getX() + room.getWidth() - 1 &&
                    pos.y >= room.getY() && pos.y <= room.getY() + room.getHeight() - 1) {
                room.hasHallwayConnected = true;
                break;
            }
        }
    }

    private static boolean isReachfloor(Position pos, int width, int height, int Hallywaytype, TETile[][] world) {
        int x = pos.x;
        int y = pos.y;

        switch (Hallywaytype) {
            case 1:
                for (int i = x; i < x + width - 1; i++) {
                    if (i + 1 >= world.length) {
                        return false;
                    }
                    if (world[i + 1][y] == Tileset.WALL && world[i + 2][y] == Tileset.FLOOR) {
                        return true;
                    }
                    if (world[i + 1][y] == Tileset.WALL && world[i + 2][y] == Tileset.WALL) {
                        return false;
                    }
                }
                return false;

            case 2:
                for (int i = x; i > x - width + 1; i--) {
                    if (i - 1 <= 0) {
                        return false;
                    }
                    if (world[i - 1][y] == Tileset.WALL && world[i - 2][y] == Tileset.FLOOR) {
                        return true;
                    }
                    if (world[i - 1][y] == Tileset.WALL && world[i - 2][y] == Tileset.WALL) {
                        return false;
                    }
                }
                return false;

            case 3:
                for (int i = y; i < y + height - 1; i++) {
                    if (i + 1 >= world[0].length) {
                        return false;
                    }
                    if (world[x][i + 1] == Tileset.WALL && world[x][i + 2] == Tileset.FLOOR) {
                        return true;
                    }
                    if (world[x][i + 1] == Tileset.WALL && world[x][i + 2] == Tileset.WALL) {
                        return false;
                    }
                }
                return false;

            case 4:
                for (int i = y; i > y - height + 1; i--) {
                    if (i - 1 <= 0) {
                        return false;
                    }
                    if (world[x][i - 1] == Tileset.WALL && world[x][i - 2] == Tileset.FLOOR) {
                        return true;
                    }
                    if (world[x][i - 1] == Tileset.WALL && world[x][i - 2] == Tileset.WALL) {
                        return false;
                    }
                }
                return false;
        }

        return false;
    }

    private static void buildHallway(Position pos, int width, int height, int Hallywaytype, TETile[][] world,
            ArrayList<Room> Rooms) {
        int x = pos.x;
        int y = pos.y;

        switch (Hallywaytype) {
            case 1:
                for (int i = x; i < x + width - 1; i++) {
                    world[i][y] = Tileset.FLOOR;
                    markRoomConnected(new Position(x, y), Rooms);
                    world[i][y + 1] = Tileset.WALL;
                    world[i][y - 1] = Tileset.WALL;

                    if (world[i + 1][y] == Tileset.WALL && world[i + 2][y] == Tileset.FLOOR) {
                        world[i + 1][y] = Tileset.FLOOR;
                        break;
                    }

                    if (world[i + 1][y + 1] == Tileset.FLOOR || world[i + 1][y - 1] == Tileset.FLOOR) {
                        break;
                    }
                }
                break;

            case 2:
                for (int i = x; i > x - width + 1; i--) {
                    world[i][y] = Tileset.FLOOR;
                    markRoomConnected(new Position(x, y), Rooms);
                    world[i][y + 1] = Tileset.WALL;
                    world[i][y - 1] = Tileset.WALL;

                    if (world[i - 1][y] == Tileset.WALL && world[i - 2][y] == Tileset.FLOOR) {
                        world[i - 1][y] = Tileset.FLOOR;
                        break;
                    }

                    if (world[i - 1][y + 1] == Tileset.FLOOR || world[i - 1][y - 1] == Tileset.FLOOR) {
                        break;
                    }
                }
                break;

            case 3:
                for (int i = y; i < y + height - 1; i++) {
                    world[x][i] = Tileset.FLOOR;
                    markRoomConnected(new Position(x, y), Rooms);
                    world[x + 1][i] = Tileset.WALL;
                    world[x - 1][i] = Tileset.WALL;

                    if (world[x][i + 1] == Tileset.WALL && world[x][i + 2] == Tileset.FLOOR) {
                        world[x][i + 1] = Tileset.FLOOR;
                        break;
                    }

                    if (world[x + 1][i + 1] == Tileset.FLOOR || world[x - 1][i + 1] == Tileset.FLOOR) {
                        break;
                    }
                }
                break;

            case 4:
                for (int i = y; i > y - height + 1; i--) {
                    world[x][i] = Tileset.FLOOR;
                    markRoomConnected(new Position(x, y), Rooms);
                    world[x + 1][i] = Tileset.WALL;
                    world[x - 1][i] = Tileset.WALL;

                    if (world[x][i - 1] == Tileset.WALL && world[x][i - 2] == Tileset.FLOOR) {
                        world[x][i - 1] = Tileset.FLOOR;
                        break;
                    }

                    if (world[x + 1][i - 1] == Tileset.FLOOR || world[x - 1][i - 1] == Tileset.FLOOR) {
                        break;
                    }
                }
                break;
        }
    }

    public static void generateHallWay(TETile[][] world, ArrayList<Room> Rooms, long seed) {
        RANDOM = new Random(seed); // Initialize RANDOM with the provided seed.
        int maxAttempt = 150;
        int attempt = 0;
        while (attempt <= maxAttempt) {
            Position start = getRandomWallPosition(world);
            int Hallywaytype = deterType(start, world);
            int width = RANDOM.nextInt(7, 10);
            int height = RANDOM.nextInt(7, 10);
            if (nearFloor(start, world) && insideBound(start, width, height, Hallywaytype, world)
                    && isReachfloor(start, width, height, Hallywaytype, world)) {
                buildHallway(start, width, height, Hallywaytype, world, Rooms);

            }
            attempt++;

        }
    }
}
