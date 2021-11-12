package fr.ensimag.tests;

import fr.ensimag.cellular_automata.GridArea;
import gui.GUISimulator;
import java.awt.*;

/**
 * test both calculation and graphical parts
 */
public class GameTest {
    public static void main(String[] args) {
        // gameChoice :
        // 1 : Conway
        // 2 : Immigration
        // 3 : Schelling
        GridArea mainGridArea = new GridArea(800, 800, 5, 3);
        GUISimulator gui = new GUISimulator(1000, 1000, Color.WHITE, mainGridArea);

        mainGridArea.addGraphicalElementsTo(gui);
    }
}