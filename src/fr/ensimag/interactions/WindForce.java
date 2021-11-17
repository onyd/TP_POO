package fr.ensimag.interactions;

import java.util.Random;
import fr.ensimag.boids.Agent;
import fr.ensimag.math.FVector2D;
import fr.ensimag.math.MathUtil;

/**
 * Represents an force interaction that apply a wind force on targets (this is a
 * global interaction)
 *
 */
public class WindForce extends GlobalForce {
	private float intensity;
	private FVector2D direction;
	private float fluctuation;

	/**
	 * Create a wind force interaction
	 * 
	 * @param actors
	 * @param multiplier
	 * @param clipping
	 * @param intensity
	 * @param direction
	 * @param fluctuation
	 */
	public WindForce(float multiplier, float clipping, float intensity, FVector2D direction, float fluctuation) {
		super(multiplier, clipping);
		this.intensity = intensity;
		this.direction = direction;
		this.fluctuation = fluctuation;
	}

	@Override
	protected FVector2D computeForce(Agent target) {
		FVector2D result = new FVector2D(direction);
		Random r = new Random();
		result.addAngle(r.nextFloat() * MathUtil.radian(fluctuation));
		result.normalize();
		result.mult(intensity);
		result.clip(getClipping());

		return result;
	}

}
