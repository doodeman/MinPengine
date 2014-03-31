package engine.objects;

import java.util.ArrayList;
import java.util.List;

import engine.defaultObjects.DefaultPlayer;
import engine.AssetManager;

public class GameMap {
	public List<EnvironmentObject> environmentObjects;
	public List<MovableObject> movableObjects;
	public List<String> texturesToLoad;
	public List<GameObject> gameObjects; 
	public Player player;
	
	public GameMap() {
		texturesToLoad = new ArrayList<String>(); 
		gameObjects = new ArrayList<GameObject>(); 
		movableObjects = new ArrayList<MovableObject>();
		environmentObjects = new ArrayList<EnvironmentObject>();
		player = new DefaultPlayer(); 
		AssetManager.loadTextures(texturesToLoad);
	}
	
	public void update(float delta) {
		for (GameObject o : movableObjects) {
			o.update(delta);
		}
		player.update(delta);
	}
}