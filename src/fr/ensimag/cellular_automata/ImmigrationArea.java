package fr.ensimag.cellular_automata;

import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.MathUtil;

import java.awt.*;
import java.util.ArrayList;

/**
 * Implementation of GridArea for Immigration's game
 */
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
}
