package automateCellulaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SchellingCell extends Cell {
	private static int K = 3; // by default
	public static List<Cell> vacantCells = new ArrayList<Cell>();
	public static List<Cell> initalVacantCells = new ArrayList<Cell>();
	public static int numberVacantCells = 0;
	
	public SchellingCell(int state) {
		super(state);
	}
	
	public SchellingCell() {
		super(0);
	}

	public static void addVacantCell(Cell vacantCell) {
		vacantCells.add(vacantCell);
		numberVacantCells++;
	}
	
	@Override
	public void updateCell(List<Cell> neighborCell) {
		/**
		 * neighborCell is the list of the 8 neighbors of the cell
		 */
		if (this.getState() != 0) {
			int nbNeighborsOfDifferentColor = 0;
			// Count how many cells are different
			for (Cell c: neighborCell) {
				if(c.getState() != 0 && c.getState() != this.getState()) {
					nbNeighborsOfDifferentColor++;
				}
			}
			
			if(nbNeighborsOfDifferentColor >= K) {
				move();
			}
		}
	}
	
	public void move() {
		int r = rand(0, numberVacantCells - 1);
		vacantCells.get(r).setState(this.getState());
		vacantCells.remove(r);
		this.setState(0);
		vacantCells.add(this);
	}

	
	private int rand(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}