package com.timvisee.loopsolver.frame;

import com.timvisee.loopsolver.LoopSolver;
import com.timvisee.loopsolver.grid.LoopGrid;
import com.timvisee.loopsolver.tile.LoopTileDrawable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridFrame extends JFrame {

    /** Frame title. */
    private static final String FORM_TITLE = LoopSolver.APP_NAME;

    public static final int TILE_WIDTH = 50;

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
        // Create the menu bar
        createMenuBar();

        // Create and add the grid panel
        this.add(createGridPanel());

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
        // Create a panel to put the grid in
        JPanel gridPanel = new JPanel();
        gridPanel.setPreferredSize(new Dimension(this.grid.getWidth() * TILE_WIDTH, this.grid.getHeight() * TILE_WIDTH));
        gridPanel.setLayout(new GridLayout(this.grid.getHeight(), this.grid.getWidth()));

        // Add the tiles
        for(int i = 0; i < this.grid.getTotal(); i++)
            gridPanel.add(new LoopTileDrawable(this.grid.getTile(i)));

        // Return the panel
        return gridPanel;
    }

    /**
     * Create the menu bar
     */
    public void createMenuBar() {
        // Store the current frame instance
        final JFrame frame = this;

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the solve menu
        JMenu solverMenu = new JMenu("Solver");
        menuBar.add(solverMenu);

        // Create the solve item
        JMenuItem solveItem = new JMenuItem("Solve!");
        solveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Solve feature not implemented yet!");
            }
        });
        solverMenu.add(solveItem);

        // Add a solve menu separator
        solverMenu.addSeparator();

        // Create the exit item
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(1);
            }
        });
        solverMenu.add(exitItem);

        // Set the menu bar
        this.setJMenuBar(menuBar);
    }
}
