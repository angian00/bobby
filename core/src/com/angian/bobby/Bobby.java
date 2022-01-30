package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelLayout.BOBBY_JUMP_DURATION;
import static com.angian.bobby.LevelLayout.FRAME_DURATION;


public class Bobby extends BaseActor {
    private float jumpElapsedTime = -1;
    private final float baseY;

    public Bobby(Stage s) {
        super(s);
        //loadTexture("bobby.png");
        loadAnimationFromSheet("bobby_spritesheet.png", 1, 4, FRAME_DURATION, true);

        Rectangle startRect = LevelLayout.standard2gdxCoords(LevelLayout.BOBBY_START);
        setPosition(startRect.x, startRect.y);
        baseY = startRect.y;

        setScale(getScaleX() * LevelLayout.SCALE_FACTOR, getScaleY() * LevelLayout.SCALE_FACTOR);
        setOrigin(0, 0);
    }

    @Override
    public void applyPhysics(float dt) {
        if (isJumping()) {
            jumpElapsedTime += dt;
            if (jumpElapsedTime >= BOBBY_JUMP_DURATION) {
                //fall down
                setVelocityY(-LevelLayout.BOBBY_RUN_SPEED);
            }
        }

        super.applyPhysics(dt);

        if (getY() < baseY) {
            setVelocityY(0);
            setY(baseY);
            jumpElapsedTime = -1;
            //TODO: check for death or collision with platform
        }
    }

    public void act(float dt) {
        super.act(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            setVelocityX(-LevelLayout.BOBBY_RUN_SPEED);
            setScaleX(-Math.abs(getScaleX())); //flips the sprite horizontally
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            setVelocityX(LevelLayout.BOBBY_RUN_SPEED);
            setScaleX(+Math.abs(getScaleX()));
        } else {
            if (!isJumping())
                setVelocityX(0);
        }

        applyPhysics(dt);

        setAnimationPaused(!isMoving());
    }


    public void jump() {
        if (isJumping())
            return;

        jumpElapsedTime = 0.0f;
        setVelocityY(LevelLayout.BOBBY_RUN_SPEED);
    }

    public boolean isJumping() {
        return (jumpElapsedTime >= 0);
    }
}
