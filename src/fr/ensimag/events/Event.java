package fr.ensimag.events;

public abstract class Event implements Comparable<Event> {
	private long date;

	public Event(long date) {
		this.date = date;
	}

	public long getDate() {
		return date;
	}

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
