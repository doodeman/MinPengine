package engine.defaultObjects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

import engine.InputHandler;
import engine.objects.GameMap;
import engine.objects.Player;


/**
 * A default control scheme for a basic platformer. 
 * Move left and right using the arrow keys, jump with spacebar
 * @author kristleifur
 *
 */
public class EventInputHandler extends InputHandler {
	GameMap map; 
	
	public EventInputHandler(GameMap map) {
		super(); 
		this.map = map; 
	}
	private String getKeyName(int keyCode){
		if(keyCode == Keys.LEFT) return "Left";
		if(keyCode == Keys.RIGHT) return "Right";
		if(keyCode == Keys.UP) return "Up";
		if(keyCode == Keys.DOWN) return "Down";
		if(keyCode == Keys.SPACE) return "Space";
		if(keyCode == Keys.A) return "A";
		if(keyCode == Keys.S) return "S";
		if(keyCode == Keys.D) return "D";
		if(keyCode == Keys.W) return "W";
		if(keyCode == Keys.ALT_LEFT || keyCode == Keys.ALT_RIGHT) return "Alt";
		if(keyCode == Keys.CONTROL_LEFT || keyCode == Keys.CONTROL_RIGHT) return "Ctrl";
		if(keyCode == Keys.ENTER) return "Enter";
		if(keyCode == Keys.Q) return "Q";
		if(keyCode == Keys.E) return "E";
		if(keyCode == Keys.ESCAPE) return "Esc";
		if(keyCode == Keys.SHIFT_LEFT || keyCode == Keys.SHIFT_RIGHT) return "Shift";
		if(keyCode == Keys.TAB) return "Tab";
		return null;
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		//All this because JAVA sucks, and won't let me get the enum name from an keyCode. Go figure.
		String key = getKeyName(keyCode);
		if (key != null){
			map.addKey(key + "Down");
			return true;		
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		String key = getKeyName(keyCode);
		if (key != null){
			map.addKey(key + "Up");
			return true;		
		}
		return false;
	}
}
