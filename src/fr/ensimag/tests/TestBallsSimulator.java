package fr.ensimag.tests;

import java.awt.Color;
import fr.ensimag.balls.BallsSimulator;
import gui.*;

/**
 * Tests the BallsSimulator.java implementation and runs the simulation.
 *
 * @author Team 52
 *
 */
public class TestBallsSimulator {
    public static void main(String[] args) {
        // balls will move in the rectangle (0,0), (0,600), (600,600), (600,0)
        BallsSimulator simulator = new BallsSimulator(0, 0, 600, 600);
        GUISimulator gui = new GUISimulator(600, 600, Color.WHITE, simulator);
        gui.addGraphicalElement(simulator.balls);

    }
}