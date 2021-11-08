package fr.ensimag.cellular_automata;

public abstract class ModuloState extends State{

    public static int nbState;

    public ModuloState(int i) {
        super(i);
    }

    public ModuloState(State s){
        super(s);
    }

    public void increaseValue(){
        this.value = (this.value + 1) % State.nbState;
    }
}
