package engine.objects;

import com.badlogic.gdx.math.Vector2;

public abstract class MovableObject extends GameObject {
	public double gravity; 
	public Vector2 velocity; 
	boolean facingRight;
	
	public void update(float delta) {
		
	}
}
