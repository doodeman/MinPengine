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
				this.pos.x += this.moveSpeed;
			}
			else{
				this.pos.x -= this.moveSpeed;
			}
		}
		this.velocity.y -= (float) this.map.gravity * delta;
		super.update(delta);
	}
}
