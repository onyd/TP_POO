package fr.ensimag.cellular_automata;

import java.util.List;
import java.util.ArrayList;
import fr.ensimag.math.MathUtil;

public class Grid {
    private final int width, height;
    private final List<Cell> cellList;
    private int gameChoice;

    public Grid(int width, int height, int gameChoice) {
        this.width = width;
        this.height = height;
        this.gameChoice = gameChoice;

        switch(gameChoice) {
            case 1: // jeu de la vie
                State.nbState = 2;
                break;
            case 2: // jeu de l'immigration
                State.nbState = 3;
                break;
            case 3: // Modle de Schelling
                State.nbState = 4;
                break;
            default:
                throw new IllegalArgumentException("Jeu non séléctionnable...");
        }

        cellList = new ArrayList<Cell>();

        switch(gameChoice) {
            case 1: // Jeu de la vie
                System.out.println("Ajout de cellules de Conway");
                break;

            case 2: // Jeu de l'immigration
                System.out.println("Ajout de cellules de l'immigration");
                break;

            case 3: // Modèle de Schelling
                System.out.println("Ajout de cellules de Schelling");
                break;

            default:
                System.out.println("Jeu non séléctionnable...");
                // TODO mettre une exception
                break;
        }

        for (int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                switch(gameChoice) {
                    case 1: // jeu de la vie
                        ConwayState currState = new ConwayState(MathUtil.rand(0, 1));
                        ConwayState nextState = new ConwayState(currState);
                        ConwayState initState = new ConwayState(currState);
                        Cell c = new Cell(currState, nextState, initState);
                        this.cellList.add(c);
                        break;

                    case 2: // jeu de l'immigration
                        ImmigrationState currState2 = new ImmigrationState(MathUtil.rand(0, State.nbState - 1));
                        ImmigrationState nextState2 = new ImmigrationState(currState2);
                        ImmigrationState initState2 = new ImmigrationState(currState2);
                        Cell c2 = new Cell(currState2, nextState2, initState2);
                        this.cellList.add(c2);
                        break;

                    case 3: // Modle de Schelling
                        int r = MathUtil.rand(0, State.nbState - 1);
                        SchellingState currState3 = new SchellingState(MathUtil.rand(0, State.nbState - 1));
                        SchellingState nextState3 = new SchellingState(currState3);
                        SchellingState initState3 = new SchellingState(currState3);
                        Cell c3 = new Cell(currState3, nextState3, initState3);
                        // TODO, pas très joli de devoir link la cellule mère après ...
                        currState3.motherCell = c3;
                        nextState3.motherCell = c3;
                        initState3.motherCell = c3;
                        this.cellList.add(c3);
                        if (r == 0) SchellingState.addVacantCell(c3);
                        break;

                    default:
                        System.out.println("Jeu non séléctionnable...");
                        // TODO mettre une exception
                        break;
                }
            }
        }
    }

    public Cell getCell(int i, int j) {
        return this.cellList.get(i * width + j);
    }

    private List<Cell> getNeighbors(int i, int j) {
        List<Cell> neighborsList = new ArrayList<Cell>();
        for(int a = -1; a <= 1; a++) {
            for(int b = -1; b <= 1; b++) {
                if(!(a == 0 && b == 0)) {
                    // l'espace de jeu est circulaire, une cellule
                    // tout  gauche a une voisine tout  droite de la grille
                    neighborsList.add(this.getCell((i + a + this.width) %  this.width, (j + b + this.height) % this.height));
                }
            }
        }
        return neighborsList;
    }

    public void restart() {
        for(Cell c : cellList){
            c.initCell();
        }

        // TODO Faire une méthode autre ?
        if(this.gameChoice == 3) {
            SchellingState.vacantCells.clear();
            SchellingState.numberVacantCells = 0;
            for (int i = 0; i < this.width; i++) {
                for(int j = 0; j < this.height; j++) {
                    // TODO avoir une méthode is null ?
                    if(this.getCell(i, j).getCurrentState().getValue() == 0){
                        SchellingState.addVacantCell(this.getCell(i, j));
                    }
                }
            }
        }
    }

    public void iterate() {
        /** calculating **/
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.width; j++){
                this.getCell(i, j).calculate(this.getNeighbors(i, j));
            }
        }
        /** updating **/
        for(Cell c: this.cellList){
            c.update();
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                str += this.getCell(i, j).getCurrentState().getValue() + " ";
            }
            str += "\n";
        }
        return str;
    }
}

