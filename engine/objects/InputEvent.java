package engine.objects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InputEvent extends Event {
	public InputEvent(MovableObject target, String event) {
		super(target, event);
		// TODO Auto-generated constructor stub
	}
	public void resolve(){
		//System.out.println("resolve collision of type " + event);
		Method m = null;
		String[] args = event.split(",");
		try {			
			m = this.getClass().getMethod(args[0], boolean.class, String[].class);
		} 
		catch (NoSuchMethodException e1) {e1.printStackTrace();} 
		catch (SecurityException e1) {e1.printStackTrace();}
		try {
			m.invoke(this, true, args);
		}
		catch (IllegalAccessException e) {e.printStackTrace();} 
		catch (IllegalArgumentException e) {e.printStackTrace();} 
		catch (InvocationTargetException e) {e.printStackTrace();}
	}
	/**
	 * this Would cause the target to jump.
	 */
	public void jump(boolean execute, String[] args){
		try{
			this.target.jump(Float.parseFloat(args[1]));
		}
		catch (ArrayIndexOutOfBoundsException e){
			this.target.jump(3);
		}
	}

	/**
	 * This causes the target to move left
	 */
	public void moveLeft(boolean execute, String[] args){
		try{
			this.target.moveLeft(Float.parseFloat(args[1]));
		}
		catch (ArrayIndexOutOfBoundsException e){
			this.target.moveLeft(3);
		}
	}
	
	/**
	 * This causes the target to move right
	 */
	public void moveRight(boolean execute, String[] args){
		try{
			this.target.moveRight(Float.parseFloat(args[1]));
		}
		catch (ArrayIndexOutOfBoundsException e){

			this.target.moveRight(3);
		}
	}

	public void moveUp(boolean execute, String[] args){
		try{
			this.target.moveUp(Float.parseFloat(args[1]));
		}
		catch (ArrayIndexOutOfBoundsException e){

			this.target.moveUp(3);
		}
	}
	public void moveDown(boolean execute, String[] args){
		try{
			this.target.moveDown(Float.parseFloat(args[1]));
		}
		catch (ArrayIndexOutOfBoundsException e){

			this.target.moveDown(3);
		}
	}
	public void stop(boolean execute, String[] args){
		this.target.stop();
	}
	public void reverseDirection(boolean execute, String[] args){
		this.target.reverseDirection();
	}
	public void stopMoveY(boolean execute, String[] args){
		this.target.stopMoveY();
	}
	public void stopMoveX(boolean execute, String[] args){
		this.target.stopMoveX();
	}

}

