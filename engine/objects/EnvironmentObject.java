package engine.objects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class EnvironmentObject {
	private ArrayList<Vector2> locations;	//a list of positions for this environment object.
	String filename;
	public EnvironmentObject(String filename){
		this.filename = filename;
		locations = new ArrayList<Vector2>();
		//TODO: Implement file parsing.
		//remember to add event system into this
	}
	public void AddLocation(Vector2 location){
		locations.add(location);
	}
}
