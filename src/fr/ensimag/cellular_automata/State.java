package fr.ensimag.cellular_automata;

<<<<<<< HEAD
public abstract class State {
	protected int value;

	public State(State s) {
		this.value = s.value;
=======
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
>>>>>>> implement_jeux
	}

	public int getValue() {
		return (value);
	}
<<<<<<< HEAD

	public abstract void nextState(GridArea cases);

	public abstract State copy();
=======
	
	public abstract void nextState(List<State> neighborsState);
	
	public void copy(State s){
		this.value = s.value;
	}
>>>>>>> implement_jeux
}
