package fr.ensimag.boids;

import java.util.ArrayList;
import java.util.Random;

import fr.ensimag.core.EventArea;
import fr.ensimag.events.AgentGroupUpdateEvent;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.FVector2D;

public class AgentArea extends EventArea<Agent> {
	private ArrayList<AgentGroup> groups;
	private boolean optimized;

	public AgentArea(int width, int height, boolean optimized) {
		super(width, height);

		this.groups = new ArrayList<>();
		this.optimized = optimized;
	}

	public void addGroup(AgentGroup group) {
		this.groups.add(group);
	}

	@Override
	public void restart() {
		this.entities.clear();
		for (AgentGroup group : groups) {
			group.setup(this);
			for (int i = 0; i < group.getAgentNumber(); i++) {
				Random r = new Random();
				Agent b = new Agent(
						new FPoint2D(width / 2.0f + (2 * r.nextFloat() - 1.0f),
								height / 2.0f + (2 * r.nextFloat() - 1.0f)),
						new FVector2D(4.0f, 2.0f), group.getInitialRadius(), group.getInitialViewDistance(),
						group.getInitialFov(), group.getInitialColor());
				group.add(b);
				this.entities.add(b);

			}
			this.addEvent(new AgentGroupUpdateEvent(0, group.getUpdateStep(), group, this, optimized));
		}
	}

}
