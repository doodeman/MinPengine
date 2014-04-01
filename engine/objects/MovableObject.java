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
	
	public MovableObject(GsonMap input, Vector2 location) {
		super(input, location); 
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
	@Override
	public void onCollide(GameObject that) {
		// TODO Auto-generated method stub
		
	}
}
