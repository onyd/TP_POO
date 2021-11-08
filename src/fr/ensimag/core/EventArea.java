package fr.ensimag.core;

import fr.ensimag.events.Event;
import fr.ensimag.events.EventManager;

public abstract class EventArea<T extends Entity> extends Area<T> {
	protected EventManager eventManger;
	
	public EventArea(int width, int height) {
		super(width, height);
		this.eventManger = new EventManager();
	}
	
	public void addEvent(Event e) {
		this.eventManger.addEvent(e);
	}
	
	public long getDate() {
		return this.eventManger.getDate();
	}
	
	@Override
	public void next() {
		eventManger.next();
	}

}
