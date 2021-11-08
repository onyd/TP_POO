package fr.ensimag.cellular_automata;

/**
 * Class that implement a single method used by Immigration and Conway
 */
public abstract class ModuloState extends State{

    public ModuloState(int i) {
        super(i);
    }

    public ModuloState(State s){
        super(s);
    }

    /**
     * increase the 'value' attribute by 1
     */
    public void increaseValue(){
        this.setState((this.value + 1) % State.nbState);
    }
}
