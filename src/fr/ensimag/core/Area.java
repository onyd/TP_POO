package fr.ensimag.core;

import java.util.ArrayList;
import gui.GUISimulator;
import gui.Simulable;

public abstract class Area<T extends Entity> implements Simulable {
	protected int width;
	protected int height;
	protected ArrayList<T> entities;
	
	public Area(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.entities = new ArrayList<>();
	}
	
	public ArrayList<T> getEntities() {
		return entities;
	}
	
	public void addGraphicalElementsTo(GUISimulator gui) {
		for (Entity entity : getEntities()) {
			gui.addGraphicalElement(entity);
		}
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
