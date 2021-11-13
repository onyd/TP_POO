package fr.ensimag.cellular_automata;

import fr.ensimag.cellular_automata.Case;

import fr.ensimag.core.Area;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that managed the graphical part of a grid
 * (linked calculation and graphics)
 */
public abstract class GridArea extends Area<Case> {
	/**
	 * pixel size
	 */
	protected int caseSize;

	/**
	 * range of color from white to blue for each state value
	 * (default color is blue)
	 */
	public static List<Color> listColors;

	public GridArea(int width, int height, int caseSize) {
		super(width, height);
		this.caseSize = caseSize;
		this.entities.ensureCapacity(width*height/caseSize/caseSize);
	}

	/**
	 * get the cell (i, j) of the grid
	 * @param i index i of the grid
	 * @param j index j of the grid
	 * @return requested cell
	 */
	public Case getCase(int i, int j) {
		return this.entities.get(i * super.height + j);
	}

	/**
	 * return the list of cell in the Moore neighborhood
	 * @param i index i of the grid
	 * @param j index j of the grid
	 * @return list of cell in the Moore neighborhood
	 */
	private List<Case> getNeighbors(int i, int j) {
		List<Case> neighborsList = new ArrayList<Case>();
		for(int a = -1; a <= 1; a++) {
			for(int b = -1; b <= 1; b++) {
				if(!(a == 0 && b == 0)) {
					// game area is "circular", a cell on the left side
					// has a neighbor on the right side
					int newI = (i + a + this.width) %  this.width;
					int newJ = (j + b + this.height) % this.height;
					neighborsList.add(this.getCase(newI, newJ));
				}
			}
		}
		return neighborsList;
	}

	/**
	 * update color of every case of this grid
	 */
	protected void updateCases(){
		for(Case c: super.entities){
			c.updateColor();
		}
	}

	/**
	 * iteration one step forward
	 */
	@Override
	public void next() {
		// calculating next :
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				this.getCase(i, j).calculate(this.getNeighbors(i, j));
			}
		}

		// updating current:
		for(Case c: super.entities){
			c.update();
		}

		this.updateCases();
	}

	/**
	 * reset the grid with initial values everywhere
	 */
	@Override
	public void restart() {
		for(Case c : super.entities){
			c.initCase();
		}

		this.updateCases();
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				str += this.getCase(i, j).getCurrentState().getValue() + " ";
			}
			str += "\n";
		}
		return str;
	}

	/**
	 * not really a hash function, but it helps to see if the game is well reseting
	 * @return a pseudo hash
	 */
	@Override
	public int hashCode() {
		int hash = 1;
		for(Case c : super.entities){
			hash += (c.getCurrentState().getValue() * c.getNextState().getValue());
		}
		return hash;
	}
}
