package fr.ensimag.core;

import gui.Simulable;

public abstract class Area implements  Simulable{
	protected int width;
	protected int height;
	
	public Area(int width, int height) {
		this.width = width;
		this.height = height;
	}
}
