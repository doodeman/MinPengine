package engine.objects;

public class CollisionEvent extends Event{
	//these are events that trigger on collision between two things. The target, and the other.
	public CollisionEvent(GameObject target, String event) {
		super(target, event);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This function would execute the type of event in question.
	 */
	public void execute(GameObject other){
		
	}
	
	/**
	 * This function would cause an entity to be destroyed.
	 */
	private void destroy(GameObject other){
		
	}
	
	/**
	 * Not sure about this one, should cause other item to be destroyed when colliding with this item.
	 * Somehow I would need to take in a parameter here, with the other item.
	 */
	private void destroyOther(GameObject other){
		
	}
	
	/**
	 * This would cause the actor to display a text bubble. This should maybe be in a different class.
	 * @param textBox
	 */
	private void speak(GameObject textBox){
		
	}
}
