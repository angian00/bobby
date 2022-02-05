package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelConstants.FINAL_BOOTH_POSITION;


public class FinalBooth extends BaseActor {

    public FinalBooth(Stage s) {
        super(s);
        loadTexture("final_booth.png");

        Rectangle rect = LevelConstants.standard2gdxCoords(FINAL_BOOTH_POSITION);

        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOrigin(0, 0);
        setPosition(rect.x, rect.y);
    }
}
