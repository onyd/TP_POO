package fr.ensimag.balls;
import java.awt.Color;
import java.awt.Graphics2D;

import gui.*;

public class Balls implements GraphicalElement {
	private Oval[] balls = new Oval[4];
	
	public Oval get(int i) {
		return balls[i];
	} 

	public Oval[] getBalls() {
		return balls;
	}

	public Balls() {
		this.reInit();
	}
	
	public void translate(int dx, int dy) {
		for (Oval p : this.balls) {
			p.translate(dx, dy);
		}
	}
	
	public void reInit() {
		balls[0] = new Oval(50, 150, Color.RED, Color.RED, 10);
		balls[1] = new Oval(150, 50, Color.RED, Color.RED, 10);
		balls[2] = new Oval(50, 50, Color.RED, Color.RED, 10);
		balls[3] = new Oval(150, 150, Color.RED, Color.RED, 10);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (Oval b : this.balls) {
			str += "x = " + b.getX() + " | y = " + b.getY() + "\n";
		}
		return(str);
	}

	@Override
	public void paint(Graphics2D g2d) {
		for (Oval b : this.balls) {
			b.paint(g2d);
		}
	}
	
}
