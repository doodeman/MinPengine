package engine;

import java.io.IOException;

import com.badlogic.gdx.Game;

import engine.defaultObjects.DefaultVictoryScreen;
import engine.objects.GameMap;

public class MinPengineGame extends Game {
	public MapLoader loader;
	GameScreen screen;
	public MinPengineGame(String gameName) throws IOException{
		this.loader = new MapLoader(gameName, this);
	}
	
	
	@Override
	public void create() {
		try {
			GameMap map = loader.LoadMap(0);
			screen = new GameScreen(this, map); 
			setScreen(screen); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MinPengineException e) {
			e.printStackTrace();
		}
	}	
	
	public void showVictoryScreen(String text) {
		screen.dispose();
		setScreen(new DefaultVictoryScreen(this, text));
	}
}
