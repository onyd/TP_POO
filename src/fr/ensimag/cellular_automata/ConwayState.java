package fr.ensimag.cellular_automata;

import java.util.List;

public class ConwayState extends ModuloState{

    public ConwayState(int i){
        super(i);
    }

    public ConwayState(State s){
        super(s);
    }

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
