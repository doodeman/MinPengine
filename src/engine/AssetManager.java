package engine;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {
	
	private HashMap<String, Texture> texturemap;
	
	public AssetManager() {
		texturemap = new HashMap<String, Texture>();
	}
	
	public void LoadTextures(List<String> textureLocations) {
		for (String s : textureLocations) {
			String filename = s.substring(s.lastIndexOf('/') + 1);
			texturemap.put(filename, new Texture(Gdx.files.internal(s)));
		}
	}
	
	public Texture getTexture(String textureName) {
		return texturemap.get(textureName);
	}
	
	public void DisposeTextures(List<String> textureNames) {
		for (String s : textureNames) {
			Texture t = texturemap.get(s);
			t.dispose();
			texturemap.remove(s);
		}
	}

}
