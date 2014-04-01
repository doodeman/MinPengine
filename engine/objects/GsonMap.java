package engine.objects;

import java.util.Map;

public class GsonMap {
	public String entityName;
	public String entityType;
	public int gravity;
	public boolean collidable;
	public double sizeX;
	public double sizeY;
	public int moveSpeed;
	public String graphics;
	public Map<String, String> onCollide;
	public Map<String, String> controls;
	public int mapNumer;
	public String onFutureFall;
}