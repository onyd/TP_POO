package automateCellulaire;

import java.util.List;

public class Cell {
	private int state = 0; // current state of the cell
	public static int nbState = 2; // nbState = 2 for Conway; 2, 3, 4, ... for immigration
	
	public Cell(int state) {
		this.state = state;
	}
	
	public Cell() {
		this(0);
	}
	
	protected int getState() {
		return state;
	}
	
	public void setState(int state) {
		if (state >= nbState) {
			throw new IllegalArgumentException("nbState doit être supérieur à state");
		}
		this.state = state;
	}
	
	protected void nextState() {
		this.state = (this.state + 1) % nbState;
	}
	
	public void updateCell(List<Cell> neighborCell) {	
	}
	
	protected void copyCell(Cell modelCell) {
		this.state = modelCell.getState();
	}
	
	@Override
	public String toString() {
		return "[" + this.state + "]";
	}
	

}

