package engine.objects;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;

public class EnvironmentObject extends GameObject{
	
	public EnvironmentObject(GsonMap input, Vector2 location, String gameName){
		super(input, location, gameName);
		collisionEvents = new HashMap<String,CollisionEvent>();
	}
	
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onCollide(GameObject that) {
		// TODO Auto-generated method stub
	}	
}