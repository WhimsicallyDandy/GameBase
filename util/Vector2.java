package util;

/** two dimensional vector, coordinate system, not polar
 * thanks to https://www.gamedev.net/articles/programming/math-and-physics/problem-solving-using-vectors-r3123/
 * for the info
 * */
public class Vector2 {
	
	private float x;
	private float y;
	
	/* CONSTRUCTOR */
	public Vector2() {x=0; y=0;}
	public Vector2(float x, float y) {setVec(x, y);}
	public Vector2(Vector2 v) {setVec(v.x, v.y);}
	
	/* METHODS */
	// changes to a unit vector
	public void normalise() {
		float magnitude = getMag();
		if (magnitude<=0) {
			x=0;
			y=0;
		}
		x/=magnitude;
		y/=magnitude;
	}
	public static Vector2 addVectors(Vector2 v, Vector2 u) {
		return new Vector2(v.x+u.x, v.y+u.y);
	}
	
	
	/* Helper Methods */
	// creates a new unit vector
	public static Vector2 unitVector(float theta) {
		return new Vector2(Calc.cosDeg(theta), Calc.sinDeg(theta));
	}
	public static Vector2 unitVector(Vector2 v) {
		float magnitude = v.getMag();
		if (magnitude<=0) {
			return new Vector2();
		}
		return new Vector2(v.x/magnitude, v.y/magnitude);
	}
	
	public static Vector2 rotateVector(Vector2 v, Vector2 u) {
		float xi = v.x*u.x - v.y*u.y;
		float yi = v.x*u.y + u.x*v.y;
		return new Vector2(xi, yi);
	}
	public void rotateVector(Vector2 v) {
		x= v.x*x - v.y*y;
		y= v.x*y + x*v.y;
	}
	public void rotateAngle(float theta) {
		rotateVector(unitVector(theta));
	}
	
	// Getters
	public float getXVec() {
		return x;
	}
	public float getYVec() {
		return y;
	}
	public float getAngle() {
		return (float) Math.toDegrees(Math.atan2(x, y));
	}
	public float getMag() {
		return Calc.hypotenuse(x, y);
	}
	
	// Setters
	public void addVector(Vector2 v) {
		x+=v.x;
		y+=v.y;
	}
	public void addVector(float x, float y) {
		this.x+=x;
		this.y+=y;
	}
	
	public void setAngle(float theta) {
		
	}
	public void setMag(float m) {
		float magnitude = getMag();
		float angle = getAngle();
		x = Calc.cosDeg(angle)*magnitude;
		y = Calc.sinDeg(angle)*magnitude;
	}
	public void addMag(float m) {
		float magnitude = getMag();
		float angle = getAngle();
		x += Calc.cosDeg(angle)*magnitude;
		y += Calc.sinDeg(angle)*magnitude;
	}
	public void setXVec(float x) {this.x = x;}
	public void setYVec(float y) {this.y = y;}
	public void setVec(float x, float y) {setXVec(x); setYVec(y);}
	
	
	
	
}
