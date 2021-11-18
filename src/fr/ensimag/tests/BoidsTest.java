package fr.ensimag.tests;

import java.awt.Color;
import fr.ensimag.boids.AgentArea;
import fr.ensimag.boids.BoidGroup;
import fr.ensimag.boids.PredatorGroup;
import fr.ensimag.interactions.SeparationForce;
import fr.ensimag.interactions.WindForce;
import fr.ensimag.math.FVector2D;
import gui.GUISimulator;

public class BoidsTest {
	public static void main(String[] args) {
		AgentArea area = new AgentArea(600, 400, true);
		float r = 5.0f;
		float maxSpeed = 5.0f;
		float fov = 360.0f;
		float viewDistance = 50.0f;

		// Use of predefined group
		BoidGroup preys1 = new BoidGroup(1, 200, r, 2 * r, viewDistance, fov, maxSpeed, Color.BLUE, area);
		BoidGroup preys2 = new BoidGroup(1, 50, r, 2 * r, viewDistance, fov, maxSpeed, Color.GREEN, area);
		PredatorGroup predators = new PredatorGroup(2, 50, r, 2 * r, viewDistance, fov, maxSpeed, Color.ORANGE, area,
				preys1, preys2);

		// Add the groups to be considered in the simulation
		area.addGroup(preys1);
		area.addGroup(preys2);
		area.addGroup(predators);

		// We can add aditionnal interactions
		preys1.addInteraction(new SeparationForce(preys2, preys1.getInitialRadius(), 1.0f, 1.1f));
		preys2.addInteraction(new SeparationForce(preys1, preys2.getInitialRadius(), 1.0f, 1.1f));

		// There is also an interaction over all the area
		// area.addInteractions(new WindForce(1.5f, 1.7f, 2.0f, new FVector2D(1.0f, -1.0f), 60.0f));

		// Specifically, predators can eat a specified group
		predators.allowEating(preys1, area, 30);

		// Launch the simulation
		area.start();
		GUISimulator gui = new GUISimulator(600, 400, Color.WHITE, area);
		area.addGraphicalElementsTo(gui);

	}
}
