package com.angian.bobby.desktop;

import com.angian.bobby.BobbyGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import static com.angian.bobby.LevelConstants.SCREEN_HEIGHT;
import static com.angian.bobby.LevelConstants.SCREEN_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bobby (a clone by AnGian)";
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;
		config.forceExit = false;

		new LwjglApplication(new BobbyGame(), config);
	}
}
