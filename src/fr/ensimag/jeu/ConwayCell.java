package automateCellulaire;

import java.util.List;

public class ConwayCell extends Cell {
	
	public ConwayCell(int state) {
		super(state);
	}
	
	public ConwayCell() {
		super(0);
	}
	
	private boolean isAlive() {
		if(this.getState() == 1) {
			return true;
		}
		return false;
	}
	
	public void updateCell(List<ConwayCell> neighborCell) {
		/**
		 * neighborCell is the list of the 8 neighbors of the cell
		 */
		int nbNeighborsAlive = 0;
		for (ConwayCell c: neighborCell) {
			if(c.isAlive()) {
				nbNeighborsAlive++;
			}
		}
		
		if(this.getState() == 0) { // Dead
			if(nbNeighborsAlive == 3) {
				super.nextState(); // setAlive
			}
		}else { // Alive
			if(!(nbNeighborsAlive == 2 ||nbNeighborsAlive == 3)) {
				super.nextState(); // setDead
			}
		}
	}

}
