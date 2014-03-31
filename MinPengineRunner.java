import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import engine.MinPengineGame;


public class MinPengineRunner {
	public static void main(String arg[]) {
		new LwjglApplication(new MinPengineGame(arg[0]), "MinPengine Game", 800, 600, false);
	}
}
