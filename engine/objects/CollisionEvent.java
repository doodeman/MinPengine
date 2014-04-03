package engine.objects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CollisionEvent extends Event{
	//these are events that trigger on collision between two things. The target, and the other.
	public CollisionEvent(GameObject target, String event) {
		super(target, event);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This function would execute the type of event in question.
	 * @throws SecurityException - This throws all the exeptions
	 * @throws NoSuchMethodException 
	 */
	public void resolve(GameObject other, Side collisionSide){
		//System.out.println("resolve collision of type " + event);
		Method m = null;
		String[] args = event.split(",");
		try {			
			m = this.getClass().getMethod(args[0], GameObject.class, Side.class, String[].class);
		} 
		catch (NoSuchMethodException e1) {e1.printStackTrace();} 
		catch (SecurityException e1) {e1.printStackTrace();}
		try {
			m.invoke(this, other, collisionSide,args);
		}
		catch (IllegalAccessException e) {e.printStackTrace();} 
		catch (IllegalArgumentException e) {e.printStackTrace();} 
		catch (InvocationTargetException e) {e.printStackTrace();}
	}

	/**
	 * This function would cause an entity to be destroyed.
	 * this function is implemented in the super class Event, implement it there.
	 */
	public void destroy(GameObject other, Side collisionSide, String[] args){
		this.setCollisionSide(other, collisionSide, "destroy");
		destroy();
	}
	
	/**
	 * This should destroy the object that this item collided with.
	 * @param other the item you collided with.
	 */
	public void destroyOther(GameObject other, Side collisionSide, String[] args){
		//System.out.println("Destroy other");
		this.setCollisionSide(other, collisionSide, "destroyOther");
		other.destroy();
	}

	public void teleport(GameObject other, Side collisionSide, String[] args){
		this.setCollisionSide(other, collisionSide, "teleport");
		other.pos.x = Float.parseFloat(args[1]);
		other.pos.y = Float.parseFloat(args[2]);
	}
	
	public void none(GameObject other, Side collisionSide, String[] args){
		//nothing should happen
		//System.out.println("none");
		this.setCollisionSide(other, collisionSide, "none");
	}
	/**
	 * This would cause this entity to reverse it's direction. Away from the "other"
	 * @param other
	 */
	public void reverseDirection(GameObject other, Side collisionSide, String[] args){
		this.setCollisionSide(other, collisionSide, "reverseDirection");
		target.reverseDirection();
	}
	
	/**
	 * This would cause this entity to jump... out of fear I presume.
	 * This method uses the general method stop from the normal event class. Implement that.
	 * @param other
	 */
	public void jump(GameObject other, Side collisionSide, String[] args){
		this.setCollisionSide(other, collisionSide, "jump");
		jump(Float.parseFloat(args[1]));
	}
	/**
	 * This would cause the other to jump... out of fear I presume.
	 * This method uses the general method stop from the normal event class. Implement that.
	 * @param other
	 */
	public void jumpOther(GameObject other, Side collisionSide, String[] args){
		this.setCollisionSide(other, collisionSide, "jump");
		//System.out.println("here");
		other.jump(Float.parseFloat(args[1]));
	}
	
	/**
	 * This would cause this entity to comlete the map.
	 * Implemented in the Event class
	 * @param other
	 */
	public void completeMap(GameObject other, Side collisionSide, String[] args){
		this.setCollisionSide(other, collisionSide, "completeMap");
		completeMap();
	}
	
	/**
	 * Causes this entity to stop.
	 * @param other
	 */
	public void stop(GameObject other, Side collisionSide, String[] args){
		this.setCollisionSide(other, collisionSide, "stop");
		stop();
	}
	private void setCollisionSide(GameObject other, Side collisionSide, String type) {
		// TODO Auto-generated method stub
		if(collisionSide == Side.BOTTOM || collisionSide == Side.TOP){
			target.collisionTypeY = type;
		}
		else{
			target.collisionTypeX = type;
		}
	}
	public void changeDirectionOther(GameObject other, Side collisionSide, String[] args){
		this.setCollisionSide(other, collisionSide, "changeDirectionOther");
		other.changeDirection(args[1]);
	}
}
