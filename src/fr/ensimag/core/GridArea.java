package fr.ensimag.core;

import fr.ensimag.cellular_automata.Case;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.cellular_automata.Grid;

/**
 * Class that managed the graphical part of a grid
 * (linked calculation and graphics)
 */
public class GridArea extends Area<Case> {
	/**
	 * pixel size
	 */
	protected int caseSize;

	/**
	 * associated grid (grid is the calculation parts)
	 */
	private Grid g;
	
	public GridArea(int width, int height, int caseSize, int gameChoice) {
		super(width, height);
		this.g = new Grid( width/caseSize, height/caseSize, gameChoice);
		this.caseSize = caseSize;
		this.entities.ensureCapacity(width*height/caseSize/caseSize);

		// fill the grid with Case :
		// (each case is linked with a cell already created in grid g)
		for (int i = 0; i < width/caseSize; i++) {
			for (int j = 0; j < height/caseSize; j++) {
				this.entities.add(new Case(new FPoint2D(i * caseSize, j * caseSize), caseSize, caseSize, g.getCell(i, j)));
			}
		}
	}

	/**
	 * update color of every case of this grid
	 */
	private void updateCases(){
		for(Case c: super.entities){
			c.updateColor();
		}
	}

	/**
	 * calculate an iteration in the grid g and update graphics
	 */
	@Override
	public void next() {
		g.iterate();
		this.updateCases();
	}

	/**
	 * reset of the grid g and update graphics
	 */
	@Override
	public void restart() {
		g.restart();
		this.updateCases();
	}
}
