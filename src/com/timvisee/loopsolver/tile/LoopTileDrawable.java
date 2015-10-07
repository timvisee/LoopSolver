package com.timvisee.loopsolver.tile;

import javax.swing.*;
import java.awt.*;

public class LoopTileDrawable extends JPanel {

    /**
     * Constructor.
     */
    public LoopTileDrawable() {
        // Set the border
        setBorder(BorderFactory.createLineBorder(Color.black));
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
}
