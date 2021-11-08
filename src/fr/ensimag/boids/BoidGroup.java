package fr.ensimag.boids;

import java.awt.Color;

import fr.ensimag.interactions.AlignmentInteraction;
import fr.ensimag.interactions.CohesionInteraction;
import fr.ensimag.interactions.SeparationInteraction;

public class BoidGroup extends AgentGroup {

	public BoidGroup(int updateStep, int agentNumber, float radius, float viewDistance, float fov, Color color) {
		super(updateStep, agentNumber, radius, viewDistance, fov, color);
		
		this.addInteraction(new CohesionInteraction(this, 1.0f, 0.4f, maxSpeed));
		this.addInteraction(new AlignmentInteraction(this, 1.0f, 0.5f, maxSpeed));
		this.addInteraction(new SeparationInteraction(this, radius, 1.0f, 1.1f, maxSpeed));
	}

}
