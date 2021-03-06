package engine.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class MovableObject extends GameObject {
	public double gravity;
	public Vector2 velocity; 
	boolean facingRight;
	public float moveSpeed;
	protected String filename;
	List<Side> collisionSides;
	private boolean jumping;
	
	public MovableObject(GsonMap input, Vector2 location, String gameName, GameMap map) {
		super(input, location, gameName, map); 
		this.jumping = false;
		velocity = new Vector2(0,0); 
		gravity = input.gravity; 
		moveSpeed = input.moveSpeed;
		facingRight = input.facingRight; 
		if(input.controls != null){
			for (Map.Entry<String, String> entry : input.controls.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    ArrayList<InputEvent> eventArray = map.inputEvents.get(key);
			    if(eventArray == null){
			    	eventArray = new ArrayList<InputEvent>();
			    	eventArray.add(new InputEvent(this, value));
			    	map.inputEvents.put(key, eventArray);
			    }
			    else{
			    	eventArray.add(new InputEvent(this, value));
			    }
			}
		}
		collisionSides = new ArrayList<Side>();
	}
	
	public void update(float delta) {
		if(!collisionSides.contains(Side.BOTTOM) || !collisionTypeY.equals("stop")){
			this.velocity.y -= (float) this.map.gravity * delta;
		}
		Vector2 addPos = new Vector2(pos.x,pos.y);
		addPos.add(this.velocity.cpy().scl(delta).sub(new Vector2(map.friction, map.friction)));
		if((!collisionSides.contains(Side.RIGHT) && !collisionSides.contains( Side.LEFT)) || !collisionTypeX.equals("stop")){
			this.pos.x = addPos.x;
		}
		if((!collisionSides.contains(Side.TOP) && !collisionSides.contains(Side.BOTTOM)) || !collisionTypeY.equals("stop")){
			this.pos.y = addPos.y;
		}
		if(collisionSides.contains(Side.BOTTOM)){
			this.jumping = false;
		}
		this.sprite.setPosition(this.pos.x * this.ppU, this.pos.y * this.ppU);
		this.collisionSides.clear();
		this.collisionTypeX = "none";
		this.collisionTypeY = "none";
	}
	
	private Side hasCollidedX(GameObject that, float delta){
		Vector2 nextPos = new Vector2(this.pos.x, this.pos.y);
		
		nextPos.add(new Vector2(this.velocity.x,0).scl(delta));
		Side retSide = null;
		retSide = outOfRange(that, nextPos);
		if(retSide == null){
			Vector2 thisCenter = this.getCenter(); 
			Vector2 thatCenter = that.getCenter();
			float deltaX = thisCenter.x - thatCenter.x; 
			if (deltaX > 0)  {
				retSide = Side.LEFT; 
			}
			else {
				retSide = Side.RIGHT; 
			}
		}
		return retSide;
	}
	private Side hasCollidedY(GameObject that, float delta){
		Vector2 nextPos = new Vector2(this.pos.x, this.pos.y);
		nextPos.add(new Vector2(0,this.velocity.y).scl(delta));
		nextPos.y -= (float) this.map.gravity * delta;
		Side retSide = null;
		retSide = outOfRange(that, nextPos);
		if(retSide == null){
			Vector2 thisCenter = this.getCenter(); 
			Vector2 thatCenter = that.getCenter();
			float deltaY = thisCenter.y - thatCenter.y; 
			if (deltaY > 0)  {
				retSide = Side.BOTTOM; 
			}
			else {
				retSide = Side.TOP; 
			}
		}
		return retSide;
	}
	private Side outOfRange(GameObject that, Vector2 nextpos) {
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
		return null;
	}
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
	/**
	 * Returns Side.NONE if not collided. Otherwise returns which of this' sides collided. 
	 * @param that
	 * @return
	 */
	public Side hasCollided(GameObject that, float delta) {
		//Rectangle 1's bottom edge is higher than Rectangle 2's top edge.
		//collisionSides.clear();
		Side collidedX = hasCollidedX(that, delta);
		Side collidedY = hasCollidedY(that, delta);
		Side retVal = Side.NONE;
		if(collidedX != Side.NONE){
			retVal = collidedX;
			collisionSides.add(collidedX);
		}
		if(collidedY != Side.NONE){
			retVal = collidedY;
			collisionSides.add(collidedY);
		}
		return retVal;
	}
	

	/**
	 * This would cause this entity to stop. We will probably have to calculate some way 
	 * to make this entity move backwards the way it came away from what it crashed into, 
	 * so that we can't walk into the other entity indefinetly.
	 * This method uses the general method stop from the normal event class. Implement that.
	 */
	public void stop(){
		//TODO: implement
		if ((this.collisionSides.contains(Side.RIGHT) || this.collisionSides.contains(Side.LEFT)) && this.collisionTypeX.equals("stop"))
			this.velocity.x = 0;
		if ((this.collisionSides.contains(Side.TOP) || this.collisionSides.contains(Side.BOTTOM)) && this.collisionTypeY.equals("stop"))
			this.velocity.y = 0;
	}
	/**
	 * Reverses the direction of this object.
	 */
	public void reverseDirection(){
		this.velocity.x = 0;
		this.facingRight = !this.facingRight;
		sprite.flip(true, false);
	}
	
	/**
	 * Makes this object jump. Heel!
	 */
	public void jump(float height){
		if(!jumping){
			velocity.y = height; 
			jumping = true;
		}
	}
	public void moveLeft(float distance){
		this.velocity.x -= distance;
		if(facingRight){
			this.facingRight = false;
			sprite.flip(true, false);
		}
	}
	public void moveRight(float distance){
		this.velocity.x += distance;
		if(!facingRight){
			this.facingRight = true;
			sprite.flip(true, false);
		}
	}
	public void moveUp(float distance){
		this.velocity.y += distance;
	}
	public void moveDown(float distance){
		this.velocity.y -= distance;
	}
	public void stopMoveY(){
		this.velocity.y = 0;
	}
	public void stopMoveX(){
		this.velocity.x = 0;
	}
}
