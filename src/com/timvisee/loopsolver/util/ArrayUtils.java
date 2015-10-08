package com.timvisee.loopsolver.util;

public class ArrayUtils {

    /**
     * Shift an array down. The last item will be put into the first position of the shifted array.
     *
     * @param array The array to shift.
     *
     * @return The shifted array.
     */
    public static boolean[] shiftArrayDown(boolean[] array) {
        boolean end = array[array.length - 1];
        System.arraycopy(array, 0, array, 1, array.length - 1);
        array[0] = end;
        return array;
    }

    /**
     * Shift an array down. The last item will be put into the first position of the shifted array.
     *
     * @param array The array to shift.
     *
     * @return The shifted array.
     */
    public static boolean[] shiftArrayDown(boolean[] array, int count) {
        // Mod the count
        count = MathUtils.realMod(count, array.length);

        // Shift the array by the given amount
        for(int i = 0; i < count; i++)
            array = ArrayUtils.shiftArrayDown(array);

        // Return the shifted array
        return array;
    }
}
