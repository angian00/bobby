package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelConstants.ANIM_FRAME_DURATION;
import static com.angian.bobby.LevelConstants.SCALE_FACTOR;

public class Carpet extends Solid {
    public Rectangle startRect;

    public Carpet(Stage s) {
        super(0, 0, 0, 0, s);
        loadAnimationFromSheet("carpet_spritesheet.png", 4, 1, ANIM_FRAME_DURATION, true);
        setBoundaryRectangle();

        startRect = LevelConstants.standard2gdxCoords(LevelConstants.CARPET_START);
        setPosition(startRect.x, startRect.y);

        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOrigin(0, 0);
        velocityVec = new Vector2(LevelConstants.CARPET_SPEED, 0);
    }

    public void act(float dt) {
        super.act(dt);

        applyPhysics(dt);

        if (getX() + startRect.width >= LevelConstants.CARPET_TO * SCALE_FACTOR)
            velocityVec.x = - LevelConstants.CARPET_SPEED;
        else if (getX() <= startRect.x)
            velocityVec.x = LevelConstants.CARPET_SPEED;
    }
}
