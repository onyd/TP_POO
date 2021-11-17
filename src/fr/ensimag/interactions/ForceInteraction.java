package fr.ensimag.interactions;

import java.util.List;

import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;

/**
 * Represents an interaction involving a force that is applied by actors on
 * targets
 *
 */
public abstract class ForceInteraction extends Interaction {
	private float multiplier;
	private float clipping;

	/**
	 * Initializes attributes
	 * 
	 * @param actors
	 * @param multiplier
	 * @param clipping
	 */
	public ForceInteraction(AgentGroup actors, float multiplier, float clipping) {
		super(actors);

		this.multiplier = multiplier;
		this.clipping = clipping;
	}

	@Override
	public void interaction(Agent target, List<Agent> actors) {
		FVector2D force = this.computeForce(target, actors);
		target.applyForce(force);
	}

	/**
	 * Do the computations of the force to apply
	 * 
	 * @param target
	 * @param actors
	 * @return
	 */
	protected abstract FVector2D computeForce(Agent target, List<Agent> actors);

	/**
	 * multiplier getter
	 * 
	 * @return
	 */
	public float getMultiplier() {
		return multiplier;
	}

	/**
	 * clipping getter
	 * 
	 * @return
	 */
	public float getClipping() {
		return clipping;
	}


	/**
	 * Utilitary function that compute the force from target velocity and apply
	 * force mofiers
	 * 
	 * @param agent
	 * @param target
	 */
	protected void process(Agent agent, FVector2D target) {
		target.normalize();
		target.mult(agent.getMaxSpeed());
		target.sub(agent.getVelocity());
		target.mult(multiplier);
		target.clip(clipping);
	}
}
