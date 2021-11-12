package fr.ensimag.cellular_automata;

import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.MathUtil;

public class SchellingArea extends GridArea{
    public SchellingArea(int width, int height, int caseSize){
        super(width, height, caseSize);
        State.nbState = 4; // by default

        System.out.println("Add Schelling cells");
        // set the cells into the grid :
        for (int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                SchellingState currState3 = new SchellingState(MathUtil.rand(0, State.nbState - 1));
                SchellingState nextState3 = currState3;
                SchellingState initState3 = new SchellingState(currState3);

                // Note that currState3 and nextState3 have same state
                // It is necessary to change the grid dynamically (see Cell.calculate() method)
                Cell c3 = new Cell(currState3, nextState3, initState3);
                this.cellList.add(c3);

                // link the mother cell for each state :
                // (a state should be able to find back her mother cell
                //  to put it in the vacant list)
                currState3.motherCell = c3;
                nextState3.motherCell = c3;
                initState3.motherCell = c3;

                if (currState3.getValue() == 0) {
                    SchellingState.addVacantCell(c3);
                }
            }
        }

        // fill the grid with Cases :
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                super.entities.add(new Case(new FPoint2D(i * caseSize, j * caseSize), caseSize, caseSize, super.getCell(i, j)));
            }
        }

        super.updateCases();
    }

    /**
     * reset the grid with initial values everywhere
     */
    @Override
    public void restart() {
        for(Cell c : cellList){
            c.initCell();
        }

        // Vacant cells must be changed for initial ones
        SchellingState.currentVacantCells.clear();
        SchellingState.currentVacantCells.addAll(SchellingState.initialVacantCells);

        super.updateCases();
    }
}
