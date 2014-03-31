package engine;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;

public abstract class InputHandler implements InputProcessor {
	List<Integer> pressedKeys;
	
	public InputHandler() {
		pressedKeys = new ArrayList<Integer>(); 
	}
	
	@Override 
	public boolean keyUp(int keyCode) {
		return false; 
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		return false; 
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
