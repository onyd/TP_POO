package fr.ensimag.interactions;

import java.util.List;

import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;

/**
 * Represents an interaction that makes targets eat the actors (ie kill them and increase the radius)
 *
 */
public class EatInteraction extends Interaction {

	/**
	 * Create an eat interaction 
	 * @param actors
	 * @param multiplier
	 * @param clipping
	 * @param maxSpeed
	 */
	public EatInteraction(AgentGroup actors) {
		super(actors);
	}

	@Override
	public void interaction(Agent target, List<Agent> actors) {
		for (Agent a : actors) {
			if (a.isAlive() && target.isViewing(a) && target.getRadius() < target.getMaxRadius()) {
				a.kill();
				target.setRadius(target.getRadius() + 1);
			}
		}
	}

}
