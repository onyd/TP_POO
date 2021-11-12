package fr.ensimag.math;

/**
 * Represents a vector with float coordinates in 2D
 *
 */
public class FVector2D {
	public float x;
	public float y;
	
	/**
	 * Create a vector with coordinate x and y
	 * @param x
	 * @param y
	 */
	public FVector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * The copy contructor
	 * @param v
	 */
	public FVector2D(FVector2D v) {
		this(v.x, v.y);
	}
	
	/**
	 * Performs the sum of this and v
	 * @param v the vector to add
	 */
	public void add(FVector2D v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	/**
	 * Performs the sum of this and a point
	 * @param p the point to add
	 */
	public void add(FPoint2D p) {
		this.x += p.x;
		this.y += p.y;
	}
	
	/**
	 * Performs the substraction of this and a vector
	 * @param v the vector to substract
	 */
	public void sub(FVector2D v) {
		this.x -= v.x;
		this.y -= v.y;
	}
	
	/**
	 * Performs the substraction of this and a point
	 * @param v the point to substract
	 */
	public void sub(FPoint2D p) {
		this.x -= p.x;
		this.y -= p.y;
	}
	
	/**
	 * Performs the multiplication by a scalar
	 * @param a the multiplication scalar
	 */
	public void mult(float a) {
		this.x *= a;
		this.y *= a;
	}
	
	/**
	 * Performs the division by a scalar
	 * @param a the scalar denominator
	 */
	public void div(float a) {
		this.x /= a;
		this.y /= a;
	}
	
	/**
	 * Computes the scalar product
	 * @param v other vector
	 * @return the scalar product of this and v
	 */
	public float dot(FVector2D v) {
		return x*v.x + y*v.y;
	}
	
	/**
	 * Compute the euclidean norm of this
	 * @return the norm of this
	 */
	public float norm() {
		return (float) Math.sqrt(dot(this));
	}
	
	/**
	 * Normalize the vector
	 */
	public void normalize() {
		this.div(norm());
	}
	
	/**
	 * Copy and normalize the copy
	 * @return a normalized copy of this
	 */
	public FVector2D normalized() {
		FVector2D result = new FVector2D(this);
		result.normalize();
		return new FVector2D(result);
	}
	
	/**
	 * Clip the vector to a maximum norm
	 * @param norm the maximum norm
	 */
	public void clip(float norm) {
		if (this.norm() > norm) {
			this.normalize();
			this.mult(norm);
		}
	}
	
	/**
	 * Compute the angle of the vector w.r.t the horizontal axis
	 * @return the angle
	 */
	public float  getAngle() {
		return (float) Math.atan2(y, x);
	}
}
