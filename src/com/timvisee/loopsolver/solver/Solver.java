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
//        // Loop through all the tiles in the whole grid
//        for(LoopTile tile : this.grid.getTiles()) {
//            // Solve empty tiles
//            if(tile.isEmpty())
//                tile.setSolved(true);
//
//            // Solve tiles with 4 sides
//            if(tile.getConnectibleSides() == 4)
//                tile.setSolved(true);
//        }

        for(int i = 0; i < this.grid.getTotal(); i++)
            solveTile(this.grid.getTile(i));

        /*// Loop through all tiles on the top and bottom side
        for(int x = 1; x < (this.grid.getWidth() - 1); x++) {
            // Get the tile
            LoopTile tileTop = this.grid.getTile(x, 0);
            LoopTile tileBottom = this.grid.getTile(x, this.grid.getHeight() - 1);

            // Rotate the piece if it's straight and vertical
            if(tileTop.isStraight()) {
                if(tileTop.isVertical())
                    tileTop.rotateClockwise();
                tileTop.setSolved(true);
                solveTiles(tileTop.getNeighbours());
            }
            if(tileBottom.isStraight()) {
                if(tileBottom.isVertical())
                    tileBottom.rotateClockwise();
                tileBottom.setSolved(true);
                solveTiles(tileBottom.getNeighbours());
            }

            // Properly rotate T-Shapes
            if(tileTop.isTShape()) {
                tileTop.rotate(LoopTileSide.TOP.side() - tileTop.getFirstEmptySide().side());
                tileTop.setSolved(true);
                solveTiles(tileTop.getNeighbours());
            }
            if(tileBottom.isTShape()) {
                tileBottom.rotate(LoopTileSide.BOTTOM.side() - tileBottom.getFirstEmptySide().side());
                tileBottom.setSolved(true);
                solveTiles(tileBottom.getNeighbours());
            }
        }

        // Loop through all tiles on the left and right
        for(int y = 1; y < (this.grid.getHeight() - 1); y++) {
            // Get the tile
            LoopTile tileLeft = this.grid.getTile(0, y);
            LoopTile tileRight = this.grid.getTile(this.grid.getWidth() - 1, y);

            // Rotate the piece if it's straight and vertical
            if(tileLeft.isStraight()) {
                if(tileLeft.isHorizontal())
                    tileLeft.rotateClockwise();
                tileLeft.setSolved(true);
                solveTiles(tileLeft.getNeighbours());
            }
            if(tileRight.isStraight()) {
                if(tileRight.isHorizontal())
                    tileRight.rotateClockwise();
                tileRight.setSolved(true);
                solveTiles(tileRight.getNeighbours());
            }

            // Properly rotate T-Shapes
            if(tileLeft.isTShape()) {
                tileLeft.rotate(LoopTileSide.LEFT.side() - tileLeft.getFirstEmptySide().side());
                tileLeft.setSolved(true);
                solveTiles(tileLeft.getNeighbours());
            }
            if(tileRight.isTShape()) {
                tileRight.rotate(LoopTileSide.RIGHT.side() - tileRight.getFirstEmptySide().side());
                tileRight.setSolved(true);
                solveTiles(tileRight.getNeighbours());
            }
        }

        // Get the corner pieces
        LoopTile topLeft = this.grid.getTile(0, 0);
        LoopTile topRight = this.grid.getTile(this.grid.getWidth() - 1, 0);
        LoopTile bottomLeft = this.grid.getTile(0, this.grid.getHeight() - 1);
        LoopTile bottomRight = this.grid.getTile(this.grid.getWidth() - 1, this.grid.getHeight() - 1);

        // Rotate the corners correctly if they're corner pieces
        if(topLeft.isCorner()) {
            topLeft.rotate(LoopTileSide.LEFT.side() - topLeft.getFirstEmptySide().side());
            topLeft.setSolved(true);
            solveTiles(topLeft.getNeighbours());
        }
        if(topRight.isCorner()) {
            topRight.rotate(LoopTileSide.TOP.side() - topRight.getFirstEmptySide().side());
            topRight.setSolved(true);
            solveTiles(topRight.getNeighbours());
        }
        if(bottomLeft.isCorner()) {
            bottomLeft.rotate(LoopTileSide.BOTTOM.side() - bottomLeft.getFirstEmptySide().side());
            bottomLeft.setSolved(true);
            solveTiles(bottomLeft.getNeighbours());
        }
        if(bottomRight.isCorner()) {
            bottomRight.rotate(LoopTileSide.RIGHT.side() - bottomRight.getFirstEmptySide().side());
            bottomRight.setSolved(true);
            solveTiles(bottomRight.getNeighbours());
        }*/
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
