package engine.objects;

import java.util.ArrayList;
import java.util.List;

import engine.defaultObjects.DefaultPlayer;
import engine.AssetManager;

public class GameMap implements Comparable<GameMap>{
	public List<EnvironmentObject> environmentObjects;
	public List<MovableObject> movableObjects;
	public List<String> texturesToLoad;
	public List<GameObject> gameObjects; 
	public Player player;
	public int mapNumber;
	public String mapName;
	
	//Hoping this constructer will never get called.
	public GameMap() {
		texturesToLoad = new ArrayList<String>(); 
		gameObjects = new ArrayList<GameObject>(); 
		movableObjects = new ArrayList<MovableObject>();
		environmentObjects = new ArrayList<EnvironmentObject>();
		player = new DefaultPlayer(); 

		AssetManager.loadTextures(texturesToLoad);
	}
	
	public GameMap(String mapName) {
		this.mapName = mapName;
		texturesToLoad = new ArrayList<String>(); 
		gameObjects = new ArrayList<GameObject>(); 
		movableObjects = new ArrayList<MovableObject>();
		environmentObjects = new ArrayList<EnvironmentObject>();
		player = new DefaultPlayer(); 
	}
	
	public void setNumber(int number){
		this.mapNumber = number;
	}
	
	public int getNumber(){
		return mapNumber;
	}	
	
	public void update(float delta) {
		for (GameObject o : movableObjects) {
			o.update(delta);
		}
		player.update(delta);
	}
	
	public int compareTo(GameMap that){
	    return this.mapNumber > that.mapNumber ? 1 : this.mapNumber < that.mapNumber ? -1 : 0;
	}
	
	/**
	 * This method loads the map file, and parses out all the assets in that file, creating an object for each asset.
	 */
	public void loadAssets() {
		//for item in mapname.map, load all textures, assets and create classes.
		ArrayList<String> loaded = new ArrayList<String>();
		//iteratea í gegnum fælinn, ef er ekki í string listanum, þá parsa fælinn, ef environment object, adda í loaded
		//ef í string listanum, þá:
		//	finna objectið í environ lista... somehow... loopa bara blindly.
		//  bæta locationinu við objectið.
		// Henda inn textures
		AssetManager.loadTextures(texturesToLoad);
	}
}