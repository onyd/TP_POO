package fr.ensimag.boids;

import java.awt.Color;

import fr.ensimag.interactions.SeparationInteraction;
import fr.ensimag.interactions.TrackingInteraction;

public class PredatorGroup extends BoidGroup {

	public PredatorGroup(int updateStep, int agentNumber, float radius, float viewDistance, float fov, Color color,
			AgentGroup... preyGroups) {
		super(updateStep, agentNumber, radius, viewDistance, fov, color);
		for (AgentGroup preys : preyGroups) {
			this.addPreyGroup(preys);
		}
	}

	public void addPreyGroup(AgentGroup group) {
		group.addInteraction(new SeparationInteraction(this, 20.0f, 1.0f, 1.4f, 5.0f));
		this.addInteraction(new TrackingInteraction(group, 1.0f, 1.2f, 5.0f));
	}

}
