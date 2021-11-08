package fr.ensimag.balls;

import java.awt.Color;

import gui.*;

public class TestBalls {
	public static void main(String[] args) {
		BallsSimulator simulator = new BallsSimulator(0, 0, 400, 400);
		GUISimulator gui = new GUISimulator(400, 400, Color.WHITE, simulator);

		gui.addGraphicalElement(simulator.balls);

	}
}
