package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.angian.bobby.LevelConstants.*;
import static com.badlogic.gdx.Input.Peripheral.MultitouchScreen;

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

	private static Logger logger = Logger.getLogger("BobbyGame");

	private int level;
	private final float startScore;
	private int nLives;
	private int nBonusLives;
	private float levelScore;
	public boolean isDying;
	public boolean isWinning;
	public float timeToSwitchLevel;
	private boolean isFinal;

	private Label timeLabel;
	public final boolean isTouchEnabled;

	private final List<Solid> platforms;
	private Bobby bobby;
	public Carpet carpet;
	private CreamPie creampie;
	private Sausage sausage;
	private Swordsman swordsman;
	private FinalSwordsman finalSwordsman;

	public Button touchLeft;
	public Button touchRight;
	private Button touchJump;


	public LevelScreen() {
		this(1, 0, 5, 0);
		//this(2, 0, 5, 0);  //DEBUG
	}

	public LevelScreen(int level, float startScore, int nLives, int nBonusLives) {
		logger.severe("LevelScreen constructor [level=" + level + "]");

		this.level = level;
		this.startScore = startScore;
		this.nLives = nLives;
		this.nBonusLives = nBonusLives;
		this.isDying = false;
		this.isWinning = false;
		this.platforms = new ArrayList<>();
		this.isTouchEnabled = Gdx.input.isPeripheralAvailable(MultitouchScreen);

		Sounds.reset();

		initializeTemplate();
		initializeLabels();

		if (isTouchEnabled) {
			initializeTouchControls();
		}
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

		Label scoreLabel = new Label(Util.formatLabelNumber((int)startScore), Styles.labelStyle);
		labelRect = LevelConstants.standard2gdxCoords(LevelConstants.TEXT_SCORE);
		scoreLabel.setPosition(labelRect.x, labelRect.y);
		scoreLabel.setWidth(labelRect.width);
		scoreLabel.setHeight(labelRect.height);
		mainStage.addActor(scoreLabel);

		levelScore = 9999f;


		for (int iLife=0; iLife < nLives; iLife++) {
			Rectangle rect = LevelConstants.standard2gdxCoords(LIFE_POSITIONS[iLife]);
			new LifeIcon(rect.x, rect.y, mainStage);
		}

		for (int iLife=0; iLife < nBonusLives; iLife++) {
			Rectangle rect = LevelConstants.standard2gdxCoords(LIFE_BONUS_POSITIONS[iLife]);
			new LifeIcon(rect.x, rect.y, mainStage);
		}

		new ProgressCursor(level, mainStage);
	}

	private void initializeTouchControls() {
		Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("arrow_left.png"))));
		touchLeft = new ImageButton(drawable);
		Rectangle rect = LevelConstants.standard2gdxCoords(BUTTON_LEFT_POSITION);
		touchLeft.setPosition(rect.x, rect.y);
		mainStage.addActor(touchLeft);

		drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("arrow_right.png"))));
		touchRight = new ImageButton(drawable);
		rect = LevelConstants.standard2gdxCoords(BUTTON_RIGHT_POSITION);
		touchRight.setPosition(rect.x, rect.y);
		mainStage.addActor(touchRight);


		drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("arrow_up.png"))));
		touchJump = new ImageButton(drawable);
		rect = LevelConstants.standard2gdxCoords(BUTTON_JUMP_POSITION);
		touchJump.setPosition(rect.x, rect.y);

		touchJump.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if ((!isDying) && (!isWinning))
					bobby.jump();
			}
		});

		mainStage.addActor(touchJump);
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

		BaseActor screenBackground = new BaseActor(0, 0, mainStage);
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
			creampie = new CreamPie(this, creampieHeight, mainStage);
		if (sausageHeight != null)
			sausage = new Sausage(this, sausageHeight, mainStage);

		if (levelType == LevelType.CARPET) {
			carpet = new Carpet(this, mainStage);
			platforms.add(carpet);  // carpet is a platform too!
		}

		if (hasSwordsman)
			swordsman = new Swordsman(this, mainStage);

		if (isFinal) {
			new FinalBooth(mainStage);
		}
		this.isFinal = isFinal;

		bobby = new Bobby(this, mainStage);
	}

	@Override
	protected void update(float dt) {
		if (isDying || isWinning) {
			timeToSwitchLevel -= dt;
			if (timeToSwitchLevel <= 0) {
				//level switch music has finished playing? ok, switch level
				BobbyGame.setActiveScreen(new LevelScreen(level, startScore, nLives, nBonusLives));
			}

			return;
		}

		if (levelScore <= 0) {
			levelScore = 0;
			logger.severe("Bobby died by timeout");
			die();
		}

		levelScore -= (dt * TIME_FACTOR);
		timeLabel.setText(Util.formatLabelNumber((int)levelScore));

		float endX = isFinal ? END_GAME_X : END_LEVEL_X;
		if (bobby.getX() >= endX  * SCALE_FACTOR + bobby.getWidth() / 2) {
			logger.severe("Level " + level + " won");
			if (nBonusLives < 3)
				nBonusLives ++;

			isWinning = true;
			level = level + 1;
			if (level > 14) {
				// restart from beginning
				level = 1;
			}

			/*
			Sounds.levelWon(new Runnable() {
				@Override
				public void run() {
					BobbyGame.setActiveScreen(new LevelScreen(level, levelScore + startScore, nLives, nBonusLives));
				}
			});
			*/

			//---- dirty hacks to avoid Music.OnCompletionListener not triggering in html port ---
			timeToSwitchLevel = WINNING_LEVEL_DURATION;
			Sounds.levelWon(null);
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

			logger.severe("Bobby died by collision");
			die();
		}

		if ( (bobby.getY() < bobby.startRect.y) && ((carpet == null) || (!bobby.belowOverlaps(carpet))) ) {
			logger.severe("Bobby died by falling");
			isDying = true;
			bobby.fallToDeath();

			Sounds.fallDead(null);
			timeToSwitchLevel = FALL_TO_DEATH_DURATION;
		}


		if (swordsman != null) {
			//move swordsman from hole to hole
			int nCrossedPlatforms = countCrossedPlatforms();
			if (nCrossedPlatforms >= 3 && swordsman.getCurrHole() < 2)
				swordsman.setCurrHole(2);
			else if (nCrossedPlatforms == 2 && swordsman.getCurrHole() < 1)
				swordsman.setCurrHole(1);
		}

		if (isFinal) {
			//show swordsman sprite in front of booth for final level
			if (countCrossedPlatforms() == 3 && bobby.isOnSolid() && finalSwordsman == null) {
				finalSwordsman = new FinalSwordsman(mainStage);
			}
		}
	}

	private void die() {
		logger.severe("die");

		if (nBonusLives > 0)
			nBonusLives --;
		else
			nLives --;

		if (nLives == 0) {
			//game over
			BobbyGame.setActiveScreen(new MenuScreen());
		} else {
			//restart level
			BobbyGame.setActiveScreen(new LevelScreen(level, startScore, nLives, nBonusLives));
		}
	}


	@Override
	public boolean keyDown(int keyCode) {
		if (isDying || isWinning)
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
