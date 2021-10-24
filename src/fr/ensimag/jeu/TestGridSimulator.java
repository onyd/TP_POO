package automateCellulaire;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class TestGridSimulator {

	public static void main(String[] args) {
		int gridXSize = 50, gridYSize = 50;
		Grid grid = new Grid(gridXSize, gridYSize);
		
		GridSimulator simu = new GridSimulator(grid);
		
		int limX = gridXSize * simu.getCellSize();
		int limY = gridYSize * simu.getCellSize();
		GUISimulator gui = new GUISimulator(limX, limY, Color.BLACK);
		gui.addGraphicalElement(new Rectangle(limX/2, limY/2, Color.BLACK, Color.BLACK, limX, limY));
		
		gui.setSimulable(simu);
		
		for (Rectangle r :simu.getListRectangle()) {
			gui.addGraphicalElement(r);
		}
	}

}
