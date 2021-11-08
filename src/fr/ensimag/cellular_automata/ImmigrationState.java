package fr.ensimag.cellular_automata;

import java.util.List;

public class ImmigrationState extends ModuloState{
    public ImmigrationState (State s){
        super(s);
    }

    public ImmigrationState (int i){
        super(i);
    }

    public void nextState(List<State> neighborsState){
        int nbBiggerNeighbor = 0; // nombre de cellule  l'état k+1
        int stateValue = this.getValue();

        // calcul de nbBiggerNeighbor
        for (State s: neighborsState) {
            if(s.getValue() == ((stateValue + 1) % State.nbState)) {
                nbBiggerNeighbor++;
            }
        }

        if(nbBiggerNeighbor >= 3) {
            super.increaseValue();
        }
    }
}
