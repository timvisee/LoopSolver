package com.timvisee.loopsolver.tile;

import com.timvisee.loopsolver.util.MathUtils;

public enum LoopTileSide {

    /** Enum sides. */
    TOP(0),
    RIGHT(1),
    BOTTOM(2),
    LEFT(3);

    /** Side number. */
    private final int side;

    /**
     * Constructor.
     *
     * @param side Side number.
     */
    LoopTileSide(int side) {
        this.side = side;
    }

    /**
     * Get the side number.
     *
     * @return Side number.
     */
    public int side() {
        return side;
    }

    /**
     * Get the side enum by a side value.
     *
     * @param side Side value.
     *
     * @return The enum.
     */
    public static LoopTileSide bySide(int side) {
        // Mod the side
        side = MathUtils.realMod(side, LoopTile.TILE_SIDES);

        // Loop through the sides and return the correct one
        for(LoopTileSide entry : LoopTileSide.values())
            if(entry.side() == side)
                return entry;

        // Return null
        return null;
    }

    /**
     * Get the opposite side.
     *
     * @return Opposite side.
     */
    public LoopTileSide opposite() {
        return bySide(this.side() + 2);
    }
}
