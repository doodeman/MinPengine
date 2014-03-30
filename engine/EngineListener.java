package engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EngineListener implements ApplicationListener {

	//TODO: this is just basic functionality to test moving the camera around in 2d space
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture t1, t2, t3;
	private Sprite s1, s2, s3;
	private float width, height;
	
	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(width, height);
		batch = new SpriteBatch();
		
		t1 = new Texture(Gdx.files.internal("assets/textures/Bulbasaur.png"));
		t2 = new Texture(Gdx.files.internal("assets/textures/Squirtle.png"));
		t3 = new Texture(Gdx.files.internal("assets/textures/Charmander.png"));
		
		s1 = new Sprite(t1);
		s2 = new Sprite(t2);
		s3 = new Sprite(t3);

		camera.position.set(width / 2, height / 2, 0);

		s1.setPosition(0, 0);
		s2.setPosition(300, 0);
		s3.setPosition(600, 0);	
	}

	@Override
	public void dispose() {
		batch.dispose();
		t1.dispose();
		t2.dispose();
		t3.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl11.glClearColor(1, 1, 1, 1);
		Gdx.gl11.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		s1.draw(batch);
		s2.draw(batch);
		s3.draw(batch);
		batch.end();
		
		// TODO: move input handling somewhere else
		float delta = Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(500 *  delta, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-500 *  delta, 0);
		}
		// prevent camera from going too far left
		if(camera.position.x <= width / 2)
			camera.position.set(width / 2, camera.position.y, 0);

		camera.update();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
}
