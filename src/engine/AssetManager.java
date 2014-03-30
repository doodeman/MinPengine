package engine;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {
	
	private static HashMap<String, Texture> texturemap = new HashMap<String, Texture>();
	
	public static void loadTextures(List<String> textureLocations) {
		for (String s : textureLocations) {
			String filename = s.substring(s.lastIndexOf('/') + 1);
			texturemap.put(filename, new Texture(Gdx.files.internal(s)));
		}
	}
	
	public static Texture getTexture(String textureName) {
		return texturemap.get(textureName);
	}
	
	public static void disposeTextures(List<String> textureLocations) {
		for (String s : textureLocations) {
			String filename = s.substring(s.lastIndexOf('/') + 1);
			Texture t = texturemap.get(filename);
			t.dispose();
			texturemap.remove(filename);
		}
	}
}
