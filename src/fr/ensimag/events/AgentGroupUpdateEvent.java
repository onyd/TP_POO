package fr.ensimag.events;

import fr.ensimag.boids.AgentGroup;
import fr.ensimag.boids.Agent;
import fr.ensimag.core.EventArea;

public class AgentGroupUpdateEvent extends Event {
	private EventArea<Agent> area;
	private AgentGroup caller;
	private boolean optimized;
	private int updateStep;

	public AgentGroupUpdateEvent(long date, int updateStep, AgentGroup caller, EventArea<Agent> area, boolean optimized) {
		super(date);
		this.updateStep = updateStep;
		this.area = area;
		this.caller = caller;
		this.optimized = optimized;
	}

	@Override
	public void execute() {
		if (!optimized)
			caller.applyInteractions(area);
		else
			caller.applyInteractionsOptimized(area);
		
		caller.update(area);
		area.addEvent(new AgentGroupUpdateEvent(this.getDate() + updateStep, updateStep, caller, area, optimized));
	}

}
