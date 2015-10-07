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
            if(tileTop.isStraight() && tileTop.isVertical())
                tileTop.rotateClockwise();
            if(tileBottom.isStraight() && tileBottom.isVertical())
                tileBottom.rotateClockwise();

            // Properly rotate T-Shapes
            if(tileTop.isTShape() && tileTop.getSide(LoopTileSide.TOP)) {
                if(!tileTop.getSide(LoopTileSide.LEFT))
                    tileTop.rotate(LoopTile.TILE_ROTATE_CLOCKWISE);
                if(!tileTop.getSide(LoopTileSide.BOTTOM))
                    tileTop.rotate(LoopTile.TILE_ROTATE_HALF);
                if(!tileTop.getSide(LoopTileSide.RIGHT))
                    tileTop.rotate(LoopTile.TILE_ROTATE_COUNTERCLOCKWISE);
            }
            if(tileBottom.isTShape() && tileBottom.getSide(LoopTileSide.BOTTOM)) {
                if(!tileBottom.getSide(LoopTileSide.LEFT))
                    tileBottom.rotate(LoopTile.TILE_ROTATE_COUNTERCLOCKWISE);
                if(!tileBottom.getSide(LoopTileSide.TOP))
                    tileBottom.rotate(LoopTile.TILE_ROTATE_HALF);
                if(!tileBottom.getSide(LoopTileSide.RIGHT))
                    tileBottom.rotate(LoopTile.TILE_ROTATE_CLOCKWISE);
            }
        }

        // Loop through all tiles on the left and right
        for(int y = 1; y < (this.grid.getHeight() - 1); y++) {
            // Get the tile
            LoopTile tileLeft = this.grid.getTile(0, y);
            LoopTile tileRight = this.grid.getTile(this.grid.getWidth() - 1, y);

            // Rotate the piece if it's straight and vertical
            if(tileLeft.isStraight() && tileLeft.isHorizontal())
                tileLeft.rotateClockwise();
            if(tileRight.isStraight() && tileRight.isHorizontal())
                tileRight.rotateClockwise();

            // Properly rotate T-Shapes
            if(tileLeft.isTShape() && tileLeft.getSide(LoopTileSide.LEFT)) {
                if(!tileLeft.getSide(LoopTileSide.TOP))
                    tileLeft.rotate(LoopTile.TILE_ROTATE_COUNTERCLOCKWISE);
                if(!tileLeft.getSide(LoopTileSide.RIGHT))
                    tileLeft.rotate(LoopTile.TILE_ROTATE_HALF);
                if(!tileLeft.getSide(LoopTileSide.BOTTOM))
                    tileLeft.rotate(LoopTile.TILE_ROTATE_CLOCKWISE);
            }
            if(tileRight.isTShape() && tileRight.getSide(LoopTileSide.RIGHT)) {
                if(!tileRight.getSide(LoopTileSide.BOTTOM))
                    tileRight.rotate(LoopTile.TILE_ROTATE_COUNTERCLOCKWISE);
                if(!tileRight.getSide(LoopTileSide.LEFT))
                    tileRight.rotate(LoopTile.TILE_ROTATE_HALF);
                if(!tileRight.getSide(LoopTileSide.TOP))
                    tileRight.rotate(LoopTile.TILE_ROTATE_CLOCKWISE);
            }
        }
    }
}
