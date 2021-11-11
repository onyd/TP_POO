package fr.ensimag.cellular_automata;

import java.util.List;
import java.util.ArrayList;
import fr.ensimag.math.MathUtil;

/**
 * Class which stores all cells in a grid (only calculation, no graphics)
 */
public class Grid {
    /**
     * grid size is 'width' cells by 'height' cells
     */
    private final int width, height;

    /**
     * grid is represented as a list
     * (but grid is interpreted as an 2D array)
     */
    private final List<Cell> cellList;

    /**
     * choice of the game made when instantiate the grid :
     * 1 : Conway
     * 2 : Immigration
     * 3 : Schelling
     */
    private int gameChoice;

    public Grid(int width, int height, int gameChoice) {
        this.width = width;
        this.height = height;
        this.gameChoice = gameChoice;

        // set maximum value in each state :
        switch(gameChoice) {
            case 1: // Conway
                State.nbState = 2;
                break;
            case 2: // Immigration
                State.nbState = 3;
                break;
            case 3: // Schelling
                State.nbState = 4;
                break;
            default:
                throw new IllegalArgumentException("Game not available ...");
        }

        cellList = new ArrayList<Cell>();

        // set the cells into the grid :
        switch(gameChoice) {
            case 1: // Conway
                System.out.println("Add Conway cells");
                break;

            case 2: // Immigration
                System.out.println("Add Immigration cells");
                break;

            case 3: // Schelling
                System.out.println("Add Schelling cells");
                break;

            default:
                throw new IllegalArgumentException("Game not available ...");
        }

        for (int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                switch(gameChoice) {
                    case 1: // Conway
                        // set the 3 states with the same value
                        ConwayState currState = new ConwayState(Rand.rand(0, 1));
                        ConwayState nextState = new ConwayState(currState);
                        ConwayState initState = new ConwayState(currState);
                        Cell c = new Cell(currState, nextState, initState);
                        this.cellList.add(c);
                        break;

                    case 2: // Immigration
                        ImmigrationState currState2 = new ImmigrationState(Rand.rand(0, State.nbState - 1));
                        ImmigrationState nextState2 = new ImmigrationState(currState2);
                        ImmigrationState initState2 = new ImmigrationState(currState2);
                        Cell c2 = new Cell(currState2, nextState2, initState2);
                        this.cellList.add(c2);
                        break;

                    case 3: // Schelling
                        int r = Rand.rand(0, State.nbState - 1);
                        SchellingState currState3 = new SchellingState(r);
                        SchellingState nextState3 = new SchellingState(currState3);
                        SchellingState initState3 = new SchellingState(currState3);
                        Cell c3 = new Cell(currState3, nextState3, initState3);

                        // link the mother cell for each state :
                        // (a state should be able to find back her mother cell
                        //  to put it in the vacant list)
                        currState3.motherCell = c3;
                        nextState3.motherCell = c3;
                        initState3.motherCell = c3;

                        this.cellList.add(c3);

                        if (r == 0) SchellingState.addVacantCell(c3);
                        break;

                    default:
                        throw new IllegalArgumentException("Game not available ...");
                }
            }
        }
    }

    /**
     * get the cell (i, j) of the grid
     * @param i index i of the grid
     * @param j index j of the grid
     * @return requested cell
     */
    public Cell getCell(int i, int j) {
        return this.cellList.get(i * width + j);
    }

    /**
     * return the list of cell in the Moore neighborhood
     * @param i index i of the grid
     * @param j index j of the grid
     * @return list of cell in the Moore neighborhood
     */
    private List<Cell> getNeighbors(int i, int j) {
        List<Cell> neighborsList = new ArrayList<Cell>();
        for(int a = -1; a <= 1; a++) {
            for(int b = -1; b <= 1; b++) {
                if(!(a == 0 && b == 0)) {
                    // game area is "circular", a cell on the left side
                    // has a neighbor on the right side
                    neighborsList.add(this.getCell((i + a + this.width) %  this.width, (j + b + this.height) % this.height));
                }
            }
        }
        return neighborsList;
    }

    /**
     * reset the grid with initial values everywhere
     */
    public void restart() {
        for(Cell c : cellList){
            c.initCell();
        }

        // TODO Faire une méthode autre ?
        if(this.gameChoice == 3) {
            SchellingState.vacantCells.clear();
            SchellingState.numberVacantCells = 0;

            for(Cell c : cellList){
                if(c.getCurrentState().getValue() == 0){
                    SchellingState.addVacantCell(c);
                }
            }
        }
    }

    /**
     * iteration one step forward
     */
    public void iterate() {
        // calculating next :
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.width; j++){
                this.getCell(i, j).calculate(this.getNeighbors(i, j));
            }
        }

        // updating :
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

