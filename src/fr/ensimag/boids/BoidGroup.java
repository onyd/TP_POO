package fr.ensimag.boids;

import java.awt.Color;

import fr.ensimag.interactions.AlignmentForce;
import fr.ensimag.interactions.CohesionForce;
import fr.ensimag.interactions.SeparationForce;

public class BoidGroup extends AgentGroup {

	public BoidGroup(int updateStep, int agentNumber, float radius, float viewDistance, float fov, Color color) {
		super(updateStep, agentNumber, radius, viewDistance, fov, color);
		
		this.addInteraction(new CohesionForce(this, 1.0f, 0.4f, maxSpeed));
		this.addInteraction(new AlignmentForce(this, 1.0f, 0.5f, maxSpeed));
		this.addInteraction(new SeparationForce(this, radius, 1.0f, 1.1f, maxSpeed));
	}

}
