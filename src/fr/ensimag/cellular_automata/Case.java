package fr.ensimag.cellular_automata;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.ensimag.core.Entity;
import fr.ensimag.math.FPoint2D;

/**
 * Class storing different states :
 * the current state, the next state that will be calculate with the current states
 * of each neighbor case, and the initial state to reset the game
 * + managed the graphical part of a case
 */
public class Case extends Entity {
	private Color color;
	private int width, height;

	private State currentState;
	private State nextState;
	private State initialState;

	/**
	 *
	 * @param position position of the case
	 * @param width width
	 * @param height height
	 * @param curr current state
	 * @param next next state
	 * @param init initail state
	 */
	public Case(FPoint2D position, int width, int height, State curr, State next, State init) {
		super(position);
		this.width = width;
		this.height = height;
		this.currentState = curr;
		this.nextState = next;
		this.initialState = init;
	}

	/**
	 * value of current state become equal to the value of the initial state
	 */
	public void initCase(){
		this.currentState.copy(this.initialState);
		this.nextState.copy(this.initialState);
	}

	/**
	 * return current state
	 * @return currentState
	 */
	public State getCurrentState(){
		return this.currentState;
	}

	/**
	 * return nextState
	 * @return nextState
	 */
	public State getNextState(){
		return this.nextState;
	}

	/**
	 * calculate and update nextState with his neighborhood
	 * @param neighborsList cases belonging to Moore neighborhood
	 */
	public void calculate(List<Case> neighborsList){
		List<State> neighborsState = new ArrayList<>();
		for(Case c: neighborsList){
			neighborsState.add(c.getCurrentState());
		}

		// update of nextState with current neighbor state :
		// (each rules are apply in nextState)
		this.getNextState().nextState(neighborsState);
	}

	/**
	 * update of currentState (after calculating nextState of each case)
	 */
	public void update() {
		this.currentState.copy(this.nextState);
	}

	/**
	 * update the 'color' of the case
	 */
	public void updateColor(){
		int currValue = this.getCurrentState().getValue();
		int maxValue = State.nbState - 1;
		// TODO moyen d'opti ici pour ne pas recrér à chaque fois ...

		// create a range of color from white to blue for each state value
		// (default color is blue)
		int otherParam = (int) (255.0 * (1.0 - (float) currValue / maxValue));
		Color c = new Color(otherParam, otherParam,  255);
		this.color = c;
	}

	/**
	 * set color and fill the case
	 * @param g2d
	 */
	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.fillRect(getX(), getY(), width, height);
	}
}
