package com.timvisee.loopsolver;

public class LoopSolver {

    /** App name. */
    public static final String APP_NAME = "Loop Solver";
    /** App version name. */
    public static final String APP_VERSION_NAME = "0.1-Alpha";
    /** App version code. */
    public static final int APP_VERSION_CODE = 1;

    /**
     * Main method called on start.
     *
     * @param args Startup arguments.
     */
    public static void main(String[] args) {
        // Print the app name
        System.out.println(APP_NAME + " v" + APP_VERSION_NAME + " (" + APP_VERSION_CODE + ")");

        // Start the app
        new App(true);
    }
}
