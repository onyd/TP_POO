package fr.ensimag.cellular_automata;

import fr.ensimag.core.GridArea;

import java.util.ArrayList;
import java.util.List;
import fr.ensimag.math.Rand;

public class SchellingState extends State{
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

    public void setState(int i){
        this.value = i;
    }

    public static void addVacantCell(Cell vacantCell) {
        vacantCells.add(vacantCell);
        numberVacantCells++;
    }

    public void nextState(List<State> neighborsList){
        if (this.getValue() != 0) {
            int nbNeighborsOfDifferentColor = 0;

            // Count how many cells are different
            for (State s: neighborsList) {
                if(s.getValue() != 0 && s.getValue() != this.getValue()) {
                    nbNeighborsOfDifferentColor++;
                }
            }

            if(nbNeighborsOfDifferentColor >= K) {
                move();
            }
        }
    }

    private void move() {
        // TODO si toutes les cases sont prises ???
        int r = Rand.rand(0, numberVacantCells - 1);

        // vacantCells.get(r) est la cellule qui devient habitée
        vacantCells.get(r).getNextState().copy(this);
        vacantCells.remove(r);

        // la cellule courante devient libre
        this.setState(0);
        vacantCells.add(motherCell);
    }




}
