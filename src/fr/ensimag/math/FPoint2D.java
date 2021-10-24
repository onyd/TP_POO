package fr.ensimag.math;

import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class FPoint2D extends Point2D.Float {
	
	public FPoint2D(float x, float y) {
		super(x, y);
	}
	
	public FVector2D sub(FPoint2D p) {
		return new FVector2D((float) (x-p.getX()), (float) (y-p.getY()));
	}
	
	public float distance(FPoint2D p) {
		return this.sub(p).norm();
	}
}
