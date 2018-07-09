package util;

public class Calc {
	
	
	public static float sinDeg(float x) {
		return (float)Math.sin(Math.toRadians(x));
	}
	public static float cosDeg(float x) {
		return (float)Math.cos(Math.toRadians(x));
	}
	public static float aSinDeg(float x) {
		return (float)Math.toDegrees(Math.asin(x));
	}
	public static float aCosDeg(float x) {
		return (float)Math.toDegrees(Math.acos(x));
	}
	public static float hypotenuse(float x, float y) {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	public static float hypotenuse(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	public static float thirdTriLength(float hyp, float y) {
		return (float) Math.sqrt(Math.pow(hyp, 2) - Math.pow(y, 2));
	}
	public static float cosLength(float hyp, float theta) {
		return (float) Math.cos(theta)*hyp;
	}
	public static float sinLength(float hyp, float theta) {
		return (float) Math.sin(theta)*hyp;
	}
}
