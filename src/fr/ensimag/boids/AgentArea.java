package fr.ensimag.boids;

import java.util.ArrayList;
import java.util.Random;

import fr.ensimag.core.EventArea;
import fr.ensimag.events.AgentGroupUpdateEvent;
import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.FVector2D;

/**
 * Represents the area where agents evolve
 *
 */
public class AgentArea extends EventArea<Agent> {
	private ArrayList<AgentGroup> groups;
	private boolean optimized;

	/**
	 * Create an agent area of size (width, height) in pixel
	 * 
	 * @param width
	 * @param height
	 * @param optimized boolean which precise if we should use grid cell
	 *                  optimization which restrict all agents to have same
	 *                  viewDistance and interactions that only interact in that
	 *                  range
	 */
	public AgentArea(int width, int height, boolean optimized) {
		super(width, height);

		this.groups = new ArrayList<>();
		this.optimized = optimized;
	}

	/**
	 * Add an group of agents
	 * 
	 * @param group
	 */
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
				FPoint2D position = new FPoint2D(width / 2.0f + (2 * r.nextFloat() - 1.0f),
						height / 2.0f + (2 * r.nextFloat() - 1.0f));
				FVector2D velocity = new FVector2D(4.0f, 2.0f);
				Agent agent = new Agent(position, velocity, group.getInitialRadius(), group.getInitialMaxRadius(),
						group.getInitialViewDistance(), group.getInitialFov(), group.getInitialColor());

				// Add agent both in the group for interactions and in entities for enabling
				// graphic display
				group.add(agent);
				this.entities.add(agent);

			}
			this.addEvent(new AgentGroupUpdateEvent(0, group.getUpdateStep(), group, this, optimized));
		}
	}

}
