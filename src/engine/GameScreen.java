package engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.objects.GameMap;
import engine.objects.GameObject;

public class GameScreen implements Screen {

	GameMap map;
	SpriteBatch batch;
	OrthographicCamera camera;
	float width, height;
	
	@Override
	public void dispose() {
		AssetManager.disposeTextures(map.texturesToLoad);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// handle player input
		
		// update all objects in GameMap
		for (GameObject o : map.gameObjects) {
			o.update();
		}
		
		// render all objects
		batch.begin();
		for (GameObject o : map.gameObjects) {
			o.render(batch);
		}
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(width, height);
		camera.position.set(width / 2, height / 2, 0);
		
		// instantiate map
	}
}
