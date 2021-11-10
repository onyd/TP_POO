package fr.ensimag.boids;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import fr.ensimag.core.Area;
import fr.ensimag.interactions.Interaction;
import fr.ensimag.util.BiDimensionalArray;
import fr.ensimag.util.Pair;

public class AgentGroup {
	protected int agentNumber;
	private BiDimensionalArray<ArrayList<Agent>> agentsGrid; // Stores the Agent in each grid cell of width/height
																// viewDistance/sqrt(2)
	private ArrayList<Interaction> interactions;
	private float initialRadius;
	private float initialViewDistance;
	private float initialFov;
	private Color initialColor;
	private int updateStep;

	protected float maxSpeed = 5.0f;

	public AgentGroup(int updateStep, int agentNumber, float radius, float viewDistance, float fov, Color color) {
		this.initialRadius = radius;
		this.initialViewDistance = viewDistance;
		this.initialFov = fov;
		this.initialColor = color;
		this.updateStep = updateStep;
		this.agentNumber = agentNumber;

		this.interactions = new ArrayList<>();

	}

	public int getAgentNumber() {
		return agentNumber;
	}

	public float getInitialRadius() {
		return initialRadius;
	}

	public float getInitialViewDistance() {
		return initialViewDistance;
	}

	public float getInitialFov() {
		return initialFov;
	}

	public void setInitialRadius(float initialRadius) {
		this.initialRadius = initialRadius;
	}

	public void setInitialViewDistance(float initialViewDistance) {
		this.initialViewDistance = initialViewDistance;
	}

	public void setInitialFov(float initialFov) {
		this.initialFov = initialFov;
	}

	public void setInitialColor(Color initialColor) {
		this.initialColor = initialColor;
	}

	public Color getInitialColor() {
		return initialColor;
	}

	public Pair<Integer, Integer> getKey(Agent a) {
		return new Pair<Integer, Integer>((int) (a.getX() / initialViewDistance),
				(int) (a.getY() / initialViewDistance));
	}

	public void add(Agent a) {
		Pair<Integer, Integer> key = getKey(a);
		this.agentsGrid.get(key.first, key.second).add(a);
	}

	public void addInteraction(Interaction i) {
		this.interactions.add(i);
	}

	public void removeInteraction(Interaction i) {
		this.interactions.remove(i);
	}

	public int getUpdateStep() {
		return updateStep;
	}

	private void clipPosition(Agent b, Area<Agent> area) {
		if (b.getPosition().x > area.getWidth()) {
			b.getPosition().x -= area.getWidth();
		} else if (b.getPosition().x < 0) {
			b.getPosition().x += area.getWidth();
		}
		if (b.getPosition().y > area.getHeight()) {
			b.getPosition().y -= area.getHeight();
		} else if (b.getPosition().y < 0) {
			b.getPosition().y += area.getHeight();
		}
	}

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

	public void update(Area<Agent> area) {
		for (int i = 0; i < agentsGrid.getHeight(); i++) {
			for (int j = 0; j < agentsGrid.getWidth(); j++) {
				Iterator<Agent> it = this.agentsGrid.get(i, j).iterator();
				for (Agent b; it.hasNext();) {
					b = it.next();
					Pair<Integer, Integer> lastKey = this.getKey(b);
					if (!b.update()) { // If it is dead
						it.remove();
					} else {
						this.clipPosition(b, area);
						// Update Boids cell
						Pair<Integer, Integer> key = this.getKey(b);
						if (!lastKey.equals(key)) {
							it.remove();
							this.agentsGrid.get(key).add(b);
						}
					}
				}
			}
		}
	}

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

	private List<Pair<Integer, Integer>> getNeighboorsRegions(int i, int j) {
		List<Pair<Integer, Integer>> neighboorsIndicies = new ArrayList<>();
		for (int k = -2; k <= 2; k++) {
			for (int p = -2; p <= 2; p++) {
				if (Math.abs(k) != 2 && Math.abs(p) != 2) {
					neighboorsIndicies.add(new Pair<Integer, Integer>(i + k, j + p));
				}
			}
		}
		return neighboorsIndicies;
	}

	public List<Agent> getNeighboors(Pair<Integer, Integer> cell) {
		ArrayList<Agent> neighboors = new ArrayList<>();
		for (Pair<Integer, Integer> key : this.getNeighboorsRegions(cell.first, cell.second)) {
			for (Agent neighboor : agentsGrid.get(key)) {
				neighboors.add(neighboor);
			}
		}
		return neighboors;
	}

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