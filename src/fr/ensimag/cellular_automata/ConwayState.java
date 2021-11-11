package fr.ensimag.cellular_automata;

import java.util.List;

/**
 * Implements Conway rules
 */
public class ConwayState extends ModuloState{
    public ConwayState(int i){
        super(i);
    }

    public ConwayState(State s){
        super(s);
    }

    /**
     * update this state with Conway rules
     * @param neighborsState Moore neighborhood of this state
     */
    public void nextState(List<State> neighborsState){
        int nbNeighborsAlive = 0;
        for (State s: neighborsState) {
            if(s.getValue() == 1) { // a neighbor is alive
                nbNeighborsAlive++;
            }
        }

        if(this.getValue() == 0) { // Dead
            if(nbNeighborsAlive == 3) {
                super.increaseValue(); // setAlive
            }
        }else { // Alive
            if(!(nbNeighborsAlive == 2 ||nbNeighborsAlive == 3)) {
                super.increaseValue(); // setDead
            }
        }
    }
}
