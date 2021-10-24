package fr.ensimag.core;

import fr.ensimag.cellular_automata.Case;
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
			c.update(this);
		}
		for (Case c : entities) {
			c.updateState();
		}
	}
	
	public Case getCase(int i, int j) {
		return entities.get(i * width + j);
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
