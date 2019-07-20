package org.angrypigs.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.Util.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Constants.HEIGHT;
		config.width = Constants.WIDTH;
		config.title = " ";
		config.resizable = false;
		config.backgroundFPS = Constants.FPS;
		config.foregroundFPS = Constants.FPS;
		new LwjglApplication(new AngryPigs(), config);
	}
}
