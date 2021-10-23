package fr.ensimag.cellular_automata;

public abstract class State {
	protected int value;
	
	public int getValue() {
		return (value);
	}
	
	public abstract void nextState();
}
