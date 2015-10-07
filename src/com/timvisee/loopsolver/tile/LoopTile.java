package com.timvisee.loopsolver.tile;

public class LoopTile {

    /** Number of tile sides. */
    public static final int TILE_SIDES = 4;

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
}
