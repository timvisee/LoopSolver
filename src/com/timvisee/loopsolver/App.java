package com.timvisee.loopsolver;

import com.timvisee.loopsolver.frame.GridFrame;
import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.solver.Solver;
import com.timvisee.loopsolver.util.WindowUtils;

import java.util.Random;

public class App {

    /** App instance. */
    public static App instance;

    /** Current grid. */
    private LoopGrid grid;

    /**
     * Constructor.
     *
     * @param init True to initialize, false otherwise.
     */
    public App(boolean init) {
        // Set the app instance
        instance = this;

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

        // TODO: This code is currently for debugging only
        // Create the grid frame
        Random rand = new Random();
        this.grid = new LoopGrid(5 + rand.nextInt(8), 5 + rand.nextInt(8));
        this.grid.fillWithRandom();

        // Show an initialized message
        System.out.println(LoopSolver.APP_NAME + " initialized! Cave Johnson here.");

        // Create and show the grid frame
        GridFrame frame = new GridFrame(grid);
    }

    public void solve() {
        Thread t = new Thread(new Solver(this.grid));
        t.start();
    }
}
