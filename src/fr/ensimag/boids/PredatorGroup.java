package fr.ensimag.boids;

import java.awt.Color;

import fr.ensimag.core.EventArea;
import fr.ensimag.events.AddInteractionEvent;
import fr.ensimag.events.Event;
import fr.ensimag.interactions.EatInteraction;
import fr.ensimag.interactions.SeparationForce;
import fr.ensimag.interactions.TrackingForce;

public class PredatorGroup extends BoidGroup {

	public PredatorGroup(int updateStep, int agentNumber, float radius, float viewDistance, float fov, Color color,
			AgentGroup... preyGroups) {
		super(updateStep, agentNumber, radius, viewDistance, fov, color);
		for (AgentGroup preys : preyGroups) {
			this.addPreyGroup(preys);
		}
	}

	public void addPreyGroup(AgentGroup group) {
		group.addInteraction(new SeparationForce(this, 20.0f, 1.0f, 1.4f, 5.0f));
		this.addInteraction(new TrackingForce(group, 1.0f, 1.2f, 5.0f));
	}

	public void allowEating(AgentGroup group, EventArea<Agent> area, long delay) {
		Event e = new AddInteractionEvent(area.getDate() + delay, this, new EatInteraction(group));
		area.addEvent(e);
	}

}
