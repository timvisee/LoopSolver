package com.timvisee.loopsolver.util;

import com.timvisee.loopsolver.LoopSolver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class WindowUtils {

    public static final String APP_ICON_RES = "/icon.png";

    /**
     * Try to use the native look and feel of the system
     */
    public static void useNativeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            System.out.println("Failed to set native GUI look and feel!");
        }
    }

    /**
     * Set icon of a window to the default icon of the application
     * @param w Window to change the icon of
     */
    public static void setWindowIcon(Window w) {
        setWindowIcon(w, APP_ICON_RES);
    }

    /**
     * Set the icon if a window
     * @param w Window to change the icon of
     * @param res Icon resource directory
     */
    public static void setWindowIcon(Window w, String res) {
        try {
            InputStream in = LoopSolver.class.getResourceAsStream(res);
            if(in != null)
                w.setIconImage(ImageIO.read(in));
        } catch(IOException ignored) { }
    }

    public static void setDialogIcon(JDialog d, String res) {
        try {
            InputStream in = LoopSolver.class.getResourceAsStream(res);
            if(in != null)
                d.setIconImage(ImageIO.read(in));
        } catch(IOException ignored) { }
    }
}
