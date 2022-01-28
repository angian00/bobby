package com.angian.bobby;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class BobbyGame extends BaseGame {
	@Override
	public void create() {
		super.create();
		setActiveScreen(new MenuScreen());
	}
}
