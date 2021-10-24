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

public class Boids extends Entity {
	private FVector2D velocity;
	private float viewDistance;
	private float fov; 


	public Boids(FPoint2D position, FVector2D velocity, float visionradius, float fov) {
		super(position);
		this.velocity = velocity; 
		this.viewDistance = visionradius;
		this.fov = fov;
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		g2d.drawRect(getX(), getY(), 10, 10);;
	}

	@Override
	public void update(Area<?> area) {
		FVector2D separation = separate(area.getEntities());
	}
	
	private FVector2D separate(ArrayList<?> collection) {
		return null;
	}
	
	private FVector2D cohesion(ArrayList<?> boids) {
		return null;
		
	}
	
	private FVector2D align(ArrayList<?> boids) {
		return null;
		
	}
	

}
