package com.angian.bobby;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.angian.bobby.LevelConstants.*;

public class Swordsman extends BaseActor {
    public final List<Rectangle> posRects = new ArrayList<>();

    public enum Pose {
        CROUCHED,
        ERECT
    }

    int currHole;

    public Swordsman(Stage s) {
        super(s);
        loadAnimationFromSheet("swordsman_spritesheet.png", 1, 2, SWORDSMAN_INTERVAL, true);

        for (Rectangle rect: SWORDSMAN_POSITIONS) {
            posRects.add(standard2gdxCoords(rect));
        }

        setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
        setOrigin(0, 0);
        setCurrHole(0);
    }


    public Pose getCurrentPose() {
        if (animation.getKeyFrameIndex(elapsedTime) == 0)
            return Pose.CROUCHED;
        else
            return Pose.ERECT;
    }

    public void setCurrHole(int hole) {
        this.currHole = hole;
        Rectangle rect = posRects.get(hole);
        setPosition(rect.x, rect.y);

        new SwordsmanBase(hole, getStage());
    }

    public int getCurrHole() {
        return currHole;
    }


    private static class SwordsmanBase extends BaseActor {
        public static final List<Rectangle> basePosRects = new ArrayList<>();

        static {
            for (Rectangle rect: SWORDSMAN_BASE_POSITIONS) {
                basePosRects.add(standard2gdxCoords(rect));
            }
        }

        public SwordsmanBase(int basePos, Stage s) {
            super(s);
            loadTexture("swordsman_pedestal.png");

            setScale(getScaleX() * LevelConstants.SCALE_FACTOR, getScaleY() * LevelConstants.SCALE_FACTOR);
            setOrigin(0, 0);
            Rectangle rect = basePosRects.get(basePos);
            setPosition(rect.x, rect.y);
        }
    }
}
