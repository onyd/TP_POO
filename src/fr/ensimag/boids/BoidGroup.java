package fr.ensimag.boids;

import java.awt.Color;

import fr.ensimag.interactions.AlignmentForce;
import fr.ensimag.interactions.CohesionForce;
import fr.ensimag.interactions.SeparationForce;

/**
 * Represents a group of boids with the 3 simplest rules of flocking
 *
 */
public class BoidGroup extends AgentGroup {

	/**
	 * Create a group of boids with initial characteristics
	 * 
	 * @param updateStep
	 * @param agentNumber
	 * @param initialRadius
	 * @param initialViewDistance
	 * @param initialFov
	 * @param initialMaxSpeed
	 * @param initialColor
	 */
	public BoidGroup(int updateStep, int agentNumber, float initialRadius, float initialMaxradius,
			float initialViewDistance, float initialFov, float initialMaxSpeed, Color initialColor) {
		super(updateStep, agentNumber, initialRadius, initialMaxradius, initialViewDistance, initialFov,
				initialMaxSpeed, initialColor);

		this.addInteraction(new CohesionForce(this, 1.0f, 0.4f, initialMaxSpeed));
		this.addInteraction(new AlignmentForce(this, 1.0f, 0.5f, initialMaxSpeed));
		this.addInteraction(new SeparationForce(this, initialRadius, 1.0f, 1.1f, initialMaxSpeed));
	}

}
