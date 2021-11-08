package fr.ensimag.math;

import java.util.Random;

/**
 * Implements random fonctions
 */
public class Rand {
    /**
     * return an int between 'min' and 'max' (borders included)
     * @param min min border (included)
     * @param max max border (included)
     * @return a random int between 'min' and 'max'
     */
    public static int rand(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
