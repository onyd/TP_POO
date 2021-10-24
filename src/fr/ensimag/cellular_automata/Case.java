package fr.ensimag.cellular_automata;

import java.awt.Graphics2D;
import fr.ensimag.core.Area;
import fr.ensimag.core.Entity;
import fr.ensimag.math.FPoint2D;

public class Case extends Entity {
	private State currentState;
	private State nextState;
	
	private int width, height;

	public Case(FPoint2D position, int width, int height) {
		super(position);
		this.width = width;
		this.height = height;
	}

	public void updateState() {
		currentState = nextState;
	}

	@Override
	public void update(Area<?> area) {
		Area<Case> cases = (Area<Case>) area;
		// Here update cases state
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		g2d.drawRect(getX(), getY(), width, height);
		
	}



}
