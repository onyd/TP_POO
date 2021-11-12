package fr.ensimag.tests;

import fr.ensimag.cellular_automata.ImmigrationArea;
import gui.GUISimulator;

import java.awt.*;

public class ImmigrationTest {
    public static void main(String[] args) {
        ImmigrationArea mainArea = new ImmigrationArea(150, 150, 3);
        GUISimulator gui = new GUISimulator(450, 450, Color.WHITE, mainArea);

        mainArea.addGraphicalElementsTo(gui);
    }
}
