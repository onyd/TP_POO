package fr.ensimag.math;

import java.util.Random;

/**
 * Provides useful mathematical functions
 *
 */
public class MathUtil {

	/**
	 * Convert an angle in degrees into an angle in radians
	 * 
	 * @param degree
	 * @return the converted angle
	 */
	public static float radian(float degree) {
		return (degree * (float) Math.PI / 180.0f);
	}

	/**
	 * Convert an angle in radians into an angle in degrees
	 * 
	 * @param radian
	 * @return the converted angle
	 */
	public static float degree(float radian) {
		return (radian * 180.0f / (float) Math.PI);
	}

	/**
	 * Check if a point is within the closed disk of D(center, rdius) - {center}
	 * 
	 * @param toCheck the point to test
	 * @param center
	 * @param radius
	 * @return true if toCheck is in the range
	 */
	public static boolean isInRange(FPoint2D toCheck, FPoint2D center, float radius) {
		float d = center.distance(toCheck);
		return (d != 0.0f && d <= radius);
	}

	/**
	 * Check if a point is in the field ie if the angle between the point and the looking direction is between beginAngle and endAngle
	 * @param toCheck
	 * @param lookDir
	 * @param center
	 * @param beginAngle
	 * @param endAngle
	 * @return true if toCheck is in the field
	 */
	public static boolean isInField(FPoint2D toCheck, FVector2D lookDir, FPoint2D center, float beginAngle,
			float endAngle) {
		FVector2D dir = toCheck.sub(center);
		dir.normalize();
		float angle = (float) Math.acos(lookDir.normalized().dot(dir));
		return (angle >= beginAngle && angle <= endAngle);
	}
	
	/**
	 * Check if a point is both in range and in field
	 * @param toCheck
	 * @param lookDir
	 * @param center
	 * @param radius
	 * @param beginAngle
	 * @param endAngle
	 * @return true if in the range and field
	 */
	public static boolean isInView(FPoint2D toCheck, FVector2D lookDir, FPoint2D center, float radius, float beginAngle,
			float endAngle) {
		return isInRange(toCheck, center, radius) && isInField(toCheck, lookDir, center, beginAngle, endAngle);
	}
	
	/**
	 * Compute a random integer between min and max
	 * @param min
	 * @param max
	 * @return the random number
	 */
	public static int rand(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
