package fr.ensimag.interactions;

import java.util.ArrayList;
import java.util.List;
import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.util.Pair;

public abstract class Interaction {
	protected AgentGroup actors;

	public Interaction(AgentGroup actors) {
		this.actors = actors;
	}

	public abstract void interaction(Agent target, List<Agent> actors);
	
	public void apply(ArrayList<Agent> targets) {
		List<Agent> agents = actors.getAgents();
		for (Agent target : targets)
			this.interaction(target, agents);
	}
	
	public void applyOptimized(ArrayList<Agent> targets, Pair<Integer, Integer> key) {
		List<Agent> neighboors = actors.getNeighboors(key);
		for (Agent target : targets)
			this.interaction(target, neighboors);
	}

	
}
