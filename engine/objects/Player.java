package engine.objects;

import com.badlogic.gdx.math.Vector2;

import engine.InputHandler;

public class Player extends MovableObject {
	InputHandler inputHandler;
	
	public Player(GsonMap input, Vector2 location, String gameName, GameMap map) {
		super(input, location, gameName, map); 
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		this.velocity.y -= (float) this.map.gravity * delta; 
	}
}
