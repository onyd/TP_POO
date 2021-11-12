package fr.ensimag.tests;

import fr.ensimag.cellular_automata.ConwayArea;
import gui.GUISimulator;

import java.awt.*;

public class ConwayTest {
    public static void main(String[] args) {
        ConwayArea mainArea = new ConwayArea(50, 50, 5);
        GUISimulator gui = new GUISimulator(250, 250, Color.WHITE, mainArea);

        mainArea.addGraphicalElementsTo(gui);
    }
}
