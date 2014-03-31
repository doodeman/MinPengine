package engine.objects;

import java.util.List;

import engine.AssetManager;

public class GameMap {
	public List<EnvironmentObject> environmentObjects;
	public List<MovableObject> movableObjects;
	public List<String> texturesToLoad;
	public Player player;
	
	public GameMap() {
		AssetManager.loadTextures(texturesToLoad);
	}
	
	public void update(float delta) {
		for (GameObject o : movableObjects) {
			o.update(delta);
		}
		player.update(delta);
	}
}