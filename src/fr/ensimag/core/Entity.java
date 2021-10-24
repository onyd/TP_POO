package fr.ensimag.core;

import fr.ensimag.math.FPoint2D;
import gui.GraphicalElement;

public abstract class Entity implements GraphicalElement {
	protected FPoint2D position;
	
	public Entity(FPoint2D position) {
		this.position = position;
	}
	
	public int getX() {
		return (int) position.getX();
	} 
	
	public int getY() {
		return (int) position.getY();
	}
	
	public abstract void update(Area area);

}
