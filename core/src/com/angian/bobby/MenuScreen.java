package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen extends BaseScreen {
	public void initialize() {
		BaseActor screenBackground = new BaseActor(0, 0, mainStage);
		screenBackground.loadTexture("start_screen.png");
		screenBackground.setSize(960, 600);
	}


	public void update(float dt) {
		if (Gdx.input.isKeyPressed(Input.Keys.S))
			BobbyGame.setActiveScreen(new LevelScreen());
	}


	@Override
	public boolean keyDown(int keyCode) {
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(Input.Keys.SPACE))
			BobbyGame.setActiveScreen(new LevelScreen());
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();

		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		BobbyGame.setActiveScreen(new LevelScreen());

		return true;
	}
}
