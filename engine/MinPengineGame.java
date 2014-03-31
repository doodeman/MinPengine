package engine;

import com.badlogic.gdx.Game;

public class MinPengineGame extends Game {
	public String gameName;
	//public list maps
	//public int fcurrentmap
	GameScreen screen; 
	public MinPengineGame(String gameName){
		this.gameName = gameName;
	}
	
	@Override
	public void create() {
		screen = new GameScreen(this); 
		setScreen(screen); 
	}	
}
