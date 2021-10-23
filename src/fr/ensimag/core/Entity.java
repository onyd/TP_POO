package fr.ensimag.core;

import java.awt.Point;
import gui.GraphicalElement;

public abstract class Entity implements GraphicalElement {
	protected Point position;
	
	public int getX() {
		return (int) (position.getX());
	}
	
	public int getY() {
		return (int) (position.getY());
	}
	
	public abstract void update(Area area);

}
