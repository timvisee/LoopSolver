package com.timvisee.loopsolver;

import com.timvisee.loopsolver.frame.GridFrame;
import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.tile.LoopTile;
import com.timvisee.loopsolver.util.WindowUtils;

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

        // Set the native look and feel
        System.out.println("Setting native look and feel...");
        WindowUtils.useNativeLookAndFeel();

        // TODO: Remove this, debug only
        // Create the grid frame
        Random rand = new Random();
        LoopGrid grid = new LoopGrid(5 + rand.nextInt(8), 5 + rand.nextInt(8));
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
