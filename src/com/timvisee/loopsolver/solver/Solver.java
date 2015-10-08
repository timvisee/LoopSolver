package com.timvisee.loopsolver.solver;

import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.tile.LoopTile;
import com.timvisee.loopsolver.tile.LoopTileSide;
import com.timvisee.loopsolver.util.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class Solver implements Runnable {

    /** The grid to solve. */
    private LoopGrid grid;

    /**
     * Constructor.
     *
     * @param grid The grid to solve.
     */
    public Solver(LoopGrid grid) {
        this.grid = grid;
    }

    /**
     * Get the grid.
     *
     * @return The grid.
     */
    public LoopGrid getGrid() {
        return this.grid;
    }

    @Override
    public void run() {
        // Loop through all tiles and try to solve them
        for(int i = 0; i < this.grid.getTotal(); i++)
            solveTile(this.grid.getTile(i));
    }

    public void solveTiles(List<LoopTile> tiles) {
        tiles.forEach(this::solveTile);
    }

    public void solveTile(LoopTile tile) {
        // Make sure the tile isn't solved already
        if(tile.isSolved())
            return;

        if(rotateIfOnePossible(tile))
            solveTiles(tile.getNeighbours());
    }

    public boolean rotateIfOnePossible(LoopTile tile) {
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // Make sure the tile isn't solved already
        if(tile.isSolved())
            return true;

        // Make sure the tile isn't correct already
        if(tile.isCorrect()) {
            tile.setSolved(true);
            return true;
        }

        // Get the current array of sides
        boolean[] sidesOriginal = tile.getSides();
        boolean[] sides = sidesOriginal.clone();

        // Check whether there is just one rotation this tile fits in
        int rotate = -1;
        boolean rotateMust = false;
        boolean[] sidesRotated = new boolean[sides.length];
        for(int i = 0; i < 4; i++) {
            // Shift the array by one
            if(i > 0)
                sides = ArrayUtils.shiftArrayDown(sides);

            // Check whether it fits
            boolean fits = true;
            boolean fitsMust = false;
            for(LoopTileSide side : LoopTileSide.values()) {
                // Get the neighbour tile
                LoopTile neighbour = tile.getNeighbour(side);

                // Check whether it exists
                if(neighbour == null) {
                    if(sides[side.side()]) {
                        fits = false;
                        break;
                    } else
                        continue;
                }

                // Check whether it can't fit
                if(neighbour.getSideCanNotConnectSelf(side.opposite()) && sides[side.side()]) {
                    fits = false;
                    break;
                }

                // Check whether it can't fit
                if(neighbour.getSideMustConnectSelf(side.opposite()) && !sides[side.side()]) {
                    fits = false;
                    break;
                }

                // Check whether this side must fit
                if(neighbour.getSideMustConnectSelf(side.opposite())) {
                    fitsMust = true;
                }
            }

            // Store this rotation, if it's the first rotation that fit's
            if(fits) {
                if(rotate < 0 || (!rotateMust && fitsMust)) {
                    rotate = i;
                    sidesRotated = sides.clone();
                    rotateMust = fitsMust || rotateMust;

                } else if(!Arrays.equals(sides, sidesRotated))
                    return false;
            }
        }

        // If a rotation is specified, rotate the tile
        if(rotate >= 0) {
            // Rotate the tile
            tile.rotate(rotate);

            // Set and return the result
            tile.setSolved(true);
            return true;
        }

        // Tile didn't just fit in one position, return  false
        return false;
    }
}
