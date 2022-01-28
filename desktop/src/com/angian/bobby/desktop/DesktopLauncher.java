package com.angian.bobby.desktop;

import com.angian.bobby.BobbyGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bobby (a clone by AnGian)";
		config.width = 960;
		config.height = 600;
		config.forceExit = false;

		new LwjglApplication(new BobbyGame(), config);
	}
}
