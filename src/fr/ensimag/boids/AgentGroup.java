package fr.ensimag.boids;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import fr.ensimag.core.Area;
import fr.ensimag.interactions.Interaction;
import fr.ensimag.util.BiDimensionalArray;
import fr.ensimag.util.Pair;

/**
 * Represents a group of agents which share some characteristics as initial ones
 * and follow same behaviour ie interactions
 * 
 */
public class AgentGroup {
	protected int agentNumber;
	private BiDimensionalArray<ArrayList<Agent>> agentsGrid; // Stores the Agent in each grid cell of width/height
																// viewDistance/sqrt(2)
	private ArrayList<Interaction> interactions;
	private float initialRadius;
	private float initialMaxRadius;
	private float initialViewDistance;
	private float initialFov;
	private Color initialColor;
	private int updateStep;

	protected float initialMaxSpeed;

	/**
	 * Create a group of agent with the corresponding characteristics
	 * 
	 * @param updateStep          the number of step between updates
	 * @param agentNumber         how many agents are in the group
	 * @param initialRadius       the initial radius of all agents
	 * @param initialMaxRadius    the initial max radius of all agents
	 * @param initialViewDistance the initial viewDistance of all agents
	 * @param initialFov          the initial fov of all agents
	 * @param initialColor        the initial color of all agents
	 */
	public AgentGroup(int updateStep, int agentNumber, float initialRadius, float initialMaxRadius,
			float initialViewDistance, float initialFov, float initialMaxSpeed, Color initialColor) {
		this.initialRadius = initialRadius;
		this.initialMaxRadius = initialMaxRadius;
		this.initialViewDistance = initialViewDistance;
		this.initialFov = initialFov;
		this.initialMaxSpeed = initialMaxSpeed;
		this.initialColor = initialColor;
		this.updateStep = updateStep;
		this.agentNumber = agentNumber;

		this.interactions = new ArrayList<>();

	}

	/**
	 * agent number getter
	 * 
	 * @return agentNumber
	 */
	public int getAgentNumber() {
		return agentNumber;
	}

	/**
	 * initial radius getter
	 * 
	 * @return initialNumber
	 */
	public float getInitialRadius() {
		return initialRadius;
	}

	/**
	 * initial max radius getter
	 * 
	 * @return initialMaxRadius
	 */
	public float getInitialMaxRadius() {
		return initialMaxRadius;
	}

	/**
	 * initial view distance getter
	 * 
	 * @return initialViewDistance
	 */
	public float getInitialViewDistance() {
		return initialViewDistance;
	}

	/**
	 * initial fov getter
	 * 
	 * @return initialFov
	 */
	public float getInitialFov() {
		return initialFov;
	}

	/**
	 * initial color getter
	 * 
	 * @return initialColor
	 */
	public Color getInitialColor() {
		return initialColor;
	}

	/**
	 * update step getter
	 * 
	 * @return updateStep
	 */
	public int getUpdateStep() {
		return updateStep;
	}

	/**
	 * Compute the cell indicies of an agent
	 * 
	 * @param agent
	 * @return the Pair of indicies
	 */
	public Pair<Integer, Integer> getKey(Agent agent) {
		return new Pair<Integer, Integer>((int) (agent.getX() / initialViewDistance),
				(int) (agent.getY() / initialViewDistance));
	}

	/**
	 * Add an agent to the group and place it in the right cell
	 * 
	 * @param agent
	 */
	public void add(Agent agent) {
		Pair<Integer, Integer> key = getKey(agent);
		this.agentsGrid.get(key.first, key.second).add(agent);
	}

	/**
	 * Add an interaction to the group
	 * 
	 * @param i
	 */
	public void addInteraction(Interaction i) {
		this.interactions.add(i);
	}

	/**
	 * Remove an interaction of the group
	 * 
	 * @param i
	 */
	public void removeInteraction(Interaction i) {
		this.interactions.remove(i);
	}

	/**
	 * Clip the position of an agent in the area with a circular behavior
	 * 
	 * @param agent
	 * @param area
	 */
	private void clipPosition(Agent agent, Area<Agent> area) {
		if (agent.getPosition().x > area.getWidth()) {
			agent.getPosition().x -= area.getWidth();
		} else if (agent.getPosition().x < 0) {
			agent.getPosition().x += area.getWidth();
		}
		if (agent.getPosition().y > area.getHeight()) {
			agent.getPosition().y -= area.getHeight();
		} else if (agent.getPosition().y < 0) {
			agent.getPosition().y += area.getHeight();
		}
	}

