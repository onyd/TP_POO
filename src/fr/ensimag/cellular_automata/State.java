package fr.ensimag.cellular_automata;

import java.util.List;

/**
 * Abstract class that stores the value of state.
 */
public abstract class State {
	protected int value;
	public static int nbState = 2;
	
	public State(int i) {
		this.value = i;
	}

	public State(State s){
		this.value = s.getValue();
	}

	/**
	 * get 'value' attribute
	 * @return 'value' of the state
	 */
	public int getValue() {
		return (value);
	}

	/**
	 * update the attribute 'value' with state in the Moore neighborhood
	 */
	public abstract void nextState(List<State> neighborsState);

	/**
	 * update the attribute 'value' by copying the value of a given state
	 */
	public void copy(State s){
		this.value = s.value;
	}
}
