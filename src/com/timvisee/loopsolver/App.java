package com.timvisee.loopsolver;

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

        // Show an initialized message
        System.out.println(LoopSolver.APP_NAME + " initialized! Cave Johnson here.");
    }
}
