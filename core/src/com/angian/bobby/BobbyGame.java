package com.angian.bobby;

public class BobbyGame extends BaseGame {
	@Override
	public void create() {
		super.create();

		Sounds.initialize();
		setActiveScreen(new MenuScreen());
	}
}
