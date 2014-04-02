package engine.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

public abstract class MovableObject extends GameObject {
	public double gravity;
	public Vector2 velocity; 
	boolean facingRight;
	public double moveSpeed;
	protected String filename;
	protected HashMap<String, InputEvent> inputEvents;
	List<Side> collisionSides;
	
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
		collisionSides = new ArrayList<Side>();
	}
	
	public void update(float delta) {
		this.pos.add(this.velocity.cpy().scl(delta));
		this.sprite.setPosition(this.pos.x * this.ppU, this.pos.y * this.ppU);
		this.collisionSides.clear();
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
			if (deltaX > 0)  {
				retval = Side.LEFT; 
				this.collisionSides.add(Side.LEFT);
			}
			else {
				retval = Side.RIGHT; 
				this.collisionSides.add(Side.RIGHT);
			}
		}
		else {
			if (deltaY > 0) {
				retval = Side.BOTTOM;
				this.collisionSides.add(Side.BOTTOM);
			}
			else {
				retval = Side.TOP;
				this.collisionSides.add(Side.TOP);
			}
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
		if (this.collisionSides.contains(Side.RIGHT) || this.collisionSides.contains(Side.LEFT))
			this.velocity.x = 0;
		if (this.collisionSides.contains(Side.TOP) || this.collisionSides.contains(Side.BOTTOM))
			this.velocity.y = 0;
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
