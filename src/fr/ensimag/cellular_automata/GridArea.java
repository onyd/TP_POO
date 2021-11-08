package fr.ensimag.cellular_automata;

import fr.ensimag.core.Area;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.cellular_automata.Grid;

public class GridArea extends Area<Case> {
	protected int caseSize;
	private Grid g;
	
	public GridArea(int width, int height, int caseSize, int gameChoice) {
		super(width, height);
		this.g = new Grid( width/caseSize, height/caseSize, gameChoice);
		this.caseSize = caseSize;
		this.entities.ensureCapacity(width*height/caseSize/caseSize);

		for (int i = 0; i < width/caseSize; i++) {
			for (int j = 0; j < height/caseSize; j++) {
				this.entities.add(new Case(new FPoint2D(i * caseSize, j * caseSize), caseSize, caseSize, g.getCell(i, j)));
			}
		}
	}

	private void updateCases(){
		for(Case c: super.entities){
			c.updateColor();
		}
	}
	
	@Override
	public void next() {
		g.iterate();
		this.updateCases();
	}

	@Override
	public void restart() {
		g.restart();
		this.updateCases();
	}
}
