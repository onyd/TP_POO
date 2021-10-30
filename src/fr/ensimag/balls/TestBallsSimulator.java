package fr.ensimag.balls;
import java.awt.Color ;
import gui.GUISimulator;

public class TestBallsSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUISimulator gui = new GUISimulator (500 , 500 , Color.BLACK ) ;
		gui.setSimulable (new BallsSimulator (10, 10, 5, 5)) ;
	}

}
