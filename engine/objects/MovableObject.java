package engine.objects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

public abstract class MovableObject extends GameObject {
	public double gravity;
	public Vector2 velocity; 
	boolean facingRight;
	public double moveSpeed;
	protected String filename;
	protected HashMap<String, InputEvent> inputEvents;
	
	public MovableObject(GsonMap input, Vector2 location, String gameName, GameMap map) {
		super(input, location, gameName, map); 
		velocity = new Vector2(0,0); 
		gravity = input.gravity; 
		moveSpeed = input.moveSpeed;
		facingRight = input.facingRight; 
		inputEvents = new HashMap<String, InputEvent>();
		if(input.controls != null){
			for (Map.Entry<String, String> entry : input.controls.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    inputEvents.put(key, new InputEvent(this, value));
			}
		}
	}
	public void update(float delta) {
		this.pos.add(this.velocity.cpy().scl(delta));
		this.sprite.setPosition(this.pos.x * this.ppU, this.pos.y * this.ppU);
	}
	
	
	/**
	 * Returns Side.NONE if not collided. Otherwise returns which of this' sides collided. 
	 * @param that
	 * @return
	 */
	public Side hasCollided(GameObject that, float delta) {
		//Rectangle 1's bottom edge is higher than Rectangle 2's top edge.
		Vector2 nextpos = this.pos.cpy().add(this.velocity.cpy().scl(delta));
		if ((nextpos.y) >= (that.pos.y + that.size.y)) {
			return Side.NONE;
		}
		//Rectangle 1's top edge is lower than Rectangle 2's bottom edge.
		if ((nextpos.y + this.size.y)<= that.pos.y) {
			return Side.NONE; 
		}
		//Rectangle 1's left edge is to the right of Rectangle 2's right edge.
		if ((nextpos.x) >= (that.pos.x + that.size.x) ) {
			return Side.NONE; 
		}
		//Rectangle 1's right edge is to the left of Rectangle 2's left edge.
		if ((nextpos.x + this.size.x) <= that.pos.x ) {
			return Side.NONE; 
		}
		
		double topDist = -(nextpos.y - that.topBorder());
		double botDist = -(nextpos.y + this.size.y - that.botBorder());
		double leftDist = -(nextpos.x - that.leftBorder());
		double rightDist = -(nextpos.x + this.size.x - that.rightBorder());
		
		if ((topDist > botDist) && (topDist > leftDist) && (topDist > rightDist)) 
			return Side.TOP; 
		if ((botDist > topDist) && (botDist > leftDist) && (botDist > rightDist))
			return Side.BOTTOM; 
		if ((leftDist > topDist) && (leftDist > botDist) && (leftDist > rightDist)) 
			return Side.LEFT; 
		if ((rightDist > topDist) && (rightDist > botDist) && (rightDist > leftDist)) 
			return Side.RIGHT; 
		//this shouldn't ever happen
		return Side.TOP; 
	}
	

	/**
	 * This would cause this entity to stop. We will probably have to calculate some way 
	 * to make this entity move backwards the way it came away from what it crashed into, 
	 * so that we can't walk into the other entity indefinetly.
	 * This method uses the general method stop from the normal event class. Implement that.
	 */
	public void stop(){
		//TODO: implement
		this.velocity.set(0, 0);
	}
	/**
	 * Reverses the direction of this object.
	 */
	public void reverseDirection(){
		this.facingRight = !this.facingRight;
	}
	
	/**
	 * Makes this object jump. Heel!
	 */
	public void jump(){
		//TODO: implement
	}
}
