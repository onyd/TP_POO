package fr.ensimag.core;

import java.util.Random;
import fr.ensimag.boids.Boids;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.FVector2D;

public class BoidsArea extends Area<Boids> {
	private float viewDistance;
	private float fov;
	private int n_boids;
	
	public BoidsArea(int width, int height, int n_boids, float defaultViewDistance, float defaultFov) {
		super(width, height);
		
		this.viewDistance = defaultViewDistance;
		this.fov = defaultFov;
		this.n_boids = n_boids;
		
		this.restart();
	}

	@Override
	public void next() {
		for (Boids b : entities) {
			b.update(this);
		}
	}
	
	@Override
	public void restart() {
		// Initialization with randomly distributed Boids
		Random r = new Random();
		for (int i = 0; i < n_boids; i++) {
			entities.add(new Boids(new FPoint2D(r.nextFloat() * width, r.nextFloat() * height), new FVector2D(2.0f*r.nextFloat()-1.0f, 2.0f*r.nextFloat()-1.0f), viewDistance, fov));
		}
	}


}
