package fr.ensimag.interactions;

import fr.ensimag.boids.Agent;
import fr.ensimag.math.FVector2D;

/**
 * Represents a global interaction involving a force that is applied by actors on
 * targets
 *
 */
public abstract class GlobalForce extends GlobalInteraction {
	private float multiplier;
	private float clipping;

	/**
	 * Initializes attributes
	 * 
	 * @param multiplier
	 * @param clipping
	 */
	public GlobalForce(float multiplier, float clipping) {
		this.multiplier = multiplier;
		this.clipping = clipping;
	}

	@Override
	public void interaction(Agent target) {
		FVector2D force = this.computeForce(target);
		target.applyForce(force);
	}

	/**
	 * Do the computations of the force to apply
	 * 
	 * @param target
	 * @return
	 */
	protected abstract FVector2D computeForce(Agent target);

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
