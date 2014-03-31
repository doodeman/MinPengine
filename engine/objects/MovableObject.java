package engine.objects;

import com.badlogic.gdx.math.Vector2;

public abstract class MovableObject extends GameObject {
	public double gravity; 
	public Vector2 velocity; 
	boolean facingRight;
	
	public MovableObject() {
		super(); 
		velocity = new Vector2(0,0); 
		gravity = 0; 
		facingRight = true; 
	}
	public void update(float delta) {
		
	}
}
