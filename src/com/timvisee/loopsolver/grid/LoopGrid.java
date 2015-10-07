package com.timvisee.loopsolver.grid;

import com.timvisee.loopsolver.tile.LoopTile;

import java.util.ArrayList;
import java.util.List;

public class LoopGrid {

    /** Grid width. */
    private int width;
    /** Grid height. */
    private int height;

    /** List of tiles in this grid. */
    private List<LoopTile> tiles = new ArrayList<LoopTile>();

    /**
     * Constructor.
     *
     * @param width Grid width.
     * @param height Grid height.
     */
    public LoopGrid(int width, int height) {
        setSize(width, height);
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

    /**
     * Set the size of the grid.
     * This fills the grid with empty tiles.
     *
     * @param width Grid width.
     * @param height Grid height.
     */
    public void setSize(int width, int height) {
        // Set the width and height
        this.width = width;
        this.height = height;

        // Fill the list with empty tiles
        fillWithEmpty();
    }

    /**
     * Get the total number of tiles in this grid.
     *
     * @return Total number of tiles in this grid.
     */
    public int getTotal() {
        return this.getWidth() * this.getHeight();
    }

    /**
     * Get the tile list position for a specific location.
     *
     * @param x The x coordinate (horizontal from the left), zero-indexed.
     * @param y The y coordinate (vertical from the top), zero-indexed.
     *
     * @return The tile list position for this tile.
     */
    public int getTilePosition(int x, int y) {
        // TODO: Make sure the position isn't out of bound!
        return x + (y * this.getWidth());
    }

    /**
     * Get the tile at a specific location.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     *
     * @return The tile.
     */
    public LoopTile getTile(int x, int y) {
        return getTile(getTilePosition(x, y));
    }

    /**
     * Get the tile at a specific position.
     *
     * @param position The position in the tile list.
     *
     * @return The tile.
     */
    public LoopTile getTile(int position) {
        return this.tiles.get(position);
    }

    /**
     * Set the tile at a specific location.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     *
     * @param tile The tile.
     */
    public void setTile(int x, int y, LoopTile tile) {
        setTile(getTilePosition(x, y), tile);
    }

    /**
     * Set the tile at a specific position.
     *
     * @param position The position.
     * @param tile The tile.
     */
    public void setTile(int position, LoopTile tile) {
        this.tiles.set(position, tile);
    }

    /**
     * Get the full list of tiles.
     *
     * @return Full list of tiles.
     */
    public List<LoopTile> getTiles() {
        return this.tiles;
    }

    /**
     * Fill the grid with empty tiles.
     * Note: This replaces all current tiles.
     */
    public void fillWithEmpty() {
        // Clear the tiles list
        this.tiles.clear();

        // Fill the list with empty tiles
        for(int i = 0; i < getTotal(); i++)
            this.tiles.add(LoopTile.createEmpty());
    }

    /**
     * Fill the grid with random tiles.
     * Note: This replaces all current tiles.
     */
    public void fillWithRandom() {
        // Clear the tiles list
        this.tiles.clear();

        // Fill the list with empty tiles
        for(int i = 0; i < getTotal(); i++)
            this.tiles.add(LoopTile.createRandom());
    }
}
