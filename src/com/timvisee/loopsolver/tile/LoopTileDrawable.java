package com.timvisee.loopsolver.tile;

import javax.swing.*;
import java.awt.*;

public class LoopTileDrawable extends JPanel {

    /** Loop tile instance to draw. */
    private LoopTile tile;

    /**
     * Constructor.
     */
    public LoopTileDrawable(LoopTile tile) {
        // Set the loop tile
        setTile(tile);

        // Set the border
        setBorder(BorderFactory.createLineBorder(Color.gray));
    }

    /**
     * Get the preferred size.
     *
     * @return Preferred size.
     */
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    /**
     * Paint the component.
     *
     * @param g Graphics.
     */
    public void paintComponent(Graphics g) {
        // Paint the super
        super.paintComponent(g);

        // Paint the component itself
        g.drawString("Test", 10, 20);
    }

    /**
     * Get the loop tile.
     *
     * @return Loop tile.
     */
    public LoopTile getTile() {
        return this.tile;
    }

    /**
     * Set the loop tile.
     *
     * @param tile Loop tile.
     */
    public void setTile(LoopTile tile) {
        this.tile = tile;
    }
}
