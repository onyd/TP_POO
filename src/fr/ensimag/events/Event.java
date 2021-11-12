package fr.ensimag.events;

/**
 * Represents an abstract discrete event which will be executed by the event
 * manager
 *
 */
public abstract class Event implements Comparable<Event> {
	private long date;

	/**
	 * Have to be called in every child of this class to create an event
	 * 
	 * @param date
	 */
	public Event(long date) {
		this.date = date;
	}

	/**
	 * date getter
	 * 
	 * @return date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * The method called when the event manager come to process the event
	 */
	public abstract void execute();

	@Override
	public int compareTo(Event e) {
		if (this.date < e.date) {
			return -1;
		} else if (this.date > e.date) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Event) {
			Event e = (Event) o;
			return this.date == e.date;
		}
		return false;
	}
}
