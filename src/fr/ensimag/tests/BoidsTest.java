package fr.ensimag.tests;

import java.awt.Color;
import fr.ensimag.boids.AgentArea;
import fr.ensimag.boids.BoidGroup;
import fr.ensimag.boids.PredatorGroup;
import fr.ensimag.interactions.SeparationInteraction;
import gui.GUISimulator;

public class BoidsTest {
	public static void main(String[] args) {
		AgentArea area = new AgentArea(600, 400, true);
		
		BoidGroup preys1 = new BoidGroup(1, 200, 5.0f, 40.0f, 150.0f, Color.BLUE);
		BoidGroup preys2 = new BoidGroup(1, 200, 5.0f, 40.0f, 150.0f, Color.GREEN);
		PredatorGroup predators = new PredatorGroup(1, 50, 5.0f, 40.0f, 150.0f, Color.ORANGE, preys1, preys2);
		area.addGroup(preys1);
		area.addGroup(preys2);
		preys1.addInteraction(new SeparationInteraction(preys2, preys1.getRadius(), 1.0f, 1.1f, 5.0f));
		preys2.addInteraction(new SeparationInteraction(preys1, preys2.getRadius(), 1.0f, 1.1f, 5.0f));
		area.addGroup(predators);
		area.restart();
		
		GUISimulator gui = new GUISimulator(600, 400, Color.WHITE, area);

		area.addGraphicalElementsTo(gui);
	}
}
