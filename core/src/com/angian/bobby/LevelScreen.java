package com.angian.bobby;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LevelScreen extends BaseScreen {
	private Label timeLabel;
	private Label scoreLabel;

	private Bobby bobby;
	private CreamPie creampie;
	private Sausage sausage;

	public void initialize() {
		BaseActor screenBackground = new BaseActor(0, 0, mainStage);
		screenBackground.loadTexture("level_plain.png");
		screenBackground.setSize(960, 600);

		timeLabel = new Label("012345", Styles.labelStyle);
		Rectangle labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_TIME);
		timeLabel.setPosition(labelRect.x, labelRect.y);
		timeLabel.setWidth(labelRect.width);
		timeLabel.setHeight(labelRect.height);
		mainStage.addActor(timeLabel);

		scoreLabel = new Label("567890", Styles.labelStyle);
		labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_SCORE);
		scoreLabel.setPosition(labelRect.x, labelRect.y);
		scoreLabel.setWidth(labelRect.width);
		scoreLabel.setHeight(labelRect.height);
		mainStage.addActor(scoreLabel);

		bobby = new Bobby(mainStage);
		creampie = new CreamPie(mainStage);
		sausage = new Sausage(mainStage);
	}

	@Override
	protected void update(float dt) {

	}


	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Input.Keys.SPACE || keyCode == Input.Keys.UP) {
			bobby.jump();
		}

		return false;
	}
}
