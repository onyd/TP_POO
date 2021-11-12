package fr.ensimag.events;

import java.util.PriorityQueue;

/**
 * Represents the manager of event which keep track of events and execute them
 * when time has come
 *
 */
public class EventManager {
	private long currentDate;
	private PriorityQueue<Event> events;

	/**
	 * Create an event manager
	 */
	public EventManager() {
		this.currentDate = 0;
		this.events = new PriorityQueue<>();
	}

	/**
	 * date getter
	 * 
	 * @return date
	 */
	public long getDate() {
		return currentDate;
	}

	/**
	 * Push an event in the event manager
	 * 
	 * @param e
	 */
	public void addEvent(Event e) {
		this.events.add(e);
	}

	/**
	 * Process all event that have to be executed
	 */
	public void next() {
		this.currentDate++;
		while (!events.isEmpty() && events.peek().getDate() <= this.currentDate) {
			events.poll().execute();
		}
	}

	/**
	 * Compute if there are still some events in the event manager
	 * 
	 * @return true if there is no more event
	 */
	public boolean isFinished() {
		return events.isEmpty();
	}

	/**
	 * Reset the event manager
	 */
	public void restart() {
		this.events.clear();
		this.currentDate = 0;
	}

}
