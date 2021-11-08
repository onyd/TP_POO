package fr.ensimag.cellular_automata;

import java.util.List;

/**
 * Implements Immigration rules
 */
public class ImmigrationState extends ModuloState{
    public ImmigrationState (State s){
        super(s);
    }

    public ImmigrationState (int i){
        super(i);
    }

    /**
     * update this state with Immigration rules
     * @param neighborsState Moore neighborhood of this state
     */
    public void nextState(List<State> neighborsState){
        int nbBiggerNeighbor = 0; // number of cell at state k+1
        int stateValue = this.getValue();

        // calculation of nbBiggerNeighbor
        for (State s: neighborsState) {
            if(s.getValue() == ((stateValue + 1) % State.nbState)) {
                nbBiggerNeighbor++;
            }
        }

        // condition to change the state :
        if(nbBiggerNeighbor >= 3) {
            super.increaseValue();
        }
    }
}
