package fr.ensimag.cellular_automata;

import java.util.ArrayList;
import java.util.List;
import fr.ensimag.math.MathUtil;

/**
 * Implements Schelling rules
 */
public class SchellingState extends State {
    /**
     * threshold for moving
     */
    private static int K = 3; // by default
    /**
     * List of Cases which are vacant at currentState
     */
    public static List<Case> currentVacantCases = new ArrayList<Case>();
    /**
     * List of Cases which are vacant at initialState
     */
    public static List<Case> initialVacantCases = new ArrayList<Case>();

    public Case motherCase;

    public SchellingState(State s){
        super(s);
    }

    public SchellingState(int i){
        super(i);
    }

    /**
     * add a vacant Case to the lists currentVacantCases and initialVacantCases
     * @param vacantCase case to add
     */
    public static void addVacantCase(Case vacantCase) {
        currentVacantCases.add(vacantCase);
        initialVacantCases.add(vacantCase);
    }

    /**
     * update this state with Schelling rules
     * @param neighborsList Moore neighborhood of this state
     */
    public void nextState(List<State> neighborsList){
        if (this.getValue() != 0) {
            int nbNeighborsOfDifferentColor = 0;

            // Count how many neighbor cases are different :
            for (State s: neighborsList) {
                if(s.getValue() != 0 && s.getValue() != this.getValue()) {
                    nbNeighborsOfDifferentColor++;
                }
            }

            // condition to change the state :
            if(nbNeighborsOfDifferentColor >= K ) {
                move();
            }
        }
    }

    /**
     * move this state in an other case and free this case
     * (this case become vacant)
     */
    private void move() {

        int r = MathUtil.rand(0, currentVacantCases.size() - 1);
        // currentVacantCases.get(r) become occupied
        currentVacantCases.get(r).getNextState().copy(this);
        currentVacantCases.remove(r);

        // this case became free
        this.setState(0);
        currentVacantCases.add(motherCase);
    }




}
