package engine;

import engine.objects.GameMap;

public class MapLoader {
	private String mapName;
	private String[] maps;
	public MapLoader(String mapName){
		this.mapName = mapName; 
		//loada inn folder með mapname, finna alla fæla sem enda á .map.
		//inní þeim eru öll asset sem þarf til að loada mappið.
		//skoða hvern file og finna file með sama nafni, sem endar á .mp eru map stillingar
	}
	
	/**
	 * Loads the assets for a particular map, number defines what map it is.
	 * @param number - The number of the beast.
	 */
	public GameMap LoadMap(int number){
		return null;	
	}

}
