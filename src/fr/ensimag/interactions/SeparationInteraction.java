package fr.ensimag.interactions;

import java.util.List;
import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;

public class SeparationInteraction extends Interaction {
	private float effectRadius;
	
	public SeparationInteraction(AgentGroup actors, float effectRadius, float multiplier, float clipping, float maxSpeed) {
		super(actors, multiplier, clipping, maxSpeed);
		this.effectRadius = effectRadius;
	}

	@Override
	public void interaction(Agent target, List<Agent> actors) {
		FVector2D result = new FVector2D(0.0f, 0.0f);
		int count = 0;

		for (Agent b : actors) {
			float d = target.getPosition().distance(b.getPosition());
			if (d > 0.0f && d < 2.0f * effectRadius) {
				FVector2D dp = target.getPosition().sub(b.getPosition());
				dp.normalize();
				dp.div(d);
				result.add(dp);
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
