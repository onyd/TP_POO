package fr.ensimag.tests;

import fr.ensimag.cellular_automata.*;

public class GridTest {
    public static void main(String[] args) {
        Grid g = new Grid(10, 10, 1);
        System.out.println(g);

        for(int i = 0; i < 10; i++){
            g.iterate();
            System.out.println(g);
        }
    }
}
