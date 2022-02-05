package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelConstants.ANIM_FRAME_DURATION;


public class LifeIcon extends BaseActor {

    public LifeIcon(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("life.png");
        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOrigin(0, 0);
    }
}
