package fr.ensimag.core;

import java.util.ArrayList;
import gui.Simulable;

public abstract class Area implements Simulable {
	protected ArrayList<Entity> entities;
	protected ArrayList<Entity> buffer;
	protected int caseSize;
	
	@Override
	public void next() {
		for (Entity entity : entities) {
			entity.update(this);
		}
	}
	
}
