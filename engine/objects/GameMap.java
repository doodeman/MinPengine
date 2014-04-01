package engine.objects;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import engine.AssetManager;

public class GameMap implements Comparable<GameMap>{
	public List<String> texturesToLoad;
	public List<GameObject> gameObjects; 
	public Player player;
	public int mapNumber;
	public String mapName;
	public double gravity;
	private String gameName;
	

	public GameMap(String mapName, String gameName) throws IOException {
		this.mapName = mapName;
		this.gameName = gameName;
		texturesToLoad = new ArrayList<String>(); 
		gameObjects = new ArrayList<GameObject>(); 
		GsonMap gsonMap = Helpers.getGsonMap(mapName);
		gravity = gsonMap.gravity;		//these defaul to zero
		mapNumber = gsonMap.mapNumer;
	}
	
	public void update(float delta) {
		for (GameObject o : gameObjects) {
			o.update(delta);
		}
		player.update(delta);
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
		ArrayList<String> loaded = new ArrayList<String>();
		String mapFile = mapName.substring(0,mapName.length()-2) + "map";
		List<String> lines = Files.readAllLines(Paths.get(mapFile), StandardCharsets.UTF_8);
		System.out.println(mapFile);
		int x = 0;
		int y = 0;
		for(String line: lines){	
			String[] words = line.split("\\s+");		//wow, such regex
			
			for(int i = 0; i < words.length; i++){
				Vector2 location = new Vector2(x, y);
				String word = words[i];
				if(!loaded.contains(word)){
					loadObject(word, location);
					loaded.add(word);
				}	
				else{
					for(GameObject e : gameObjects){
						if(e.getEntityName() == word){
							EnvironmentObject env = (EnvironmentObject) e; 
							env.AddLocation(location);
							break;
						}
					}
				}
			}
		}
		AssetManager.loadTexturesForObjects(gameObjects);
	}
	private void loadObject(String word, Vector2 location) throws IOException {

		GsonMap gsonMap = Helpers.getGsonMap(this.gameName + "/src/" + word + ".mp");
		String type = gsonMap.entityType;
		if(type.equals("character")){
			gameObjects.add(new Character(gsonMap, location));
		}
		else if(type.equals("player")){
			player = new Player(gsonMap, location);
		}
		else if(type.equals("environment")){
			gameObjects.add(new EnvironmentObject(gsonMap, location));
		}
		else if(type.equals("static")){
			gameObjects.add(new StaticObject(gsonMap, location));
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
}