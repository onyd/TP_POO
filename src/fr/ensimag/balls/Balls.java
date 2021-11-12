package fr.ensimag.balls;

import java.awt.Color;
import java.awt.Graphics2D;
import gui.*;

/**
 * Contains a list of balls, represented by Ovals, which can been
 * translated and put back at initial coordinates.
 *
 * @author Team 52
 */
public class Balls implements GraphicalElement {
    /** balls is an array of Ovals, representing the balls */
    private Oval[] balls = new Oval[4];

    /**
     * Puts the balls (of Oval type) at initial coordinates
     */
    public Balls() {
        this.reInit();
    }

    /**
     * @return the array of balls
     */
    public Oval[] getBalls() {
        return balls;
    }

    /**
     * @param i, index of the ball
     * @return the ball of index i
     */
    public Oval get(int i) {
        return balls[i];
    }

    /**
     *  Translates the balls with (dx, dy) displacement
     * @param dx, movement w.r.t x coordinates
     * @param dy, movement w.r.t y coordinates
     */
    public void translate(int dx, int dy) {
        for (Oval p : this.balls) {
            p.translate(dx, dy);
        }
    }

    /**
     * Puts the balls at initial coordinates (arbitrarily chosen)
     */
    public void reInit() {
        balls[0] = new Oval(50, 150, Color.RED, Color.RED, 10);
        balls[1] = new Oval(150, 50, Color.RED, Color.RED, 10);
        balls[2] = new Oval(50, 50, Color.RED, Color.RED, 10);
        balls[3] = new Oval(150, 150, Color.RED, Color.RED, 10);
    }

    @Override
    public String toString() {
        String str = "";
        for (Oval b : this.balls) {
            str += "x = " + b.getX() + " | y = " + b.getY() + "\n";
        }
        return(str + "\n");
    }

    @Override
    public void paint(Graphics2D g2d) {
        for (Oval b : this.balls) {
            b.paint(g2d);
        }
    }

}
