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
	 * This would cause this entity to stop. We will probably have to calculate some way 
	 * to make this entity move backwards the way it came away from what it crashed into, 
	 * so that we can't walk into the other entity indefinetly.
	 * This method uses the general method stop from the normal event class. Implement that.
	 */
	public void stop(){
		//TODO: implement
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
