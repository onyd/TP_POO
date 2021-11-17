package fr.ensimag.interactions;

import java.util.Iterator;
import java.util.List;

import fr.ensimag.boids.Agent;
import fr.ensimag.boids.AgentGroup;
import fr.ensimag.math.FVector2D;

/**
 * Represents an force interaction that apply a tracking of actors by target
 * behaviour
 *
 */
public class TrackingForce extends ForceInteraction {

	/**
	 * Create a tracking force interaction
	 * 
	 * @param actors
	 * @param multiplier
	 * @param clipping
	 */
	public TrackingForce(AgentGroup actors, float multiplier, float clipping) {
		super(actors, multiplier, clipping);
	}

	@Override
	public FVector2D computeForce(Agent target, List<Agent> actors) {
		Iterator<Agent> it = actors.iterator();
		if (it.hasNext()) {
			Agent nearest = it.next();
			Agent current = nearest;
			float currentDistance = target.getPosition().distance(nearest.getPosition());

			// In case there is target in actors or boids at same position
			while (currentDistance == 0.0f && it.hasNext()) {
				nearest = it.next();
				current = nearest;
				currentDistance = target.getPosition().distance(nearest.getPosition());
			}

			// Find the nearest actor to target
			while (it.hasNext()) {
				current = it.next();
				float d = target.getPosition().distance(current.getPosition());
				if (d > 0.0f && d < currentDistance) {
					nearest = current;
				}
			}

			// nearest can have 0 distance in the case where all actors have 0 distance but
			// isViewing would return false
			if (target.isViewing(nearest)) {
				FVector2D result = nearest.getPosition().sub(target.getPosition());
				result.mult(currentDistance);
				this.process(target, result);
				return result;
			}
		}
		return new FVector2D(0.0f, 0.0f);
	}

}
