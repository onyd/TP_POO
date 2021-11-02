package fr.ensimag.events;

import java.util.PriorityQueue;

public class EventManager {
	private long currentDate;
	private PriorityQueue<Event> events;

	public EventManager() {
		this.currentDate = 0;
		this.events = new PriorityQueue<>();
	}

	public void addEvent(Event e) {
		this.events.add(e);
	}

	public void next() {
		this.currentDate++;
		while (!events.isEmpty() && events.peek().getDate() <= this.currentDate) {
			events.poll().execute();
		}
	}

	public boolean isFinished() {
		return events.isEmpty();
	}

	public void restart() {
		this.events.clear();
		this.currentDate = 0;
	}

}
