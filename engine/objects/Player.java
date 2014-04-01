package engine.objects;

import engine.InputHandler;

public abstract class Player extends MovableObject {
	InputHandler inputHandler;
	
	public Player() {
		super(); 
	}
	public Player(String filename){
		this.filename = filename;
		//TODO: Parse the information from the file.
	}
	
}
