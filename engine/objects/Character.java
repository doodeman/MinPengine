package engine.objects;

import com.badlogic.gdx.math.Vector2;

public class Character extends MovableObject {
	public Character(GsonMap input, Vector2 location, String gameName, GameMap map) {
		super(input, location, gameName, map);
	}
	@Override
	public void update(float delta) {
		if(this.collisionSides.contains(Side.BOTTOM)){
			if(this.facingRight){
				this.velocity.x += this.moveSpeed; 
			}
			else{
				this.velocity.y -= this.moveSpeed;
			}
		}
		super.update(delta);
	}
}
