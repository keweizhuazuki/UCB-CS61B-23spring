package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /*
     * x: size of hex
     * y: level of the x, the first level is 0
     */
    private static final Random RANDOM = new Random();

    public static int hexRowWidth(int x, int y) {
        int effectiveI = y;
        if (y >= x) {
            effectiveI = 2 * x - 1 - effectiveI;
        }
        return x + 2 * effectiveI;
    }

    public static int hexRowOffset(int x, int y) {
        int effectiveI = y;
        if (y >= x) {
            effectiveI = 2 * x - 1 - effectiveI;
        }
        return -effectiveI;
    }

    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int i = 0; i < width; i++) {
            int xcoor = p.x + i;
            int ycoor = p.y;
            world[xcoor][ycoor] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    /* s = size of the hexagon */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 0) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int i = 0; i < 2 * s; i++) {
            int thisRowY = p.y + i;
            int xRowStart = p.x + hexRowOffset(s, i);
            Position rowstartp = new Position(xRowStart, thisRowY);
            int rowwidth = hexRowWidth(s, i);
            addRow(world, rowstartp, rowwidth, t);
        }
    }

    public static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7); // Generate a random integer between 0 and 4
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOOR;
            case 2:
                return Tileset.LOCKED_DOOR;
            case 3:
                return Tileset.FLOWER;
            case 4:
                return Tileset.GRASS;
            case 5:
                return Tileset.MOUNTAIN;
            case 6:
                return Tileset.PLAYER;
            case 7:
                return Tileset.SAND;
            default:
                return Tileset.NOTHING;
        }
    }

    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int x) {
        for (int i = 0; i < x; i++) {
            TETile t = randomTile(); // Tile type for the hexagon
            Position newP = new Position(p.x, p.y + i * 6); // New Position object
            addHexagon(world, newP, 3, t); // Create a hexagon at the new position
        }
    }

    public static void main(String[] args) {
        int width = 30;
        int height = 30;

        // Step 0: Initialize TERenderer
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        // Step 1: Initialize the world array
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // Step 2: Add hexagons or other shapes
        Position p1 = new Position(14, 0); // Initialize Position object
        drawRandomVerticalHexes(world, p1, 5);
        Position p2 = new Position(4, 6);
        drawRandomVerticalHexes(world, p2, 3);
        Position p3 = new Position(9, 3);
        drawRandomVerticalHexes(world, p3, 4);
        Position p4 = new Position(19, 3);
        drawRandomVerticalHexes(world, p4, 4);
        Position p5 = new Position(24, 6);
        drawRandomVerticalHexes(world, p5, 3);
        ter.renderFrame(world);
    }

}
