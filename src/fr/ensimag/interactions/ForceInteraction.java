package fr.ensimag.interactions;

import java.util.List;

import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;

public abstract class ForceInteraction extends Interaction {
	private float multiplier;
	private float clipping;
	private float maxSpeed;

	public ForceInteraction(AgentGroup actors) {
		this(actors, 1.0f, 0.5f, 5.0f);
	}
	
	@Override 
	public void interaction(Agent target, List<Agent> actors) {
		FVector2D force = this.computeForce(target, actors);
		target.applyForce(force);
	}
	
	protected abstract FVector2D computeForce(Agent target, List<Agent> actors);
	
	public ForceInteraction(AgentGroup actors, float multiplier, float clipping, float maxSpeed) {
		super(actors);

		this.multiplier = multiplier;
		this.clipping = clipping;
		this.maxSpeed = maxSpeed;
	}

	public float getMultiplier() {
		return multiplier;
	}

	public float getClipping() {
		return clipping;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}
	
	protected void process(Agent agent, FVector2D target) {
		target.normalize();
		target.mult(maxSpeed);
		target.sub(agent.getVelocity());
		target.mult(multiplier);
		target.clip(clipping);
	}
}
