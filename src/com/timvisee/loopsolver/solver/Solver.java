package com.timvisee.loopsolver.solver;

import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.tile.LoopTile;
import com.timvisee.loopsolver.tile.LoopTileSide;

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
        // Loop through all the tiles in the whole grid
        for(LoopTile tile : this.grid.getTiles()) {
            // Solve empty tiles
            if(tile.isEmpty())
                tile.setSolved(true);

            // Solve tiles with 4 sides
            if(tile.getConnectibleSides() == 4)
                tile.setSolved(true);
        }




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
            solveTiles(topLeft.getTilesAround());
            topLeft.setSolved(true);
        }
        if(topRight.isCorner()) {
            topRight.rotate(LoopTileSide.TOP.side() - topRight.getFirstEmptySide().side());
            solveTiles(topRight.getTilesAround());
            topRight.setSolved(true);
        }
        if(bottomLeft.isCorner()) {
            bottomLeft.rotate(LoopTileSide.BOTTOM.side() - bottomLeft.getFirstEmptySide().side());
            solveTiles(bottomLeft.getTilesAround());
            bottomLeft.setSolved(true);
        }
        if(bottomRight.isCorner()) {
            bottomRight.rotate(LoopTileSide.RIGHT.side() - bottomRight.getFirstEmptySide().side());
            solveTiles(bottomRight.getTilesAround());
            bottomRight.setSolved(true);
        }
    }

    public void solveTiles(List<LoopTile> tiles) {
        for(LoopTile tile : tiles)
            solveTile(tile);
    }

    public void solveTile(LoopTile tile) {
        // Make sure the tile isn't solved already
        if(tile.isSolved())
            return;

        // Check whether this tile has one side
        if(tile.getConnectibleSides() == 1) {
            // Loop though all tiles around this one
            for(LoopTileSide side : LoopTileSide.values()) {
                // Get the relative tile
                LoopTile relativeTile = tile.getRelativeTile(side, 1);

                // Make sure the relative tile isn't null
                if(relativeTile == null)
                    continue;

                // Make sure the tile is solved
                if(!tile.isSolved())
                    continue;

                // Check whether the side facing to this one is connectible
                if(relativeTile.getSide(side.opposite())) {
                    // Properly rotate the current tile
                    tile.rotate((side.side() + 1) - tile.getFirstEmptySide().side());

                    // Set the tile as solved
                    tile.setSolved(true);
                    break;
                }
            }
        }
    }
}
