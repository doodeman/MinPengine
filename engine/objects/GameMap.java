package engine.objects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.defaultObjects.DefaultPlayer;
import engine.AssetManager;

public class GameMap {
	public List<String> texturesToLoad;
	public List<GameObject> gameObjects; 
	public Player player;
	
	public GameMap() {
		texturesToLoad = new ArrayList<String>(); 
		gameObjects = new ArrayList<GameObject>(); 
		player = new DefaultPlayer(); 
		gameObjects.add(player);
		AssetManager.loadTexturesForObjects(gameObjects);
	}
	
	public void update(float delta) {
		for (GameObject o : gameObjects) {
			o.update(delta);
		}
		player.update(delta);
	}
	
	public void renderObjects(SpriteBatch batch) {
		batch.begin();
		for (GameObject o : gameObjects){
			o.render(batch);
		}
		batch.end();
	}
}