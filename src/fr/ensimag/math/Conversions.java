package fr.ensimag.math;

public class Conversions {
	public static float radian(float degree) {
		return (degree * (float) Math.PI / 180.0f);
	}
	
	public static float degree(float radian) {
		return (radian * 180.0f / (float) Math.PI);
	}
}
