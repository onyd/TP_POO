package fr.ensimag.boids;

import java.awt.Color;
import java.awt.Graphics2D;
import fr.ensimag.core.Entity;
import fr.ensimag.math.MathUtil;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.FVector2D;

/**
 * Represents a moving entity which can be subjected to forces
 *
 */
public class Agent extends Entity {
	private FVector2D velocity;
	private float radius;
	public float maxRadius;
	public float viewDistance;
	public float fov;
	public Color color;
	private boolean alive = true;

	/**
	 * Create an agent with specified caracteristics
	 * 
	 * @param position
	 * @param velocity
	 * @param radius
	 * @param viewDistance
	 * @param fov
	 * @param color
	 */
	public Agent(FPoint2D position, FVector2D velocity, float radius, float maxRadius, float viewDistance, float fov,
			Color color) {
		super(position);
		this.velocity = velocity;
		this.radius = radius;
		this.maxRadius = maxRadius;
		this.viewDistance = viewDistance;
		this.fov = MathUtil.radian(fov);
		this.color = color;
	}

	/**
	 * Velocity getter
	 * 
	 * @return velocity
	 */
	public FVector2D getVelocity() {
		return velocity;
	}

	/**
	 * radius getter
	 * 
	 * @return radius
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * radius setter
	 * 
	 * @param radius
	 */
	public void setRadius(float radius) {
		this.radius = radius;
		if (radius > maxRadius)
			radius = maxRadius;
	}

	/**
	 * Alive getter
	 * 
	 * @return true if alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Set agent dead
	 */
	public void kill() {
		this.alive = false;
	}

	/**
	 * Apply Newton's law on the agent
	 * 
	 * @param force
	 */
	public void applyForce(FVector2D force) {
		this.getVelocity().add(force);
	}

	/**
	 * Move the agent according to its velocity for one step
	 * 
	 * @return true if still alive
	 */
	public boolean update() {
		this.getPosition().translate(this.getVelocity());
		return alive;
	}

	/**
	 * Compute if an agent see another one
	 * 
	 * @param agent
	 * @return true if this can see agent
	 */
	public boolean isViewing(Agent agent) {
		return MathUtil.isInView(agent.getPosition(), this.getVelocity(), this.getPosition(), viewDistance, -fov, fov);
	}

	@Override
	public void paint(Graphics2D g2d) {
		if (alive) {
			g2d.setColor(color);
			float orientation = getVelocity().getAngle() - (float) Math.PI / 2;
			float theta1 = (float) Math.PI / 2.0f + orientation;
			float theta2 = 4.0f * (float) Math.PI / 3.0f + orientation;
			float theta3 = 5.0f * (float) Math.PI / 3.0f + orientation;

			FVector2D v1 = new FVector2D(radius * (float) Math.cos(theta1), radius * (float) Math.sin(theta1));
			FVector2D v2 = new FVector2D(radius * (float) Math.cos(theta2), radius * (float) Math.sin(theta2));
			FVector2D v3 = new FVector2D(radius * (float) Math.cos(theta3), radius * (float) Math.sin(theta3));

			FPoint2D x1 = new FPoint2D(getPosition());
			x1.translate(v1);
			FPoint2D x2 = new FPoint2D(getPosition());
			x2.translate(v2);
			FPoint2D x3 = new FPoint2D(getPosition());
			x3.translate(v3);

			g2d.drawLine((int) x1.getX(), (int) x1.getY(), (int) x2.getX(), (int) x2.getY());
			g2d.drawLine((int) x3.getX(), (int) x3.getY(), (int) x2.getX(), (int) x2.getY());
			g2d.drawLine((int) x1.getX(), (int) x1.getY(), (int) x3.getX(), (int) x3.getY());
		}
	}

}
