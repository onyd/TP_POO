package fr.ensimag.core;

import fr.ensimag.events.Event;
import fr.ensimag.events.EventManager;

/**
 * Specialization of Area which allows to push discrete events and have them
 * executed at the right time of simulation
 *
 * @param <T>
 */
public abstract class EventArea<T extends Entity> extends Area<T> {
	protected EventManager eventManger;

	/**
	 * Create an EventArea of size (width, height)
	 * 
	 * @param width
	 * @param height
	 */
	public EventArea(int width, int height) {
		super(width, height);
		this.eventManger = new EventManager();
	}

	/**
	 * Push an event
	 * 
	 * @param e
	 */
	public void addEvent(Event e) {
		this.eventManger.addEvent(e);
	}

	/**
	 * Current simulation step getter
	 * 
	 * @return
	 */
	public long getDate() {
		return this.eventManger.getDate();
	}

	@Override
	public void next() {
		eventManger.next();
	}

}
