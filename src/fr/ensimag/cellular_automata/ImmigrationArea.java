package fr.ensimag.cellular_automata;

import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.MathUtil;

public class ImmigrationArea extends GridArea{
    public ImmigrationArea(int width, int height, int caseSize){
        super(width, height, caseSize);
        State.nbState = 3;

        System.out.println("Add Immigration cases");
        // set the cases into the grid :
        for (int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                ImmigrationState currState2 = new ImmigrationState(MathUtil.rand(0, State.nbState - 1));
                ImmigrationState nextState2 = new ImmigrationState(currState2);
                ImmigrationState initState2 = new ImmigrationState(currState2);
                Case c2 = new Case(new FPoint2D(i * caseSize, j * caseSize), caseSize, caseSize, currState2, nextState2, initState2);
                super.entities.add(c2);
            }
        }

        super.updateCases();
    }
}
