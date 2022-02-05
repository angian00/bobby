package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.bobby.LevelConstants.*;


public class ProgressCursor extends BaseActor {

    public ProgressCursor(int level, Stage s) {
        super(s);
        loadTexture("progress_cursor.png");
        Rectangle startRect = LevelConstants.standard2gdxCoords(PROGRESS_START);
        setPosition(startRect.x + (level-1) * PROGRESS_STEP * SCALE_FACTOR , startRect.y);

        setScale(getScaleX() * SCALE_FACTOR, getScaleY() * SCALE_FACTOR);
        setOrigin(0, 0);
    }
}
