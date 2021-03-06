package engine.defaultObjects;

import com.badlogic.gdx.Input.Keys;

import engine.InputHandler;
import engine.objects.Player;


/**
 * A default control scheme for a basic platformer. 
 * Move left and right using the arrow keys, jump with spacebar
 * @author kristleifur
 *
 */
public class DefaultPlatformerInputHandler extends InputHandler {
	Player player; 
	
	public DefaultPlatformerInputHandler(Player player) {
		super(); 
		this.player = player; 
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Keys.LEFT) {
			player.velocity.x = -5; 
			return true; 
		}
		if (keyCode == Keys.RIGHT) {
			player.velocity.x = 5; 
			return true; 
		}
		if (keyCode == Keys.SPACE || keyCode == Keys.UP) {
			player.jump(3);
			return true; 
		}
		if (keyCode == Keys.DOWN){
			player.velocity.y = -5;
			return true;
		}
		return false; 
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		if (keyCode == Keys.LEFT || keyCode == Keys.RIGHT){
			player.velocity.x = 0;
			return true;
		}
		if (keyCode == Keys.SPACE || keyCode == Keys.DOWN) {
			player.velocity.y = 0; 
			return true; 
		}
		return false;
	}
}
