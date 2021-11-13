package fr.ensimag.boids;

import java.util.ArrayList;
import java.util.List;
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
		for (Agent agent : group.getLivingAgents()) {
			this.entities.add(agent);
		}
	}

	/**
	 * Compute all initial positions for the group of agents
	 * 
	 * @param group
	 * @return positions as a List of points
	 */
	private List<FPoint2D> getInitialPositions(AgentGroup group) {
		List<FPoint2D> positions = new ArrayList<>();

		for (int i = 0; i < group.getInitialAgentNumber(); i++) {
			Random r = new Random();
			positions.add(new FPoint2D(width / 2.0f + (2 * r.nextFloat() - 1.0f),
					height / 2.0f + (2 * r.nextFloat() - 1.0f)));

		}
		return positions;
	}

	/**
	 * Copute all initial velocities for the group of agents
	 * 
	 * @param group
	 * @return velocities as List of vectors
	 */
	private List<FVector2D> getInitialVelocities(AgentGroup group) {
		List<FVector2D> velocities = new ArrayList<>();
		for (int i = 0; i < group.getInitialAgentNumber(); i++) {
			velocities.add(new FVector2D(4.0f, 2.0f));
		}
		return velocities;
	}

	/**
	 * Setup the groups and begin the simulation
	 */
	public void start() {
		// Setup all groups in the area
		for (AgentGroup group : groups) {
			group.restart(this.getInitialPositions(group), this.getInitialVelocities(group), this);

			// The event to launch the simulation
			this.addEvent(new AgentGroupUpdateEvent(0, group.getUpdateStep(), group, this, optimized));
		}
	}

	/**
	 * Restart the simulation
	 */
	@Override
	public void restart() {
		this.eventManger.restart();
		this.start();
	}

}
