package com.angian.bobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    private static Sound startWater;



    public static void initialize() {
        startWater = Gdx.audio.newSound(Gdx.files.internal("sounds/start_water.wav"));
    }

    public static void startWater() {
        startWater.play();
    }
}
