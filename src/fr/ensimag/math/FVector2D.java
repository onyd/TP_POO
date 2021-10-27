package fr.ensimag.math;


public class FVector2D {
	public float x;
	public float y;
	
	public FVector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public FVector2D(FVector2D v) {
		this(v.x, v.y);
	}
	
	public void add(FVector2D v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	public void add(FPoint2D p) {
		this.x += p.x;
		this.y += p.y;
	}
	
	public void sub(FVector2D v) {
		this.x -= v.x;
		this.y -= v.y;
	}
	
	public void sub(FPoint2D p) {
		this.x -= p.x;
		this.y -= p.y;
	}
	
	public void mult(float a) {
		this.x *= a;
		this.y *= a;
	}
	
	public void div(float a) {
		this.x /= a;
		this.y /= a;
	}
	
	public float dot(FVector2D v) {
		return x*v.x + y*v.y;
	}
	
	public float norm() {
		return (float) Math.sqrt(dot(this));
	}
	
	public void normalize() {
		this.div(norm());
	}
	
	public void clip(float norm) {
		if (this.norm() > norm) {
			this.normalize();
			this.mult(norm);
		}
	}
	
	public float  getAngle() {
		return (float) Math.atan2(y, x);
	}
}
