package fr.ensimag.cellular_automata;

import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.MathUtil;

import java.awt.*;
import java.util.ArrayList;

public class SchellingArea extends GridArea{
    public SchellingArea(int width, int height, int caseSize){
        super(width, height, caseSize);
        State.nbState = 4; // by default

        // set the cases into the grid :
        System.out.println(State.nbState);
        for (int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                SchellingState currState3 = new SchellingState(MathUtil.rand(0, State.nbState - 1));
                SchellingState nextState3 = currState3;
                SchellingState initState3 = new SchellingState(currState3);

                // Note that currState3 and nextState3 have same state
                // It is necessary to change the grid dynamically (see Case.calculate() method)
                Case c3 = new Case(new FPoint2D(i * caseSize, j * caseSize), caseSize, caseSize, currState3, nextState3, initState3);
                super.entities.add(c3);

                // link the mother case for each state :
                // (a state should be able to find back her mother case
                //  to put it in the vacant list)
                currState3.motherCase = c3;
                nextState3.motherCase = c3;
                initState3.motherCase = c3;

                if (currState3.getValue() == 0) {
                    SchellingState.addVacantCase(c3);
                }
            }
        }

        // init listColors :
        int maxValue = State.nbState - 1;
        GridArea.listColors = new ArrayList<Color>();
        for(int i = 0; i <= State.nbState - 1; i++){
            int otherParam = (int) (255.0 * (1.0 - (float) i / maxValue));
            Color c = new Color(otherParam, otherParam,  255);
            GridArea.listColors.add(c);
        }

        super.updateCases();
    }

    /**
     * reset the grid with initial values everywhere
     */
    @Override
    public void restart() {
        for(Case c : super.entities){
            c.initCase();
        }

        // Vacant cases must be changed for initial ones
        SchellingState.currentVacantCases.clear();
        SchellingState.currentVacantCases.addAll(SchellingState.initialVacantCases);

        super.updateCases();
    }
}
