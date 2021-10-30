package automateCellulaire;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class TestGridSimulator {

	public static void main(String[] args) {
		int gridXSize = 100, gridYSize = 75;
		
		// gameChoice -->
			// 1 : jeu de la vie
			// 2 : jeu de l'immigration
		int gameChoice = 3;
		
		Grid grid = new Grid(gridXSize, gridYSize, gameChoice);
				
		GridSimulator simu = new GridSimulator(grid);
		
		int limX = gridXSize * simu.getCellSize();
		int limY = gridYSize * simu.getCellSize();
		GUISimulator gui = new GUISimulator(limX, limY, Color.BLACK);
		gui.addGraphicalElement(new Rectangle(limX/2, limY/2, Color.BLACK, Color.BLACK, limX, limY));
		
		gui.setSimulable(simu);
		
		for (Rectangle r :simu.getListRectangle()) {
			//System.out.println("Ajout graphical element");
			gui.addGraphicalElement(r);
		}
		
		System.out.println("Le jeu n°" + gameChoice + " va se lancer sur une grille de " + gridXSize + "x" + gridYSize + ".");

	}

}
