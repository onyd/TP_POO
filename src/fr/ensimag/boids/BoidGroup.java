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
	 * @param initialMaxradius
	 * @param initialViewDistance
	 * @param initialFov
	 * @param initialMaxSpeed
	 * @param initialColor
	 * @param area
	 */
	public BoidGroup(int updateStep, int agentNumber, float initialRadius, float initialMaxradius,
			float initialViewDistance, float initialFov, float initialMaxSpeed, Color initialColor, AgentArea area) {
		super(updateStep, agentNumber, initialRadius, initialMaxradius, initialViewDistance, initialFov,
				initialMaxSpeed, initialColor, area);

		this.addInteraction(new CohesionForce(this, 1.0f, 0.4f));
		this.addInteraction(new AlignmentForce(this, 1.0f, 0.5f));
		this.addInteraction(new SeparationForce(this, initialRadius, 1.0f, 1.3f));
	}

}
