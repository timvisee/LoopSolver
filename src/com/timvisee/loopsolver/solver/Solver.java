package com.timvisee.loopsolver.solver;

import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.tile.LoopTile;
import com.timvisee.loopsolver.tile.LoopTileSide;

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
        // Loop through all tiles on the top and bottom side
        for(int x = 1; x < (this.grid.getWidth() - 1); x++) {
            // Get the tile
            LoopTile tileTop = this.grid.getTile(x, 0);
            LoopTile tileBottom = this.grid.getTile(x, this.grid.getHeight() - 1);

            // Rotate the piece if it's straight and vertical
            if(tileTop.isStraight()) {
                if(tileTop.isVertical())
                    tileTop.rotateClockwise();
                tileTop.setSolved(true);
            }
            if(tileBottom.isStraight()) {
                if(tileBottom.isVertical())
                    tileBottom.rotateClockwise();
                tileBottom.setSolved(true);
            }

            // Properly rotate T-Shapes
            if(tileTop.isTShape()) {
                tileTop.rotate(LoopTileSide.TOP.side() - tileTop.getFirstEmptySide().side());
                tileTop.setSolved(true);
            }
            if(tileBottom.isTShape()) {
                tileBottom.rotate(LoopTileSide.BOTTOM.side() - tileBottom.getFirstEmptySide().side());
                tileBottom.setSolved(true);
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
            }
            if(tileRight.isStraight()) {
                if(tileRight.isHorizontal())
                    tileRight.rotateClockwise();
                tileRight.setSolved(true);
            }

            // Properly rotate T-Shapes
            if(tileLeft.isTShape()) {
                tileLeft.rotate(LoopTileSide.LEFT.side() - tileLeft.getFirstEmptySide().side());
                tileLeft.setSolved(true);
            }
            if(tileRight.isTShape()) {
                tileRight.rotate(LoopTileSide.RIGHT.side() - tileRight.getFirstEmptySide().side());
                tileRight.setSolved(true);
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
        }
        if(topRight.isCorner()) {
            topRight.rotate(LoopTileSide.TOP.side() - topRight.getFirstEmptySide().side());
            topRight.setSolved(true);
        }
        if(bottomLeft.isCorner()) {
            bottomLeft.rotate(LoopTileSide.BOTTOM.side() - bottomLeft.getFirstEmptySide().side());
            bottomLeft.setSolved(true);
        }
        if(bottomRight.isCorner()) {
            bottomRight.rotate(LoopTileSide.RIGHT.side() - bottomRight.getFirstEmptySide().side());
            bottomRight.setSolved(true);
        }
    }
}
