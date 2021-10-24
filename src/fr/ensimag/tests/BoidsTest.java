package fr.ensimag.tests;

import java.awt.Color;
import fr.ensimag.core.BoidsArea;
import gui.GUISimulator;

public class BoidsTest {
	public static void main(String[] args) {
		BoidsArea area = new BoidsArea(600, 400, 10, 20.0f, 120.0f);
		GUISimulator gui = new GUISimulator(600, 400, Color.WHITE, area);
		
		area.addGraphicalElementsTo(gui);
	}
}
