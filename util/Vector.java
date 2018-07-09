package util;

/** holds 2d vector information */
public class Vector {
	private final static float UP = 270;
	private final static float DOWN = 90;
	private final static float RIGHT = 0;
	private final static float LEFT = 180;
	
	private float magnitude;
	private float angle;
	
	/* CONSTRUCTORS */
	public Vector() {
		magnitude = 0;
		angle = 0;
	}
	public Vector(Vector v) {
		magnitude = v.getMagnitude();
		angle = v.getAngle();
	}
	public Vector(float magnitude, float angle) {
		this.magnitude = magnitude;
		this.angle = angle;
	}
	
	/* Helper Methods */
	
	// add vector to this one
	public void addVector(Vector v) {
		float xTemp = v.getXVec() + getXVec();
		float yTemp = v.getYVec() + getYVec();
		setVec(xTemp, yTemp);
	}
	public void addVector(float m, float a) {
		// find the x and y coordinates of the addition
		float xTemp = Calc.cosDeg(a)*m + getXVec();
		float yTemp = Calc.sinDeg(a)*m + getYVec();
		// calculate new magnitude and angle
		setVec(xTemp, yTemp);
	}
	// maybe a fancy one with any number of vectors
	
	// returns a new vector from addition
	public Vector projectVector(Vector v) {
		Vector babby = new Vector();
		babby.addVector(this);
		babby.addVector(v);
		return babby;
	}
	
	// fixes up magnitude and angles for adding calculation
	private void setVec(float x, float y) {
		// trig stuff yay
		magnitude = Calc.hypotenuse(x, y);
		// corrects angle to 1st quarter if positive, 2nd if negative (- adds on)
		angle = 90-Calc.aSinDeg(y/magnitude);
		// fixes up angle depending on x
		if (Math.signum(x)<0) {angle = 180+(90-angle);}
	}
	
	// keeps angle within 0-360
	private float clamp360(float a) {
		while (a<0) {a+=360;}
		return a%360;
	}
	
	// Getters
	public float getMagnitude() {return magnitude;}
	public float getAngle() {return angle;}
	
	public float getXVec() {return Calc.cosDeg(angle)*magnitude;}
	public float getYVec() {return Calc.sinDeg(angle)*magnitude;}
	
	// Setters
	public void setMagnitude(float m) {magnitude = m;}
	public void addMagnitude(float m) {magnitude += m;}
	
	public void setAngle(float a) {angle = clamp360(a);}
	public void addAngle(float a) {
		angle += a;
		angle = clamp360(angle);
	}
	
	public void setVector(Vector v) {
		setMagnitude(v.getMagnitude());
		setAngle(v.getAngle());
	}
	public void setVector(float m, float a) {
		setMagnitude(m);
		setAngle(a);
	}
	
	public void setXVec(float x) {
		// creates a new vector of just the x component, and subtracts it from the vector
		// if the angle is on the left side, it becomes ultimate left
		// adjusted a bit becase Slick's 0 is dead right
		addVector(new Vector(getXVec(), (Math.signum(Calc.sinDeg(angle))>0)?RIGHT:LEFT));
		// adds the fresh x component to the vector
		addVector(new Vector(x, (Math.signum(x)>=0)?RIGHT:LEFT));
	}
	public void setYVec(float y) {
		// same as above
		addVector(new Vector(getYVec(), (Math.signum(Calc.cosDeg(angle))>0)?DOWN:UP));
		addVector(new Vector(y, (Math.signum(y)>=0)?DOWN:UP));
	}	
}
