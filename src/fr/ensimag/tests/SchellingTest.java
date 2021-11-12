package fr.ensimag.tests;

import fr.ensimag.cellular_automata.SchellingArea;
import gui.GUISimulator;

import java.awt.*;

public class SchellingTest {
    public static void main(String[] args) {
        SchellingArea mainArea = new SchellingArea(100, 100, 3);
        GUISimulator gui = new GUISimulator(500, 500, Color.WHITE, mainArea);

        mainArea.addGraphicalElementsTo(gui);
    }
}
