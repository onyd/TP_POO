package fr.ensimag.core;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

import fr.ensimag.boids.Boids;

public class BoidsArea extends Area {
	private Boids[] boids; 
	private float viewDistance;
	private float fov;
	
	public BoidsArea(int width, int height, int n_boids, float defaultViewDistance, float defaultFov) {
		super(width, height);
		
		this.boids = new Boids[n_boids];
		this.viewDistance = defaultViewDistance;
		this.fov = defaultFov;
		
		this.restart();
	}

	public Boids[] getBoids() {
		return boids;
	}
	
	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void restart() {
		// Initialization with randomly distributed Boids
		Random r = new Random();
		for (int i = 0; i < boids.length; i++) {
			boids[i] = new Boids(new Point(r.nextInt(width), r.nextInt(height)), new Point2D.Float(2.0f*r.nextFloat()-1.0f, 2.0f*r.nextFloat()-1.0f), r.nextFloat(), viewDistance, fov);
		}
	}

}
