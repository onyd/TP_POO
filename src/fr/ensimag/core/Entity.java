package fr.ensimag.core;

import fr.ensimag.math.FPoint2D;
import gui.GraphicalElement;

/**
 * Represents an abstract element of an area which manage graphics and intrinsic
 * properties
 *
 */
public abstract class Entity implements GraphicalElement {
	private FPoint2D position;

	/**
	 * Create an entity at position
	 * 
	 * @param position
	 */
	public Entity(FPoint2D position) {
		this.position = position;
	}

	/**
	 * x coordinate getter
	 * 
	 * @return x
	 */
	public int getX() {
		return (int) getPosition().getX();
	}

	/**
	 * y coordinate getter
	 * 
	 * @return y
	 */
	public int getY() {
		return (int) getPosition().getY();
	}

	/**
	 * position getter
	 * 
	 * @return position
	 */
	public FPoint2D getPosition() {
		return position;
	}
	
	/**
	 * position setter
	 * @param position
	 */
	public void setPosition(FPoint2D position) {
		this.position = position;
	}

}
