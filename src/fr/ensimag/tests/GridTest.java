package fr.ensimag.tests;

import fr.ensimag.cellular_automata.*;

/**
 * test of the calculation part
 */
public class GridTest {
    public static void main(String[] args) {
        // results in the console

        // gameChoice :
        // 1 : Conway
        // 2 : Immigration
        // 3 : Schelling
        Grid g = new Grid(10, 10, 1);
        System.out.println(g);

        for(int i = 0; i < 10; i++){
            g.iterate();
            System.out.println(g);
        }
    }
}
