package engine.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class AIObject extends GameObject {

	public AIObject(GsonMap input, Vector2 location, String gameName, GameMap map) {
		super(input, location, gameName, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}
	@Override
	public void render(SpriteBatch batch) {
		//intentionally empty
	}
}
