package engine.objects;

import com.badlogic.gdx.math.Vector2;

public class Character extends MovableObject {
	String onFutureFall;
	public Character(GsonMap input, Vector2 location, String gameName, GameMap map) {
		super(input, location, gameName, map);
		onFutureFall = input.onFutureFall;	//this should be used to decide how a character responds to cliffs.
	}
}
