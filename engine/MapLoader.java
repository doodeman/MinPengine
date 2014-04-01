package engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


import engine.objects.GameMap;
import engine.objects.Helpers;

public class MapLoader {
	private String gameName;
	private ArrayList<GameMap> maps;
	public MapLoader(String gameName) throws IOException{
		maps = new ArrayList<GameMap>(); 
		this.gameName = gameName; 
		final File folder = new File(this.gameName + "/src");
		
		for(final File file: folder.listFiles()){
			if(Helpers.getExtension(file.getName()).equals("map")){ //this doesn't work, find a way to find .map endings.
				String newFile = file.getName().substring(0, file.getName().length()-3) + "mp"; //take in the name of the file, minus the ending
				String mapConfig = this.gameName + "/src/" + newFile;
				GameMap newMap = parseMap(mapConfig);
				maps.add(newMap);
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
		GameMap newMap = new GameMap(mapName);
		return newMap;
	}
	
	/**
	 * Loads a map with a particular number, loads all assets, and returns it.
	 * @param number - The number of the beast.
	 */
	public GameMap LoadMap(int number){
		GameMap currentMap = maps.get(number);
		if(currentMap == null){
			return null;
		}
		currentMap.loadAssets();
		return currentMap;	
	}
}
