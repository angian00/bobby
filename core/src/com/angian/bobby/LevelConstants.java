package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;

public class LevelConstants {
    public static final int ORIG_SCREEN_WIDTH = 320;
    public static final int ORIG_SCREEN_HEIGHT = 200;
    public static final float SCALE_FACTOR = 3.0f;

    public static final int SCREEN_WIDTH = (int)(ORIG_SCREEN_WIDTH * SCALE_FACTOR);
    public static final int SCREEN_HEIGHT = (int)(ORIG_SCREEN_HEIGHT * SCALE_FACTOR);

    public static final float ANIM_FRAME_DURATION = 0.1f;
    public static final float TIME_FACTOR = 408;

    public static final Rectangle TEXT_TIME  = new Rectangle(250,  10, 54, 7);
    public static final Rectangle TEXT_SCORE = new Rectangle(250, 178, 54, 7);

    public static final Rectangle[] LIFE_POSITIONS = {
            new Rectangle(120, 169, 6, 7),
            new Rectangle(136, 169, 6, 7),
            new Rectangle(152, 169, 6, 7),
            new Rectangle(168, 169, 6, 7),
            new Rectangle(184, 169, 6, 7),
    };

    public static final Rectangle[] LIFE_BONUS_POSITIONS = {
            new Rectangle(72,  8, 6, 7),
            new Rectangle(88,  8, 6, 7),
            new Rectangle(104, 8, 6, 7),
    };

    public static final Rectangle PROGRESS_START = new Rectangle(128, 16, 2, 1);
    public static final float PROGRESS_STEP = 4;

    public static final Rectangle BOBBY_START = new Rectangle(0, 74, 16, 22);
    public static final float BOBBY_RUN_SPEED = 30 * SCALE_FACTOR; //in px per sec
    public static final float BOBBY_JUMP_SPEED = 43 * SCALE_FACTOR; // in px per sec
    public static final float BOBBY_DEATH_FALL_SPEED = 8 * SCALE_FACTOR;
    public static final float GRAVITY = 61 * SCALE_FACTOR; //in px per sec2
    public static final float END_LEVEL_X = 292;
    public static final float END_GAME_X = 264;

    public static final Rectangle CARPET_START = new Rectangle(112, 96, 18, 9);
    public static final int   CARPET_TO = 208; //right limit for carpet
    public static final float CARPET_SPEED = 18 * SCALE_FACTOR;

    public static final Rectangle CREAMPIE_START_HIGH = new Rectangle(-40, 63, 18, 8);
    public static final Rectangle CREAMPIE_START_LOW = new Rectangle(-40, 86, 18, 8);
    public static final float CREAMPIE_SPEED = 30 * SCALE_FACTOR; //in px per sec
    public static final float CREAMPIE_START_DELAY = 1.25f; //in sec

    public static final Rectangle SAUSAGE_START_HIGH = new Rectangle(320, 63, 18, 8);
    public static final Rectangle SAUSAGE_START_LOW = new Rectangle(320, 86, 18, 8);
    public static final float SAUSAGE_SPEED = 60 * SCALE_FACTOR; //in px per sec
    public static final float SAUSAGE_START_DELAY = 0.25f; //in sec

    public static final float SWORDSMAN_INTERVAL = 1; // in sec
    public static final Rectangle[] SWORDSMAN_POSITIONS = {
            new Rectangle(54,  81, 24, 35),
            new Rectangle(150, 81, 24, 35),
            new Rectangle(246, 81, 24, 35),
    };

    public static final Rectangle[] SWORDSMAN_BASE_POSITIONS = {
            new Rectangle(60,  118, 20, 2),
            new Rectangle(156, 118, 20, 2),
            new Rectangle(252, 118, 20, 2),
    };

    public static final Rectangle FINAL_BOOTH_POSITION =  new Rectangle(304,  72, 16, 24);
    public static final Rectangle FINAL_SWORDSMAN_POSITION =  new Rectangle(280,  60, 24, 35);


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
