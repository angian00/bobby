package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.angian.bobby.LevelConstants.*;

public class FinalSwordsman extends BaseActor {
    public FinalSwordsman(Stage s) {
        super(s);

        loadTexture("swordsman_up.png");

        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOrigin(0, 0);

        Rectangle rect = standard2gdxCoords(FINAL_SWORDSMAN_POSITION);
        setPosition(rect.x, rect.y);
    }
}
