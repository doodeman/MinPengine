package engine;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {
	
	private static HashMap<String, Texture> texturemap = new HashMap<String, Texture>();
	
	public static void loadTextures(List<String> textureLocations) {
		for (String s : textureLocations) {
			loadTexture(s);
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
		return texturemap.get(textureName);
	}
}
