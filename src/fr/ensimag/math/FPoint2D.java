package fr.ensimag.math;

import java.awt.geom.Point2D;

/**
 * Represents a point with float coordinates in 2D
 *
 */
@SuppressWarnings("serial")
public class FPoint2D extends Point2D.Float {
	
	/**
	 * Create a point with coordinates x and y
	 * @param x coordinate
	 * @param y coordinate
	 */
	public FPoint2D(float x, float y) {
		super(x, y);
	}
	
	/**
	 * The copy constructor
	 * @param p 
	 */
	public FPoint2D(FPoint2D p) {
		this(p.x, p.y);
	}
	
	/**
	 * Translate the point
	 * @param v vector of the translation
	 */
	public void translate(FVector2D v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	/**
	 * @param p
	 * @return the vector starting at p and ending to this
	 */
	public FVector2D sub(FPoint2D p) {
		return new FVector2D((float) (x-p.getX()), (float) (y-p.getY()));
	}
	
	/**
	 * 
	 * @param p the point to which we compute the distance
	 * @return the euclidean distance between this and p
	 */
	public float distance(FPoint2D p) {
		return this.sub(p).norm();
	}
}
