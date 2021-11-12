package fr.ensimag.boids;

import java.awt.Color;

import fr.ensimag.core.EventArea;
import fr.ensimag.events.AddInteractionEvent;
import fr.ensimag.events.Event;
import fr.ensimag.interactions.EatInteraction;
import fr.ensimag.interactions.SeparationForce;
import fr.ensimag.interactions.TrackingForce;

/**
 * Represents a group of predators which follows same rules than boids but can
 * target preys and eat them if allowed
 *
 */
public class PredatorGroup extends BoidGroup {

	/**
	 * Create a group of predator
	 * 
	 * @param updateStep
	 * @param agentNumber
	 * @param initialRadius
	 * @param initialViewDistance
	 * @param initialFov
	 * @param initialMaxSpeed
	 * @param initialColor
	 * @param preyGroups
	 */
	public PredatorGroup(int updateStep, int agentNumber, float initialRadius, float initialMaxRadius,
			float initialViewDistance, float initialFov, float initialMaxSpeed, Color initialColor,
			AgentGroup... preyGroups) {
		super(updateStep, agentNumber, initialRadius, initialMaxRadius, initialViewDistance, initialFov,
				initialMaxSpeed, initialColor);
		for (AgentGroup preys : preyGroups) {
			this.addPreyGroup(preys);
		}
	}

	/**
	 * Add a group targeted by the predators
	 * 
	 * @param group
	 */
	public void addPreyGroup(AgentGroup group) {
		group.addInteraction(new SeparationForce(this, 20.0f, 1.0f, 1.4f, 5.0f));
		this.addInteraction(new TrackingForce(group, 1.0f, 1.2f, 5.0f));
	}

	/**
	 * Allow the predators to eat the group within a delay
	 * 
	 * @param group
	 * @param area
	 * @param delay
	 */
	public void allowEating(AgentGroup group, EventArea<Agent> area, long delay) {
		Event e = new AddInteractionEvent(area.getDate() + delay, this, new EatInteraction(group));
		area.addEvent(e);
	}

}
