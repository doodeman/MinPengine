package engine.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * A superclass for all objects in the game
 * @author kristleifur
 *
 */
public abstract class GameObject {
	public boolean collidable; 
	Vector2 pos;
	Sprite sprite; 
	
	public abstract void update(); 
	public abstract void render(); 
	public abstract void onCollide(GameObject that);  
	
	public boolean hasCollided(GameObject that) {
		return false; 
	}
}
