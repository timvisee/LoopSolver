package com.timvisee.loopsolver.grid;

public class LoopGrid {

    /** Grid width. */
    private int width;
    /** Grid heigth. */
    private int height;

    /**
     * Constructor.
     *
     * @param width Grid width.
     * @param height Grid height.
     */
    public LoopGrid(int width, int height) {
        // Set the width and height
        this.width = width;
        this.height = height;
    }

    /**
     * Get the grid width.
     *
     * @return Grid width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the grid height.
     *
     * @return Grid height.
     */
    public int getHeight() {
        return this.height;
    }
}
