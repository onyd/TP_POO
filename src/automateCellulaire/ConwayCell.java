package automateCellulaire;

import java.util.List;

public class ConwayCell extends Cell {
	
	public ConwayCell(int state) {
		super(state);
	}
	
	public ConwayCell() {
		super(0);
	}
	
	@Override
	public void updateCell(List<Cell> neighborCell) {
		/**
		 * neighborCell is the list of the 8 neighbors of the cell
		 */
		int nbNeighborsAlive = 0;
		for (Cell c: neighborCell) {
			if(c.getState() == 1) {
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
