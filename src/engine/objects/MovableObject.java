package engine.objects;

import com.badlogic.gdx.math.Vector2;

public abstract class MovableObject extends GameObject {
	public int gravity; 
	public Vector2 velocity; 
	boolean facingRight; 
}
