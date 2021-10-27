package fr.ensimag.boids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

import fr.ensimag.core.Area;
import fr.ensimag.core.BoidsArea;
import fr.ensimag.core.Entity;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.FVector2D;
import fr.ensimag.math.Conversions;

public class Boids extends Entity {
	private FVector2D velocity;
	private float radius = 5.0f;
	private float viewDistance;
	private float fov; 


	public Boids(FPoint2D position, FVector2D velocity, float visionradius, float fov) {
		super(position);
		this.velocity = velocity; 
		
		this.viewDistance = visionradius;
		this.fov = Conversions.radian(fov);
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		float orientation = velocity.getAngle() - (float) Math.PI / 2;
		float theta1 = (float) Math.PI / 2.0f + orientation;
		float theta2 =  4.0f * (float) Math.PI / 3.0f + orientation;
		float theta3 =  5.0f * (float) Math.PI / 3.0f + orientation;
		
		FVector2D v1 = new FVector2D(radius * (float) Math.cos(theta1), radius * (float) Math.sin(theta1));
		FVector2D v2 = new FVector2D(radius * (float) Math.cos(theta2), radius * (float) Math.sin(theta2));
		FVector2D v3 = new FVector2D(radius * (float) Math.cos(theta3), radius * (float) Math.sin(theta3));
		
		FPoint2D x1 = new FPoint2D(position);
		x1.translate(v1);
		FPoint2D x2 = new FPoint2D(position);
		x2.translate(v2);
		FPoint2D x3 = new FPoint2D(position);
		x3.translate(v3);
		
		g2d.drawLine((int) x1.getX(), (int) x1.getY(), (int) x2.getX(), (int) x2.getY());
		g2d.drawLine((int) x3.getX(), (int) x3.getY(), (int) x2.getX(), (int) x2.getY());
		g2d.drawLine((int) x1.getX(), (int) x1.getY(), (int) x3.getX(), (int) x3.getY());
	}

	@Override
	public void update(Area<?> area) {
		ArrayList<Boids> boids = (ArrayList<Boids>) area.getEntities();
		FVector2D separationForce = separate(boids);
		FVector2D cohesionForce = cohesion(boids);
		FVector2D alignmentForce = align(boids);
		
		this.applyForce(separationForce);
		this.applyForce(cohesionForce);
		this.applyForce(alignmentForce);
		
		this.position.translate(velocity);
		
		if (this.position.x > area.getWidth()) {
			this.position.x -= area.getWidth();
		} else if (this.position.x < 0) {
			this.position.x += area.getWidth();
		}
		if (this.position.y > area.getHeight()) {
			this.position.y -= area.getHeight();
		} else if (this.position.y < 0) {
			this.position.y += area.getHeight();
		}
	}
	
	private void applyForce(FVector2D f) {
		this.velocity.add(f);
	}
	
	private Boolean isViewing(Boids b) {
		float d = this.position.distance(b.position);
		if (d == 0.0f || d > 2.0f * radius) {
			return false;
		} 
		FVector2D dir = b.position.sub(this.position);
		dir.normalize();
		float angle = (float) Math.acos(this.velocity.dot(dir));
		if (angle < -fov || angle > fov) {
			return false;
		}
		
		return true;
	}
	
	private FVector2D separate(ArrayList<Boids> boids) {
		FVector2D result = new FVector2D(0.0f, 0.0f);
		int count = 0;
		for (Boids b : boids) {
			float d = this.position.distance(b.position);
			if (d > 0.0f && d < 2.0f * radius) {
				FVector2D dp = this.position.sub(b.position);
				dp.normalize();
				dp.div(d);
				result.add(dp);
				count++;
			}
		}
		
		if (count > 0) {
			result.div(count);
			result.normalize();
			result.mult(5.0f);
			result.sub(this.velocity);
			result.clip(1.1f);
			return result;
		} else {
			return new FVector2D(0.0f, 0.0f);
		}
	}
	
	private FVector2D cohesion(ArrayList<Boids> boids) {
		FVector2D result = new FVector2D(0.0f, 0.0f);
		int count = 0;
		for (Boids b : boids) {
			if (this.isViewing(b)) {
				result.add(b.position);
				count++;
			}
		}
		
		if (count > 0) {
			result.div(count);
			result.sub(this.position);
			result.normalize();
			result.mult(5.0f);
			result.sub(this.velocity);
			result.clip(0.4f);
			return result;
		} else {
			return new FVector2D(0.0f, 0.0f);
		}
	}
	
	private FVector2D align(ArrayList<Boids> boids) {
		FVector2D result = new FVector2D(0.0f, 0.0f);
		int count = 0;
		for (Boids b : boids) {
			if (this.isViewing(b)) {
				result.add(b.velocity);
				count++;
			}
		}
		
		if (count > 0) {
			result.div(count);
			result.normalize();
			result.mult(5.0f);
			result.sub(this.velocity);
			result.clip(0.5f);
			return result;
		} else {
			return new FVector2D(0.0f, 0.0f);
		}
	}
	

}
