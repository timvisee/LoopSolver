package com.timvisee.loopsolver.tile;

import com.timvisee.loopsolver.util.MathUtils;

public class LoopTile {

    /** Number of tile sides. */
    public static final int TILE_SIDES = 4;

    /** Number to rotate tile clockwise. */
    public static final int TILE_ROTATE_CLOCKWISE = 1;
    /** Number to rotate tile counterclockwise. */
    public static final int TILE_ROTATE_COUNTERCLOCKWISE = 3;
    /** Number to rotate tile half. */
    public static final int TILE_ROTATE_HALF = 2;

    /** Defines the tile sides, true if a side can connect, false if not. */
    private boolean[] sides = new boolean[TILE_SIDES];

    /**
     * Constructor.
     *
     * This will create an empty tile.
     */
    public LoopTile() {
        this(false, false, false, false);
    }

    /**
     * Constructor.
     *
     * @param top True if this side is connectible, false if not.
     * @param right True if this side is connectible, false if not.
     * @param bottom True if this side is connectible, false if not.
     * @param left True if this side is connectible, false if not.
     */
    public LoopTile(boolean top, boolean right, boolean bottom, boolean left) {
        this(new boolean[]{top, right, bottom, left});
    }

    /**
     * Constructor.
     *
     * @param sides An array with booleans to specify what sides are connectible.
     */
    public LoopTile(boolean[] sides) {
        this.setSides(sides);
    }

    /**
     * Check whether a specific side of the tile is connectible.
     *
     * @param side The side.
     *
     * @return True if it's connectible, false if not.
     */
    public boolean getSide(LoopTileSide side) {
        return this.sides[side.side()];
    }

    /**
     * Set whether a specific side is connectible.
     *
     * @param side The side.
     * @param connectible True if the side is connectible, false if not.
     */
    public void setSide(LoopTileSide side, boolean connectible) {
        this.sides[side.side()] = connectible;
    }

    /**
     * Get the loop tile sides in an boolean array.
     *
     * @return Sides.
     */
    public boolean[] getSides() {
        return this.sides;
    }

    /**
     * Set the sides of the tile.
     *
     * @param sides The sides in an array.
     */
    public void setSides(boolean[] sides) {
        System.arraycopy(sides, 0, this.sides, 0, TILE_SIDES);
    }

    /**
     * Set the sides of the tile.
     *
     * @param top True if this side is connectible, false if not.
     * @param right True if this side is connectible, false if not.
     * @param bottom True if this side is connectible, false if not.
     * @param left True if this side is connectible, false if not.
     */
    public void setSides(boolean top, boolean right, boolean bottom, boolean left) {
        setSides(new boolean[]{top, right, bottom, left});
    }

    /**
     * Make this tile empty.
     */
    public void empty() {
        // Set the sides, to make this tile empty
        setSides(false, false, false, false);
    }

    /**
     * Check if this tile is empty. (If it doesn't have any side that can connect to anything).
     *
     * @return True if the tile is empty, false otherwise.
     */
    public boolean isEmpty() {
        // Loop through the sides array, return false if any side is true
        for(boolean side : this.sides)
            if(side)
                return false;

        // The tile seems to be empty, return true
        return true;
    }

    /**
     * Rotate the tile by the given amount. The amount of the number of quarters to rotate.
     *
     * @param amount Number of quarters to rotate.
     */
    public void rotate(int amount) {
        // Get the modulo value
        amount = MathUtils.realMod(amount, TILE_SIDES);

        // Shift the values by the given amount
        for(int i = 0; i < amount; i++) {
            // Shift the values by one position
            boolean end = this.sides[this.sides.length - 1];
            System.arraycopy(this.sides, 0, this.sides, 1, this.sides.length - 1);
            this.sides[0] = end;
        }
    }
}
