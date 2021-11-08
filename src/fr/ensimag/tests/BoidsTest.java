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
		
		BoidGroup preys1 = new BoidGroup(1, 200, 5.0f, 40.0f, 180.0f, Color.BLUE);
		BoidGroup preys2 = new BoidGroup(1, 200, 5.0f, 40.0f, 180.0f, Color.GREEN);
		PredatorGroup predators = new PredatorGroup(1, 50, 5.0f, 40.0f, 180.0f, Color.ORANGE, preys1, preys2);
		area.addGroup(preys1);
		area.addGroup(preys2);
		preys1.addInteraction(new SeparationForce(preys2, preys1.getInitialRadius(), 1.0f, 1.1f, 5.0f));
		preys2.addInteraction(new SeparationForce(preys1, preys2.getInitialRadius(), 1.0f, 1.1f, 5.0f));
		area.addGroup(predators);
		predators.allowEating(preys1, area, 15);
		area.restart();
		
		GUISimulator gui = new GUISimulator(600, 400, Color.WHITE, area);
		area.addGraphicalElementsTo(gui);
		
	}
}
