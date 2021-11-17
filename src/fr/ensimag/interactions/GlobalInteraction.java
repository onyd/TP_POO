package fr.ensimag.interactions;

import java.util.ArrayList;
import fr.ensimag.boids.Agent;

/**
 * Represents a global interaction over all the area
 *
 */
public abstract class GlobalInteraction {

	/**
	 * Do all the computations of the interaction
	 * 
	 * @param target
	 * @param actors
	 */
	public abstract void interaction(Agent target);

	/**
	 * Apply the interactions to all the targets (null actors is used for
	 * interactions related to environment interactions)
	 * 
	 * @param targets
	 */
	public void apply(ArrayList<Agent> targets) {
		for (Agent target : targets)
			this.interaction(target);
	}

}
