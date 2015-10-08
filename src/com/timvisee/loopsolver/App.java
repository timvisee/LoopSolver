package com.timvisee.loopsolver;

import com.timvisee.loopsolver.frame.GridFrame;
import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.solver.Solver;
import com.timvisee.loopsolver.tile.LoopTile;
import com.timvisee.loopsolver.util.WindowUtils;

import javax.swing.*;
import java.util.Random;

public class App {

    /** App instance. */
    public static App instance;

    /** Current grid. */
    private LoopGrid grid;
    /** Current drawable grid. */
    private JPanel drawableGrid;

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
        createPossibleGrid();
        //createRandomGrid();

        // Show an initialized message
        System.out.println(LoopSolver.APP_NAME + " initialized! Cave Johnson here.");

        // Create and show the grid frame
        GridFrame frame = new GridFrame(grid);
    }

    public void solve() {
        Thread t = new Thread(new Solver(this.grid));
        t.start();
    }

    public void createRandomGrid() {
        Random rand = new Random();
        this.grid = new LoopGrid(5 + rand.nextInt(8), 5 + rand.nextInt(8));
        this.grid.fillWithRandom();
    }

    public void createPossibleGrid() {
        // Puzzle 80

        this.grid = new LoopGrid(5, 5);
        this.grid.getTiles().clear();

        this.grid.getTiles().add(new LoopTile(true, true, false, false));
        this.grid.getTiles().add(new LoopTile(true, true, true, false));
        this.grid.getTiles().add(new LoopTile(false, true, false, true));
        this.grid.getTiles().add(new LoopTile(false, true, true, true));
        this.grid.getTiles().add(new LoopTile(false, false, true, true));

        this.grid.getTiles().add(new LoopTile(false, true, false, false));
        this.grid.getTiles().add(new LoopTile(true, false, true, false));
        this.grid.getTiles().add(new LoopTile(false, false, false, false));
        this.grid.getTiles().add(new LoopTile(true, false, true, false));
        this.grid.getTiles().add(new LoopTile(false, false, true, false));

        this.grid.getTiles().add(new LoopTile(true, false, false, true));
        this.grid.getTiles().add(new LoopTile(true, true, true, true));
        this.grid.getTiles().add(new LoopTile(true, false, true, false));
        this.grid.getTiles().add(new LoopTile(true, true, true, true));
        this.grid.getTiles().add(new LoopTile(true, true, false, false));

        this.grid.getTiles().add(new LoopTile(false, true, false, false));
        this.grid.getTiles().add(new LoopTile(true, false, true, true));
        this.grid.getTiles().add(new LoopTile(false, true, false, true));
        this.grid.getTiles().add(new LoopTile(true, true, false, true));
        this.grid.getTiles().add(new LoopTile(false, false, false, true));

        this.grid.getTiles().add(new LoopTile(true, false, false, false));
        this.grid.getTiles().add(new LoopTile(true, false, true, true));
        this.grid.getTiles().add(new LoopTile(false, true, false, true));
        this.grid.getTiles().add(new LoopTile(true, true, true, false));
        this.grid.getTiles().add(new LoopTile(false, true, false, false));
    }

    /**
     * Get the current drawable grid.
     *
     * @return Drawable grid.
     */
    public JPanel getDrawableGrid() {
        return drawableGrid;
    }

    /**
     * Set the drawable grid.
     *
     * @param drawableGrid Drawable grid.
     */
    public void setDrawableGrid(JPanel drawableGrid) {
        this.drawableGrid = drawableGrid;
    }
}
