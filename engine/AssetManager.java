package engine;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import engine.objects.GameObject;

public class AssetManager {
	
	private static HashMap<String, Texture> texturemap = new HashMap<String, Texture>();
	private static String defaultTexture = "UserGame/assets/p1.png";
	
	public static void loadTextures(List<String> textureLocations) {
		for (String s : textureLocations) {
			loadTexture(s);
		}
	}
	
	public static void loadTexturesForObjects(List<GameObject> objects) {
		for (GameObject o : objects) {
			if (o.spritePath == "") {
				if (!texturemap.containsKey(defaultTexture)) {
					loadTexture(defaultTexture);
				}
			} 
			else {
				if (!texturemap.containsKey(o.spritePath)) {
					loadTexture(o.spritePath); 
				}
			}
			o.sprite = new Sprite(getTexture(o.spritePath));
			o.sprite.setPosition(0, 0);
		}
	}
	
	public static void loadTexture(String textureName) {
		texturemap.put(textureName, new Texture(Gdx.files.internal(textureName)));
	}
	
	public static void disposeTextures(List<String> textureLocations) {
		for (String s : textureLocations) {
			disposeTexture(s);
		}
	}
	
	public static void disposeTexture(String textureName) {
		Texture t = texturemap.get(textureName);
		t.dispose();
		texturemap.remove(textureName);
	}
	
	public static Texture getTexture(String textureName) {
		if (textureName == "") {
			return texturemap.get(defaultTexture); 
		}
		return texturemap.get(textureName);
	}
}
