package com.timvisee.loopsolver.util;

public class MathUtils {

    /**
     * Mod that works with negative numbers.
     *
     * @param x The dividend.
     * @param y The divisor.
     *
     * @return The floor modulus.
     */
    public static int realMod(int x, int y) {
        return Math.floorMod(x, y);
    }
}
