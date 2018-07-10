package gameobjects;

import backend.GameApp;
import util.*;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;


/** 
 * The standard abstract object that represents everything that appears in the game
 * perhaps not UI tho maybe that's different (nah probs same)
 * @author ngellie
 *
 */
public class GameObject {
	
	/* Constants */
	public static final String S_DEFAULT = "res/shield.png";

	
	/* Variables for movement, data and such */
	private Position position;
	private Image sprite;
	private Hitbox hitbox;
	private Vector2 velocity;
	private Vector2 accel;
	
	private float objectAngle;
	
	private float rotationSpeed;

	private boolean canRender = true;
	private boolean canMove = true;
	private boolean canRotate = true;
	
	/* CONSTRUCTORS */
	public GameObject() throws SlickException {
		
	}
	public GameObject(float x, float y, float rotationAngle, float rotationSpeed, String imgsrc) throws SlickException {
		position = new Position(x, y);
		this.rotationSpeed = rotationSpeed;
		sprite = new Image(imgsrc);
		hitbox = new Hitbox(position, sprite);
		objectAngle = rotationAngle;
		sprite.setRotation(rotationAngle+90);
		
		velocity = new Vector2();
		accel = new Vector2();
		
		
	}
	
	/* METHODS */
	
	/**
	 * If it can move, it moves, updates hitbox position
	 * @param input the Player's keystrokes
	 * @param delta milliseconds since last frame
	 */
	public void update(Input input, int delta) {
		if (canMove) {move(input, delta);}
		hitbox.setPos(position);
		// keeps angle within 360 at all times
		sprite.setRotation(clamp360(sprite.getRotation()));
		objectAngle = clamp360(objectAngle);
		
	}
	
	/**
	 * renders the GameObject's sprite on the screen
	 */
	public void render() {
		if (canRender) {sprite.draw(hitbox.getLeft(), hitbox.getTop());}
	}
	
	/* Helper Methods */
	
	// moves the object. currently blank, for abstraction reasons
	public void move(Input input, int delta) {}
	
	// clamps within 360
	public float clamp360(float a) {
		while (a<0) {a+=360;}
		return a%360;
	}

	
	// rotates the sprite


	/* Setters */
	public void setX(float x) {position.setX(x);}
	public void setY(float y) {position.setY(y);}
	
	public void setPos(float x, float y) {position.setPos(x, y);}
	public void setPos(Position p) {position.setPos(p);}
	
	public void addX(float x) {position.addX(x);}
	public void addY(float y) {position.addY(y);}
	
	public void setSpriteAngle(float angle) {sprite.setRotation(angle);}
	public void addSpriteAngle(float angle) {sprite.setRotation(sprite.getRotation()+angle);}
	
	public void setObjAngle(float angle) {objectAngle=angle;}
	public void addObjAngle(float angle) {objectAngle+=angle;}
	
	public void addVelAngle(float angle) {velocity.rotateAngle(angle);}
	
	public void setVelMag(float m) {velocity.setMag(m);}
	public void addVelMag(float m) {velocity.addMag(m);}
	
	public void setVelocity(float m, float a) {velocity.setVec(m, a);}
	public void setVelocity(Vector2 v) {velocity = new Vector2(v);}
	
	public void accelerate() {velocity.addVector(accel);}
	public void accelerate(Vector2 v) {velocity.addVector(v);}
	public void addVelocity(float m, float a) {velocity.addVector(m, a);}
	public void setAcceleration(float m, float a) {accel.setVec(m, a);}
	public void setAcceleration(Vector2 v) {accel = new Vector2(v);}
	public void addAcceleration(float m, float a) {accel.addVector(m, a);}
	public void addAcceleration(Vector2 v) {accel.addVector(v);}
	
	public void setXSpeed(float x) {velocity.setXVec(x);}
	public void setYSpeed(float y) {velocity.setYVec(y);}
	
	//public void setSpriteRotationSpeed(float rotationSpeed) {this.spriteRotationSpeed = rotationSpeed;}
	
	/* Getters */
	public float getX() {return position.getX();}
	public float getY() {return position.getY();}
	public Position getPos() {return position;}
	public Hitbox getBox() {return hitbox;}
	
	public float getXSpeed() {return velocity.getXVec();}
	public float getYSpeed() {return velocity.getYVec();}
	public float getVelMag() {return velocity.getMag();}
	public Vector2 getVelocity() {return new Vector2(velocity);}
	
	public float getXAccel() {return accel.getXVec();}
	public float getYAccel() {return accel.getYVec();}
	public float getAccelMag() {return accel.getMag();}
	public Vector2 getAcceleration() {return accel;}
	
	public float getSpriteAngle() {return sprite.getRotation();}
	public float getObjAngle() {return objectAngle;}
	public float getVelocityAngle() {return velocity.getAngle();}
	
	
	// (float)Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2))
	/*
	public float getXspeed() {
		return (float)Math.cos((velAngle-DEGREE_OFFSET)*Math.PI/180)*velocity;
	}
	public float getVerticalVelocity() {
		return (float)Math.sin((velAngle-DEGREE_OFFSET)*Math.PI/180)*velocity;
	}
	*/
}
