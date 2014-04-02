package engine.defaultObjects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class DefaultVictoryScreen implements Screen {

	String victoryText;
	
	Game game;
	SpriteBatch batch;
	OrthographicCamera camera;
	BitmapFont font;
	float width, height;
	float textWidth, textHeight;
	
	public DefaultVictoryScreen(Game g, String text) {
		victoryText = text;
		game = g;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
		Gdx.gl11.glClearColor(0, 0, 0, 1);
		Gdx.gl11.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		long time = System.currentTimeMillis() / 750;
		if (time % 2 == 0) {
			batch.begin();
			font.draw(batch, victoryText, width / 2 - textWidth / 2, height / 2 + textHeight / 2);
			batch.end();
		}
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
		camera = new OrthographicCamera(width, height);
		camera.position.set(width / 2, height / 2, 0);
		font = new BitmapFont(Gdx.files.internal("assets/fonts/derp.fnt"));
		batch = new SpriteBatch();
		font.setColor(Color.RED);
		textWidth = font.getBounds(victoryText).width;
		textHeight = font.getBounds(victoryText).height;
	}

}
