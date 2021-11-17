package fr.ensimag.interactions;

import java.util.List;
import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;

/**
 * Represents an force interaction that apply a separation behaviour of targets
 * and actors
 *
 */
public class SeparationForce extends ForceInteraction {
	private float effectRadius;

	/**
	 * Create an separation force interaction
	 * 
	 * @param actors
	 * @param multiplier
	 * @param clipping
	 */
	public SeparationForce(AgentGroup actors, float effectRadius, float multiplier, float clipping) {
		super(actors, multiplier, clipping);
		this.effectRadius = effectRadius;
	}

	@Override
	public FVector2D computeForce(Agent target, List<Agent> actors) {
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
			return result;
		} else {
			return new FVector2D(0.0f, 0.0f);
		}
	}

}
