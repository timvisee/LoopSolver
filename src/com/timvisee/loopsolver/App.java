package com.timvisee.loopsolver;

import com.timvisee.loopsolver.frame.GridFrame;
import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.tile.LoopTile;

import java.util.Random;

public class App {

    /**
     * Constructor.
     *
     * @param init True to initialize, false otherwise.
     */
    public App(boolean init) {
        // Initialize
        if(init)
            init();
    }

    /**
     * Initialize the app.
     */
    public void init() {
        // Show an initialization message
        System.out.println("Initializing " + LoopSolver.APP_NAME + "...");

        LoopGrid grid = new LoopGrid(10, 10);
        grid.fillWithRandom();
        GridFrame frame = new GridFrame(grid);

        // Show an initialized message
        System.out.println(LoopSolver.APP_NAME + " initialized! Cave Johnson here.");
    }

    // TODO: Remove this method!

    /**
     * Generate a random tile.
     *
     * @return Random tile.
     */
    public static LoopTile getRandomTile() {
        Random rand = new Random();
        return new LoopTile(rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean(), rand.nextBoolean());
    }
}
