package fr.ensimag.interactions;

import java.util.ArrayList;
import java.util.List;

import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;
import fr.ensimag.util.Pair;

public abstract class Interaction {
	private float multiplier;
	private float clipping;
	private float maxSpeed;

	public float getMultiplier() {
		return multiplier;
	}

	public float getClipping() {
		return clipping;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	protected AgentGroup actors;

	public Interaction(AgentGroup actors) {
		this(actors, 1.0f, 0.5f, 5.0f);
	}

	public Interaction(AgentGroup actors, float multiplier, float clipping, float maxSpeed) {
		this.multiplier = multiplier;
		this.clipping = clipping;
		this.maxSpeed = maxSpeed;

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

	protected void process(Agent agent, FVector2D target) {
		target.normalize();
		target.mult(maxSpeed);
		target.sub(agent.getVelocity());
		target.mult(multiplier);
		target.clip(clipping);
	}
}
