package engine.defaultObjects;

import com.badlogic.gdx.math.Vector2;

import engine.objects.GameMap;
import engine.objects.GsonMap;
import engine.objects.Player;
import engine.objects.GameObject; 

public class DefaultPlayer extends Player {
	
	public DefaultPlayer(GsonMap input, Vector2 location, String gameName, GameMap map) {
		super(input, location, gameName, map);
	}
	public void onCollide(GameObject that) {
	}
}
