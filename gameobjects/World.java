package gameobjects;

import backend.GameApp;
import util.Calc;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

/** Handles the main world for the player, which it interacts with
 * 
 * @author ngellie
 *
 */
public class World {
	
	/* CONSTANTS */
	private static final int DEBUG_DISPLAY_XMARGIN = 20;
	private static final int DEBUG_DISPLAY_YMARGIN = 10;
	private static final int DEBUG_DISPLAY_YGAP = 20;
	
	/* VARIABLES */
	private int debugLine = DEBUG_DISPLAY_YMARGIN;
	private Player player;
	
	/* CONSTRUCTOR */
	public World() throws SlickException {
		player = new Player(GameApp.SCREEN_WIDTH/2, GameApp.SCREEN_HEIGHT/2);
	}
	/* METHODS */
	
	public void update(Input input, int delta) throws SlickException {
		if (input.isKeyPressed(Input.KEY_R)) {player = new Player(GameApp.SCREEN_WIDTH/2, GameApp.SCREEN_HEIGHT/2);}
		player.update(input, delta);
		
	}
	public void render(Graphics g) {
		debugDraw(String.format("player at |x : %4.1f|y : %4.1f|", player.getX(), player.getY()), g);
		debugDraw(String.format("hitbox top: %4.1f, left: %4.1f", player.getBox().getTop(), player.getBox().getLeft()), g);
		g.drawString("Player sprite angle: "+player.getSpriteAngle(), DEBUG_DISPLAY_XMARGIN, newLine());
		g.drawString("Player object angle: "+player.getObjAngle(), DEBUG_DISPLAY_XMARGIN, newLine());
		debugDraw(String.format("cos: %1.3f, sin: %1.3f", Calc.cosDeg(player.getObjAngle()), Calc.sinDeg(player.getObjAngle())), g);
		debugDraw(String.format("x and y speeds: %2.3f, %2.3f", player.getXSpeed(), player.getYSpeed()), g);
		debugDraw(String.format("asin: %2.3f, hyp: %2.3f", 
				Calc.aSinDeg(player.getObjAngle()), Calc.hypotenuse(debugLine, debugLine)), g);
		g.drawString("Player velocity angle: "+player.getVelocityAngle(), DEBUG_DISPLAY_XMARGIN, newLine());
		g.drawString("player velocity magnitude = "+player.getVelMag(), DEBUG_DISPLAY_XMARGIN, newLine());
		g.drawString("player acceleration = "+player.getAccelMag(), DEBUG_DISPLAY_XMARGIN, newLine());
		player.render();
		
		debugLine = DEBUG_DISPLAY_YMARGIN;
	}
	
	/* Helper Methods */
	private void debugDraw(String text, Graphics g) {
		g.drawString(text, DEBUG_DISPLAY_XMARGIN, newLine());
	}
	private int newLine() {
		debugLine += DEBUG_DISPLAY_YGAP;
		return debugLine;
	}
	
	/* Setters */
	
	/* Getters */
	
	
}
