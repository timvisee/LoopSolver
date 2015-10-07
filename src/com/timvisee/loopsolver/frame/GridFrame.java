package com.timvisee.loopsolver.frame;

import com.timvisee.loopsolver.LoopSolver;
import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.tile.LoopTileDrawable;

import javax.swing.*;
import java.awt.*;

public class GridFrame extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = LoopSolver.APP_NAME;

    private LoopGrid grid;

    /**
     * Constructor.
     */
    public GridFrame(LoopGrid grid) {
        // Construct the form
        super(FORM_TITLE);

        // Set the grid
        this.grid = grid;

        // Create the form UI
        createUIComponents();

        // Close application when closing form
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Show the form
        this.setVisible(true);
    }

    /**
     * Create all UI components for the frame.
     */
    private void createUIComponents() {
        // Set the frame layout
        this.setLayout(new GridLayout(this.grid.getHeight(), this.grid.getWidth()));

        // Set the frame size
        this.setSize(500, 500);

        // Make the frame non-resizable
        this.setResizable(true);

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);

        // TODO: Do this properly!

        for(int i = 0; i < this.grid.getTotal(); i++)
            this.add(new LoopTileDrawable(this.grid.getTile(i)));
    }
}
