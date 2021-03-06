package fr.ensimag.events;

import fr.ensimag.boids.AgentGroup;
import fr.ensimag.boids.AgentArea;

/**
 * Represents an event which is charged to do one step of simulation of a
 * group of agents
 *
 */
public class AgentGroupUpdateEvent extends Event {
	private AgentArea area;
	private AgentGroup caller;
	private boolean optimized;
	private int updateStep;

	/**
	 * Create the event which will execute one step of simulation to the group
	 * 
	 * @param date
	 * @param updateStep
	 * @param caller
	 * @param area
	 * @param optimized
	 */
	public AgentGroupUpdateEvent(long date, int updateStep, AgentGroup caller, AgentArea area,
			boolean optimized) {
		super(date);
		this.updateStep = updateStep;
		this.area = area;
		this.caller = caller;
		this.optimized = optimized;
	}

	@Override
	public void execute() {
		if (!optimized)
			caller.applyInteractions();
		else
			caller.applyInteractionsOptimized();

		caller.update(area);
		area.addEvent(new AgentGroupUpdateEvent(this.getDate() + updateStep, updateStep, caller, area, optimized));
	}

}
