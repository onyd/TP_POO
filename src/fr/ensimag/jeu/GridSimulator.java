package automateCellulaire;

import java.awt.Color;
import java.util.*;

import gui.*;

public class GridSimulator implements Simulable {
	private Grid grid;
	private int cellSize = 10;
	public List<Rectangle> listRect = new ArrayList<Rectangle>();
	
	public GridSimulator(Grid inputGrid) {
		this.grid = inputGrid;
		this.cellSize = 10;
		for(int i = 0; i < grid.getXSize(); i++) {
			for(int j = 0; j < grid.getYSize(); j++) {
				for(int k = 0; k < Cell.nbState; k++) {
					Color c = new Color(255*(Cell.nbState-k)/Cell.nbState, 255*(Cell.nbState-k)/Cell.nbState, 255);
					Rectangle newRectangle = new Rectangle(cellSize * i + cellSize/2, cellSize * j + cellSize/2, c, c, cellSize - 3, cellSize - 3);
					this.listRect.add(newRectangle);
					positionRect(i, j, k, newRectangle);
				}
			}
		}
	}
	
	private void positionRect(int i, int j, int k, Rectangle rect) {
		if(k != grid.getCellState(i, j)) {
			if(rect.getX() == cellSize * i + cellSize/2) { // le rectangle �tait en jeu
				rect.translate(- cellSize * i - cellSize, - cellSize * j - cellSize); // on le met sur le banc de touche
			}
		}else {
			if(rect.getX() == - cellSize/2) { // le rectangle �tait sur le banc de touche
				rect.translate(+ cellSize * i + cellSize, + cellSize * j + cellSize); // on le met en jeu
			}
		}
	}
	
	public List<Rectangle> getListRectangle(){
			return this.listRect;
	}
	
	public void updateRectanglePosition() {
		for(int i = 0; i < grid.getXSize(); i++) {
			for(int j = 0; j < grid.getYSize(); j++) {
				for(int k = 0; k < Cell.nbState; k++) { 
					positionRect(i, j, k, listRect.get(i * grid.getYSize() * 2 + j * 2 + k));
				}
			}
		}
	}
	
	@Override
	public void next() {
		grid.iterate();
		this.updateRectanglePosition();
	}
	
	@Override 
	public void restart() {
		grid.restart();
		this.updateRectanglePosition();
	}
	
	public int getCellSize() {
		return this.cellSize;
	}
		
}
