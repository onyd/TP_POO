package fr.ensimag.interactions;

import java.util.List;

import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;

public class EatInteraction extends Interaction {

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
