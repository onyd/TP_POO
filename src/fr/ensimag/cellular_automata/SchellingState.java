package fr.ensimag.cellular_automata;

import java.util.ArrayList;
import java.util.List;
import fr.ensimag.math.MathUtil;

/**
 * Implements Schelling rules
 */
public class SchellingState extends State{
    /**
     * threshold for moving
     */
    private static int K = 3; // by default
    /**
     * List of Cells which are vacant at currentState
     */
    public static List<Cell> currentVacantCells = new ArrayList<Cell>();
    /**
     * List of Cells which are vacant at initialState
     */
    public static List<Cell> initialVacantCells = new ArrayList<Cell>();
    public Cell motherCell;

    public SchellingState(State s){
        super(s);
    }

    public SchellingState(int i){
        super(i);
    }

    /**
     * add a vacant Cell to the lists currentVacantCells and initialVacantCells
     * @param vacantCell cell to add
     */
    public static void addVacantCell(Cell vacantCell) {
        currentVacantCells.add(vacantCell);
        initialVacantCells.add(vacantCell);
    }

    /**
     * update this state with Schelling rules
     * @param neighborsList Moore neighborhood of this state
     */
    public void nextState(List<State> neighborsList){
        if (this.getValue() != 0) {
            int nbNeighborsOfDifferentColor = 0;

            // Count how many cells are different :
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
     * move this state in an other cell and free this cell
     * (this cell become vacant)
     */
    private void move() {

        int r = MathUtil.rand(0, currentVacantCells.size() - 1);
        // vacantCells.get(r) est la cellule qui devient habitée
        currentVacantCells.get(r).getNextState().copy(this);
        currentVacantCells.remove(r);

        // this cell became free
        this.setState(0);
        currentVacantCells.add(motherCell);
    }




}
