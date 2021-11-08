package fr.ensimag.boids;

import java.awt.Color;
import java.awt.Graphics2D;
import fr.ensimag.core.Entity;
import fr.ensimag.math.Conversions;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.FVector2D;

public class Agent extends Entity {
	private FVector2D velocity;
	private float radius;
	private float viewDistance;
	private float fov;
	private Color color;

	public Agent(FPoint2D position, FVector2D velocity, float radius, float viewDistance, float fov, Color color) {
		super(position);
		this.velocity = velocity;
		this.radius = radius;
		this.viewDistance = viewDistance;
		this.fov = Conversions.radian(fov);
		this.color = color;
	}

	public FVector2D getVelocity() {
		return velocity;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getViewDistance() {
		return viewDistance;
	}

	public float getFov() {
		return fov;
	}

	public void applyForce(FVector2D force) {
		this.getVelocity().add(force);
	}

	public void update() {
		this.getPosition().translate(this.getVelocity());
	}

	public boolean isViewing(Agent a) {
		float d = this.getPosition().distance(a.getPosition());
		if (d == 0.0f || d > viewDistance) {
			return false;
		}
		FVector2D dir = a.getPosition().sub(this.getPosition());
		dir.normalize();
		float angle = (float) Math.acos(this.getVelocity().dot(dir));
		if (angle < -fov || angle > fov) {
			return false;
		}

		return true;
	}

	@Override
	public void paint(Graphics2D g2d) {
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