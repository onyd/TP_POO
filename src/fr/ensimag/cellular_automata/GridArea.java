package fr.ensimag.cellular_automata;

import fr.ensimag.core.Area;
import fr.ensimag.math.FPoint2D;

public class GridArea extends Area<Case> {
	protected int caseSize;
	
	public GridArea(int width, int height, int caseSize) {
		super(width, height);
		this.caseSize = caseSize;
		
		this.entities.ensureCapacity(width*height/caseSize/caseSize);
		this.restart();
	}
	
	@Override
	public void next() {
		for (Case c : entities) {
			c.getNextState().nextState(this);
		}
		for (Case c : entities) {
			c.updateState();
		}
	}
	
	public Case getCase(int i, int j) {
		return entities.get(i * width / caseSize + j);
	}
	
	@Override
	public void restart() {
		for (int i = 0; i < width/caseSize; i++) {
			for (int j = 0; j < height/caseSize; j++) {
				this.entities.add(new Case(new FPoint2D(i, j), width, height));
			}
		}
	}

}
