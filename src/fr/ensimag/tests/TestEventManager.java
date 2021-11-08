package fr.ensimag.tests;

import fr.ensimag.events.EventManager;

public class TestEventManager {
	public static void main(String[] args) throws InterruptedException {
		// On cr�e un simulateur
		EventManager manager = new EventManager();

		// On poste un �v� nement [ PING ] tous les deux pas de temps
		for (int i = 2; i <= 10; i += 2) {
			manager.addEvent(new MessageEvent(i, " [ PING ]"));
		}
		// On poste un �v� nement [ PONG ] tous les trois pas de temps
		for (int i = 3; i <= 9; i += 3) {
			manager.addEvent(new MessageEvent(i, " [ PONG ]"));
		}

		while (!manager.isFinished()) {
			manager.next();
			Thread.sleep(1000);
		}
	}
}