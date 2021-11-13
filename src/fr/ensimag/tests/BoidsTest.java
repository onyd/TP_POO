package fr.ensimag.tests;

import java.awt.Color;
import fr.ensimag.boids.AgentArea;
import fr.ensimag.boids.BoidGroup;
import fr.ensimag.boids.PredatorGroup;
import fr.ensimag.interactions.SeparationForce;
import gui.GUISimulator;

public class BoidsTest {
	public static void main(String[] args) {
		AgentArea area = new AgentArea(600, 400, true);
		float r = 5.0f;
		float maxSpeed = 5.0f;
		BoidGroup preys1 = new BoidGroup(1, 200, r, 2*r, 40.0f, 360.0f, maxSpeed, Color.BLUE, area);
		BoidGroup preys2 = new BoidGroup(1, 200, r, 2*r, 40.0f, 360.0f, maxSpeed, Color.GREEN, area);
		PredatorGroup predators = new PredatorGroup(1, 50, r, 2*r, 40.0f, 360.0f, maxSpeed, Color.ORANGE, area, preys1, preys2);
		area.addGroup(preys1);
		area.addGroup(preys2);
		preys1.addInteraction(new SeparationForce(preys2, preys1.getInitialRadius(), 1.0f, 1.1f, maxSpeed));
		preys2.addInteraction(new SeparationForce(preys1, preys2.getInitialRadius(), 1.0f, 1.1f, maxSpeed));
		area.addGroup(predators);
		predators.allowEating(preys1, area, 15);
		area.start();
		
		GUISimulator gui = new GUISimulator(600, 400, Color.WHITE, area);
		area.addGraphicalElementsTo(gui);
		
	}
}
