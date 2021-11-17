package fr.ensimag.interactions;

import java.util.ArrayList;
import java.util.List;
import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.util.Pair;

/**
 * Represents an interaction between 2 group of agents (both can be the same
 * group in case of intern interaction)
 *
 */
public abstract class Interaction {
	protected AgentGroup actors;

	/**
	 * Initialize attributes
	 * 
	 * @param actors
	 */
	public Interaction(AgentGroup actors) {
		this.actors = actors;
	}

	/**
	 * Do all the computations of the interaction
	 * 
	 * @param target
	 * @param actors
	 */
	public abstract void interaction(Agent target, List<Agent> actors);

	/**
	 * Apply the interactions to all the targets (null actors is used for
	 * interactions related to environment interactions)
	 * 
	 * @param targets
	 */
	public void apply(ArrayList<Agent> targets) {
		if (this.actors != null) {
			List<Agent> agents = actors.getLivingAgents();
			for (Agent target : targets)
				this.interaction(target, agents);
		} else {
			for (Agent target : targets)
				this.interaction(target, null);
		}
	}

	/**
	 * Apply the interaction to all the targets with the grid cell optimization
	 * 
	 * @param targets
	 * @param key
	 */
	public void applyOptimized(ArrayList<Agent> targets, Pair<Integer, Integer> key) {
		List<Agent> neighboors = actors.getLivingNeighboors(key);
		for (Agent target : targets)
			this.interaction(target, neighboors);
	}

}
