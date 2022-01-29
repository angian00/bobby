package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Sausage extends BaseActor {
    public Sausage(Stage s) {
        super(s);
        loadTexture("sausage.png");

        Rectangle startRect = LevelLayout.standard2gdxCoords(LevelLayout.SAUSAGE_START);
        setPosition(startRect.x, startRect.y);

        setScale(getScaleX() * LevelLayout.SCALE_FACTOR, getScaleY() * LevelLayout.SCALE_FACTOR);
        setOrigin(0, 0);

        setVelocity(-LevelLayout.SAUSAGE_SPEED, 0);
    }

    public void act(float dt) {
        super.act(dt);

        applyPhysics(dt);
    }
}
