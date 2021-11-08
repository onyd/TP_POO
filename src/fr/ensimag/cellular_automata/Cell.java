package fr.ensimag.cellular_automata;

import java.util.ArrayList;
import java.util.List;

/**
 * Class storing different states :
 * the current state, the next state that will be calculate with the current states
 * of each neighbor cell, and the initial state to reset the game
 */
public class Cell{
    private State currentState;
    private State nextState;
    private State initialState;

    public Cell(State curr, State next, State init){
        currentState = curr;
        nextState = next;
        initialState = init;
    }

    /**
     * value of current state become equal to the value of the initial state
     */
    public void initCell(){
        this.currentState.copy(this.initialState);
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
     * @param neighborsList cells belonging to Moore neighborhood
     */
    public void calculate(List<Cell> neighborsList){
        List<State> neighborsState = new ArrayList<>();
        for(Cell c: neighborsList){
            neighborsState.add(c.getCurrentState());
        }

        // update of nextState with current neighbor state :
        // (each rules are apply in nextState)
        this.getNextState().nextState(neighborsState);
    }

    /**
     * update of currentState (after calculating nextState of each cell)
     */
    public void update() {
        this.currentState.copy(this.nextState);
    }
}
