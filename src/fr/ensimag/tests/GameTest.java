package fr.ensimag.tests;

import fr.ensimag.cellular_automata.GridArea;
import fr.ensimag.core.GridArea;
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
        GridArea mainGridArea = new GridArea(500, 500, 10, 3);
        GUISimulator gui = new GUISimulator(600, 400, Color.WHITE, mainGridArea);

        mainGridArea.addGraphicalElementsTo(gui);
    }
}