<<<<<<< HEAD
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
=======
package engine.objects;

import java.util.List;

public class GameMap {
	List<EnvironmentObject> environment; 
}
>>>>>>> 2f39f27a77dde40b34a45ce34113eb50a0ca3f4e
