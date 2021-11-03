package fr.ensimag.cellular_automata;

import fr.ensimag.core.Area;
import fr.ensimag.core.GridArea;

import java.util.ArrayList;
import java.util.List;

public abstract class State {
	protected int value;
	public static int nbState = 2;
	
	public State(int i) {
		this.value = i;
	}

	public State(State s){
		this.value = s.getValue();
	}
	
	public int getValue() {
		return (value);
	}
	
	public abstract void nextState(List<State> neighborsState);
	
	public void copy(State s){
		this.value = s.value;
	}
}
