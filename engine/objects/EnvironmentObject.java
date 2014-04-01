package engine.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EnvironmentObject extends GameObject{
	private ArrayList<Vector2> locations;	//a list of positions for this environment object.
	
	public EnvironmentObject(GsonMap input, Vector2 location){
		super(input, location);
		collisionEvents = new HashMap<String,CollisionEvent>();
		locations = new ArrayList<Vector2>();
		locations.add(location);
	}
	public void AddLocation(Vector2 location){
		locations.add(location);
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onCollide(GameObject that) {
		// TODO Auto-generated method stub
	}	
	@Override
	public void render(SpriteBatch batch) {
		for (Vector2 location : locations) {
			sprite.setPosition(location.x * this.ppU, location.y * this.ppU);
			sprite.draw(batch);
		}
	}
}