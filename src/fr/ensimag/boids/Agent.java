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
	private float maxSpeed;
	private float radius;
	private float maxRadius;
	private float viewDistance;
	private float fov;
	private Color color;
	private boolean alive = true;

	/**
	 * Create an agent with specified caracteristics
	 * 
	 * @param position
	 * @param velocity
	 * @param maxSpeed
	 * @param radius
	 * @param viewDistance
	 * @param fov
	 * @param color
	 */
	public Agent(FPoint2D position, FVector2D velocity, float maxSpeed, float radius, float maxRadius,
			float viewDistance, float fov, Color color) {
		super(position);
		this.velocity = velocity;
		this.maxSpeed = maxSpeed;
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

	public void setVelocity(FVector2D velocity) {
		this.velocity = velocity;
		velocity.clip(maxSpeed);
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
		if (radius > maxRadius)
			radius = maxRadius;
		this.radius = radius;
	}

	/**
	 * Max speed getter
	 * 
	 * @return maxSpeed
	 */
	public float getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * Max speed setter
	 * 
	 * @param maxSpeed
	 */
	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * Max radius getter
	 * 
	 * @return maxRadius
	 */
	public float getMaxRadius() {
		return maxRadius;
	}

	/**
	 * Max radius setter
	 * 
	 * @param maxRadius
	 */
	public void setMaxRadius(float maxRadius) {
		this.maxRadius = maxRadius;
	}

	/**
	 * View distance getter
	 * 
	 * @return viewDistance
	 */
	public float getViewDistance() {
		return viewDistance;
	}

	/**
	 * View distance setter
	 * 
	 * @param viewDistance
	 */
	public void setViewDistance(float viewDistance) {
		this.viewDistance = viewDistance;
	}

	/**
	 * FOV getter
	 * 
	 * @return fov
	 */
	public float getFov() {
		return fov;
	}

	/**
	 * FOV setter
	 * 
	 * @param fov
	 */
	public void setFov(float fov) {
		this.fov = fov;
		while (fov > 360) {
			fov -= 360;
		}
		while (fov < 0) {
			fov += 360;
		}
	}

	/**
	 * Color getter
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Color setter
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
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
	 * Set agent alive
	 */
	public void revive() {
		this.alive = true;
	}

	/**
	 * Apply Newton's law on the agent
	 * 
	 * @param force
	 */
	public void applyForce(FVector2D force) {
		FVector2D newVelocity = this.getVelocity();
		newVelocity.add(force);
		this.setVelocity(newVelocity);
	}

	/**
	 * Move the agent according to its velocity for one step
	 * 
	 * @return true if still alive
	 */
	public void update() {
		this.getPosition().translate(this.getVelocity());
	}

	/**
	 * Compute if an agent see another one
	 * 
	 * @param agent
	 * @return true if this can see agent
	 */
	public boolean isViewing(Agent agent) {
		return MathUtil.isInView(agent.getPosition(), this.getVelocity(), this.getPosition(), viewDistance, -fov/2, fov/2);
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
