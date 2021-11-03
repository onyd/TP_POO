package fr.ensimag.tests;
import fr.ensimag.core.BoidsArea;
import fr.ensimag.core.GridArea;
import gui.GUISimulator;

import java.awt.*;
import fr.ensimag.cellular_automata.*;

public class GameTest {
    public static void main(String[] args) {
        // gameChoice :
        // 1 jeu de la vie
        // 2 jeu de l'immigration
        // 3 modèle de Schelling
        GridArea mainGridArea = new GridArea(500, 500, 10, 3);
        GUISimulator gui = new GUISimulator(600, 400, Color.WHITE, mainGridArea);

        mainGridArea.addGraphicalElementsTo(gui);
    }
}