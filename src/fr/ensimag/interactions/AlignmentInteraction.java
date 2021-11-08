package fr.ensimag.interactions;

import java.util.List;
import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;

public class AlignmentInteraction extends ForceInteraction {

	public AlignmentInteraction(AgentGroup actors, float multiplier, float clipping, float maxSpeed) {
		super(actors, multiplier, clipping, maxSpeed);
	}

	@Override
	public void interaction(Agent target, List<Agent> actors) {
		FVector2D result = new FVector2D(0.0f, 0.0f);
		int count = 0;
		for (Agent b : actors) {
			if (target.isViewing(b)) {
				result.add(b.getVelocity());
				count++;
			}
		}

		if (count > 0) {
			result.div(count);
			this.process(target, result);
			target.applyForce(result);
		}
	}

}
