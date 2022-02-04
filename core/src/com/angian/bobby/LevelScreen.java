package com.angian.bobby;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

import static com.angian.bobby.LevelConstants.*;

public class LevelScreen extends BaseScreen {
	private enum LevelType {
		PLAIN,
		CARPET,
		HOLES
	}

	public enum EnemyHeight {
		HIGH,
		LOW
	}

	private final int level;
	private final float startScore;
	private float levelScore;
	private boolean terminating;

	private BaseActor screenBackground;
	private Label timeLabel;
	private Label scoreLabel;

	private final List<Solid> platforms;
	private Bobby bobby;
	public Carpet carpet;
	private CreamPie creampie;
	private Sausage sausage;
	private Swordsman swordsman;


	public LevelScreen() {
		//this(1, 0);
		this(3, 0);  //DEBUG
	}

	public LevelScreen(int level, float startScore) {
		this.level = level;
		this.startScore = startScore;
		this.terminating = false;
		this.platforms = new ArrayList<>();

		initializeTemplate();
		initializeLabels();
	}

	public List<Solid> getPlatforms() {
		return platforms;
	}

	//in initialize we do not have access to constructor arguments
	@Override
	public void initialize() {}

	public void initializeLabels() {
		timeLabel = new Label("012345", Styles.labelStyle);
		Rectangle labelRect = LevelConstants.standard2gdxCoords(LevelConstants.TEXT_TIME);
		timeLabel.setPosition(labelRect.x, labelRect.y);
		timeLabel.setWidth(labelRect.width);
		timeLabel.setHeight(labelRect.height);
		mainStage.addActor(timeLabel);

		scoreLabel = new Label(String.format("%06d", (int)startScore), Styles.labelStyle);
		labelRect = LevelConstants.standard2gdxCoords(LevelConstants.TEXT_SCORE);
		scoreLabel.setPosition(labelRect.x, labelRect.y);
		scoreLabel.setWidth(labelRect.width);
		scoreLabel.setHeight(labelRect.height);
		mainStage.addActor(scoreLabel);

		levelScore = 9999f;
	}


	private void initializeTemplate() {
		LevelType levelType;
		EnemyHeight creampieHeight;
		EnemyHeight sausageHeight;
		boolean hasSwordsman = false;
		boolean isFinal = false;
		Rectangle[] platformDefs;

		switch (level) {
			case 1:
				levelType = LevelType.PLAIN;
				creampieHeight = EnemyHeight.HIGH;
				sausageHeight = EnemyHeight.LOW;
				break;
			case 2:
				levelType = LevelType.HOLES;
				creampieHeight = null;
				sausageHeight = null;
				break;
			case 3:
				levelType = LevelType.CARPET;
				creampieHeight = null;
				sausageHeight = null;
				break;
			case 4:
				levelType = LevelType.HOLES;
				creampieHeight = null;
				sausageHeight = null;
				hasSwordsman = true;
				break;
			case 5:
				levelType = LevelType.HOLES;
				creampieHeight = EnemyHeight.HIGH;
				sausageHeight = EnemyHeight.LOW;
				break;
			case 6:
				levelType = LevelType.CARPET;
				creampieHeight = EnemyHeight.HIGH;
				sausageHeight = EnemyHeight.HIGH;
				break;
			case 7:
				levelType = LevelType.HOLES;
				creampieHeight = null;
				sausageHeight = EnemyHeight.HIGH;
				hasSwordsman = true;
				break;
			case 8:
				levelType = LevelType.HOLES;
				creampieHeight = null;
				sausageHeight = EnemyHeight.LOW;
				hasSwordsman = true;
				break;
			case 9:
				levelType = LevelType.HOLES;
				creampieHeight = EnemyHeight.HIGH;
				sausageHeight = null;
				hasSwordsman = true;
				break;
			case 10:
				levelType = LevelType.CARPET;
				creampieHeight = EnemyHeight.LOW;
				sausageHeight = EnemyHeight.HIGH;
				break;
			case 11:
				levelType = LevelType.HOLES;
				creampieHeight = EnemyHeight.LOW;
				sausageHeight = EnemyHeight.LOW;
				break;
			case 12:
				levelType = LevelType.HOLES;
				creampieHeight = EnemyHeight.HIGH;
				sausageHeight = EnemyHeight.HIGH;
				hasSwordsman = true;
				break;
			case 13:
				levelType = LevelType.HOLES;
				creampieHeight = EnemyHeight.LOW;
				sausageHeight = EnemyHeight.LOW;
				hasSwordsman = true;
				break;
			case 14:
				levelType = LevelType.CARPET;
				creampieHeight = EnemyHeight.LOW;
				sausageHeight = EnemyHeight.LOW;
				isFinal = true;
				break;
			default:
				throw new IllegalArgumentException("Invalid level: " + level);
		}

		screenBackground = new BaseActor(0, 0, mainStage);
		switch (levelType) {
			case PLAIN:
				screenBackground.loadTexture("level_plain.png");
				platformDefs = LEVEL_PLAIN_PLATFORMS;
				break;
			case CARPET:
				screenBackground.loadTexture("level_carpet.png");
				platformDefs = LEVEL_CARPET_PLATFORMS;
				break;
			case HOLES:
				screenBackground.loadTexture("level_holes.png");
				platformDefs = LEVEL_HOLES_PLATFORMS;
				break;
			default:
				throw new IllegalArgumentException("Invalid levelType: " + levelType);
		}
		screenBackground.setSize(LevelConstants.SCREEN_WIDTH, LevelConstants.SCREEN_HEIGHT);


		for (Rectangle platformDef: platformDefs) {
			platformDef = standard2gdxCoords(platformDef);
			platforms.add(new Solid(platformDef.x, platformDef.y, platformDef.width, platformDef.height, mainStage));
		}


		if (creampieHeight != null)
			creampie = new CreamPie(creampieHeight, mainStage);
		if (sausageHeight != null)
			sausage = new Sausage(sausageHeight, mainStage);

		if (levelType == LevelType.CARPET) {
			carpet = new Carpet(mainStage);
			platforms.add(carpet);  // carpet is a platform too!
		}

		if (hasSwordsman)
			swordsman = new Swordsman(mainStage);

		if (isFinal) {
			//TODO: final booth
		}

		bobby = new Bobby(this, mainStage);
	}

