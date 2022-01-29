package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelLayout.FRAME_DURATION;


public class CreamPie extends BaseActor {
    public CreamPie(Stage s) {
        super(s);
        //loadTexture("creampie.png");
        loadAnimationFromSheet("creampie_spritesheet.png", 4, 1, FRAME_DURATION, true);

        Rectangle startRect = LevelLayout.standard2gdxCoords(LevelLayout.CREAMPIE_START);
        setPosition(startRect.x, startRect.y);

        setScale(getScaleX() * LevelLayout.SCALE_FACTOR, getScaleY() * LevelLayout.SCALE_FACTOR);
        setOrigin(0, 0);

        setVelocity(LevelLayout.CREAMPIE_SPEED, 0);
    }

    public void act(float dt) {
        super.act(dt);

        applyPhysics(dt);
    }
}
