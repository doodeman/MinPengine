package engine;

import java.io.IOException;

import com.badlogic.gdx.Game;

import engine.objects.GameMap;

public class MinPengineGame extends Game {
	public MapLoader loader;
	GameScreen screen; 
	public MinPengineGame(String gameName) throws IOException{
		this.loader = new MapLoader(gameName);
	}
	
	@Override
	public void create() {
		GameMap map = loader.LoadMap(0);
		//screen = new GameScreen(this, map); 
		//setScreen(screen); 
	}	
}