	@Override
	protected void update(float dt) {
		if (terminating)
			return;

		if (levelScore <= 0) {
			levelScore = 0;
			System.out.println("Bobby died by timeout");
			BobbyGame.setActiveScreen(new LevelScreen(level, startScore));
		}

		levelScore -= (dt * TIME_FACTOR);
		timeLabel.setText(String.format("%06d", (int)levelScore));

		if (bobby.getX() >= END_LEVEL_X + bobby.getWidth() / 2) {
			System.out.println("Level " + level + " won");
			BobbyGame.setActiveScreen(new LevelScreen(level + 1, levelScore + startScore));
		}


		for (Solid platform: platforms) {
			if (bobby.overlaps(platform)) {
				Vector2 offset = bobby.preventOverlap(platform);
				if (offset != null) {
					if (Math.abs(offset.x) > Math.abs(offset.y))
						bobby.velocityVec.x = 0;
					else
						bobby.velocityVec.y = 0;
				}
			}
		}

		if ( (sausage != null && bobby.overlaps(sausage))
				|| (creampie != null && bobby.overlaps(creampie))
				|| (swordsman != null && bobby.overlapsSwordsman(swordsman)) ) {

			System.out.println("Bobby died by collision");
			BobbyGame.setActiveScreen(new LevelScreen(level, startScore));
		}

		if ( bobby.getY() < bobby.startRect.y && (carpet != null) && !bobby.belowOverlaps(carpet)) {
			System.out.println("Bobby died by falling");
			terminating = true;
			bobby.fallToDeath();
			Action deathSequence = Actions.sequence(
					Actions.delay(2),
					Actions.run(() -> BobbyGame.setActiveScreen(new LevelScreen(level, startScore)))
			);
			bobby.addAction(deathSequence);
		}


		if (swordsman != null) {
			//move swordsman from hole to hole
			int nCrossedPlatforms = countCrossedPlatforms();
			if (nCrossedPlatforms >= 3 && swordsman.getCurrHole() < 2)
				swordsman.setCurrHole(2);
			else if (nCrossedPlatforms == 2 && swordsman.getCurrHole() < 1)
				swordsman.setCurrHole(1);
		}
	}


	@Override
	public boolean keyDown(int keyCode) {
		if (terminating)
			return true;

		if (keyCode == Input.Keys.SPACE || keyCode == Input.Keys.UP) {
			bobby.jump();
		}

		return false;
	}


	private int countCrossedPlatforms() {
		int count = 0;
		for (Solid platform: platforms) {
			if (platform.getX() <= bobby.getX())
				count ++;
		}

		return count;
	}
}
