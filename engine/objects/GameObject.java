package engine.objects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * A superclass for all objects in the game
 * @author kristleifur
 *
 */
public abstract class GameObject {
	public boolean collidable; 

	public Vector2 pos;
	Vector2 size; 
	public Sprite sprite;

	protected HashMap<String, CollisionEvent> collisionEvents;
	public final float ppU = 64; // pixels per game world unit. This should be the same as the sprite size.
	
	public String spritePath;

	protected String collisionType;

	private String entityName;

	private String entityType;

	protected GameMap map;

	//public event onCollide()... myndi returna eventi. Eða hugsanlega hafa tvö array hérna, collision og 
	//click event. Bæta við fleirum ef það ætti við. Í hverju updatei þyrfti að fara í gegnum þessi event, og 
	//athuga hvort þau triggerast.
	public GameObject(GsonMap input, Vector2 location, String gameName, GameMap map) {
		this.map = map;
		this.entityName = input.entityName;
		spritePath = "";
		this.pos = location; 
		this.size = new Vector2(1,1); 
		sprite = new Sprite(); 
		collisionEvents = new HashMap<String,CollisionEvent>();
		if(input.onCollide != null){
			for (Map.Entry<String, String> entry : input.onCollide.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    collisionEvents.put(key, new CollisionEvent(this, value));
			}
		}
		
		this.size = new Vector2(input.sizeX, input.sizeY);
		this.spritePath = gameName + "/" + input.graphics;
		this.collidable = input.collidable;
		this.entityType = input.entityType;
	}
	
	public GameMap getMap(){
		return this.map;
	}
	public String getEntityType(){
		return this.entityType;
	}
	
	public void update(float delta) {
		
	}
	
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
	public String getEntityName(){
		return entityName;
	}
	public HashMap<String, CollisionEvent> getCollisionEvents(){
		return collisionEvents;
	}

	/**
	 * returns the center of the object's box
	 * @return
	 */
	public Vector2 getCenter() {
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
	/**
	 * This is just here to be here, implemented in MovableObject, this is here so we can call it from collisionEvents,
	 * But it should not do anything in static or non-movable objects, and thus is empty here.
	 */
	public void stop() {
		this.collisionType = "stop";
		// Intentionally empty.
		
	}
	/**
	 * This function would destroy this object. Kill it essentially. Make it so.
	 */
	public void destroy() {
		this.collisionType = "destroy";
		// TODO Auto-generated method stub
		
	}
	/**
	 * This method is implemented in the "movableobject" class, and should not be used here.
	 */
	public void reverseDirection() {
		this.collisionType = "reverseDirection";
		// TODO Auto-generated method stub
		
	}
	/**
	 * This method is implemented in the "movableObject" class, and should not be used here.
	 */
	public void jump() {
		// TODO Auto-generated method stub
		
	}
}
