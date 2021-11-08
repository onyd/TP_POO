package automateCellulaire;

import java.util.List;

public class ImmigrationCell extends Cell{
	public ImmigrationCell(int state) {
		super(state);
	}
	
	public ImmigrationCell() {
		super(0);
	}
	
	@Override
	public void updateCell(List<Cell> neighborCell) {
		/**
		 * neighborCell is the list of the 8 neighbors of the cell
		 */
		int nbBiggerNeighbor = 0; // compte le nombre de cellule à l'état k+1
		int state = super.getState();
		for (Cell c: neighborCell) {
			if(c.getState() == ((state + 1) % Cell.nbState)) {
				nbBiggerNeighbor++;
			}
		}
		
		if(nbBiggerNeighbor >= 3) {
			super.nextState();
		}
	}
}
