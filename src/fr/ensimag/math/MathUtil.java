package fr.ensimag.math;

import java.util.Random;

public class MathUtil {
	public static float radian(float degree) {
		return (degree * (float) Math.PI / 180.0f);
	}

	public static float degree(float radian) {
		return (radian * 180.0f / (float) Math.PI);
	}

	public static boolean isInRange(FPoint2D toCheck, FPoint2D center, float radius) {
		float d = center.distance(toCheck);
		return (d != 0.0f && d <= radius);
	}

	public static boolean isInField(FPoint2D toCheck, FVector2D lookDir, FPoint2D center, float beginAngle, float endAngle) {
		FVector2D dir = toCheck.sub(center);
		dir.normalize();
		float angle = (float) Math.acos(lookDir.normalized().dot(dir));
		return (angle >= beginAngle && angle <= endAngle);
	}

	public static boolean isInView(FPoint2D toCheck, FVector2D lookDir, FPoint2D center, float radius, float beginAngle,
			float endAngle) {
		return isInRange(toCheck, center, radius) && isInField(toCheck, lookDir, center, beginAngle, endAngle);
	}
	
	public static int rand(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
