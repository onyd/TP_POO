package fr.ensimag.interactions;

import java.util.List;
import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;

public class AlignmentForce extends ForceInteraction {

	public AlignmentForce(AgentGroup actors, float multiplier, float clipping, float maxSpeed) {
		super(actors, multiplier, clipping, maxSpeed);
	}

	@Override
	public FVector2D computeForce(Agent target, List<Agent> actors) {
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
			return result;
		}
		return new FVector2D(0.0f, 0.0f);
	}

}
