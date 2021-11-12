package fr.ensimag.core;

import java.util.ArrayList;
import gui.GUISimulator;
import gui.Simulable;

/**
 * Represents a generic area which contains entities
 *
 * @param <T>
 */
public abstract class Area<T extends Entity> implements Simulable {
	protected int width;
	protected int height;
	protected ArrayList<T> entities;

	public Area(int width, int height) {
		this.width = width;
		this.height = height;

		this.entities = new ArrayList<>();
	}

	/**
	 * Add all entities to the GUISimulator
	 * 
	 * @param gui
	 */
	public void addGraphicalElementsTo(GUISimulator gui) {
		for (Entity entity : entities) {
			gui.addGraphicalElement(entity);
		}

	}

	/**
	 * width getter
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * height getter
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

}
