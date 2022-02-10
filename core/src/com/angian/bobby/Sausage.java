package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.angian.bobby.LevelConstants.ANIM_FRAME_DURATION;


public class Sausage extends BaseActor {
    private final LevelScreen levelScreen;
    public Rectangle startRect;
    private boolean isMoving;

    public Sausage(LevelScreen levelScreen, LevelScreen.EnemyHeight height, Stage s) {
        super(s);
        this.levelScreen = levelScreen;
        this.isMoving = false;

        loadAnimationFromSheet("sausage_spritesheet.png", 2, 1, ANIM_FRAME_DURATION, true);


        if (height == LevelScreen.EnemyHeight.HIGH)
            startRect = LevelConstants.SAUSAGE_START_HIGH;
        else
            startRect = LevelConstants.SAUSAGE_START_LOW;

        startRect = LevelConstants.standard2gdxCoords(startRect);
        setPosition(startRect.x, startRect.y);
        setVisible(false);

        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOrigin(0, 0);

        velocityVec = new Vector2(-LevelConstants.SAUSAGE_SPEED, 0);

        Action startingAction = Actions.sequence(
                Actions.delay(LevelConstants.SAUSAGE_START_DELAY),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(true);
                        isMoving = true;
                    }
                })
        );

        this.addAction(startingAction);
    }

    public void act(float dt) {
        if (levelScreen.isWinning)
            return;

        super.act(dt);

        if (!isMoving)
            return;

        applyPhysics(dt);

        if (getX() < -getWidth())
            setX(startRect.x);
    }
}
