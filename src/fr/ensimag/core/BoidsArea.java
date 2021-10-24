package fr.ensimag.core;

import java.awt.Point;
import java.util.Random;

import fr.ensimag.boids.Boids;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.FVector2D;

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
		for (Boids b : boids) {
			b.update(this);
		}
	}
	
	@Override
	public void restart() {
		// Initialization with randomly distributed Boids
		Random r = new Random();
		for (int i = 0; i < boids.length; i++) {
			boids[i] = new Boids(new FPoint2D(r.nextFloat() * width, r.nextFloat() * height), new FVector2D(2.0f*r.nextFloat()-1.0f, 2.0f*r.nextFloat()-1.0f), viewDistance, fov);
		}
	}

}
