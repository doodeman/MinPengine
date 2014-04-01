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
	public void resolve(GameObject other){
		System.out.println("resolve collision of type " + event);
		Method m = null;
		try {
			m = this.getClass().getMethod(event, GameObject.class);
		} 
		catch (NoSuchMethodException e1) {e1.printStackTrace();} 
		catch (SecurityException e1) {e1.printStackTrace();}
		try {
			m.invoke(this, other);
		}
		catch (IllegalAccessException e) {e.printStackTrace();} 
		catch (IllegalArgumentException e) {e.printStackTrace();} 
		catch (InvocationTargetException e) {e.printStackTrace();}
	}

	/**
	 * This function would cause an entity to be destroyed.
	 */
	public void destroy(GameObject other){
		System.out.println("Destroy this");
	}
	
	/**
	 * This should destroy the object that this item collided with.
	 * @param other the item you collided with.
	 */
	public void destroyOther(GameObject other){
		System.out.println("Destroy other");
	}
	
	/**
	 * This would cause this entity to reverse it's direction.
	 * @param other
	 */
	public void reverseDirection(GameObject other){
		System.out.println("Reverse Direction");
	}
	
	/**
	 * This would cause this entity to jump... out of fear I presume.
	 * @param other
	 */
	public void jump(GameObject other){
		System.out.println("jump");
	}
	
	/**
	 * This would cause this entity to comlete the map.
	 * @param other
	 */
	public void completeMap(GameObject other){
		System.out.println("You won this map!");
	}
	
	/**
	 * This would cause this entity to stop. We will probably have to calculate some way 
	 * to make this entity move backwards the way it came away from what it crashed into, 
	 * so that we can't walk into the other entity indefinetly.
	 * @param other
	 */
	public void stop(GameObject other){
		System.out.println("Stop");
	}
}
