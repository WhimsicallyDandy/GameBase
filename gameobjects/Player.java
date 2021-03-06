package gameobjects;

import backend.GameApp;
import util.Calc;

import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * The Player, the ship the user controls. can rotate, accelerate, shoot, and maybe more
 * @author ngellie
 *
 */
public class Player extends GameObject {
	private final static float UP = 270;
	private final static float DOWN = 90;
	private final static float RIGHT = 0;
	private final static float LEFT = 180;
	
	private final static float INIT_ROTATE_SPEED = 1/10f;
	private final static float INIT_ROTATION = 0f;
	private final static float INIT_THRUST = 1/10f;
	private final static float INIT_ACCEL = 1/200f;
	private final static String SPRITE_PLAYER = "res/spaceship.png";
	
	
	/* CONSTRUCTOR */
	public Player(float x, float y) throws SlickException {
		super(GameApp.SCREEN_WIDTH/2, GameApp.SCREEN_HEIGHT/2, INIT_ROTATION, INIT_ROTATE_SPEED, SPRITE_PLAYER);
	}
	
	/** update */
	public void update (Input input, int delta) {
		super.update(input, delta);
		setSpriteAngle(getObjAngle()+90);
	}
	public void move(Input input, int delta) {
		//addX(Calc.cosDeg(INIT_THRUST*delta));
		//addVelocity(delta*INIT_ACCEL, getObjAngle());
		
		//circle(input, delta);
	
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			//addX(delta*INIT_THRUST);
			//addVelocity(delta*INIT_ACCEL, RIGHT);
			turnRight(INIT_ROTATE_SPEED);
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			//addX(-delta*INIT_THRUST);
			//addVelocity(delta*INIT_ACCEL, LEFT);
			turnLeft(INIT_ROTATE_SPEED);
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			//addY(-delta*INIT_THRUST);
			setVelocity(delta*INIT_THRUST, getObjAngle());
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			//addY(delta*INIT_THRUST);
			addVelocity(delta*INIT_ACCEL, DOWN);
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			setVelocity(0, 0);
		}
		if (input.isKeyDown(Input.KEY_Z)) {
			addVelAngle(delta*INIT_ROTATE_SPEED);
		}
		debugMovement(input);
		
		//accelerate();
		
		addX(getXSpeed());
		addY(getYSpeed());
		
	}
	
	
	/* Helper Methods */
	private void turnRight(float rotation) {
		addSpriteAngle(rotation);
		addObjAngle(rotation);
	}
	private void turnLeft(float rotation) {
		addSpriteAngle(-rotation);
		addObjAngle(-rotation);
	}
	
	// Debugging
	private void circle(Input input, int delta) {
		
		addY(Calc.sinDeg(getObjAngle())*INIT_THRUST*delta);
		addX(Calc.cosDeg(getObjAngle())*INIT_THRUST*delta);
		addObjAngle(INIT_ROTATE_SPEED*delta);
	}
	
	private void debugMovement(Input input) {
		if (input.isKeyPressed(Input.KEY_A)) {
			setObjAngle(LEFT);
		}
		if (input.isKeyPressed(Input.KEY_D)) {
			setObjAngle(RIGHT);
		}
		if (input.isKeyPressed(Input.KEY_W)) {
			setObjAngle(UP);
		}
		if (input.isKeyPressed(Input.KEY_S)) {
			setObjAngle(DOWN);
		}
	}
	
	
}
