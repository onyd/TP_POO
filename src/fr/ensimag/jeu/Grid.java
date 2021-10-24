package automateCellulaire;

import java.util.*;

public class Grid {
	private int xSize, ySize;
	
	// private int gameChoice;
	
	private List<List<ConwayCell>> initialGrid = new ArrayList<List<ConwayCell>>();
	private List<List<ConwayCell>> currentGrid = new ArrayList<List<ConwayCell>>();
	private List<List<ConwayCell>> nextGrid = new ArrayList<List<ConwayCell>>();
	
	public Grid(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		// this.gameChoice = gameChoice;
		for (int i = 0; i < xSize; i++) {
			List<ConwayCell> listCell = new ArrayList<ConwayCell>();
			currentGrid.add(listCell);
			
			List<ConwayCell> listCell2 = new ArrayList<ConwayCell>();
			nextGrid.add(listCell2);
			
			List<ConwayCell> listCell3 = new ArrayList<ConwayCell>();
			initialGrid.add(listCell3);
			
			for(int j = 0; j < ySize; j++) {
				ConwayCell cell = new ConwayCell(rand(0, 1));
				currentGrid.get(i).add(cell);
				
				ConwayCell cell2 = new ConwayCell(rand(0, 1));
				nextGrid.get(i).add(cell2);
				
				ConwayCell cell3 = new ConwayCell(rand(0, 1));
				initialGrid.get(i).add(cell3);
			}
		}
	}
	
	public Grid() {
		this(20, 20);
	}
	
	public int getCellState(int i, int j) {
		return currentGrid.get(i).get(j).getState();
	}
	
	public void iterate() {
		calculateNextGrid();
		copyAIntoB(this.nextGrid, this.currentGrid);
	}
	
	public void restart() {
		copyAIntoB(this.initialGrid, this.currentGrid);
	}
	
	private void calculateNextGrid() {
		for (int i = 0; i < xSize; i++) {
			for(int j = 0; j < ySize; j++) {
				// update case [i][j] in nextGrid
				List<ConwayCell> neighborsCell = getNeighbors(i, j);
				nextGrid.get(i).get(j).updateCell(neighborsCell);
			}
		}
	}
	
	private List<ConwayCell> getNeighbors(int i, int j) {
		List<ConwayCell> neighborsList = new ArrayList<ConwayCell>();
		for(int a = -1; a <= 1; a++) {
			for(int b = -1; b <= 1; b++) {
				if(!(a == 0 && b == 0)) {
					if(this.isInGrid(i + a, j + b)) {
						neighborsList.add(currentGrid.get(i + a).get(j + b));
					}
				}
			}
		}
		return neighborsList;
	}
	
	private void copyAIntoB(List<List<ConwayCell>> a, List<List<ConwayCell>> b) {
		for (int i = 0; i < xSize; i++) {
			for(int j = 0; j < ySize; j++) {
				b.get(i).get(j).copyCell(a.get(i).get(j));
			}
		}
	}
	
	private boolean isInGrid(int k, int l) {
		return (k >= 0 && k < this.xSize && l >= 0 && l < this.ySize);
	}
	
	public int getXSize() {
		return this.xSize;
	}
	
	public int getYSize() {
		return this.ySize;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < xSize; i++) {
			for(int j = 0; j < ySize; j++) {
				str += currentGrid.get(i).get(j) + " ";
			}
			str += "\n";
		}
		return str;
	}
	
	private int rand(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
