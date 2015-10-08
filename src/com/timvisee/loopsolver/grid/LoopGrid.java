package com.timvisee.loopsolver.grid;

import com.timvisee.loopsolver.App;
import com.timvisee.loopsolver.tile.LoopTile;
import com.timvisee.loopsolver.tile.LoopTileDrawable;

import java.util.ArrayList;
import java.util.List;

public class LoopGrid {

    /** Grid width. */
    private int width;
    /** Grid height. */
    private int height;

    /** List of tiles in this grid. */
    private List<LoopTile> tiles = new ArrayList<>();

    /** The tile the solver is currently working on. */
    private LoopTile workingTile = null;
    /** The tile the solver is currently checking. */
    private LoopTile checkingTile = null;

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
        // Make sure the position isn't negative
        if(x < 0 || y < 0)
            return -1;

        // Make sure the x or y position isn't out of bound
        if(x >= getWidth() || y >= getHeight())
            return -1;

        // Return the position
        return x + (y * this.getWidth());
    }

    /**
     * Get the x coordinate by a position.
     *
     * @param position The position.
     *
     * @return The x coordinate.
     */
    public int getTilePositionX(int position) {
        // Make sure the position is valid, return -1 if not
        if(!isPositionInGrid(position))
            return -1;

        // Return the x coordinate
        return position % getWidth();
    }

    /**
     * Get the y coordinate by a position.
     *
     * @param position The position.
     *
     * @return The y coordinate.
     */
    public int getTilePositionY(int position) {
        // Make sure the position is valid, return -1 if not
        if(!isPositionInGrid(position))
            return -1;

        // Return the y coordinate
        return position / getWidth();
    }

    /**
     * Check whether the specified position is in the grid.
     *
     * @param x The x position.
     * @param y The y position.
     *
     * @return True if this position is inside the grid, false if not.
     */
    public boolean isPositionInGrid(int x, int y) {
        return isPositionInGrid(getTilePosition(x, y));
    }

    /**
     * Check whether the specified position is in the grid.
     *
     * @param position The position.
     *
     * @return True if the position is in the grid, false if not.
     */
    public boolean isPositionInGrid(int position) {
        return position >= 0 && position < getTotal();
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
        // Make sure the tile is in the grid, return null if not
        if(!isPositionInGrid(position))
            return null;

        // Return the tile
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
     *
     * @return True if a tile was changed, false if not because it wasn't in the grid.
     */
    public boolean setTile(int position, LoopTile tile) {
        // Make sure the position is in the grid, return false if not
        if(!isPositionInGrid(position))
            return false;

        // Set the tile, return the result
        this.tiles.set(position, tile);
        return true;
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
            this.tiles.add(LoopTile.createEmpty(this, getTilePositionX(i), getTilePositionY(i)));
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
            this.tiles.add(LoopTile.createRandom(this, getTilePositionX(i), getTilePositionY(i)));
    }

    public LoopTile getWorkingTile() {
        return workingTile;
    }

    public void setWorkingTile(LoopTile workingTile) {
        // Change the working tile
        this.workingTile = workingTile;

        // Repaint the frame
        App.instance.getGridFrame().repaint();
    }

    public LoopTile getCheckingTile() {
        return checkingTile;
    }

    public void setCheckingTile(LoopTile checkingTile) {
        // Change the checking tile
        this.checkingTile = checkingTile;

        // Repaint the frame
        App.instance.getGridFrame().repaint();
    }
}
