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
    public static List<Cell> vacantCells = new ArrayList<Cell>();
    public static int numberVacantCells = 0;
    public Cell motherCell;

    public SchellingState(State s){
        super(s);
    }

    public SchellingState(int i){
        super(i);
    }

    /**
     * add vacantCell to the list
     * @param vacantCell cell to add
     */
    public static void addVacantCell(Cell vacantCell) {
        vacantCells.add(vacantCell);
        numberVacantCells++;
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
        // TODO si toutes les cases sont prises ??? --> raise error
        int r = Rand.rand(0, numberVacantCells - 1);

        // vacantCells.get(r) become occupied next step
        vacantCells.get(r).getNextState().copy(this.motherCell.getNextState());
        vacantCells.remove(r);

        // this cell became free
        this.setState(0);
        vacantCells.add(motherCell);
    }




}
