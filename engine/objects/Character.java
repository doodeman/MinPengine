package engine.objects;

import com.badlogic.gdx.math.Vector2;

public class Character extends MovableObject {
	String onFutureFall;
	public Character(GsonMap input, Vector2 location, String gameName) {
		super(input, location, gameName);
		onFutureFall = input.onFutureFall;	//this should be used to decide how a character responds to cliffs.
	}
}
