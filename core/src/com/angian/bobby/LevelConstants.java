package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;

public class LevelConstants {
    public static final int ORIG_SCREEN_WIDTH = 320;
    public static final int ORIG_SCREEN_HEIGHT = 200;
    public static final float SCALE_FACTOR = 3.0f;

    public static final int SCREEN_WIDTH = (int)(ORIG_SCREEN_WIDTH * SCALE_FACTOR);
    public static final int SCREEN_HEIGHT = (int)(ORIG_SCREEN_HEIGHT * SCALE_FACTOR);

    public static final float FRAME_DURATION = 0.1f;
    public static final float TIME_FACTOR = 200;

    public static final Rectangle TEXT_TIME  = new Rectangle(250,  10, 54, 7);
    public static final Rectangle TEXT_SCORE = new Rectangle(250, 178, 54, 7);

    public static final Rectangle BOBBY_START = new Rectangle(0, 74, 16, 22);
    public static final float BOBBY_RUN_SPEED = 20 * SCALE_FACTOR; //in px per sec
    public static final float BOBBY_JUMP_SPEED = 1.2f * BOBBY_RUN_SPEED; // in px per sec
    public static final float BOBBY_DEATH_FALL_SPEED = 5 * SCALE_FACTOR;
    public static final float GRAVITY = 60.0f; //in px per sec2
    public static final float END_LEVEL_X = 292 * SCALE_FACTOR;

    public static final Rectangle CREAMPIE_START_HIGH = new Rectangle(-40, 58, 28, 8);
    public static final Rectangle CREAMPIE_START_LOW = new Rectangle(-40, 64, 28, 8);
    public static final float CREAMPIE_SPEED = 20 * SCALE_FACTOR; //in px per sec

    public static final Rectangle SAUSAGE_START_HIGH = new Rectangle(320, 80, 18, 8);
    public static final Rectangle SAUSAGE_START_LOW = new Rectangle(320, 86, 18, 8);
    public static final float SAUSAGE_SPEED = 40 * SCALE_FACTOR; //in px per sec


    public static final Rectangle[] LEVEL_PLAIN_PLATFORMS = {
            new Rectangle(0, 96, 320, 20)
    };

    public static final Rectangle[] LEVEL_CARPET_PLATFORMS = {
            new Rectangle(0, 96, 112, 20),
            new Rectangle(208, 96, 112, 20)
    };

    public static final Rectangle[] LEVEL_HOLES_PLATFORMS = {
            new Rectangle(0,  96, 48, 20),
            new Rectangle(88, 96, 56, 20),
            new Rectangle(184,96, 56, 20),
            new Rectangle(280,96, 40, 20)
    };

    public static Rectangle standard2gdxCoords(Rectangle layoutRect) {
        float flippedY = SCREEN_HEIGHT - layoutRect.y*SCALE_FACTOR -layoutRect.height*SCALE_FACTOR;
        return new Rectangle(layoutRect.x*SCALE_FACTOR, flippedY, layoutRect.width*SCALE_FACTOR, layoutRect.height*SCALE_FACTOR);
    }
}
