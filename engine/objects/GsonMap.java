package engine.objects;

import java.util.Map;

public class GsonMap {
	public String entityName;
	public String entityType;
	public double gravity;
	public boolean collidable;
	public float sizeX;
	public float sizeY;
	public int moveSpeed;
	public String graphics;
	public Map<String, String> onCollide;
	public Map<String, String> controls;
	public int mapNumer;
	public String onFutureFall;
	public boolean facingRight;
}