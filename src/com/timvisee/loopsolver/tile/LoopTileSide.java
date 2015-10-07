package com.timvisee.loopsolver.tile;

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
}
