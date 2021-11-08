package fr.ensimag.cellular_automata;

import java.util.ArrayList;
import java.util.List;

public class Cell{
    private State currentState;
    private State nextState;
    private State initialState;

    public Cell(State curr, State next, State init){
        currentState = curr;
        nextState = next;
        initialState = init;
    }

    public void initCell(){
        this.currentState.copy(this.initialState);
    }

    public State getCurrentState(){
        return this.currentState;
    }

    public State getNextState(){
        return this.nextState;
    }

    public void calculate(List<Cell> neighborsList){
        List<State> neighborsState = new ArrayList<>();
        for(Cell c: neighborsList){
            neighborsState.add(c.getCurrentState());
        }
        // Mise à jour de la cellule suivante
        // avec les cellules voisines courantes :
        this.getNextState().nextState(neighborsState);
    }

    public void update() {
        this.currentState.copy(this.nextState);
    }
}
