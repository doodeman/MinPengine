package engine.objects;

import java.util.ArrayList;
import java.util.List;

import engine.defaultObjects.DefaultPlayer;
import engine.AssetManager;

public class GameMap {
	public List<GameObject> gameObjects;
	public List<String> texturesToLoad;
	public Player player;
	
	public GameMap() {
		texturesToLoad = new ArrayList<String>(); 
		gameObjects = new ArrayList<GameObject>(); 
		player = new DefaultPlayer(); 
		AssetManager.loadTextures(texturesToLoad);
	}
	
	public void update(float delta) {
		for (GameObject o : gameObjects) {
			o.update(delta);
		}
		player.update(delta);
	}
}