package fr.ensimag.cellular_automata;

import fr.ensimag.core.GridArea;

public abstract class State {
	protected int value;
	
	public State(State s) {
		this.value = s.value; 
	}
	
	public int getValue() {
		return (value);
	}
	
	public abstract void nextState(GridArea cases);
	
	public abstract State copy();
}
