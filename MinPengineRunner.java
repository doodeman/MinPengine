import java.io.IOException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import engine.MinPengineGame;


public class MinPengineRunner {
	public static void main(String arg[]) throws IOException {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.fullscreen = false;
		cfg.useGL20 = false;
		cfg.height = 720;
		cfg.width = 1280;
		cfg.resizable = false;
		cfg.title = "MinPengine Game";
		new LwjglApplication(new MinPengineGame(arg[0]), cfg);
	}
}
