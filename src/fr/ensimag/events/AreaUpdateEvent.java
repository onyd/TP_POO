package fr.ensimag.events;

import fr.ensimag.boids.AgentArea;

/**
 * Represents an event which is charged to apply specific area interactions
 *
 */
public class AreaUpdateEvent extends Event {
	private AgentArea area;

	/**
	 * Create the event which will apply area interactions
	 * 
	 * @param date
	 * @param area
	 */
	public AreaUpdateEvent(long date, AgentArea area) {
		super(date);
		this.area = area;
	}

	@Override
	public void execute() {
		area.applyGlobalInteractions();

		area.addEvent(new AreaUpdateEvent(this.getDate() + 1, area));
	}

}