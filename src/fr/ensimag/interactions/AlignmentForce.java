package fr.ensimag.interactions;

import java.util.List;
import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;

/**
 * Represents an force interaction that apply an alignment behaviour of targets' velocity vector with actors' ones 
 *
 */
public class AlignmentForce extends ForceInteraction {

	/**
	 * Create an alignment force interaction 
	 * @param actors
	 * @param multiplier
	 * @param clipping
	 */
	public AlignmentForce(AgentGroup actors, float multiplier, float clipping) {
		super(actors, multiplier, clipping);
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
