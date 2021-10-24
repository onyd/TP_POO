package fr.ensimag.math;


public class FVector2D {
	public float x;
	public float y;
	
	public FVector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(FVector2D v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	public void sub(FVector2D v) {
		this.x -= v.x;
		this.y -= v.y;
	}
	
	public float dot(FVector2D v) {
		return x*v.x + y*v.y;
	}
	
	public float norm() {
		return (float) Math.sqrt(dot(this));
	}
	
	public void normalize() {
		float norm = norm();
		this.x /= norm;
		this.y /= norm;
	}
}
