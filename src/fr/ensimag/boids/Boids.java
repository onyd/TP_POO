package fr.ensimag.boids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import fr.ensimag.core.Area;
import fr.ensimag.core.Entity;

public class Boids extends Entity {
	private Point2D.Float velocity;
	private float orientation;
	private float viewDistance;
	private float fov;


	public Boids(Point position, Point2D.Float velocity, float orientation, float visionradius, float fov) {
		super(position);
		this.velocity = velocity;
		this.orientation = orientation;
		this.viewDistance = visionradius;
		this.fov = fov;
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		g2d.drawRect(getX(), getY(), 10, 10);;
	}

	@Override
	public void update(Area area) {
		// TODO Auto-generated method stub
		
	}

}
