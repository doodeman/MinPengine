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
		
		Vector2 thisCenter = this.getCenter(); 
		Vector2 thatCenter = that.getCenter();
		
		float deltaX = thisCenter.x - thatCenter.x; 
		float deltaY = thisCenter.y - thatCenter.y;
		Side retval; 
		if (Math.abs(deltaX) > Math.abs(deltaY)) {
			if (deltaX > 0) 
				retval = Side.LEFT; 
			else 
				retval = Side.RIGHT; 
		}
		else {
			if (deltaY > 0) 
				retval = Side.BOTTOM;
			else 
				retval = Side.TOP;
		}
		System.out.println(retval);
		return retval;
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
