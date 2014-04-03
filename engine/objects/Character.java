package engine.objects;

import com.badlogic.gdx.math.Vector2;

public class Character extends MovableObject {
	private String direction;
	public Character(GsonMap input, Vector2 location, String gameName, GameMap map) {
		super(input, location, gameName, map);
		if(this.facingRight){
			this.direction = "right";
		}
		else{
			this.direction = "left";
		}
	}
	@Override
	public void update(float delta) {
		if(this.collisionSides.contains(Side.BOTTOM) || map.gravity == 0){
			if(this.direction.equals("right")){
				this.velocity.x = this.moveSpeed; 
			}
			else if(this.direction.equals("left")){
				this.velocity.x = -this.moveSpeed;
			}
			else if(this.direction.equals("down")){
				this.velocity.y = -this.moveSpeed;
			}
			else if(this.direction.equals("up")){
				this.velocity.y = this.moveSpeed;
			}
		}
		super.update(delta);
	}
	public void changeDirection(String direction){
		this.velocity.x = 0;
		this.velocity.y = 0;
		this.direction = direction;
	}
	
	public void reverseDirection(){
		this.velocity.x = 0;
		this.velocity.y = 0;
		if(this.direction.equals("right")){
			sprite.flip(true, false);
			this.facingRight = false;
			this.direction = "left";
		}
		else if(this.direction.equals("left")){
			sprite.flip(true, false);
			this.facingRight = true;
			this.direction = "right";
		}
		else if(this.direction.equals("up")){
			this.direction = "down";
		}
		else if(this.direction.equals("down")){
			this.direction = "up";
		}
	}
}

