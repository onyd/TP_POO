package fr.ensimag.balls;

import java.awt.Point;
import gui.*;

/**
 * BallsSimulator handles the movement and the reset of balls position.
 *
 * @author Team 52
 *
 */
public class BallsSimulator implements Simulable {
    /** Balls of the simulation */
    public Balls balls;
    /** Represents the vector displacement of each ball. */
    private Point[] velocity = new Point[4];
    /** x, y, width and height form the rectangle in which balls can move */
    private int x, y, width, height;

    public BallsSimulator(int x, int y, int width, int height) {
        balls = new Balls();

        velocity[0] = new Point(-5, 5);
        velocity[1] = new Point(5, -5);
        velocity[2] = new Point(-5, -5);
        velocity[3] = new Point(5, 5);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     *  Move each ball of index i in velocity[i] direction and keep it in the rectangle formed
     *  by x, y, width and height parameters.
     */
    @Override
    public void next() {
        for (int i = 0; i < balls.getBalls().length; i++) {
            balls.get(i).translate((int)velocity[i].getX(), (int)velocity[i].getY());

            // Assures each ball rests in the area formed by x, y, width, length
            if (balls.get(i).getX() < this.x | balls.get(i).getX() > this.width) {
                velocity[i].setLocation(velocity[i].getX() * -1, velocity[i].getY());
            }
            if (balls.get(i).getY() < this.y | balls.get(i).getY() > this.height) {
                velocity[i].setLocation(velocity[i].getX(), velocity[i].getY() * -1);
            }
        }
    }

    /**
     *  Just puts back the balls at initial coordinates
     */
    @Override
    public void restart() {
        balls.reInit();
    }

}
