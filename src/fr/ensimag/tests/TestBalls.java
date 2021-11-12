package fr.ensimag.tests;

import fr.ensimag.balls.Balls;

// Question 1
public class TestBalls {

    public static void main(String[] args) {

        Balls balls = new Balls();
        System.out.print(balls.toString());

        balls.translate(10,10);
        System.out.print(balls.toString());

        balls.reInit();
        System.out.print(balls.toString());
    }

}

