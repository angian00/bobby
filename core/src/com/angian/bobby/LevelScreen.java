package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class LevelScreen extends BaseScreen {
	public void initialize() {
		BaseActor screenBackground = new BaseActor(0, 0, mainStage);
		screenBackground.loadTexture("level_plain.png");
		screenBackground.setSize(960, 600);
	}

	@Override
	protected void update(float dt) {

	}


}
