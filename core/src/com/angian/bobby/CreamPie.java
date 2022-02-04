package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelConstants.ANIM_FRAME_DURATION;


public class CreamPie extends BaseActor {
    public Rectangle startRect;

    public CreamPie(LevelScreen.EnemyHeight height, Stage s) {
        super(s);
        loadAnimationFromSheet("creampie_spritesheet.png", 4, 1, ANIM_FRAME_DURATION, true);

        if (height == LevelScreen.EnemyHeight.HIGH)
            startRect = LevelConstants.CREAMPIE_START_HIGH;
        else
            startRect = LevelConstants.CREAMPIE_START_LOW;

        startRect = LevelConstants.standard2gdxCoords(startRect);
        setPosition(startRect.x, startRect.y);

        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOrigin(0, 0);

        velocityVec = new Vector2(LevelConstants.CREAMPIE_SPEED, 0);
    }

    public void act(float dt) {
        super.act(dt);

        applyPhysics(dt);

        if (getX() > LevelConstants.SCREEN_WIDTH)
            setX(startRect.x);
    }
}
