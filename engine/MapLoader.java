package engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



import com.badlogic.gdx.Game;

import engine.objects.GameMap;
import engine.objects.Helpers;

public class MapLoader {
	private String gameName;
	private ArrayList<GameMap> maps;
	
	public MapLoader(String gameName, Game g) throws IOException{
		maps = new ArrayList<GameMap>(); 
		this.gameName = gameName; 
		final File folder = new File(this.gameName + "/src");
		
		if (folder.listFiles() != null) {
			for(final File file: folder.listFiles()){
				if(Helpers.getExtension(file.getName()).equals("map")){ //this doesn't work, find a way to find .map endings.
					String newFile = file.getName().substring(0, file.getName().length()-3) + "mp"; //take in the name of the file, minus the ending
					String mapConfig = this.gameName + "/src/" + newFile;
					GameMap newMap = parseMap(mapConfig);
					newMap.game = g;
					maps.add(newMap);
				}
			}
		}
		Collections.sort(maps);
	}

	/**   
	 * This method parses creates a GameMap, loads in the config options in the corresponding mapConfig file, and
	 * passes on the information in that file to the map. It then returns the map.
	 * @param mapConfig
	 * @return
	 * @throws IOException 
	 */
	private GameMap parseMap(String mapName) throws IOException {
		GameMap newMap = new GameMap(mapName, this.gameName);
		return newMap;
	}
	
	/**
	 * Loads a map with a particular number, loads all assets, and returns it.
	 * @param number - The number of the beast.
	 * @throws IOException 
	 * @throws MinPengineException 
	 */
	public GameMap LoadMap(int number) throws IOException, MinPengineException{
		GameMap currentMap; 
		try {
			currentMap = maps.get(number);
		} 
		catch (IndexOutOfBoundsException e) {
			throw (new MinPengineException("Failed to load map " + number + "!"));
		}
		if(currentMap == null){
			return null;
		}
		currentMap.loadAssets();
		return currentMap;	
	}
}
