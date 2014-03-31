package engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.defaultObjects.DefaultPlatformerInputHandler;
import engine.objects.EnvironmentObject;
import engine.objects.GameMap;
import engine.objects.GameObject;
import engine.objects.MovableObject;

public class GameScreen implements Screen {

	Game game;
	GameMap map;
	SpriteBatch batch;
	OrthographicCamera camera;
	float width, height;
	DefaultPlatformerInputHandler inputHandler;
	
	public GameScreen(Game g) {
		game = g;
		map = new GameMap(); 
	}
	
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
		Gdx.gl11.glClearColor(1, 1, 1, 1);
		Gdx.gl11.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		float delta = Gdx.graphics.getDeltaTime();
		// handle player input
		
		// update all objects in GameMap
		map.update(delta);
		
		camera.position.set(map.player.center().x, map.player.center().y, 0);
		camera.update();
		
		// render all objects
	
		batch.begin();
		for (EnvironmentObject o : map.environmentObjects) {
			//o.render(batch);
		}
		for (MovableObject o : map.movableObjects){
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
		
		inputHandler = new DefaultPlatformerInputHandler(map.player);
		Gdx.input.setInputProcessor(inputHandler);
		
		// parse and instantiate map
	}
}
