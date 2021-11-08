package fr.ensimag.cellular_automata;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.ensimag.core.Entity;
import fr.ensimag.math.FPoint2D;

public class Case extends Entity {
	/** calcul **/
	private Cell cell;

	/** graphic **/
	private Color color;
	private int width, height;

	/** graphic **/
	public Case(FPoint2D position, int width, int height, Cell cell) {
		super(position);
		this.width = width;
		this.height = height;
		this.cell = cell;
	}

	/** graphic **/
	public void updateColor() {
		int currValue = this.cell.getCurrentState().getValue();
		int maxValue = State.nbState - 1;
		// by default : blue
		// TODO moyen d'opti ici pour ne pas recrér à chaque fois ...
		Color c = new Color((int) (255.0 * (1.0 - (float) currValue / maxValue)),
				(int) (255.0 * (1.0 - (float) currValue / maxValue)), 255);
		this.color = c;
	}

	<<<<<<<HEAD

	public void updateState() {
		currentState = nextState.copy();
	}

	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.drawRect(getX(), getY(), width, height);

	}

	=======

	/** graphic **/
	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.fillRect(getX(), getY(), width, height);
	}>>>>>>>implement_jeux
}
