package fr.ensimag.tests;

import java.awt.Color;
import fr.ensimag.core.BoidsArea;
import gui.GUISimulator;

public class BoidsTest {
	public static void main(String[] args) {
		BoidsArea area = new BoidsArea(600, 400, 100, 40.0f, 150.0f);
		GUISimulator gui = new GUISimulator(600, 400, Color.WHITE, area);
		
		area.addGraphicalElementsTo(gui);
	}
}
