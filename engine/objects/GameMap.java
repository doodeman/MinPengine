package engine.objects;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import engine.AssetManager;
import engine.defaultObjects.DefaultVictoryScreen;

public class GameMap implements Comparable<GameMap>{
	public List<String> texturesToLoad;
	public List<GameObject> gameObjects; 
	public Player player;
	public int mapNumber;
	public String mapName;
	public double gravity;
	public float friction;
	private String gameName;
	public Color color;
	public Game game;
	

	public GameMap(String mapName, String gameName) throws IOException {
		this.mapName = mapName;
		this.gameName = gameName;
		texturesToLoad = new ArrayList<String>(); 
		gameObjects = new ArrayList<GameObject>(); 
		GsonMap gsonMap = Helpers.getGsonMap(mapName);
		gravity = gsonMap.gravity;		//these default to zero
		mapNumber = gsonMap.mapNumer;
		friction = gsonMap.friction;
		color = new Color(gsonMap.bgcolorR, gsonMap.bgcolorG, gsonMap.bgcolorB, gsonMap.bgcolorA);
	}
	
	public void update(float delta) {
		checkForCollisions(delta);
		for (GameObject o : gameObjects) {
			o.update(delta);
		}
	}
	
	public int compareTo(GameMap that){
	    return this.mapNumber > that.mapNumber ? 1 : this.mapNumber < that.mapNumber ? -1 : 0;
	}
	
	/**
	 * This method loads the map file, and parses out all the assets in that file, creating an object for each asset.
	 * Environment objects are created once, and if they appear more than once, they get a location into their locations array.
	 * @throws IOException 
	 */
	public void loadAssets() throws IOException {
		String mapFile = mapName.substring(0,mapName.length()-2) + "map";
		List<String> lines = Files.readAllLines(Paths.get(mapFile), StandardCharsets.UTF_8);
		//System.out.println(mapFile);
		int x = 0;
		int y = lines.size();
		for(String line: lines){	
			String[] words = line.split("\\s+");		//wow, such regex
			
			for(int i = 0; i < words.length; i++){
				Vector2 location = new Vector2(x, y);
				String word = words[i];
				if (word.equals("ee")) {
				} 
				else {
					loadObject(word, location);
				}
				x++;
			}
			x = 0; 
			y--;
		}
		AssetManager.loadTexturesForObjects(gameObjects);
	}
	private void loadObject(String word, Vector2 location) throws IOException {

		GsonMap gsonMap = Helpers.getGsonMap(this.gameName + "/src/" + word + ".mp");
		String type = gsonMap.entityType;
		if(type.equals("character")){
			gameObjects.add(new Character(gsonMap, location, this.gameName, this));
		}
		else if(type.equals("player")){
			player = new Player(gsonMap, location, this.gameName, this);
			gameObjects.add(player);
		}
		else if(type.equals("environment")){
			gameObjects.add(new EnvironmentObject(gsonMap, location, this.gameName, this));
		}
		else if(type.equals("static")){
			gameObjects.add(new StaticObject(gsonMap, location, this.gameName, this));
		}
		else if(type.equals("ai")){
			gameObjects.add(new AIObject(gsonMap, location, this.gameName, this));
		}
		else{
			System.out.println(type + " is not a recognized object type");
		}
	}

	public void renderObjects(SpriteBatch batch) {
		batch.begin();
		for (GameObject o : gameObjects){
			o.render(batch);
		}
		batch.end();
	}
	
	public void checkForCollisions(float delta) {
		List<MovableObject> mObjects = getMovableObjects(); 
		for (MovableObject m : mObjects) {
			for (GameObject g : gameObjects) {
				if (!m.equals(g)) {
					if (m.hasCollided(g, delta) != Side.NONE) {
						resolveCollision(m, g);
						resolveCollision(g, m);
					}
				}
			}
		}
	}
	
	/**
	 * Here we go through all the events for 'm', if they apply to the type that 'g' has, then 
	 * we execute that event.
	 * If the event is the default event, we hold it, and if no other effects apply, we execute that.
	 * @param m - the item that we are checking
	 * @param g	- the item that we collided with
	 */
	private void resolveCollision(GameObject m, GameObject g) {
		CollisionEvent defAction = null;
		boolean resolved = false;
		for (Entry<String, CollisionEvent> entry : m.getCollisionEvents().entrySet()) {
			if(entry.getKey().equals(g.getEntityType())){
				entry.getValue().resolve(g);
				resolved = true;
				break;
			}
			else if(entry.getKey().equals("default")){
				defAction = entry.getValue();
			}
		}
		if(!resolved && defAction != null){
			defAction.resolve(g);
		}
	}

	private List<MovableObject> getMovableObjects() {
		List<MovableObject> retList = new ArrayList<MovableObject>(); 
		for (GameObject o : gameObjects) {
			if (MovableObject.class.isAssignableFrom(o.getClass())) {
				retList.add((MovableObject) o);
			}
		}
		return retList;
	}

	/**
	 * This happens when the map completes... if it wins or something.
	 */
	public void completeMap() {
		game.setScreen(new DefaultVictoryScreen(game, "WINNAR"));
		
	}
}