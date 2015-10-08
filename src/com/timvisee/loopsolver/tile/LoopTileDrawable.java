package com.timvisee.loopsolver.tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoopTileDrawable extends JPanel {

    /** Normal tube color. */
    public static final Color COLOR_TUBE_NORMAL = new Color(45, 45, 45);
    /** Normal center color. */
    public static final Color COLOR_CENTER_NORMAL = new Color(25, 25, 25);
    /** Empty center color. */
    public static final Color COLOR_CENTER_EMPTY = Color.GRAY;

    /** Loop tile instance to draw. */
    private LoopTile tile;

    /**
     * Constructor.
     */
    public LoopTileDrawable(final LoopTile tile) {
        // Set the loop tile
        this.tile = tile;

        // Set the border
        setBorder(BorderFactory.createLineBorder(Color.gray));

        // Add a mouse listener
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Rotate the tile
                tile.rotate(LoopTile.TILE_ROTATE_CLOCKWISE);

                // Repaint the tile
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
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

        // Parse the Graphics2D object
        Graphics2D g2 = (Graphics2D) g;

        // Add a background if the tile is solved
        if(this.tile.isSolved()) {
            // Set the background color
            g2.setColor(Color.GREEN);

            // Draw the background
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        // Enable anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get the smallest side
        int sideSmallest = Math.min(getWidth(), getHeight());

        // Determine the tube width and circle size
        int tubeSize = (int) (sideSmallest / 6.5f);
        int circleSize = (int) (tubeSize * 2.25f);

        // Set the tube color
        g2.setColor(COLOR_TUBE_NORMAL);

        // Paint the top tube
        if(this.tile.getSide(LoopTileSide.TOP))
            g2.fillRect((getWidth() / 2) - (tubeSize / 2), 0, tubeSize, getHeight() / 2);

        // Paint the right tube
        if(this.tile.getSide(LoopTileSide.RIGHT))
            g2.fillRect(getWidth() / 2, (getHeight() / 2) - (tubeSize / 2), getWidth() / 2, tubeSize);

        // Paint the bottom tube
        if(this.tile.getSide(LoopTileSide.BOTTOM))
            g2.fillRect((getWidth() / 2) - (tubeSize / 2), getHeight() / 2, tubeSize, getHeight() / 2);

        // Paint the left tube
        if(this.tile.getSide(LoopTileSide.LEFT))
            g2.fillRect(0, (getHeight() / 2) - (tubeSize / 2), getWidth() / 2, tubeSize);

        // Set the center color
        if(this.tile.isEmpty())
            g2.setColor(COLOR_CENTER_EMPTY);
        else
            g2.setColor(COLOR_CENTER_NORMAL);

        // Draw the center
        g2.fillOval((getWidth() / 2) - (circleSize / 2), (getHeight() / 2) - (circleSize / 2), circleSize, circleSize);
    }

    /**
     * Get the loop tile.
     *
     * @return Loop tile.
     */
    public LoopTile getTile() {
        return this.tile;
    }
}
