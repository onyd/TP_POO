package automateCellulaire;

import java.util.*;

public class Grid {
	private int xSize, ySize;
	
	private int gameChoice = 0;
	
	private List<List<Cell>> initialGrid;
	private List<List<Cell>> currentGrid;
	private List<List<Cell>> nextGrid;
	
	public Grid(int xSize, int ySize, int gameChoice) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.gameChoice = gameChoice;
		
		switch(this.gameChoice) {
			case 1: // jeu de la vie
				Cell.nbState = 2;
				break;
			case 2: // jeu de l'immigration
				Cell.nbState = 3; // k = 4, par défaut
				break;
			case 3: // Modèle de Schelling
				Cell.nbState = 4;
				break;
			default:
				throw new IllegalArgumentException("Jeu non sélectionnable...");
		}
		
		initialGrid = new ArrayList<List<Cell>>();
		currentGrid = new ArrayList<List<Cell>>();
		nextGrid = new ArrayList<List<Cell>>();
		
		for (int i = 0; i < xSize; i++) {
			List<Cell> listCell = new ArrayList<Cell>();
			currentGrid.add(listCell);
			
			List<Cell> listCell2 = new ArrayList<Cell>();
			nextGrid.add(listCell2);
			
			List<Cell> listCell3 = new ArrayList<Cell>();
			initialGrid.add(listCell3);
			
			for(int j = 0; j < ySize; j++) {
				switch(this.gameChoice) {
					case 1: // jeu de la vie
						System.out.println("Ajout de cell de Conway");
						ConwayCell cellc = new ConwayCell(rand(0, 1));
						currentGrid.get(i).add(cellc);
						nextGrid.get(i).add(new ConwayCell(cellc.getState()));
						initialGrid.get(i).add(new ConwayCell(cellc.getState()));
						
						break;
						
					case 2: // jeu de l'immigration
						System.out.println("Ajout de cell de l'immi");
						ImmigrationCell celli = new ImmigrationCell(rand(0, Cell.nbState - 1));
						currentGrid.get(i).add(celli);
						nextGrid.get(i).add(new ImmigrationCell(celli.getState()));
						initialGrid.get(i).add(new ImmigrationCell(celli.getState()));
						break;
						
					case 3: // Modèle de Schelling
						System.out.println("Ajout de cell de Schelling");
						int r = rand(0, Cell.nbState - 1);
						SchellingCell cells = new SchellingCell(r);
						if (r == 0) SchellingCell.addVacantCell(cells);
						currentGrid.get(i).add(cells);
						nextGrid.get(i).add(cells);
						initialGrid.get(i).add(new SchellingCell(cells.getState()));
						break;
					default:
						System.out.println("Jeu non séléctionnable...");
						break;
				}
			}
		}
	}
	
	public Grid() {
		this(20, 20, 1); // jeu de la vie par défaut
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
		if(this.gameChoice == 3) {
			SchellingCell.vacantCells.clear();
			SchellingCell.numberVacantCells = 0;
			for (int i = 0; i < xSize; i++) {
				for(int j = 0; j < ySize; j++) {
					if (this.currentGrid.get(i).get(j).getState() == 0) {
						SchellingCell.addVacantCell(this.currentGrid.get(i).get(j));
					}
				}
			}
		}
	}
	
	private void calculateNextGrid() {
		for (int i = 0; i < xSize; i++) {
			for(int j = 0; j < ySize; j++) {
				// update case [i][j] in nextGrid
				List<Cell> neighborsCell = getNeighbors(i, j);
				nextGrid.get(i).get(j).updateCell(neighborsCell);
			}
		}
		System.out.print(SchellingCell.numberVacantCells);
	}
	
	private List<Cell> getNeighbors(int i, int j) {
		List<Cell> neighborsList = new ArrayList<Cell>();
		for(int a = -1; a <= 1; a++) {
			for(int b = -1; b <= 1; b++) {
				if(!(a == 0 && b == 0)) {
					// l'espace de jeu est circulaire, une cellule
					// tout à gauche a une voisine tout à droite de la grille
					neighborsList.add(currentGrid.get((i + a + this.xSize) %  this.xSize).get((j + b + this.ySize) % this.ySize));
					
					//if(this.isInGrid(i + a, j + b)) {
					//	neighborsList.add(currentGrid.get(i + a).get(j + b));
					//}
				}
			}
		}
		return neighborsList;
	}
	
	private void copyAIntoB(List<List<Cell>> a, List<List<Cell>> b) {
		for (int i = 0; i < xSize; i++) {
			for(int j = 0; j < ySize; j++) {
				b.get(i).get(j).copyCell(a.get(i).get(j));
			}
		}
	}
	
	//private boolean isInGrid(int k, int l) {
	//	return (k >= 0 && k < this.xSize && l >= 0 && l < this.ySize);
	//}
	
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