	/**
	 * Apply each interactions of the group to each agents naively
	 * 
	 * @param area
	 */
	public void applyInteractions(Area<Agent> area) {
		// Update each boids of each grid cells
		for (int i = 0; i < agentsGrid.getHeight(); i++) {
			for (int j = 0; j < agentsGrid.getWidth(); j++) {
				Pair<Integer, Integer> key = new Pair<Integer, Integer>(i, j);
				for (Interaction interaction : interactions) {
					interaction.apply(this.agentsGrid.get(key));
				}
			}
		}
	}

	/**
	 * Apply each interactions of the group to each agents by using grid slicing
	 * optimization
	 * 
	 * @param area
	 */
	public void applyInteractionsOptimized(Area<Agent> area) {
		// Update each boids of each grid cells
		for (int i = 0; i < agentsGrid.getHeight(); i++) {
			for (int j = 0; j < agentsGrid.getWidth(); j++) {
				Pair<Integer, Integer> key = new Pair<Integer, Integer>(i, j);
				for (Interaction interaction : interactions) {
					interaction.applyOptimized(this.agentsGrid.get(key), key);
				}
			}
		}
	}

	/**
	 * Do one step of simulation for the group of agent
	 * 
	 * @param area
	 */
	public void update(Area<Agent> area) {
		for (int i = 0; i < agentsGrid.getHeight(); i++) {
			for (int j = 0; j < agentsGrid.getWidth(); j++) {
				Iterator<Agent> it = this.agentsGrid.get(i, j).iterator();
				for (Agent agent; it.hasNext();) {
					agent = it.next();
					Pair<Integer, Integer> lastKey = this.getKey(agent);
					if (!agent.update()) { // If it is dead
						it.remove();
					} else {
						this.clipPosition(agent, area);
						// Update Boids cell
						Pair<Integer, Integer> key = this.getKey(agent);
						if (!lastKey.equals(key)) {
							it.remove();
							this.agentsGrid.get(key).add(agent);
						}
					}
				}
			}
		}
	}

	/**
	 * Initialize the group
	 * 
	 * @param area
	 */
	public void setup(AgentArea area) {
		int width = (int) (area.getWidth() / initialViewDistance);
		int height = (int) (area.getHeight() / initialViewDistance);
		this.agentsGrid = new BiDimensionalArray<>(width, height);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.agentsGrid.set(i, j, new ArrayList<Agent>());
			}
		}
	}

	/**
	 * Compute the neighboors cell indicies for a given cell
	 * 
	 * @param i
	 * @param j
	 * @return the indicies as an List of Pair
	 */
	private List<Pair<Integer, Integer>> getNeighboorsRegions(Pair<Integer, Integer> cell) {
		List<Pair<Integer, Integer>> neighboorsIndicies = new ArrayList<>();
		for (int k = -2; k <= 2; k++) {
			for (int p = -2; p <= 2; p++) {
				if (Math.abs(k) != 2 && Math.abs(p) != 2) {
					neighboorsIndicies.add(new Pair<Integer, Integer>(cell.first + k, cell.second + p));
				}
			}
		}
		return neighboorsIndicies;
	}

	/**
	 * Compute the neighboors agents for a given cell
	 * 
	 * @param cell
	 * @return the neighboors agents as a List of Agent
	 */
	public List<Agent> getNeighboors(Pair<Integer, Integer> cell) {
		List<Agent> neighboors = new ArrayList<>();
		for (Pair<Integer, Integer> key : this.getNeighboorsRegions(cell)) {
			for (Agent neighboor : agentsGrid.get(key)) {
				neighboors.add(neighboor);
			}
		}
		return neighboors;
	}

	/**
	 * Compute all agents in the group
	 * 
	 * @return agents as a List of Agent
	 */
	public List<Agent> getAgents() {
		// Build an iterator over all Boids of neighboor cells
		ArrayList<Agent> agents = new ArrayList<>();
		for (int i = 0; i < agentsGrid.getHeight(); i++) {
			for (int j = 0; j < agentsGrid.getWidth(); j++) {
				for (Agent a : agentsGrid.get(i, j)) {
					agents.add(a);
				}
			}
		}
		return agents;
	}

}
