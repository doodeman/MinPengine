package engine;

import com.badlogic.gdx.Game;

public class MinPengineGame extends Game {

	GameScreen screen; 
	
	@Override
	public void create() {
		screen = new GameScreen(); 
		setScreen(screen); 
	}
	
}
