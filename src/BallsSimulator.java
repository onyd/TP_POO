package fr.ensimag.balls;
import java.awt.Point;

import gui.*;

public class BallsSimulator implements Simulable {
	public Balls balls;
	private Point[] velocity = new Point[4];
	
	int x, y, width, height;
	
	public BallsSimulator(int x, int y, int width, int height) {
		balls = new Balls();
		
		velocity[0] = new Point(-5, 5);
		velocity[1] = new Point(5, -5);
		velocity[2] = new Point(-5, -5);
		velocity[3] = new Point(5, 5);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void next() {
		for (int i = 0; i < balls.getBalls().length; i++) {
			balls.get(i).translate((int)velocity[i].getX(), (int)velocity[i].getY());
			
			if (balls.get(i).getX() < x | balls.get(i).getX() > width) {
				velocity[i].setLocation(velocity[i].getX() * -1, velocity[i].getY());
			}
			if (balls.get(i).getY() < y | balls.get(i).getY() > height) {
				velocity[i].setLocation(velocity[i].getX(), velocity[i].getY() * -1);
			}
		}
	}

	@Override
	public void restart() {
		balls.reInit();
	}

}
