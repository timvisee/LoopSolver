package com.timvisee.loopsolver.frame;

import com.timvisee.loopsolver.App;
import com.timvisee.loopsolver.LoopSolver;
import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.tile.LoopTile;
import com.timvisee.loopsolver.tile.LoopTileDrawable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridFrame extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = LoopSolver.APP_NAME;

    public static final int TILE_WIDTH = 50;

    private LoopGrid grid;

    private List<LoopTileDrawable> tiles = new ArrayList<>();

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
        // Create the menu bar
        createMenuBar();

        // Create and add the grid panel
        JPanel drawableGrid = createGridPanel();
        App.instance.setDrawableGrid(drawableGrid);
        this.add(drawableGrid);

        // Pack all components
        this.pack();

        // Make the frame non-resizable
        this.setResizable(true);

        // Set the window location to the system's default
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Create the grid panel.
     *
     * @return The grid panel.
     */
    private JPanel createGridPanel() {
        // Clear the current list of drawable tiles
        this.tiles.clear();

        // Create a panel to put the grid in
        JPanel gridPanel = new JPanel();
        gridPanel.setPreferredSize(new Dimension(this.grid.getWidth() * TILE_WIDTH, this.grid.getHeight() * TILE_WIDTH));
        gridPanel.setLayout(new GridLayout(this.grid.getHeight(), this.grid.getWidth()));

        // Add the tiles
        for(int i = 0; i < this.grid.getTotal(); i++) {
            // Create drawable tile
            LoopTileDrawable drawable = new LoopTileDrawable(this.grid.getTile(i));

            // Add the drawable tile to the list and add it to the frame
            this.tiles.add(drawable);
            gridPanel.add(drawable);
        }

        // Return the panel
        return gridPanel;
    }

    /**
     * Create the menu bar
     */
    public void createMenuBar() {
        // Set the frame
        final GridFrame frame = this;

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the solve menu
        JMenu solverMenu = new JMenu("Solver");
        menuBar.add(solverMenu);

        // Create the solve item
        JMenuItem solveItem = new JMenuItem("Solve!");
        solveItem.addActionListener(e -> {
            App.instance.solve();
            //JOptionPane.showMessageDialog(frame, "Solve feature not implemented yet!");
        });
        solverMenu.add(solveItem);

        // Add a solve menu separator
        solverMenu.addSeparator();

        // Create the exit item
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> {
            // Exit the application
            System.exit(1);
        });
        solverMenu.add(exitItem);





        // Create the solve menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // Create the solve item
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, LoopSolver.APP_NAME + " v" + LoopSolver.APP_VERSION_NAME + " (" + LoopSolver.APP_VERSION_CODE + ")\n\nDeveloped by Tim Visee.\nhttp://timvisee.com/\n\nCopyright (c) Tim Visee 2015. All rights reserved.", LoopSolver.APP_NAME + " - About", JOptionPane.PLAIN_MESSAGE);
        });
        helpMenu.add(aboutItem);

        // Add a solve menu separator
        helpMenu.addSeparator();

        // Create the exit item
        JMenuItem websiteItem = new JMenuItem("Visit website");
        websiteItem.addActionListener(e -> {
            // Exit the application
            System.exit(1);
        });
        helpMenu.add(websiteItem);

        // Set the menu bar
        this.setJMenuBar(menuBar);
    }

    /**
     * Get the drawable tile for a grid tile.
     *
     * @param tile The tile.
     *
     * @return The drawable tile.
     */
    public LoopTileDrawable getDrawableTile(LoopTile tile) {
        // Make sure the tile isn't null
        if(tile == null)
            return null;

        // Return the drawable tile
        return this.tiles.get(this.grid.getTilePosition(tile.getX(), tile.getY()));
    }
}
