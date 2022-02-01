package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelConstants.FRAME_DURATION;


public class Sausage extends BaseActor {
    public Rectangle startRect;

    public Sausage(LevelScreen.EnemyHeight height, Stage s) {
        super(s);
        loadAnimationFromSheet("sausage_spritesheet.png", 2, 1, FRAME_DURATION, true);


        if (height == LevelScreen.EnemyHeight.HIGH)
            startRect = LevelConstants.SAUSAGE_START_HIGH;
        else
            startRect = LevelConstants.SAUSAGE_START_LOW;

        startRect = LevelConstants.standard2gdxCoords(startRect);
        setPosition(startRect.x, startRect.y);

        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOrigin(0, 0);

        velocityVec = new Vector2(-LevelConstants.SAUSAGE_SPEED, 0);
    }

    public void act(float dt) {
        super.act(dt);

        applyPhysics(dt);

        if (getX() < -getWidth())
            setX(startRect.x);
    }
}
