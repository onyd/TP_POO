package fr.ensimag.cellular_automata;

import java.awt.Graphics2D;
import fr.ensimag.core.Area;
import fr.ensimag.core.Entity;
import fr.ensimag.math.FPoint2D;

public class Case extends Entity{
	State currentState;
	State nextState;

	public Case(FPoint2D position) {
		super(position);
	}

	@Override
	public void paint(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Area area) {
		// TODO Auto-generated method stub
		
	}

	public void updateState() {
		currentState = nextState;
	}


}
