package fr.ensimag.cellular_automata;

import fr.ensimag.cellular_automata.Case;

import fr.ensimag.core.Area;

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
	 * grid is represented as a list
	 * (but grid is interpreted as an 2D array)
	 */
	protected List<Cell> cellList;

	public GridArea(int width, int height, int caseSize) {
		super(width, height);
		this.caseSize = caseSize;
		this.entities.ensureCapacity(width*height/caseSize/caseSize);
		this.cellList = new ArrayList<Cell>();
	}

	/**
	 * get the cell (i, j) of the grid
	 * @param i index i of the grid
	 * @param j index j of the grid
	 * @return requested cell
	 */
	public Cell getCell(int i, int j) {
		// TODO Fix index out of range for certain width and height
		return this.cellList.get(i * super.width + j);
	}

	/**
	 * return the list of cell in the Moore neighborhood
	 * @param i index i of the grid
	 * @param j index j of the grid
	 * @return list of cell in the Moore neighborhood
	 */
	private List<Cell> getNeighbors(int i, int j) {
		List<Cell> neighborsList = new ArrayList<Cell>();
		for(int a = -1; a <= 1; a++) {
			for(int b = -1; b <= 1; b++) {
				if(!(a == 0 && b == 0)) {
					// game area is "circular", a cell on the left side
					// has a neighbor on the right side
					neighborsList.add(this.getCell((i + a + this.width) %  this.width, (j + b + this.height) % this.height));
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
			for(int j = 0; j < this.width; j++){
				this.getCell(i, j).calculate(this.getNeighbors(i, j));
			}
		}

		// updating current:
		for(Cell c: this.cellList){
			c.update();
		}

		this.updateCases();
	}

	/**
	 * reset the grid with initial values everywhere
	 */
	@Override
	public void restart() {
		for(Cell c : cellList){
			c.initCell();
		}

		this.updateCases();

		System.out.println(this.hashCode());
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				str += this.getCell(i, j).getCurrentState().getValue() + " ";
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
		for(Cell c : cellList){
			hash += (c.getCurrentState().getValue() * c.getNextState().getValue());
		}
		return hash;
	}
}
