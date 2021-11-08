package fr.ensimag.cellular_automata;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.ensimag.core.Entity;
import fr.ensimag.math.FPoint2D;

/**
 * Class that managed the graphical part of a cell
 * (linked calculation and graphics)
 */
public class Case extends Entity {
	// calculation
	/**
	 * linked cell
	 */
	private Cell cell;

	// graphic
	private Color color;
	private int width, height;

	/**
	 *
	 * @param position position of the case
	 * @param width width
	 * @param height height
	 * @param cell the cell that is linked with the case
	 */
	public Case(FPoint2D position, int width, int height, Cell cell) {
		super(position);
		this.width = width;
		this.height = height;
		this.cell = cell;
	}

	/**
	 * update the 'color' of the case
	 */
	public void updateColor(){
		int currValue = this.cell.getCurrentState().getValue();
		int maxValue = State.nbState - 1;
		// TODO moyen d'opti ici pour ne pas recrér à chaque fois ...

		// create a range of color from white to blue for each state value
		// (default color is blue)
		Color c = new Color((int) (255.0 * (1.0 - (float) currValue / maxValue)), (int) (255.0 * (1.0 - (float) currValue / maxValue)),  255);
		this.color = c;
	}

	/**
	 * set color and fill the case
	 * @param g2d
	 */
	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.fillRect(getX(), getY(), width, height);
	}
}
