package fr.ensimag.math;

import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class FPoint2D extends Point2D.Float {
	
	public FPoint2D(float x, float y) {
		super(x, y);
	}

	public float distance(FPoint2D p) {
		return (float) (Math.pow(getX()-p.getX(), 2)+Math.pow(getY()-p.getY(), 2));
	}
}
