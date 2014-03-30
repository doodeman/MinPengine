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
	Vector2 size; 
	Sprite sprite; 
	
	public void update() {
		
	}
	public void render() {
		
	}
	public abstract void onCollide(GameObject that);  
	
	/**
	 * Returns Side.NONE if not collided. Otherwise returns which of this' sides collided. 
	 * @param that
	 * @return
	 */
	public Side hasCollided(GameObject that) {
		//Rectangle 1′s bottom edge is higher than Rectangle 2′s top edge.
		if ((this.pos.y) > (that.pos.y + that.size.y)) {
			return Side.NONE;
		}
		//Rectangle 1′s top edge is lower than Rectangle 2′s bottom edge.
		if ((this.pos.y + this.size.y)< that.pos.y) {
			return Side.NONE; 
		}
		//Rectangle 1′s left edge is to the right of Rectangle 2′s right edge.
		if ((this.pos.x) > (that.pos.x + that.size.x) ) {
			return Side.NONE; 
		}
		//Rectangle 1′s right edge is to the left of Rectangle 2′s left edge.
		if ((this.pos.x + this.size.x) < that.pos.x ) {
			return Side.NONE; 
		}
		
		double topDist = -(this.pos.y - that.topBorder());
		double botDist = -(this.pos.y + this.size.y - that.botBorder());
		double leftDist = -(this.pos.x - that.leftBorder());
		double rightDist = -(this.pos.x + this.size.x - that.rightBorder());
		
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
	 * returns the center of the object's box
	 * @return
	 */
	public Vector2 center() {
		return new Vector2(this.pos.x + this.size.x/2, this.pos.y + this.size.y); 
	}
	
	public double topBorder () {
		return this.pos.y + this.size.y; 
	}
	public double botBorder() {
		return this.pos.y;
	}
	public double leftBorder() {
		return this.pos.x; 
	}
	public double rightBorder() {
		return this.pos.x + this.size.x; 
	}
}
