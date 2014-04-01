package engine.objects;

import com.badlogic.gdx.math.Vector2;

public abstract class MovableObject extends GameObject {
	public double gravity;
	public Vector2 velocity; 
	boolean facingRight;
	protected String filename;
	
	public MovableObject() {
		super(); 
		velocity = new Vector2(0,0); 
		gravity = 0; 
		facingRight = true; 
	}
	public MovableObject(String filename){
		this.filename = filename;
		//TODO: implement file parsing.
		//event system thingy should be added here.
	}
	public void update(float delta) {
		
	}
}
