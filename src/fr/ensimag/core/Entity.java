package fr.ensimag.core;

import fr.ensimag.math.FPoint2D;
import gui.GraphicalElement;

public abstract class Entity implements GraphicalElement {
	private FPoint2D position;

	public Entity(FPoint2D position) {
		this.position = position;
	}

	public int getX() {
		return (int) getPosition().getX();
	}

	public int getY() {
		return (int) getPosition().getY();
	}

	public FPoint2D getPosition() {
		return position;
	}

}
