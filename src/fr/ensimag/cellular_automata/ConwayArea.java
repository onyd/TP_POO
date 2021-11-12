package fr.ensimag.cellular_automata;

import fr.ensimag.math.FPoint2D;
import fr.ensimag.math.MathUtil;

public class ConwayArea extends GridArea{
    public ConwayArea(int width, int height, int caseSize){
        super(width, height, caseSize);
        State.nbState = 2;

        System.out.println("Add Conway cases...");
        // set the cases into the grid :
        for (int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                // set the 3 states with the same value
                ConwayState currState = new ConwayState(MathUtil.rand(0, 1));
                ConwayState nextState = new ConwayState(currState);
                ConwayState initState = new ConwayState(currState);
                Case c = new Case(new FPoint2D(i * caseSize, j * caseSize), caseSize, caseSize, currState, nextState, initState);
                super.entities.add(c);
            }
        }

        super.updateCases();
    }
}
