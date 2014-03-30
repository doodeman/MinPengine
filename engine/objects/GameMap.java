package engine.objects;

import java.util.List;

import engine.AssetManager;

public class GameMap {
	public List<GameObject> gameObjects;
	public List<String> texturesToLoad;
	
	public GameMap() {
		AssetManager.loadTextures(texturesToLoad);
	}
}