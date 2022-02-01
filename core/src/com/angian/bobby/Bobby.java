package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelConstants.*;


public class Bobby extends BaseActor {
    public Rectangle startRect;
    private float gravity;
    private boolean isFallingToDeath = false;

    private final LevelScreen levelScreen;
    private final BaseActor belowSensor;

    public Bobby(LevelScreen levelScreen, Stage s) {
        super(s);
        this.levelScreen = levelScreen;
        this.gravity = GRAVITY;

        loadAnimationFromSheet("bobby_spritesheet.png", 1, 4, FRAME_DURATION, true);

        startRect = LevelConstants.standard2gdxCoords(LevelConstants.BOBBY_START);

        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOriginY(0);
        setPosition(startRect.x + getWidth()/2, startRect.y);

        belowSensor = new BaseActor(0, 0, s);
        belowSensor.loadTexture("white.png");
        belowSensor.setSize(getWidth() - 8, 8);
        belowSensor.setBoundaryRectangle();
        //belowSensor.setVisible(true); //DEBUG
        belowSensor.setVisible(false);
    }

    @Override
    public void applyPhysics(float dt) {
        velocityVec.y -= gravity * dt;

        moveBy(velocityVec.x * dt, velocityVec.y * dt);
        belowSensor.setPosition(getX() + 4, getY() - 8);
    }

    public void act(float dt) {
        super.act(dt);

        if (!isFallingToDeath) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                velocityVec.x = -LevelConstants.BOBBY_RUN_SPEED;
                setScaleX(-Math.abs(getScaleX())); //flips the sprite horizontally
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                velocityVec.x = LevelConstants.BOBBY_RUN_SPEED;
                setScaleX(+Math.abs(getScaleX()));
            } else {
                if (!isJumping())
                    velocityVec.x = 0;
            }
        }

        applyPhysics(dt);

        if (this.isOnSolid()) {
            belowSensor.setColor(Color.GREEN);
        } else {
            belowSensor.setColor(Color.RED);
        }

        setAnimationPaused(!isRunning());
    }


    public void jump() {
        if (isJumping() || isFalling())
            return;

        velocityVec.y = LevelConstants.BOBBY_JUMP_SPEED;
    }

    public void fallToDeath() {
        velocityVec.x = 0;
        velocityVec.y = -BOBBY_DEATH_FALL_SPEED;
        gravity = 0;
        isFallingToDeath = true;
    }

    final static float EPSILON = 0.001f;

    public boolean isJumping() {
        return (velocityVec.y > EPSILON);
    }

    public boolean isFalling() {
        return (velocityVec.y < -EPSILON);
    }

    public boolean isRunning() {
        return (Math.abs(velocityVec.x) > EPSILON);
    }

    public boolean isOnSolid() {
        for (Solid platform: levelScreen.getPlatforms()) {
            if (belowOverlaps(platform))
                return true;
        }

        return false;
    }

    public boolean belowOverlaps(BaseActor actor) {
        return belowSensor.overlaps(actor);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isFallingToDeath) {
            // "drowning" animation, i.e. bobby feet disappear in the hole
            int croppedHeight = (int)(getHeight() - (startRect.y - getY()) / getScaleY());
            TextureRegion tr = animation.getKeyFrame(elapsedTime);
            tr.setRegion(tr.getRegionX(), tr.getRegionY(), tr.getRegionWidth(), croppedHeight);
            batch.draw(tr,
                    getX(), startRect.y, getOriginX(), getOriginY(),
                    getWidth(), croppedHeight, getScaleX(), getScaleY(), getRotation());

        } else {
            super.draw(batch, parentAlpha);
        }
    }
}
