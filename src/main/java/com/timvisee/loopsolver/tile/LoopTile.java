package com.timvisee.loopsolver.tile;

import com.timvisee.loopsolver.App;
import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.util.ArrayUtils;
import com.timvisee.loopsolver.util.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    /** True if this tile is solved, false if not. */
    private boolean solved = false;

    /** The x and y coordinate of the tile in the grid. */
    private int x, y;
    /** The loop grid instance. */
    private LoopGrid grid;

    /**
     * Constructor.
     *
     * This will create an empty tile.
     */
    public LoopTile(LoopGrid grid, int x, int y) {
        this(grid, x, y, false, false, false, false);
    }

    /**
     * Constructor.
     *

     * @param top True if this side is connectible, false if not.
     * @param right True if this side is connectible, false if not.
     * @param bottom True if this side is connectible, false if not.
     * @param left True if this side is connectible, false if not.
     */
    public LoopTile(LoopGrid grid, int x, int y, boolean top, boolean right, boolean bottom, boolean left) {
        this(grid, x, y, new boolean[]{top, right, bottom, left});
    }

    /**
     * Constructor.
     *
     * @param sides An array with booleans to specify what sides are connectible.
     */
    public LoopTile(LoopGrid grid, int x, int y, boolean[] sides) {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.setSides(sides);
    }

    /**
     * Create an empty tile.
     *
     * @return The empty tile.
     */
    public static LoopTile createEmpty(LoopGrid grid, int x, int y) {
        return new LoopTile(grid, x, y);
    }

    /**
     * Create a random tile.
     *
     * @return The random tile.
     */
    public static LoopTile createRandom(LoopGrid grid, int x, int y) {
        // Create a random object
        Random rand = new Random();

        // Create and return the tile
        return new LoopTile(grid, x, y, rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean());
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
     * Check whether a specific side of the tile is connectible.
     *
     * @param side The side.
     *
     * @return True if it's connectible, false if not.
     */
    public boolean getSide(int side) {
        return this.sides[MathUtils.realMod(side, TILE_SIDES)];
    }

    /**
     * Set whether a specific side is connectible.
     *
     * @param side The side.
     * @param connectible True if the side is connectible, false if not.
     */
    public void setSide(LoopTileSide side, boolean connectible) {
        this.sides[side.side()] = connectible;
        this.setSolved(false);
    }

    /**
     * Get the loop tile sides in an boolean array.
     *
     * @return Sides.
     */
    public boolean[] getSides() {
        return this.sides.clone();
    }

    /**
     * Get the number of connectible sides.
     *
     * @return Connectible sides.
     */
    public int getConnectibleSides() {
        // Count the number of connectible sides
        int sides = 0;
        for(boolean side : this.sides)
            if(side)
                sides++;

        // Return the number of connectible sides
        return sides;
    }

    /**
     * Set the sides of the tile.
     *
     * @param sides The sides in an array.
     */
    public void setSides(boolean[] sides) {
        System.arraycopy(sides, 0, this.sides, 0, TILE_SIDES);
        this.setSolved(false);
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
        this.setSolved(false);
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
        // Shift the array
        this.sides = ArrayUtils.shiftArrayDown(this.sides, amount);

        // Reset the solved flag
        this.setSolved(false);
    }

    /**
     * Rotate the tile clockwise.
     */
    public void rotateClockwise() {
        rotate(TILE_ROTATE_CLOCKWISE);
    }

    /**
     * Check whether this tile is a straight piece.
     *
     * @return True if the tile is a straight piece.
     */
    public boolean isStraight() {
        return (getSide(LoopTileSide.TOP) && getSide(LoopTileSide.BOTTOM) && !(getSide(LoopTileSide.LEFT) || getSide(LoopTileSide.RIGHT)) ||
                getSide(LoopTileSide.LEFT) && getSide(LoopTileSide.RIGHT) && !(getSide(LoopTileSide.TOP) || getSide(LoopTileSide.BOTTOM)));
    }

    /**
     * Check whether this piece is straight and horizontal.
     *
     * @return True if horizontal, false if not.
     */
    public boolean isHorizontal() {
        // Make sure the piece is straight
        if(!isStraight())
            return false;

        // Return the result
        return getSide(LoopTileSide.LEFT) || getSide(LoopTileSide.RIGHT);
    }

    /**
     * Check whether this piece is straight and vertical.
     *
     * @return True if vertical, false if not.
     */
    public boolean isVertical() {
        // Make sure the piece is straight
        if(!isStraight())
            return false;

        // Return the result
        return getSide(LoopTileSide.TOP) || getSide(LoopTileSide.BOTTOM);
    }

    /**
     * Check whether this tile is a corner piece.
     *
     * @return True if the tile is a corner piece, false if not.
     */
    public boolean isCorner() {
        return getConnectibleSides() == 2 && !isStraight();
    }

    /**
     * Check whether this tile is an end piece.
     *
     * @return True if the tile is an end piece, false if not.
     */
    public boolean isEnd() {
        return getConnectibleSides() == 1;
    }

    /**
     * Check whether this tile is T-shaped.
     *
     * @return True if the tile is T-shaped, false if not.
     */
    public boolean isTShape() {
        return getConnectibleSides() == 3;
    }

    /**
     * Check whether this tile is cross shaped.
     *
     * @return True if the tile is cross shaped, false if not.
     */
    public boolean isCross() {
        return getConnectibleSides() == 4;
    }

    /**
     * Check whether this tile is solved.
     *
     * @return True if this tile is solved.
     */
    public boolean isSolved() {
        return this.solved;
    }

    /**
     * Set whether this tile is solved.
     *
     * @param solved True if solved, false if not.
     */
    public void setSolved(boolean solved) {
        this.solved = solved;

        // TODO: Improve this!
        // Redraw the drawable grid
        if(App.instance.getDrawableGrid() != null)
            App.instance.getDrawableGrid().repaint();
    }

    /**
     * Find the first non-connectible side, in a clockwise manner.
     *
     * @return First non-connectible side.
     */
    public LoopTileSide getFirstEmptySide() {
        // Return null if the tile is empty
        if(isEmpty())
            return null;

        // Set whether we past a connectible side
        boolean connectible = false;

        // Loop through all the sides to find the first non-connectible side
        for(int i = 0; i < TILE_SIDES * 2; i++) {
            // Get the side
            boolean side = getSide(i);

            // Return the side if this is the first empty side
            if(connectible && !side)
                return LoopTileSide.bySide(i);

            if(!connectible && side)
                connectible = true;
        }

        // Not found, return null
        return null;
    }












    /**
     * Get the x coordinate.
     *
     * @return The x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate.
     *
     * @return The y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Get the loop grid.
     *
     * @return The loop grid.
     */
    public LoopGrid getGrid() {
        return grid;
    }

    /**
     * Get a tile relative to this one.
     *
     * @param rx Relative x position.
     * @param ry Relative y position.
     *
     * @return The relative tile, or null.
     */
    public LoopTile getRelativeTile(int rx, int ry) {
        // Get the relative tile
        LoopTile relativeTile = this.grid.getTile(getX() + rx, getY() + ry);

        // Set the checking tile
        if(relativeTile != null && !relativeTile.isSolved()) {
            this.grid.setCheckingTile(relativeTile);
            try {
                Thread.sleep(25);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        } else
            this.grid.setCheckingTile(null);

        // Return the relative tile
        return relativeTile;
    }

    /**
     * Get the neighbour tile at the specified side.
     *
     * @param side The side.
     *
     * @return The neighbour tile, or null if it doesn't exist.
     */
    public LoopTile getNeighbour(LoopTileSide side) {
        return getRelativeTile(side, 1);
    }

    /**
     * Get a tile relative to this one, specified by a side and distance.
     *
     * @param side The side.
     * @param distance The distance.
     *
     * @return The relative tile, or null if it's invalid.
     */
    public LoopTile getRelativeTile(LoopTileSide side, int distance) {
        // Determine the relative x and y coordinates
        int rx = 0, ry = 0;

        switch(side) {
            case TOP:
                ry -= distance;
                break;
            case RIGHT:
                rx += distance;
                break;
            case BOTTOM:
                ry += distance;
                break;
            case LEFT:
                rx -= distance;
                break;
            default:
                return null;
        }

        // Return the relative tile
        return getRelativeTile(rx, ry);
    }

    /**
     * Return all neighbour tiles.
     *
     * @return All existing neighbour tiles.
     */
    public List<LoopTile> getNeighbours() {
        // Create a list with tiles
        List<LoopTile> tiles = new ArrayList<>();

        // Add the tiles around this one
        for(LoopTileSide side : LoopTileSide.values()) {
            // Get the relative tile
            LoopTile relativeTile = getRelativeTile(side, 1);

            // Add the tile if it isn't null
            if(relativeTile != null)
                tiles.add(relativeTile);
        }

        // Return the tiles around this one
        return tiles;
    }

    /**
     * Count the empty tiles around this one. Tiles outside of the grid are also count as empty tiles.
     *
     * @return Number of empty tiles.
     */
    public int getEmptyTilesAroundCount() {
        // Count empty tiles
        int empty = 0;

        // Loop through the tiles around this one, and check whether they're empty
        for(LoopTileSide side : LoopTileSide.values())
            if(getRelativeTile(side, 1) == null || getRelativeTile(side, 1).isEmpty())
                empty++;

        // Return the number of empty tiles
        return empty;
    }

    /**
     * Check whether this tile must connect at this side.
     *
     * @param side The side.
     *
     * @return True if it must connect, false if not.
     */
    public boolean getSideMustConnectSelf(LoopTileSide side) {
        // Return true if all sides are connectible
        if(isCross())
            return true;

        // Make sure the tile is solved
        if(!isSolved())
            return false;

        // Check whether this side is connectible
        return getSide(side);
    }

    /**
     * Check whether this tile must connect at this side.
     *
     * @param side The side.
     *
     * @return True if it must connect, false if not.
     */
    public boolean getSideMustConnectNeighbour(LoopTileSide side) {
        // Get the tile next to this one at the specified side
        LoopTile neighbour = getNeighbour(side);

        // Make sure the tile exists
        if(neighbour == null)
            return false;

        // Return true if all sides are connectible
        if(neighbour.isCross())
            return true;

        // Make sure the tile is solved
        if(!neighbour.isSolved())
            return false;

        // Check whether this side is connectible
        return neighbour.getSide(side.opposite());
    }

    /**
     * Check whether this tile can connect at this side.
     *
     * @param side The side.
     *
     * @return True if it can connect, false if not.
     */
    public boolean getSideCanConnectSelf(LoopTileSide side) {
        // Return true if all sides are connectible
        if(isCross())
            return true;

        // Check whether this side is connectible if it's already solved
        if(isSolved())
            return getSide(side.opposite());

        // Check whether the neighbour has enough connectible sides
        return !isEmpty();
    }

    /**
     * Check whether this tile can connect at this side.
     *
     * @param side The side.
     *
     * @return True if it can connect, false if not.
     */
    public boolean getSideCanConnectNeighbour(LoopTileSide side) {
        // Get the tile next to this one at the specified side
        LoopTile neighbour = getNeighbour(side);

        // Make sure the tile exists
        if(neighbour == null)
            return false;

        // Return true if all sides are connectible
        if(neighbour.isCross())
            return true;

        // Check whether this side is connectible if it's already solved
        if(neighbour.isSolved())
            return neighbour.getSide(side.opposite());

        // Check whether the neighbour has enough connectible sides
        return !neighbour.isEmpty();
    }

    /**
     * Check whether this tile can connect at this side.
     *
     * @param side The side.
     *
     * @return True if it can connect, false if not.
     */
    public boolean getSideCanNotConnectSelf(LoopTileSide side) {
        // Return true if all sides are connectible
        if(isCross())
            return false;

        // Check whether this side is not connectible if it's already solved
        if(isSolved())
            return !getSide(side);

        // Check whether the neighbour tile is empty
        return isEmpty();
    }

    /**
     * Check whether this tile can connect at this side.
     *
     * @param side The side.
     *
     * @return True if it can connect, false if not.
     */
    public boolean getSideCanNotConnectNeighbour(LoopTileSide side) {
        // Get the tile next to this one at the specified side
        LoopTile neighbour = getNeighbour(side);

        // Make sure the tile exists
        if(neighbour == null)
            return true;

        // Return true if all sides are connectible
        if(neighbour.isCross())
            return false;

        // Check whether this side is not connectible if it's already solved
        if(neighbour.isSolved())
            return !neighbour.getSide(side.opposite());

        // Check whether the neighbour tile is empty
        return neighbour.isEmpty();
    }

    /**
     * Get the number of sides that must connect.
     *
     * @return Number of sides that must connect.
     */
    public int getSideMustConnectNeighbourCount() {
        // Count the tiles
        int tiles = 0;

        // Loop through all the sides
        for(LoopTileSide side : LoopTileSide.values())
            if(getSideMustConnectNeighbour(side))
                tiles++;

        // Return the number of tiles
        return tiles;
    }

    /**
     * Get the number of sides that can connect.
     *
     * @return Number of sides that can connect.
     */
    public int getSideCanConnectNeighbourCount() {
        // Count the tiles
        int tiles = 0;

        // Loop through all the sides
        for(LoopTileSide side : LoopTileSide.values())
            if(getSideCanConnectNeighbour(side))
                tiles++;

        // Return the number of tiles
        return tiles;
    }

    /**
     * Get the number of sides that can't connect.
     *
     * @return Number of sides that can't connect.
     */
    public int getSideCanNotConnectNeighbourCount() {
        // Count the tiles
        int tiles = 0;

        // Loop through all the sides
        for(LoopTileSide side : LoopTileSide.values())
            if(getSideCanNotConnectNeighbour(side))
                tiles++;

        // Return the number of tiles
        return tiles;
    }

    public boolean isCorrect() {
        // Loop through all sides and check if they're correct
        for(LoopTileSide side : LoopTileSide.values())
            if(!isSideCorrect(side))
                return false;

        // All sides are correct
        return true;
    }

    public boolean isSideCorrect(LoopTileSide side) {
        return (getSide(side) && getSideMustConnectNeighbour(side)) || (!getSide(side) && getSideCanNotConnectNeighbour(side));
    }

    public boolean fitInCurrentState() {
        // Loop through the sides
        for(LoopTileSide side : LoopTileSide.values()) {
            // Get the neighbour on this side
            LoopTile neighbour = getNeighbour(side);

            // If the neighbour is null, make sure this side isn't connectible
            if(neighbour == null) {
                if(getSide(side))
                    return false;
                continue;
            }

            // Make sure both sides are, or aren't connectible
            if(getSide(side) != neighbour.getSide(side.opposite()))
                return false;
        }

        // The tile seems to fit, return true
        return true;
    }

    public boolean fitInSolvedCurrentState() {
        // Loop through the sides
        for(LoopTileSide side : LoopTileSide.values()) {
            // Get the neighbour on this side
            LoopTile neighbour = getNeighbour(side);

            // If the neighbour is null, make sure this side isn't connectible
            if(neighbour == null) {
                if(getSide(side))
                    return false;
                continue;
            }

            // Make sure the neighbour is solved
            if(!neighbour.isSolved())
                continue;

            // Make sure both sides are, or aren't connectible
            if(getSide(side) != neighbour.getSide(side.opposite()))
                return false;
        }

        // The tile seems to fit, return true
        return true;
    }
}
