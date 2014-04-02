package engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.defaultObjects.DefaultPlatformerInputHandler;
import engine.objects.GameMap;

public class GameScreen implements Screen {

	Game game;
	GameMap map;
	SpriteBatch batch;
	OrthographicCamera camera;
	float width, height;
	DefaultPlatformerInputHandler inputHandler;
	
	public GameScreen(Game g, GameMap map) {
		game = g;
		this.map = map; 
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
		Gdx.gl11.glClearColor(map.color.r, map.color.g, map.color.b, map.color.a);
		Gdx.gl11.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		float delta = Gdx.graphics.getDeltaTime();
		// handle player input
		
		// update all objects in GameMap
		map.update(delta);
		
		camera.position.set(map.player.getCenter().x * map.player.ppU, map.player.getCenter().y * map.player.ppU, 0);
		camera.update();
		
		// render all objects
		map.renderObjects(batch);

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
